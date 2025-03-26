package com.Raschi.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "cars")
public class Car {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID carId;

    private double value;

    private String model;

    private String brand;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "site_id")
    private Site site;

    public UUID getCarId() {
        return carId;
    }

    public void setCarId(UUID carId) {
        this.carId = carId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }
}
