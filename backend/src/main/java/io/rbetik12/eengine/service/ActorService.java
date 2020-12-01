package io.rbetik12.eengine.service;

import io.rbetik12.eengine.entity.Actor;
import io.rbetik12.eengine.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {

    @Autowired
    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<Actor> getAll() {
        return actorRepository.findAll();
    }
}
