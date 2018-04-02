package com.swop4a.gd.dao;

import com.swop4a.gd.model.Garage;
import java.util.List;

public interface GarageDao {

	List<Garage> findAll();

	Garage findById(Long id);
}
