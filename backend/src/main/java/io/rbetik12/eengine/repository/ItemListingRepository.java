package io.rbetik12.eengine.repository;

import io.rbetik12.eengine.entity.ItemListing;
import io.rbetik12.eengine.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemListingRepository extends JpaRepository<ItemListing, Listing> {
}
