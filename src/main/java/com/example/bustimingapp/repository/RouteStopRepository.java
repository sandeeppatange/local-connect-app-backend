package com.example.bustimingapp.repository; 

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


import com.example.bustimingapp.entity.RouteStop;

public interface RouteStopRepository extends JpaRepository<RouteStop, Long> {
    List<RouteStop> findByRoute_IdOrderByStopSequence(Long routeId);
}