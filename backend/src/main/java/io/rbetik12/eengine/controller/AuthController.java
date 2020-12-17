package io.rbetik12.eengine.controller;

import io.rbetik12.eengine.entity.Player;
import io.rbetik12.eengine.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping(path = "create")
    public ResponseEntity<String> createUser(@RequestBody Player user, HttpServletRequest request) {
        if (!playerService.saveUser(user)) {
            request.getSession().invalidate();
            return ResponseEntity.badRequest().body("User already exists!");
        }
        request.getSession().invalidate();
        HttpSession session = request.getSession();
        session.setAttribute("Id", user.getId());
        return ResponseEntity.ok("Created user successfully!");
    }
}
