package io.rbetik12.eengine.service;

import io.rbetik12.eengine.entity.ActorCurrency;
import io.rbetik12.eengine.repository.ActorCurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorCurrencyService {

    @Autowired
    private final ActorCurrencyRepository actorCurrencyRepository;

    public ActorCurrencyService(ActorCurrencyRepository actorCurrencyRepository) {
        this.actorCurrencyRepository = actorCurrencyRepository;
    }

    public List<ActorCurrency> getAll() {
        return actorCurrencyRepository.findAll();
    }
}
