package io.rbetik12.eengine.controller;

import io.rbetik12.eengine.entity.*;
import io.rbetik12.eengine.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path = "info", produces = "application/json")
    public ResponseEntity<Player> getPlayerInfo(HttpServletRequest request) {
        return ResponseEntity.ok(playerService.getPlayerInfo(Long.parseLong(request.getParameter("id"))));
    }

    @GetMapping(path = "factories", produces = "application/json")
    public ResponseEntity<List<Factory>> getFactories(@RequestParam("actorId") int actorId) {
        return ResponseEntity.ok(playerService.getFactories(actorId));
    }
}
