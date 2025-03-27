package com.Raschi.dtos;

import java.time.LocalDate;

public record CarDto(double value, String model, String brand, String description, LocalDate publicationDate) {
}
