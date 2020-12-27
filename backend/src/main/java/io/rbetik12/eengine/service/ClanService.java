package io.rbetik12.eengine.service;

import io.rbetik12.eengine.entity.Actor;
import io.rbetik12.eengine.entity.Clan;
import io.rbetik12.eengine.entity.Player;
import io.rbetik12.eengine.repository.ActorRepository;
import io.rbetik12.eengine.repository.ClanRepository;
import io.rbetik12.eengine.repository.PlayerRepository;
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

    public ClanService(ClanRepository clanRepository, ActorRepository actorRepository, PlayerRepository playerRepository) {
        this.clanRepository = clanRepository;
        this.actorRepository = actorRepository;
        this.playerRepository = playerRepository;
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
        actorRepository.save(actor);
        return true;
    }

    public boolean leaveClan(long actorId, long clanId) {
        Actor actor = actorRepository.getOne(actorId);
        Clan clan = clanRepository.getOne(clanId);

        if (actor.getClan().getId() != clanId || actor.getClan() == null) {
            return false;
        }

        actor.setClan(null);
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

        return true;
    }
}
