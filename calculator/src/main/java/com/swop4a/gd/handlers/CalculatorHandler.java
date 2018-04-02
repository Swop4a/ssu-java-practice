package com.swop4a.gd.handlers;

import java.math.BigDecimal;
import javax.script.ScriptException;

public interface CalculatorHandler {

	BigDecimal handle(String expression) throws ScriptException;
}
