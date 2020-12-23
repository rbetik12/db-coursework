package io.rbetik12.eengine.controller;

import io.rbetik12.eengine.entity.ActorCurrency;
import io.rbetik12.eengine.entity.ActorInventory;
import io.rbetik12.eengine.entity.Clan;
import io.rbetik12.eengine.entity.Item;
import io.rbetik12.eengine.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/player")
public class PlayerController {

    @Autowired
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping(path = "clan", produces = "application/json")
    public ResponseEntity<Clan> getPlayerClan(HttpServletRequest req) {
        Clan clan = playerService.getPlayerClan((Long) req.getSession(false).getAttribute("actorId"));
        return ResponseEntity.ok(clan);
    }

    @GetMapping(path = "inventory", produces = "application/json")
    public ResponseEntity<List<ActorInventory>> getPlayerInventory(HttpServletRequest req) {
        List<ActorInventory> inventory = playerService.getPlayerInventory((Long) req.getSession(false).getAttribute("actorId"));
        for (ActorInventory e : inventory) {
            e.setActor(null);
        }
        return ResponseEntity.ok(inventory);
    }

    @GetMapping(path = "currency", produces = "application/json")
    public ResponseEntity<List<ActorCurrency>> getPlayerCurrency(HttpServletRequest req) {
        List<ActorCurrency> currency = playerService.getPlayerCurrency((Long) req.getSession(false).getAttribute("actorId"));
        for (ActorCurrency e : currency) {
            e.setActor(null);
        }
        return ResponseEntity.ok(currency);
    }
}
