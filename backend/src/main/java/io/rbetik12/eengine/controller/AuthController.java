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
    public ResponseEntity<Response> createUser(@RequestBody Player user, HttpServletRequest request) {
        if (!playerService.saveUser(user)) {
            request.getSession().invalidate();
            return ResponseEntity.badRequest().body(new Response("User already exists!"));
        }
        request.getSession().invalidate();
        HttpSession session = request.getSession();
        session.setAttribute("Id", user.getId());
        return ResponseEntity.ok(new Response("Created user successfully!"));
    }

    @PostMapping(path = "signIn", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Response> signIn(@RequestBody Player user, HttpServletRequest request) {
        if (!playerService.authUser(user)) {
            request.getSession().invalidate();
            return ResponseEntity.badRequest().body(new Response("Incorrect username or password!"));
        }

        request.getSession().invalidate();
        HttpSession session = request.getSession();
        session.setAttribute("Id", user.getId());
        return ResponseEntity.ok(new Response("Sign in successfully!"));
    }
}
