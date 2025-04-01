package com.example.EyeQlytics.EyeQlytics.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String cca3;

    private String commonName;
    private String officialName;
    private String region;
    private String subregion;
    private int population;
    private double area;
    private String flag;
    private String flagPng;

    @ElementCollection
    private List<String> capital;
}
