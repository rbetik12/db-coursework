package io.rbetik12.eengine.repository;

import io.rbetik12.eengine.entity.Inventory;
import io.rbetik12.eengine.entity.InventoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, InventoryId> {
}
