package io.rbetik12.eengine.service;

import io.rbetik12.eengine.entity.Factory;
import io.rbetik12.eengine.entity.FactoryInputItem;
import io.rbetik12.eengine.repository.FactoryInputItemRepository;
import io.rbetik12.eengine.repository.FactoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FactoryService {

    @Autowired
    private final FactoryRepository factoryRepository;

    @Autowired
    private final FactoryInputItemRepository factoryInputItemRepository;

    public FactoryService(FactoryRepository factoryRepository, FactoryInputItemRepository factoryInputItemRepository) {
        this.factoryRepository = factoryRepository;
        this.factoryInputItemRepository = factoryInputItemRepository;
    }

    public List<Factory> getAll() {
        return factoryRepository.findAll();
    }
}
