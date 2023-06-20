package com.example.springzecoproject.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int buildID;
    private String buildType;
    private String buildCity;
    private String buildStreet;
    private String buildRegion;

    @ManyToOne
    @JoinColumn(name = "ownerID")
    private Owner owner;
}
