package com.swop4a.gd.services;

import com.swop4a.gd.model.Car;
import com.swop4a.gd.model.Garage;
import java.util.List;

public interface GarageService {

	List<Garage> findAll();

	Garage findOne(Long garageId);

	Garage addCar(Car car, Long garageId);

	void removeCar(Long carId);
}
