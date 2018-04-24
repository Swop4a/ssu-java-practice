package com.swop4a.gd.handlers;

import java.math.BigDecimal;
import javax.script.ScriptException;

public interface CalculatorHandler {

	/**
	 * Method evaluate mathematics expression and return BigDecimal with one digit after dot.
	 *
	 * @param expression - mathematics expression
	 * like: 4 ^ 3 + 2, (12 + 3) / 5, etc.
	 * @return result of evaluation
	 */
	BigDecimal handle(String expression) throws ScriptException;
}
