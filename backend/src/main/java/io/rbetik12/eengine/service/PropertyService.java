package io.rbetik12.eengine.service;

import io.rbetik12.eengine.entity.Property;
import io.rbetik12.eengine.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {
    @Autowired
    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public List<Property> getAll() {
        return propertyRepository.findAll();
    }
}
