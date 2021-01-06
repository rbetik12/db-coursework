package io.rbetik12.eengine.service;

import io.rbetik12.eengine.entity.ActorCurrency;
import io.rbetik12.eengine.entity.CurrencyListing;
import io.rbetik12.eengine.repository.ActorCurrencyRepository;
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

    public CurrencyListingService(CurrencyListingRepository currencyListingRepository, ActorCurrencyRepository actorCurrencyRepository) {
        this.currencyListingRepository = currencyListingRepository;
        this.actorCurrencyRepository = actorCurrencyRepository;
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
}
