package io.rbetik12.eengine.repository;

import io.rbetik12.eengine.entity.Factory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FactoryRepository extends JpaRepository<Factory, Long> {

    @Query(value = "select count(*) from make_item(?1, ?2, ?3)", nativeQuery = true)
    int makeItem(int actorId, int factoryId, int inputItemId);
}
