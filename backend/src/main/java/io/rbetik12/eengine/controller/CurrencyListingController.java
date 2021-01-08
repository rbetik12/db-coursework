package io.rbetik12.eengine.controller;

import io.rbetik12.eengine.entity.CurrencyListing;
import io.rbetik12.eengine.model.Response;
import io.rbetik12.eengine.service.CurrencyListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(path = "create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Response> create(@RequestBody CurrencyListing listing) {
        if (!currencyListingService.create(listing)) {
            return ResponseEntity.badRequest().body(new Response("Can't create currency listing!"));
        }

        return ResponseEntity.ok(new Response("Created new currency listing!"));
    }

    @PostMapping(value = "buy", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Response> buy(@RequestBody CurrencyListing currencyListing, @RequestParam("actorId") int actorId) {
        if (!currencyListingService.buy(currencyListing, actorId)) {
            return ResponseEntity.badRequest().body(new Response("Can't buy currency!"));
        }

        return ResponseEntity.ok(new Response("Bought successfully!"));
    }

    @PostMapping(value = "buy-as-clan", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Response> buyAsClan(@RequestBody CurrencyListing currencyListing, @RequestParam("clanId") int clanId) {
        if (!currencyListingService.buyAsClan(currencyListing, clanId)) {
            return ResponseEntity.badRequest().body(new Response("Can't buy currency!"));
        }

        return ResponseEntity.ok(new Response("Bought successfully!"));
    }
}
