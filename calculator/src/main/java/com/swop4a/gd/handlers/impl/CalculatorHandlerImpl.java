package com.swop4a.gd.handlers.impl;

import com.swop4a.gd.handlers.CalculatorHandler;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class CalculatorHandlerImpl implements CalculatorHandler {

	private static final int SCALE = 1;
	private static final String POW_CASE = "(([0-9]+(\\.[0-9]+)?)+|\\(.*\\))( )*\\^( )*(((-|\\+)?[0-9]+(\\.[0-9]+)?)+|\\(.*\\))";
	private static final String ENGINE_NAME = "nashorn";
	private static final ScriptEngine JS_ENGINE;

	static {
		JS_ENGINE = new ScriptEngineManager().
			getEngineByName(ENGINE_NAME);
	}

	@Override
	public BigDecimal handle(String expression) throws ScriptException {
		expression = fixExceptionalCases(expression);
		return new BigDecimal(JS_ENGINE.eval(expression).toString())
			.setScale(SCALE, RoundingMode.HALF_EVEN);
	}

	private static String fixExceptionalCases(String expression) {
		return expression
			.replaceAll(POW_CASE, "Math.pow($0)")
			.replace("^", ",");
	}
}
