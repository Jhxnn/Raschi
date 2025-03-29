package com.Raschi.controllers;

import com.Raschi.dtos.CarDto;
import com.Raschi.dtos.SearchCarDto;
import com.Raschi.models.Car;
import com.Raschi.services.CarService;
import com.Raschi.services.WebScrappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarService carService;

    @Autowired
    WebScrappingService webScrappingService;

    @GetMapping
    public ResponseEntity<List<Car>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(carService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> findById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(carService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody CarDto carDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.createCar(carDto));
    }

    @PostMapping("/search")
    public  ResponseEntity<List<Car>> searchCar(@RequestBody SearchCarDto searchCarDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(webScrappingService.findByBrandAndModel(searchCarDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@RequestBody CarDto carDto,
                                         @PathVariable(name = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.updateCar(carDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Car> deleteCar(@PathVariable(name = "id") UUID id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
}
