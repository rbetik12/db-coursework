package io.rbetik12.eengine.service;

import io.rbetik12.eengine.entity.Listing;
import io.rbetik12.eengine.repository.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListingService {

    @Autowired
    private final ListingRepository listingRepository;

    public ListingService(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    public List<Listing> getAll() {
        return listingRepository.findAll();
    }
}
