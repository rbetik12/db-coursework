package io.rbetik12.eengine.controller;

import io.rbetik12.eengine.entity.Clan;
import io.rbetik12.eengine.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
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
}
