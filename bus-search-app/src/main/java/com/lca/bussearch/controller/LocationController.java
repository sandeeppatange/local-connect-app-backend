package com.lca.bussearch.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import com.lca.bussearch.repository.LocationRepository;
import com.lca.bussearch.entity.Location;

@RestController
@RequestMapping("/locations")
public class LocationController {
    private final LocationRepository locationRepository;

    public LocationController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @GetMapping("/search")
    public Location searchPlace(@RequestParam String placeName) {
        return locationRepository.findByPlaceName(placeName);
    }

    @GetMapping("/searchByNameLike")
    public List<Location> searchSchedules(@RequestParam String placeName) {
        // Create a Pageable object to enable pagination
        Pageable pageable = PageRequest.of(0, 10); // To sort add third parameter - Sort.by("placeName").ascending()
        return locationRepository.findByPlaceNameLike(placeName, pageable);
    }
}

