package io.rbetik12.eengine.service;

import io.rbetik12.eengine.entity.ContractListing;
import io.rbetik12.eengine.repository.ContractListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractListingService {
    @Autowired
    private final ContractListingRepository contractListingRepository;

    public ContractListingService(ContractListingRepository contractListingRepository) {
        this.contractListingRepository = contractListingRepository;
    }

    public List<ContractListing> getAll() {
        return contractListingRepository.findAll();
    }
}
