package io.rbetik12.eengine.service;

import io.rbetik12.eengine.entity.FactoryInputItem;
import io.rbetik12.eengine.repository.FactoryInputItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactoryInputItemService {

    @Autowired
    private final FactoryInputItemRepository factoryInputItemRepository;

    public FactoryInputItemService(FactoryInputItemRepository factoryInputItemRepository) {
        this.factoryInputItemRepository = factoryInputItemRepository;
    }

    public List<FactoryInputItem> getAll() {
        return factoryInputItemRepository.findAll();
    }
}
