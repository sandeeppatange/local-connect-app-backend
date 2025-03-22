package com.example.bustimingapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor
@Entity
public class Schedule {

    public Schedule(Bus bus, Route route, String departureTime, String arrivalTime, String weekDays) {
        this.bus = bus;
        this.route = route;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.weekDays = weekDays;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Bus bus;

    @ManyToOne
    private Route route;

    private String departureTime;
    private String arrivalTime;
    private String weekDays;

    // Getters and Setters
}
