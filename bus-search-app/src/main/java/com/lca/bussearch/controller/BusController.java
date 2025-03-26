package com.lca.bussearch.controller;

import org.springframework.web.bind.annotation.*;

import com.lca.bussearch.entity.Bus;
import com.lca.bussearch.repository.BusRepository;

import java.util.List;

@RestController
@RequestMapping("/buses")
public class BusController {
    private final BusRepository busRepository;

    public BusController(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    @GetMapping
    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }
}

