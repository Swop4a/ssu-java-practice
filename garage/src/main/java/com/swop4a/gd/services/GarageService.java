package com.swop4a.gd.services;

import com.swop4a.gd.model.Car;
import com.swop4a.gd.model.Garage;
import java.util.List;

public interface GarageService {

	/**
	 * Find all garages.
	 */
	List<Garage> findAll();

	/**
	 * Find garage by garageId.
	 */
	Garage findOne(Long garageId);

	/**
	 * Add car to garage using garageId.
	 */
	void addCar(Car car, Long garageId);

	/**
	 * Remove car by id.
	 */
	void removeCar(Long carId);
}
