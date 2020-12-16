package io.rbetik12.eengine.service;

import io.rbetik12.eengine.entity.CurrencyListing;
import io.rbetik12.eengine.repository.CurrencyListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyListingService {

    @Autowired
    private final CurrencyListingRepository currencyListingRepository;

    public CurrencyListingService(CurrencyListingRepository currencyListingRepository) {
        this.currencyListingRepository = currencyListingRepository;
    }

    public List<CurrencyListing> getAll() {
        return currencyListingRepository.findAll();
    }
}
