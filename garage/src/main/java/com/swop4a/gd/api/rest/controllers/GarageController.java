package com.swop4a.gd.api.rest.controllers;

import com.swop4a.gd.api.rest.model.AddCarRequest;
import com.swop4a.gd.model.Garage;
import com.swop4a.gd.services.GarageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Profile("rest")
@RestController
@Api(value = "Garage Controller", description = "Controller provide operation about garage")
public class GarageController {

	private final GarageService garageService;

	@Autowired
	public GarageController(GarageService garageService) {
		this.garageService = garageService;
	}

	@GetMapping("/findAllGarages")
	@ApiOperation(value = "/findAllGarages", response = List.class)
	@ApiResponse(code = 200, message = "return garages", response = List.class)
	public ResponseEntity<List<Garage>> findAllGarages() {
		return new ResponseEntity<>(garageService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/getGarageInfo")
	@ApiOperation(value = "/getGarageInfo", response = Garage.class)
	@ApiResponse(code = 200, message = "return user", response = Garage.class)
	public ResponseEntity<Garage> getGarageInfo(@RequestParam("id") Long garageId) {
		return new ResponseEntity<>(garageService.findOne(garageId), HttpStatus.OK);
	}

	@PostMapping("/addCar")
	@ApiOperation(value = "/addCar", response = List.class)
	@ApiResponse(code = 200, message = "return user", response = List.class)
	public ResponseEntity<Void> addCar(@RequestBody AddCarRequest request) {
		garageService.addCar(request.getCar(), request.getGarageId());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/removeCar")
	@ApiOperation(value = "/removeCar", response = List.class)
	@ApiResponse(code = 200, message = "return user", response = List.class)
	public ResponseEntity<Void> removeCar(@RequestParam("id") Long carId) {
		garageService.removeCar(carId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
