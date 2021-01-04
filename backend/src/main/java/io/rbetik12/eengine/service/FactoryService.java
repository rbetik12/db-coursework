package io.rbetik12.eengine.service;

import io.rbetik12.eengine.entity.*;
import io.rbetik12.eengine.repository.FactoryInputItemRepository;
import io.rbetik12.eengine.repository.FactoryOwnerRepository;
import io.rbetik12.eengine.repository.FactoryRepository;
import io.rbetik12.eengine.repository.InventoryRepository;
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

    @Autowired
    private final InventoryRepository inventoryRepository;

    @Autowired
    private final FactoryOwnerRepository factoryOwnerRepository;

    public FactoryService(FactoryRepository factoryRepository,
                          FactoryInputItemRepository factoryInputItemRepository,
                          InventoryRepository inventoryRepository, FactoryOwnerRepository factoryOwnerRepository) {
        this.factoryRepository = factoryRepository;
        this.factoryInputItemRepository = factoryInputItemRepository;
        this.inventoryRepository = inventoryRepository;
        this.factoryOwnerRepository = factoryOwnerRepository;
    }

    public List<Factory> getAll() {
        return factoryRepository.findAll();
    }

    public boolean craft(List<FactoryInputItem> factoryInputItems, int factoryId, int actorId) {
        FactoryOwner factoryOwner = factoryOwnerRepository.getByActor_IdAndFactory_Id(actorId, factoryId);
        if (factoryOwner == null) {
            return false;
        }

        Factory factory = factoryRepository.getOne((long) factoryId);
        Item outputItem = factory.getOutputItem();
        List<Item> inputItems = new ArrayList<>();
        for (FactoryInputItem item : factoryInputItems) {
            inputItems.add(item.getItem());
        }

        for (Item item : inputItems) {
            List<ActorInventory> actorInventory = inventoryRepository.findAllByActor_IdAndItem_Id(actorId, item.getId());
            if (actorInventory == null || actorInventory.size() <= 0) {
                return false;
            }
        }

        factoryRepository.makeItem(actorId, factoryId, (int) inputItems.get(0).getId());
        return true;
    }
}
