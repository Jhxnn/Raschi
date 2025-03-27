package com.Raschi.models;


import com.Raschi.models.enums.SellerType;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "sellers")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID sellerId;

    private String name;

    private String number;

    private String email;

    private SellerType type;

    public UUID getSellerId() {
        return sellerId;
    }

    public void setSellerId(UUID sellerId) {
        this.sellerId = sellerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SellerType getType() {
        return type;
    }

    public void setType(SellerType type) {
        this.type = type;
    }
}
