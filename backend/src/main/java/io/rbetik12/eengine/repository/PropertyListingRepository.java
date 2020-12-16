package io.rbetik12.eengine.repository;


import io.rbetik12.eengine.entity.Listing;
import io.rbetik12.eengine.entity.PropertyListing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyListingRepository extends JpaRepository<PropertyListing, Listing> {
}
