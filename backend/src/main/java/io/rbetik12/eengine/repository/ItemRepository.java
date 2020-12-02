package io.rbetik12.eengine.repository;

import io.rbetik12.eengine.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
