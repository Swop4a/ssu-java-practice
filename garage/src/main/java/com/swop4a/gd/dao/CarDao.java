package com.swop4a.gd.dao;

import com.swop4a.gd.model.Car;

public interface CarDao {

	void delete(Long id);

	Car save(Car car);
}
