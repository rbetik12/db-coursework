package io.rbetik12.eengine.service;

import io.rbetik12.eengine.entity.Region;
import io.rbetik12.eengine.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    @Autowired
    private final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> getRegionByName(String name) {
        return regionRepository.getRegionByName(name);
    }

    public List<Region> getAll() {
        return regionRepository.findAll();
    }

    public List<Region> getRegions(int amount) {
        Page<Region> regionsPage = regionRepository.findAll(PageRequest.of(0, (int) amount));
        return regionsPage.getContent();
    }
}
