package io.rbetik12.eengine.controller;

import io.rbetik12.eengine.entity.CurrencyListing;
import io.rbetik12.eengine.service.CurrencyListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/currency-listing")
public class CurrencyListingController {

    @Autowired
    private final CurrencyListingService currencyListingService;

    public CurrencyListingController(CurrencyListingService currencyListingService) {
        this.currencyListingService = currencyListingService;
    }

    @GetMapping(value = "all", produces = "application/json")
    public ResponseEntity<List<CurrencyListing>> getAll() {
        return ResponseEntity.ok(currencyListingService.getAll());
    }
}
