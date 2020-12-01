package io.rbetik12.eengine.service;

import io.rbetik12.eengine.entity.Currency;
import io.rbetik12.eengine.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {
    @Autowired
    private final CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<Currency> getCurrencyByName(String name) {
        return currencyRepository.findAllByName(name);
    }
}
