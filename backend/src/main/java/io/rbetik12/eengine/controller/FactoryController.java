package io.rbetik12.eengine.controller;

import io.rbetik12.eengine.entity.Factory;
import io.rbetik12.eengine.entity.FactoryInputItem;
import io.rbetik12.eengine.model.Response;
import io.rbetik12.eengine.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/factory")
public class FactoryController {

    @Autowired
    private final FactoryService factoryService;

    public FactoryController(FactoryService factoryService) {
        this.factoryService = factoryService;
    }

    @GetMapping(path = "all", produces = "application/json")
    public List<Factory> getAllFactories() {
        return factoryService.getAll();
    }

    @PostMapping(path = "craft", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Response> craft(@RequestBody List<FactoryInputItem> inputItems,
                                          @RequestParam("factoryId") int factoryId,
                                          @RequestParam("actorId") int actorId) {
        if (!factoryService.craft(inputItems, factoryId, actorId)) {
            return ResponseEntity.badRequest().body(new Response("Can't craft item!"));
        }
        return ResponseEntity.ok(new Response("Successfully crafted item!"));
    }
}
