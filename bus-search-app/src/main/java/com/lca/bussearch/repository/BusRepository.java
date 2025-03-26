package com.lca.bussearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lca.bussearch.entity.Bus;

public interface BusRepository extends JpaRepository<Bus, Long> {}
