package io.rbetik12.eengine.service;

import io.rbetik12.eengine.entity.ItemType;
import io.rbetik12.eengine.repository.ItemTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemTypeService {

    @Autowired
    private final ItemTypeRepository itemTypeRepository;

    public ItemTypeService(ItemTypeRepository itemTypeRepository) {
        this.itemTypeRepository = itemTypeRepository;
    }

    public List<ItemType> getAll() {
        return itemTypeRepository.findAll();
    }
}
