package com.swop4a.gd.dao.readers.utils;

import static java.util.stream.Collectors.toList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.swop4a.gd.exceptions.GarageNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JsonReaderHelper<T> {

	private static final File CARS_JSON = new File("garage/src/main/resources/cars.json");

	private final ObjectWriter writer;
	private final ObjectReader reader;

	@Autowired
	public JsonReaderHelper(ObjectMapper objectMapper) {
		this.writer = objectMapper.writerWithDefaultPrettyPrinter();
		this.reader = objectMapper.reader();
	}

	public List<T> readValues(Class<T> tClass) {
		try {
			return reader.forType(tClass)
				.readValues(CARS_JSON)
				.readAll()
				.stream()
				.map(v -> (T) v)
				.collect(toList());
		} catch (IOException e) {
			log.error("ERROR READING FILE CARS_JSON, MESSAGE {}", e.getMessage());
			throw new GarageNotFoundException();
		}
	}

	public void writeValues(List<T> values) {
		try {
			writer.writeValue(CARS_JSON, values);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
