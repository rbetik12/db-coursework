package io.rbetik12.eengine.repository;

import io.rbetik12.eengine.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingRepository extends JpaRepository<Listing, Long> {
}
