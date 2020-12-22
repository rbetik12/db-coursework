package io.rbetik12.eengine.repository;

import io.rbetik12.eengine.entity.Actor;
import io.rbetik12.eengine.entity.Clan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
 }
