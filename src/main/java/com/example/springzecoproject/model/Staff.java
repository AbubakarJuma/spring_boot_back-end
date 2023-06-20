package com.example.springzecoproject.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int staffID;
    private String username;
    private String password;
    private String staffName;
    private String staffPhone;
    private String staffEmail;
}
