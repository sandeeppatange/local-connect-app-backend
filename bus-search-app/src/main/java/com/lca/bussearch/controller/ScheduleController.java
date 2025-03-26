package com.lca.bussearch.controller;

import org.springframework.web.bind.annotation.*;
import com.lca.bussearch.repository.ScheduleRepository;
import com.lca.bussearch.entity.Schedule;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleRepository scheduleRepository;

    public ScheduleController(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @GetMapping("/search")
    public List<Schedule> searchSchedules(@RequestParam Long sourceId, @RequestParam Long destinationId) {
        return scheduleRepository.findByRoute_Source_IdAndRoute_Destination_Id(sourceId, destinationId);
    }
}

