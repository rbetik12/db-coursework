package io.rbetik12.eengine.service;

import io.rbetik12.eengine.entity.*;
import io.rbetik12.eengine.entity.enums.ClanRole;
import io.rbetik12.eengine.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClanService {

    @Autowired
    private final ClanRepository clanRepository;

    @Autowired
    private final ActorRepository actorRepository;

    @Autowired
    private final PlayerRepository playerRepository;

    @Autowired
    private final ClanCurrencyRepository clanCurrencyRepository;

    @Autowired
    private final ClanInventoryRepository clanInventoryRepository;

    @Autowired
    private final CurrencyRepository currencyRepository;

    public ClanService(ClanRepository clanRepository, ActorRepository actorRepository,
                       PlayerRepository playerRepository, ClanCurrencyRepository clanCurrencyRepository,
                       ClanInventoryRepository clanInventoryRepository, CurrencyRepository currencyRepository) {
        this.clanRepository = clanRepository;
        this.actorRepository = actorRepository;
        this.playerRepository = playerRepository;
        this.clanCurrencyRepository = clanCurrencyRepository;
        this.clanInventoryRepository = clanInventoryRepository;
        this.currencyRepository = currencyRepository;
    }

    public List<Clan> getAll() {
        return clanRepository.findAll();
    }

    public Clan getOne(long id) {
        Optional<Clan> clan = clanRepository.findById(id);
        if (clan.isEmpty()) return null;
        return clan.get();
    }

    public boolean joinClan(long actorId, long clanId) {
        Actor actor = actorRepository.getOne(actorId);
        Clan clan = clanRepository.getOne(clanId);

        if (actor.getClan() != null || clan == null) {
            return false;
        }

        actor.setClan(clan);
        actor.setClanRole(ClanRole.Member);
        actorRepository.save(actor);
        return true;
    }

    public boolean leaveClan(long actorId, long clanId) {
        Actor actor = actorRepository.getOne(actorId);
        Clan clan = clanRepository.getOne(clanId);

        if (actor.getClan() == null || actor.getClan().getId() != clanId) {
            return false;
        }

        actor.setClan(null);
        actor.setClanRole(null);
        actorRepository.save(actor);
        return true;
    }

    public boolean createClan(long playerId, Clan clan) {
        Optional<Player> optionalPlayer = playerRepository.findById(playerId);
        if (optionalPlayer.isEmpty()) return false;
        Player player = optionalPlayer.get();
        Actor actor = player.getActor();

        if (actor.getClan() != null) {
            return false;
        }

        clanRepository.createClan((int) actor.getId(), clan.getName(),
                Integer.parseInt(clan.getRegion().getId().toString()), clan.getType().toString());

        Clan newClan = clanRepository.getByName(clan.getName());
        Currency cur = currencyRepository.getOne(1L);
        ClanCurrency clanCurrency = new ClanCurrency();
        clanCurrency.setClan(newClan);
        clanCurrency.setCurrency(cur);
        clanCurrency.setAmount(10000000);

        clanCurrencyRepository.save(clanCurrency);

        return true;
    }

    public List<ClanCurrency> getClanCurrency(long clanId) {
        return clanCurrencyRepository.getAllByClan_Id(clanId);
    }

    public List<ClanInventory> getClanInventory(long clanId) {
        return clanInventoryRepository.getAllByClan_Id(clanId);
    }
}
