package com.Raschi.dtos;

import com.Raschi.models.Site;

import java.time.LocalDate;

public record CarDto(double value, String model, String brand, String description, String year, Site site) {
}
