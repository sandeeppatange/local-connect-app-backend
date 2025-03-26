package com.lca.bussearch.controller;

import org.springframework.web.bind.annotation.*;

import com.lca.bussearch.entity.Route;
import com.lca.bussearch.repository.RouteRepository;

import java.util.List;

@RestController
@RequestMapping("/routes")
public class RouteController {
    private final RouteRepository routeRepository;

    public RouteController(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @GetMapping
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }
}

