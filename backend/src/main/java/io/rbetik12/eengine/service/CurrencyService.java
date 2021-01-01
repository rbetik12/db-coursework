package io.rbetik12.eengine.service;

import io.rbetik12.eengine.entity.Currency;
import io.rbetik12.eengine.entity.CurrencyListing;
import io.rbetik12.eengine.model.CurrencyExchangePrice;
import io.rbetik12.eengine.repository.CurrencyListingRepository;
import io.rbetik12.eengine.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyService {
    @Autowired
    private final CurrencyRepository currencyRepository;

    @Autowired
    private final CurrencyListingRepository currencyListingRepository;

    public CurrencyService(CurrencyRepository currencyRepository, CurrencyListingRepository currencyListingRepository) {
        this.currencyRepository = currencyRepository;
        this.currencyListingRepository = currencyListingRepository;
    }

    public List<Currency> getCurrencyByName(String name) {
        return currencyRepository.findAllByName(name);
    }

    public List<CurrencyExchangePrice> getCurrencyExchangePrice() {
        Page<CurrencyListing> listings = currencyListingRepository.findAll(PageRequest.of(0, 40));

        List<CurrencyExchangePrice> priceList = new ArrayList<>();

        for (CurrencyListing listing: listings) {
            Currency currency1 = listing.getCurrencyForSell();
            Currency currency2 = listing.getCurrencyForBuy();
            float price = currencyRepository.countCurrencyMarketPrice(Integer.parseInt(currency1.getId().toString()), Integer.parseInt(currency2.getId().toString()));
            priceList.add(new CurrencyExchangePrice(listing.getCurrencyForSell(), listing.getCurrencyForBuy(), price));
        }

        return priceList;
    }

    public List<Currency> getAll() {
        return currencyRepository.findAll(PageRequest.of(0, 1000)).getContent();
    }
}
