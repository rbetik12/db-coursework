package io.rbetik12.eengine.controller;

import io.rbetik12.eengine.entity.Player;
import io.rbetik12.eengine.model.Response;
import io.rbetik12.eengine.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private final PlayerService playerService;

    public AuthController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping(path = "create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Player> createUser(@RequestBody Player user, HttpServletRequest request) {
        if (!playerService.saveUser(user)) {
            request.getSession().invalidate();
            return ResponseEntity.badRequest().body(user);
        }

        Player newUser = playerService.getUserByUsername(user.getUsername());
        request.getSession().invalidate();
        HttpSession session = request.getSession();
        session.setAttribute("Id", newUser.getId());
        return ResponseEntity.ok(newUser);
    }

    @PostMapping(path = "signIn", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Player> signIn(@RequestBody Player user, HttpServletRequest request) {
        if (!playerService.authUser(user)) {
            request.getSession().invalidate();
            return ResponseEntity.badRequest().body(user);
        }

        Player _user = playerService.getUserByUsername(user.getUsername());
        request.getSession().invalidate();
        HttpSession session = request.getSession();
        session.setAttribute("Id", _user.getId());
        return ResponseEntity.ok(_user);
    }
}
