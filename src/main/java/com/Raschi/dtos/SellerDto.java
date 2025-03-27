package com.Raschi.dtos;

import com.Raschi.models.enums.SellerType;

public record SellerDto(String name, String number, String email, SellerType type) {
}
