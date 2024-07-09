package com.example.springzecoproject.model;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity

@Data
public class Login {

    @Id
    private String username;
    private String password;
    private String role;
}
