package com.lca.bussearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lca.bussearch.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {}
