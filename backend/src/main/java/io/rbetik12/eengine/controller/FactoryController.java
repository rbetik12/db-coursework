package io.rbetik12.eengine.controller;

import io.rbetik12.eengine.entity.Factory;
import io.rbetik12.eengine.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
