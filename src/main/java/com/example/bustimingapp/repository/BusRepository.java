package com.example.bustimingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bustimingapp.entity.Bus;

public interface BusRepository extends JpaRepository<Bus, Long> {}
