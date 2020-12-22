package io.rbetik12.eengine.repository;

import io.rbetik12.eengine.entity.ActorInventory;
import io.rbetik12.eengine.entity.composite.ActorInventoryId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<ActorInventory, ActorInventoryId> {

    List<ActorInventory> findAllByActor_Id(long actorId);
}
