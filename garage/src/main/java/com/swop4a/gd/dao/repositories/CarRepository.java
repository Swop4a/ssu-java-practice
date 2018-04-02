package com.swop4a.gd.dao.repositories;

import com.swop4a.gd.dao.CarDao;
import com.swop4a.gd.model.Car;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

@Profile("db")
public interface CarRepository extends CarDao, JpaRepository<Car, Long> {

}
