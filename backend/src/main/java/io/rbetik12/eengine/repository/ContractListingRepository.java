package io.rbetik12.eengine.repository;

import io.rbetik12.eengine.entity.ContractListing;
import io.rbetik12.eengine.entity.composite.ContractListingId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractListingRepository extends JpaRepository<ContractListing, ContractListingId> {
}
