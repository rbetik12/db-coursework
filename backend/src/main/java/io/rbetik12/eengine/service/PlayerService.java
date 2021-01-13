package io.rbetik12.eengine.service;

import io.rbetik12.eengine.entity.*;
import io.rbetik12.eengine.entity.enums.ActorType;
import io.rbetik12.eengine.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PlayerService {

    @Autowired
    private final PlayerRepository playerRepository;

    @Autowired
    private final ActorRepository actorRepository;

    @Autowired
    private final InventoryRepository inventoryRepository;

    @Autowired
    private final ActorCurrencyRepository actorCurrencyRepository;

    @Autowired
    private final CurrencyRepository currencyRepository;

    @Autowired
    private final FactoryOwnerRepository factoryOwnerRepository;

    @Autowired
    private final ItemRepository itemRepository;

    public PlayerService(PlayerRepository playerRepository,
                         ActorRepository actorRepository,
                         InventoryRepository inventoryRepository,
                         ActorCurrencyRepository actorCurrencyRepository,
                         CurrencyRepository currencyRepository,
                         FactoryOwnerRepository factoryOwnerRepository, ItemRepository itemRepository) {
        this.playerRepository = playerRepository;
        this.actorRepository = actorRepository;
        this.inventoryRepository = inventoryRepository;
        this.actorCurrencyRepository = actorCurrencyRepository;
        this.currencyRepository = currencyRepository;
        this.factoryOwnerRepository = factoryOwnerRepository;
        this.itemRepository = itemRepository;
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
        Player createdPlayer = playerRepository.findByUsername(player.getUsername());
        player.setId(createdPlayer.getId());

        ActorCurrency actorCurrency = new ActorCurrency();
        actorCurrency.setActor(player.getActor());
        List<Currency> currencyList = currencyRepository.findAll();
        Random random = new Random();
        actorCurrency.setCurrency(currencyList.get(random.nextInt(currencyList.size())));
        actorCurrency.setAmount(100000);
        actorCurrencyRepository.save(actorCurrency);

        ActorInventory inventory = new ActorInventory();
        inventory.setActor(player.getActor());
        List<Item> items = itemRepository.findAll();
        inventory.setItem(items.get(random.nextInt(items.size())));
        inventory.setAmount(100);
        inventoryRepository.save(inventory);

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

    public List<ActorCurrency> getPlayerCurrency(long actorId) {
        return actorCurrencyRepository.findAllByActor_Id(actorId);
    }

    public Player getPlayerInfo(long playerId) {
        return playerRepository.findById(playerId).get();
    }

    public List<Factory> getFactories(int actorId) {
        List<FactoryOwner> factoryOwners = factoryOwnerRepository.getAllByActor_Id(actorId);
        List<Factory> factories = new ArrayList<>();
        for (FactoryOwner factoryOwner : factoryOwners) {
            factories.add(factoryOwner.getFactory());
        }
        return factories;
    }
}
