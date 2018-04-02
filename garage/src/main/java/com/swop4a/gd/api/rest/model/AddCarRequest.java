package com.swop4a.gd.api.rest.model;

import com.swop4a.gd.model.Car;
import lombok.Data;

@Data
public class AddCarRequest {

	private Car car;
	private Long garageId;
}
