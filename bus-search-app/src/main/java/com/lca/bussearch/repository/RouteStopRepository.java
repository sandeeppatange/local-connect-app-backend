package com.lca.bussearch.repository; 

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


import com.lca.bussearch.entity.RouteStop;

public interface RouteStopRepository extends JpaRepository<RouteStop, Long> {
    List<RouteStop> findByRoute_IdOrderByStopSequence(Long routeId);
}