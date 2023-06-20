package com.example.springzecoproject.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Suza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int suzaID;
    private String suzaLocation;
    private String suzaBulding;

    public int getSuzaID() {
        return suzaID;
    }

    public void setSuzaID(int suzaID) {
        this.suzaID = suzaID;
    }

    public String getSuzaLocation() {
        return suzaLocation;
    }

    public void setSuzaLocation(String suzaLocation) {
        this.suzaLocation = suzaLocation;
    }

    public String getSuzaBulding() {
        return suzaBulding;
    }

    public void setSuzaBulding(String suzaBulding) {
        this.suzaBulding = suzaBulding;
    }
}
