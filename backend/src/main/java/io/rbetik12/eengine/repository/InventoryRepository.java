package io.rbetik12.eengine.repository;

import io.rbetik12.eengine.entity.ActorInventory;
import io.rbetik12.eengine.entity.composite.ActorInventoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<ActorInventory, ActorInventoryId> {
}
