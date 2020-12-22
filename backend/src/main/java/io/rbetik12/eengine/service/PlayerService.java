package io.rbetik12.eengine.service;

import io.rbetik12.eengine.entity.*;
import io.rbetik12.eengine.entity.enums.ActorType;
import io.rbetik12.eengine.repository.ActorRepository;
import io.rbetik12.eengine.repository.InventoryRepository;
import io.rbetik12.eengine.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private final PlayerRepository playerRepository;

    @Autowired
    private final ActorRepository actorRepository;

    @Autowired
    private final InventoryRepository inventoryRepository;

    public PlayerService(PlayerRepository playerRepository, ActorRepository actorRepository, InventoryRepository inventoryRepository) {
        this.playerRepository = playerRepository;
        this.actorRepository = actorRepository;
        this.inventoryRepository = inventoryRepository;
    }

    public List<Player> getAll() {
        return playerRepository.findAll();
    }

    public boolean saveUser(Player player) {
        Player playerFromDB = playerRepository.findByUsername(player.getUsername());

        if (playerFromDB != null) {
            return false;
        }

        Actor actor = new Actor();
        actor.setName(player.getUsername());
        actor.setType(ActorType.Player);
        actor.setRating(0);
        actor.setClan(null);

        actor = actorRepository.save(actor);
        player.setActor(actor);

        player.setPassword(String.valueOf(player.getPassword().hashCode()));
        playerRepository.save(player);
        player.setId(playerRepository.findByUsername(player.getUsername()).getId());
        return true;
    }

    public boolean authUser(Player player) {
        Player playerFromDb = playerRepository.findByUsername(player.getUsername());

        if (playerFromDb == null) {
            playerFromDb = playerRepository.findByEmail(player.getEmail());
            if (playerFromDb == null) {
                return false;
            }
        }

        if (player.getUsername() != null
                && player.getUsername().equals(playerFromDb.getUsername())
                && playerFromDb.getPassword().equals(String.valueOf(player.getPassword().hashCode()))) {
            return true;
        }

        return player.getEmail() != null
                && player.getEmail().equals(playerFromDb.getEmail())
                && playerFromDb.getPassword().equals(String.valueOf(player.getPassword().hashCode()));
    }

    public Player getUserByUsername(String username) {
        return playerRepository.findByUsername(username);
    }

    public Clan getPlayerClan(long actorId) {
        Actor actor = actorRepository.getOne(actorId);
        return actor.getClan();
    }

    public List<ActorInventory> getPlayerInventory(long actorId) {
        return inventoryRepository.findAllByActor_Id(actorId);
    }
}
