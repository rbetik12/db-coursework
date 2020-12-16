package io.rbetik12.eengine.service;

import io.rbetik12.eengine.entity.FactoryOwner;
import io.rbetik12.eengine.repository.FactoryOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactoryOwnerService {
    @Autowired
    private final FactoryOwnerRepository factoryOwnerRepository;

    public FactoryOwnerService(FactoryOwnerRepository factoryOwnerRepository) {
        this.factoryOwnerRepository = factoryOwnerRepository;
    }

    public List<FactoryOwner> getAll() {
        return factoryOwnerRepository.findAll();
    }
}
