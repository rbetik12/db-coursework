package io.rbetik12.eengine.repository;


import io.rbetik12.eengine.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}
