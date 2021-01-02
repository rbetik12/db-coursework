package io.rbetik12.eengine.controller;

import io.rbetik12.eengine.entity.Clan;
import io.rbetik12.eengine.entity.ClanCurrency;
import io.rbetik12.eengine.entity.ClanInventory;
import io.rbetik12.eengine.entity.Player;
import io.rbetik12.eengine.model.Response;
import io.rbetik12.eengine.service.ClanService;
import io.rbetik12.eengine.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/clan")
public class ClanController {

    @Autowired
    private final ClanService clanService;

    @Autowired
    private final PlayerService playerService;

    public ClanController(ClanService clanService, PlayerService playerService) {
        this.clanService = clanService;
        this.playerService = playerService;
    }

    @GetMapping(path = "all", produces = "application/json")
    public ResponseEntity<List<Clan>> getClansList() {
        return ResponseEntity.ok(clanService.getAll());
    }

    @GetMapping(path = "info", produces = "application/json")
    public ResponseEntity<Clan> getClanInfo(HttpServletRequest request) {
        return ResponseEntity.ok(clanService.getOne(Long.parseLong(request.getParameter("id"))));
    }

    @PostMapping(path = "join", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Response> joinClan(@RequestBody Clan clan, HttpServletRequest request) {
        if (!clanService.joinClan((Long) request.getSession(false).getAttribute("actorId"), clan.getId())) {
            return ResponseEntity.badRequest().body(new Response("Can't join clan. Player already in clan!"));
        }
        return ResponseEntity.ok(new Response("Joined clan successfully!"));
    }

    @PostMapping(path = "leave", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Response> leaveClan(@RequestBody Clan clan, HttpServletRequest request) {
        if (!clanService.leaveClan((Long) request.getSession(false).getAttribute("actorId"), clan.getId())) {
            return ResponseEntity.badRequest().body(new Response("Can't leave clan"));
        }
        return ResponseEntity.ok(new Response("Left clan successfully!"));
    }

    @PostMapping(path = "create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Player> createClan(@RequestBody Clan clan, HttpServletRequest request) {
        long playerId = Long.parseLong(request.getParameter("id"));
        if (!clanService.createClan(playerId, clan)) {
            return ResponseEntity.badRequest().body(playerService.getPlayerInfo(playerId));
        }
        return ResponseEntity.ok().body(playerService.getPlayerInfo(playerId));
    }

    @GetMapping(value = "currency", produces = "application/json")
    public ResponseEntity<List<ClanCurrency>> getClanCurrency(@RequestParam("clanId") long clanId) {
        return ResponseEntity.ok(clanService.getClanCurrency(clanId));
    }

    @GetMapping(value = "inventory", produces = "application/json")
    public ResponseEntity<List<ClanInventory>> getClanInventory(@RequestParam("clanId") long clanId) {
        return ResponseEntity.ok(clanService.getClanInventory(clanId));
    }
}
