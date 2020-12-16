package io.rbetik12.eengine.repository;

import io.rbetik12.eengine.entity.CurrencyListing;
import io.rbetik12.eengine.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyListingRepository extends JpaRepository<CurrencyListing, Listing> {
}
