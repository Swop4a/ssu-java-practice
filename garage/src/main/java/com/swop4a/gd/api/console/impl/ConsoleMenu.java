package com.swop4a.gd.api.console.impl;

import com.swop4a.gd.api.console.View;
import com.swop4a.gd.model.Car;
import com.swop4a.gd.model.Garage;
import com.swop4a.gd.services.GarageService;
import java.io.PrintStream;
import java.util.Objects;
import java.util.Scanner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

@Profile("console")
@Controller
public class ConsoleMenu implements View {

	private final Scanner in;
	private final PrintStream out;

	public ConsoleMenu() {
		this.in = new Scanner(System.in);
		this.out = System.out;
	}

	@Bean
	@Override
	public CommandLineRunner run(GarageService garageService) {
		return args -> {
			Garage garage = null;
			help();
			while (true) {
				while (Objects.isNull(garage)) {
					out.print("Garage doesn't exist or empty. Please, enter the number of garage: ");
					garage = garageService.findOne(in.nextLong());
				}
				out.printf("Garage #%s>", garage.getTitle());
				int option = in.nextInt();
				switch (option) {
					case 0:
						help();
						break;
					case 1:
						out.println(garage);
						break;
					case 2:
						garageService.addCar(addCarDialog(), garage.getId());
						garage = garageService.findOne(garage.getId());
						break;
					case 3:
						garageService.removeCar(removeCarDialog());
						garage = garageService.findOne(garage.getId());
						break;
					case 4:
						garage = null;
						break;
					case -1:
						return;
					default:
				}
			}
		};
	}

	private Car addCarDialog() {
		out.println("Please, enter car parameters");
		Car car = new Car();
		out.print("Enter car number: ");
		car.setId(in.nextLong());
		out.print("Enter car make: ");
		car.setMake(in.next());
		out.print("Enter car model: ");
		car.setModel(in.next());
		out.print("Enter car color: ");
		car.setColor(in.next());
		out.print("Enter car is running: ");
		car.setRunning(in.nextBoolean());
		return car;
	}

	private Long removeCarDialog() {
		out.print("Please, enter car number which you want to remove: ");
		return in.nextLong();
	}

	private void help() {
		out.println("If you want see information about garage, press 1");
		out.println("If you want put car to garage, press 2");
		out.println("If you want to remove car from garage, press 3");
		out.print("If you want leave the garage, press 4");
		out.println("If you want to see help dialog, press 0");
		out.println("If you want to exit form program, press -1");
	}
}
