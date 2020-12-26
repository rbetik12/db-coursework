package io.rbetik12.eengine.controller;

import io.rbetik12.eengine.entity.Region;
import io.rbetik12.eengine.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/region")
@CrossOrigin
public class RegionController {
    @Autowired
    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping(path = "all", produces = "application/json")
    public List<Region> getRegions(HttpServletRequest request) {
        return regionService.getRegions(Integer.parseInt(request.getParameter("amount")));
    }
}
