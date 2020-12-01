package io.rbetik12.eengine.repository;

import io.rbetik12.eengine.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Long> {
    List<Region> getRegionByName(String name);
}
