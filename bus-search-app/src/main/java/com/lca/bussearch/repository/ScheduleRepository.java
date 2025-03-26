package com.lca.bussearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lca.bussearch.entity.Schedule;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByRoute_Source_IdAndRoute_Destination_Id(Long sourceId, Long destinationId);
}
