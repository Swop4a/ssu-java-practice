package com.swop4a.gd.dao.readers;

import com.swop4a.gd.dao.GarageDao;
import com.swop4a.gd.dao.readers.utils.JsonReaderHelper;
import com.swop4a.gd.exceptions.GarageNotFoundException;
import com.swop4a.gd.model.Garage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("!db")
public class GarageJsonReaderImpl implements GarageDao {

	private final JsonReaderHelper<Garage> readerHelper;

	@Autowired
	public GarageJsonReaderImpl(JsonReaderHelper<Garage> readerHelper) {
		this.readerHelper = readerHelper;
	}

	@Override
	public List<Garage> findAll() {
		return readerHelper.readValues(Garage.class);
	}

	@Override
	public Garage findById(Long id) {
		return readerHelper.readValues(Garage.class)
			.stream()
			.filter(garage -> id.equals(garage.getId()))
			.findAny()
			.orElseThrow(GarageNotFoundException::new);
	}
}
