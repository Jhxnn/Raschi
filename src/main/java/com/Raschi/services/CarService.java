package com.Raschi.services;


import com.Raschi.dtos.CarDto;
import com.Raschi.models.Car;
import com.Raschi.repositories.CarRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    public Car findById(UUID id){
        return carRepository.findById(id).orElseThrow(()-> new RuntimeException(("Cannot be found")));
    }

    public List<Car> findAll(){
        return carRepository.findAll();
    }

    public Car createCar(CarDto carDto){
        var car = new Car();
        BeanUtils.copyProperties(carDto, car);
        return  carRepository.save(car);
    }

    public Car updateCar(CarDto carDto, UUID id){
        var car = findById(id);
        BeanUtils.copyProperties(carDto, car);
        return carRepository.save(car);
    }

    public void deleteCar(UUID id){
        var car = findById(id);
        carRepository.delete(car);
    }
}
