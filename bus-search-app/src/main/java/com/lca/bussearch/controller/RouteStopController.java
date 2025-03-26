package com.lca.bussearch.controller;

import org.springframework.web.bind.annotation.*;
import com.lca.bussearch.repository.RouteStopRepository;
import com.lca.bussearch.entity.RouteStop;

import java.util.List;

@RestController
@RequestMapping("/route-stops")
public class RouteStopController {
    private final RouteStopRepository routeStopRepository;

    public RouteStopController(RouteStopRepository routeStopRepository) {
        this.routeStopRepository = routeStopRepository;
    }

    // Fetch stops for a specific route
    @GetMapping("/{routeId}")
    public List<RouteStop> getRouteStops(@PathVariable Long routeId) {
        return routeStopRepository.findByRoute_IdOrderByStopSequence(routeId);
    }
}
