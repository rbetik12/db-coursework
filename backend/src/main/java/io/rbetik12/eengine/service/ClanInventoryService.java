package io.rbetik12.eengine.service;

import io.rbetik12.eengine.entity.ClanInventory;
import io.rbetik12.eengine.repository.ClanInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClanInventoryService {

    @Autowired
    private final ClanInventoryRepository clanInventoryRepository;

    public ClanInventoryService(ClanInventoryRepository clanInventoryRepository) {
        this.clanInventoryRepository = clanInventoryRepository;
    }

    public List<ClanInventory> getAll() {
        return clanInventoryRepository.findAll();
    }
}
