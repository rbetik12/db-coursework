package io.rbetik12.eengine.controller;

import io.rbetik12.eengine.model.CurrencyExchangePrice;
import io.rbetik12.eengine.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/currency")
@CrossOrigin
public class CurrencyController {

    @Autowired
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping(path = "price", produces = "application/json")
    public ResponseEntity<List<CurrencyExchangePrice>> getCurrencyExchangePriceList() {
        List<CurrencyExchangePrice> priceList = currencyService.getCurrencyExchangePrice();
        return ResponseEntity.ok(priceList);
    }

}