package io.rbetik12.eengine.controller;

import io.rbetik12.eengine.entity.Player;
import io.rbetik12.eengine.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private final PlayerService playerService;

    public AuthController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping(path = "create")
    public ResponseEntity<String> createUser(@RequestBody Player user) {
        System.out.println(user.getEmail());
        return ResponseEntity.ok("Created user successfully!");
    }
}
