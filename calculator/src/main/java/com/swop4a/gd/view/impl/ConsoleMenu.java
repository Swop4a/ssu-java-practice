package com.swop4a.gd.view.impl;

import com.swop4a.gd.handlers.CalculatorHandler;
import com.swop4a.gd.view.View;
import java.util.Scanner;
import javax.script.ScriptException;

public class ConsoleMenu implements View {

	private CalculatorHandler handler;
	private Scanner scanner;

	private static final String EXIT_COMMAND = "exit";
	private static final String CONSOLE_RED_COLOR = (char) 27 + "[31m%s";
	private static final String CONSOLE_BLACK_COLOR = (char) 27 + "[30m%s";

	public ConsoleMenu(CalculatorHandler handler) {
		this.handler = handler;
		this.scanner = new Scanner(System.in);
	}

	@Override
	public void run() {
		while (true) {
			try {
				print("Calculator> ");
				String exp = scanner.nextLine();
				if (EXIT_COMMAND.equals(exp)) {
					println("Bye!");
					return;
				}
				println(String.format("Result: %s", handler.handle(exp)));
			} catch (ScriptException e) {
				printError("Invalid operation!");
			}
		}
	}

	private void print(String message) {
		System.out.print(String.format(CONSOLE_BLACK_COLOR, message));
	}

	private void println(String message) {
		System.out.println(String.format(CONSOLE_BLACK_COLOR, message));
	}

	private void printError(String error) {
		System.out.println(String.format(CONSOLE_RED_COLOR, error));
	}
}
