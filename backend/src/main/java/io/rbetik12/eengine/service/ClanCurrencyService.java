package io.rbetik12.eengine.service;

import io.rbetik12.eengine.entity.ClanCurrency;
import io.rbetik12.eengine.repository.ClanCurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClanCurrencyService {

    @Autowired
    private final ClanCurrencyRepository clanCurrencyRepository;

    public ClanCurrencyService(ClanCurrencyRepository clanCurrencyRepository) {
        this.clanCurrencyRepository = clanCurrencyRepository;
    }

    public List<ClanCurrency> getAll() {
        return clanCurrencyRepository.findAll();
    }
}
