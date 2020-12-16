package io.rbetik12.eengine.service;

import io.rbetik12.eengine.entity.PropertyListing;
import io.rbetik12.eengine.repository.PropertyListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyListingService {

    @Autowired
    private final PropertyListingRepository propertyListingRepository;

    public PropertyListingService(PropertyListingRepository propertyListingRepository) {
        this.propertyListingRepository = propertyListingRepository;
    }

    public List<PropertyListing> getAll() {
        return propertyListingRepository.findAll();
    }
}
