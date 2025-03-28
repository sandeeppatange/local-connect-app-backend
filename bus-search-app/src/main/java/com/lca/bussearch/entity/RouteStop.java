package com.lca.bussearch.entity;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@Entity
@Table(name = "route_stop", schema = "bus_search")
public class RouteStop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Route route;

    @ManyToOne
    private Location stop;

    private int stopSequence;
    private String stopTime; // Format: HH:mm

    public RouteStop(Route route, Location stop, int stopSequence, String stopTime) {
        this.route = route;
        this.stop = stop;
        this.stopSequence = stopSequence;
        this.stopTime = stopTime;
    }

}
