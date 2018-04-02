package com.swop4a.gd.services.impl;

import com.swop4a.gd.dao.CarDao;
import com.swop4a.gd.exceptions.GarageNotFoundException;
import com.swop4a.gd.dao.GarageDao;
import com.swop4a.gd.model.Car;
import com.swop4a.gd.model.Garage;
import com.swop4a.gd.services.GarageService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GarageServiceImpl implements GarageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(GarageServiceImpl.class);

	private final GarageDao garageDao;
	private final CarDao carDao;

	@Autowired
	public GarageServiceImpl(GarageDao garageDao, CarDao carDao) {
		this.garageDao = garageDao;
		this.carDao = carDao;
	}

	@Override
	public List<Garage> findAll() {
		return garageDao.findAll();
	}

	@Override
	public Garage findOne(Long garageId) {
		LOGGER.info("TRY TO RETRIEVE GARAGE WITH ID: {}", garageId);
		Optional<Garage> garage = Optional.ofNullable(garageDao.findById(garageId));
		if (!garage.isPresent()) {
			LOGGER.error("ERROR RETRIEVING GARAGE {}! GARAGE NOT FOUND!", garageId);
			throw new GarageNotFoundException();
		}
		Garage one = garage.get();
		LOGGER.info("GARAGE {} RETRIEVED SUCCESSFULLY", one);
		return one;
	}

	@Override
	public void addCar(Car car, Long garageId) {
		LOGGER.info("INSERT CAR {} TO GARAGE {}", car, garageId);
		Garage garage = Optional.ofNullable(car.getGarage())
			.orElse(new Garage());
		garage.setId(garageId);
		car.setGarage(garage);
		carDao.save(car);
	}

	@Override
	public void removeCar(Long carId) {
		LOGGER.info("REMOVE CAR {} FROM GARAGE", carId);
		carDao.delete(carId);
	}
}
