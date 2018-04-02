package com.swop4a.gd.api.console;

import com.swop4a.gd.services.GarageService;
import org.springframework.boot.CommandLineRunner;

public interface View {

	CommandLineRunner run(GarageService garageService);
}
