package io.rbetik12.eengine.repository;

import io.rbetik12.eengine.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    Player findByUsername(String username);
}
