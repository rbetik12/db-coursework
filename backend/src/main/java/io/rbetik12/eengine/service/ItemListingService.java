package io.rbetik12.eengine.service;

import io.rbetik12.eengine.entity.Actor;
import io.rbetik12.eengine.entity.ActorCurrency;
import io.rbetik12.eengine.entity.ClanCurrency;
import io.rbetik12.eengine.entity.ItemListing;
import io.rbetik12.eengine.entity.enums.ClanRole;
import io.rbetik12.eengine.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemListingService {
    @Autowired
    private final ItemListingRepository itemListingRepository;

    @Autowired
    private final InventoryRepository actorInventoryRepository;

    @Autowired
    private final ActorCurrencyRepository actorCurrencyRepository;

    @Autowired
    private final ClanCurrencyRepository clanCurrencyRepository;

    @Autowired
    private final ActorRepository actorRepository;

    public ItemListingService(ItemListingRepository itemListingRepository, InventoryRepository actorInventoryRepository, ActorCurrencyRepository actorCurrencyRepository, ClanCurrencyRepository clanCurrencyRepository, ActorRepository actorRepository) {
        this.itemListingRepository = itemListingRepository;
        this.actorInventoryRepository = actorInventoryRepository;
        this.actorCurrencyRepository = actorCurrencyRepository;
        this.clanCurrencyRepository = clanCurrencyRepository;
        this.actorRepository = actorRepository;
    }

    public List<ItemListing> getAll() {
        return itemListingRepository.findAll(PageRequest.of(1, 1000)).toList();
    }

    public boolean buyItem(ItemListing itemListing, int actorId) {
        List<ActorCurrency> currencyList = actorCurrencyRepository.findAllByActor_IdAndCurrency_Id(actorId,
                itemListing.getCurrency().getId());

        if (currencyList == null) return false;
        boolean enough = false;
        for (ActorCurrency cur: currencyList) {
            if (cur.getAmount() >= itemListing.getPrice()) {
                enough = true;
                break;
            }
        }

        if (!enough) return false;

        itemListingRepository.buyItemAsActor(actorId, (int) itemListing.getListing().getListingId());
        return true;
    }

    public boolean buyItemAsClan(ItemListing itemListing, int clanId, int actorId) {
        Actor actor = actorRepository.getOne((long) actorId);
        if (actor.getClanRole() != ClanRole.Leader) return false;

        List<ClanCurrency> currencyList = clanCurrencyRepository.getAllByClan_IdAndCurrency_Id(clanId,
                itemListing.getCurrency().getId());

        if (currencyList == null || currencyList.size() <= 0) return false;
        if (currencyList.get(0).getAmount() < itemListing.getPrice()) return false;

        itemListingRepository.buyItemAsClan(clanId, (int) itemListing.getListing().getListingId());
        return true;
    }
}
