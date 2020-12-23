package io.rbetik12.eengine.controller;

import io.rbetik12.eengine.entity.Clan;
import io.rbetik12.eengine.service.ClanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/clan")
@CrossOrigin
public class ClanController {

    @Autowired
    private final ClanService clanService;

    public ClanController(ClanService clanService) {
        this.clanService = clanService;
    }

    @GetMapping(path = "all", produces = "application/json")
    public ResponseEntity<List<Clan>> getClansList() {
        return ResponseEntity.ok(clanService.getAll());
    }

    @GetMapping(path = "info", produces = "application/json")
    public ResponseEntity<Clan> getClanInfo(HttpServletRequest request) {
        return ResponseEntity.ok(clanService.getOne(Long.parseLong(request.getParameter("id"))));
    }
}
