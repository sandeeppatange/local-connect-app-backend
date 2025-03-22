package com.example.bustimingapp.controller;

import org.springframework.web.bind.annotation.*;

import com.example.bustimingapp.entity.Bus;
import com.example.bustimingapp.repository.BusRepository;

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

