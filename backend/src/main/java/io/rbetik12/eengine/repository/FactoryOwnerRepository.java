package io.rbetik12.eengine.repository;

import io.rbetik12.eengine.entity.FactoryOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FactoryOwnerRepository extends JpaRepository<FactoryOwner, Long> {
    FactoryOwner getByActor_IdAndFactory_Id(long actorId, long factoryId);

    List<FactoryOwner> getAllByActor_Id(long actorId);
}
