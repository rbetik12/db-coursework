package io.rbetik12.eengine.controller;

import io.rbetik12.eengine.entity.ItemListing;
import io.rbetik12.eengine.service.ItemListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/item-listing")
@CrossOrigin
public class ItemListingController {

    @Autowired
    private final ItemListingService itemListingService;

    public ItemListingController(ItemListingService itemListingService) {
        this.itemListingService = itemListingService;
    }

    @GetMapping(path = "all", produces = "application/json")
    public List<ItemListing> getItemListings() {
        return itemListingService.getAll();
    }

}
