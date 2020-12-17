package io.rbetik12.eengine.controller;

import io.rbetik12.eengine.entity.Listing;
import io.rbetik12.eengine.service.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/listing")
public class ListingController {

    @Autowired
    private final ListingService listingService;

    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    @GetMapping(path = "all", produces = "application/json")
    public List<Listing> getAllListings() {
        return listingService.getAll();
    }
}
