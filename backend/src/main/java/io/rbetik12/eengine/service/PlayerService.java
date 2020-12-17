package io.rbetik12.eengine.service;

import io.rbetik12.eengine.entity.Player;
import io.rbetik12.eengine.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getAll() {
        return playerRepository.findAll();
    }

    public boolean saveUser(Player player) {
        Player playerFromDB = playerRepository.findByUsername(player.getUsername());

        if (playerFromDB != null) {
            return false;
        }

        player.setPassword(String.valueOf(player.getPassword().hashCode()));
        playerRepository.save(player);
        player.setId(playerRepository.findByUsername(player.getUsername()).getId());
        return true;
    }
}
