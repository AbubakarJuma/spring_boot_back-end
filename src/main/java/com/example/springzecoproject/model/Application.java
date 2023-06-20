package com.example.springzecoproject.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appID;
    private String appName;
    private String appDate;
    private String appStatus;
    @ManyToOne
    @JoinColumn(name = "onwerID")
    private Owner owner;

}
