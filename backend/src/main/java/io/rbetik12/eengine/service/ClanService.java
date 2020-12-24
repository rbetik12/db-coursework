package io.rbetik12.eengine.service;

import io.rbetik12.eengine.entity.Actor;
import io.rbetik12.eengine.entity.Clan;
import io.rbetik12.eengine.repository.ActorRepository;
import io.rbetik12.eengine.repository.ClanRepository;
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

    public ClanService(ClanRepository clanRepository, ActorRepository actorRepository) {
        this.clanRepository = clanRepository;
        this.actorRepository = actorRepository;
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
}
