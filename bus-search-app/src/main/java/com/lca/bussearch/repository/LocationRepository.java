package com.example.bustimingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bustimingapp.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {}
