package io.rbetik12.eengine.service;

import io.rbetik12.eengine.entity.FactoryListing;
import io.rbetik12.eengine.repository.FactoryListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactoryListingService {

    @Autowired
    private final FactoryListingRepository factoryListingRepository;

    public FactoryListingService(FactoryListingRepository factoryListingRepository) {
        this.factoryListingRepository = factoryListingRepository;
    }

    public List<FactoryListing> getAll() {
        return factoryListingRepository.findAll();
    }
}
