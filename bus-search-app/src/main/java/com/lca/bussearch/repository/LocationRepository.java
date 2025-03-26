package com.lca.bussearch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lca.bussearch.entity.Location;
import org.springframework.data.domain.Pageable;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Location findByPlaceName(String placeName);

    @Query("SELECT l FROM Location l WHERE l.placeName LIKE %:placeName%")
    List<Location> findByPlaceNameLike(@Param("placeName") String placeName, Pageable pageable);

}
