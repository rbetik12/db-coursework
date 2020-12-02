package io.rbetik12.eengine.service;

import io.rbetik12.eengine.entity.Factory;
import io.rbetik12.eengine.repository.FactoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactoryService {

    @Autowired
    private final FactoryRepository factoryRepository;

    public FactoryService(FactoryRepository factoryRepository) {
        this.factoryRepository = factoryRepository;
    }

    public List<Factory> getAll() {
        return factoryRepository.findAll();
    }
}
