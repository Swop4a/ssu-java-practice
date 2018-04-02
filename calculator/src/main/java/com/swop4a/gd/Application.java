package com.swop4a.gd;

import com.swop4a.gd.handlers.impl.CalculatorHandlerImpl;
import com.swop4a.gd.view.impl.ConsoleMenu;

public class Application {

	public static void main(String[] args) {
		new ConsoleMenu(new CalculatorHandlerImpl()).run();
	}
}
