package com.swop4a.gd.dao.repositories;

import com.swop4a.gd.dao.GarageDao;
import com.swop4a.gd.model.Garage;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

@Profile("db")
public interface GarageRepository extends GarageDao, JpaRepository<Garage, Long> {
}
