package com.example.bustimingapp.controller;

import org.springframework.web.bind.annotation.*;

import com.example.bustimingapp.entity.Route;
import com.example.bustimingapp.repository.RouteRepository;

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

