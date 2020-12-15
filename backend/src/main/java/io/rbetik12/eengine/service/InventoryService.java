package io.rbetik12.eengine.service;

import io.rbetik12.eengine.entity.ActorInventory;
import io.rbetik12.eengine.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<ActorInventory> getAll() {
        return inventoryRepository.findAll();
    }
}
