package io.rbetik12.eengine.service;

import io.rbetik12.eengine.entity.ActorCurrency;
import io.rbetik12.eengine.entity.ClanCurrency;
import io.rbetik12.eengine.entity.CurrencyListing;
import io.rbetik12.eengine.repository.ActorCurrencyRepository;
import io.rbetik12.eengine.repository.ClanCurrencyRepository;
import io.rbetik12.eengine.repository.CurrencyListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyListingService {

    @Autowired
    private final CurrencyListingRepository currencyListingRepository;

    @Autowired
    private final ActorCurrencyRepository actorCurrencyRepository;

    @Autowired
    private final ClanCurrencyRepository clanCurrencyRepository;

    public CurrencyListingService(CurrencyListingRepository currencyListingRepository, ActorCurrencyRepository actorCurrencyRepository, ClanCurrencyRepository clanCurrencyRepository) {
        this.currencyListingRepository = currencyListingRepository;
        this.actorCurrencyRepository = actorCurrencyRepository;
        this.clanCurrencyRepository = clanCurrencyRepository;
    }

    public List<CurrencyListing> getAll() {
        return currencyListingRepository.findAll();
    }

    public boolean create(CurrencyListing listing) {
        List<ActorCurrency> actorCurrencyList = actorCurrencyRepository.findAllByActor_IdAndCurrency_Id(
                listing.getListing().getActor().getId(), listing.getCurrencyForSell().getId());

        if (actorCurrencyList == null || actorCurrencyList.size() <= 0) {
            return false;
        }

        ActorCurrency actorCurrency = actorCurrencyList.get(0);

        if (actorCurrency.getAmount() < listing.getSellAmount()) {
            return false;
        }

        currencyListingRepository.createForActor((int) listing.getListing().getActor().getId(),
                listing.getCurrencyForSell().getId().intValue(),
                listing.getCurrencyForBuy().getId().intValue(),
                listing.getSellAmount(),
                listing.getBuyAmount(),
                listing.getListing().getDescription()
        );

        return true;
    }

    public boolean buy(CurrencyListing currencyListing, int actorId) {
        List<ActorCurrency> actorCurrencyList = actorCurrencyRepository.findAllByActor_IdAndCurrency_Id(actorId,
                currencyListing.getCurrencyForBuy().getId());

        if (actorCurrencyList == null || actorCurrencyList.size() <= 0) {
            return false;
        }

        ActorCurrency actorCurrency = actorCurrencyList.get(0);

        if (actorCurrency.getAmount() < currencyListing.getBuyAmount()) {
            return false;
        }

        currencyListingRepository.buyAsActor(actorId, (int) currencyListing.getListing().getListingId());
        return true;
    }

    public boolean buyAsClan(CurrencyListing currencyListing, int clanId) {
        List<ClanCurrency> clanCurrencyList = clanCurrencyRepository.getAllByClan_IdAndCurrency_Id(clanId, currencyListing.getCurrencyForBuy().getId());

        if (clanCurrencyList == null || clanCurrencyList.size() <= 0) {
            return false;
        }

        ClanCurrency clanCurrency = clanCurrencyList.get(0);

        if (clanCurrency.getAmount() < currencyListing.getBuyAmount()) {
            return false;
        }

        currencyListingRepository.buyAsClan(clanId, (int) currencyListing.getListing().getListingId());

        return true;
    }
}
