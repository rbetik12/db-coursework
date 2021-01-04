package io.rbetik12.eengine.controller;

import io.rbetik12.eengine.entity.ItemListing;
import io.rbetik12.eengine.model.Response;
import io.rbetik12.eengine.service.ItemListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(path = "buy", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Response> buyItem(@RequestBody ItemListing itemListing, @RequestParam("id") int actorId) {
        if (!itemListingService.buyItem(itemListing, actorId)) {
            return ResponseEntity.badRequest().body(new Response("Can't buy item"));
        }
        return ResponseEntity.ok(new Response("Bought item successfully"));
    }

    @PostMapping(path = "buy-as-clan", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Response> buyItemAsClan(@RequestBody ItemListing itemListing,
                                                  @RequestParam("clanId") int clanId,
                                                  @RequestParam("actorId") int actorId) {
        if (!itemListingService.buyItemAsClan(itemListing, clanId, actorId)) {
            return ResponseEntity.badRequest().body(new Response("Can't buy item"));
        }
        return ResponseEntity.ok(new Response("Bought item successfully"));
    }

    @PostMapping(path = "create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Response> create(@RequestBody ItemListing itemListing) {
        if (!itemListingService.create(itemListing)) {
            return ResponseEntity.badRequest().body(new Response("Can't create listing!"));
        }
        return ResponseEntity.ok(new Response("Created listing!"));
    }

    @GetMapping(path = "price")
    public ResponseEntity<Response> getAvgPrice(@RequestParam("itemName") String itemName,
                                              @RequestParam("currencyId") int currencyId) {
        return ResponseEntity.ok(new Response(itemListingService.getAvgPrice(itemName, currencyId)));
    }

}
