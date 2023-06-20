package com.example.springzecoproject.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Zu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int zuID;
    private String zuLocation;
    private String zuName;

    @ManyToOne
    @JoinColumn(name = "suzaID")
    private Suza suza;


}
