package io.rbetik12.eengine.service;

import io.rbetik12.eengine.entity.ActorCurrency;
import io.rbetik12.eengine.entity.ActorInventory;
import io.rbetik12.eengine.entity.ItemListing;
import io.rbetik12.eengine.repository.ActorCurrencyRepository;
import io.rbetik12.eengine.repository.InventoryRepository;
import io.rbetik12.eengine.repository.ItemListingRepository;
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

    public ItemListingService(ItemListingRepository itemListingRepository, InventoryRepository actorInventoryRepository, ActorCurrencyRepository actorCurrencyRepository) {
        this.itemListingRepository = itemListingRepository;
        this.actorInventoryRepository = actorInventoryRepository;
        this.actorCurrencyRepository = actorCurrencyRepository;
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
}
