package io.rbetik12.eengine.service;

import io.rbetik12.eengine.entity.ItemListing;
import io.rbetik12.eengine.repository.ItemListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemListingService {
    @Autowired
    private final ItemListingRepository itemListingRepository;

    public ItemListingService(ItemListingRepository itemListingRepository) {
        this.itemListingRepository = itemListingRepository;
    }

    public List<ItemListing> getAll() {
        return itemListingRepository.findAll();
    }
}
