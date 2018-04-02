package com.swop4a.gd.dao.readers;

import static java.util.stream.Collectors.toList;

import com.swop4a.gd.dao.CarDao;
import com.swop4a.gd.dao.readers.utils.JsonReaderHelper;
import com.swop4a.gd.exceptions.GarageNotFoundException;
import com.swop4a.gd.model.Car;
import com.swop4a.gd.model.Garage;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("!db")
public class CarJsonReaderImpl implements CarDao {

	private final JsonReaderHelper<Garage> readerHelper;

	@Autowired
	public CarJsonReaderImpl(JsonReaderHelper<Garage> readerHelper) {
		this.readerHelper = readerHelper;
	}

	@Override
	public void delete(Long id) {
		List<Garage> all = readerHelper.readValues(Garage.class).stream()
			.filter(g -> Objects.nonNull(g.getCars()))
			.peek(g -> g.setCars(g.getCars().stream()
				.filter(car -> !id.equals(car.getId()))
				.collect(toList())))
			.collect(toList());
		readerHelper.writeValues(all);
	}

	@Override
	public Car save(Car car) {
		List<Garage> all = readerHelper.readValues(Garage.class);
		Garage garage = all
			.stream()
			.filter(g -> g.getId().equals(car.getGarage().getId()))
			.findAny()
			.orElseThrow(GarageNotFoundException::new);
		garage.getCars().add(car);
		readerHelper.writeValues(all);
		return car;
	}
}
