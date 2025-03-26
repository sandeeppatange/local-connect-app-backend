package com.lca.bussearch.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String postalCode;
    private String placeName;
    private String taluka;
    private String district;
    private String state;
    private Integer talukaCode;
    private Integer districtCode;
    private String stateCode;
    private String countryCode;
    private Double latitude;
    private Double longitude;
    private Integer accuracy;

    // Getters and Setters
}
