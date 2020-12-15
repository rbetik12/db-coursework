package io.rbetik12.eengine.repository;

import io.rbetik12.eengine.entity.ClanInventory;
import io.rbetik12.eengine.entity.composite.ClanInventoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClanInventoryRepository extends JpaRepository<ClanInventory, ClanInventoryId> {
}
