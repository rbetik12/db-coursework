package io.rbetik12.eengine.service;

import io.rbetik12.eengine.entity.Clan;
import io.rbetik12.eengine.repository.ClanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClanService {

    @Autowired
    private final ClanRepository clanRepository;

    public ClanService(ClanRepository clanRepository) {
        this.clanRepository = clanRepository;
    }

    public List<Clan> getAll() {
        return clanRepository.findAll();
    }
}
