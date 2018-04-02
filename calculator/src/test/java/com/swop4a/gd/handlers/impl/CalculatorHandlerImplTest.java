package com.swop4a.gd.handlers.impl;

import static org.junit.Assert.assertEquals;

import com.swop4a.gd.handlers.CalculatorHandler;
import java.math.BigDecimal;
import javax.script.ScriptException;
import org.junit.Test;

public class CalculatorHandlerImplTest {

	private CalculatorHandler handler = new CalculatorHandlerImpl();

	@Test
	public void test1() throws ScriptException {
		checkResult("5.0", "5");
	}

	@Test
	public void test2() throws ScriptException {
		checkResult("-5.0", "-5");
	}

	@Test
	public void test3() throws ScriptException {
		checkResult("5.0", "5.0");
	}

	@Test
	public void test4() throws ScriptException {
		checkResult("25.0", "25.0");
	}

	@Test
	public void test5() throws ScriptException {
		checkResult("7.0", "4+3");
	}

	@Test
	public void test6() throws ScriptException {
		checkResult("12.0", "4*3");
	}

	@Test
	public void test7() throws ScriptException {
		checkResult("1.3", "4/3");
	}

	@Test
	public void test8() throws ScriptException {
		checkResult("64.0", "4 ^ 3");
	}

	@Test
	public void test9() throws ScriptException {
		checkResult("1.0", "4-3");
	}

	@Test
	public void test10() throws ScriptException {
		checkResult("1.0", "(4-3)");
	}

	@Test
	public void test11() throws ScriptException {
		checkResult("7.0", "(4+3)");
	}

	@Test
	public void test12() throws ScriptException {
		checkResult("7.0", "((4+3))");
	}

	@Test
	public void test13() throws ScriptException {
		checkResult("49.0", "(4+3)^(5-3)");
	}

	@Test
	public void test14() throws ScriptException {
		checkResult("-3.0", "-5- -2");
	}

	@Test
	public void test15() throws ScriptException {
		checkResult("10.0", "-5* -2");
	}

	@Test
	public void test16() throws ScriptException {
		checkResult("23.0", "3+4*5");
	}

	@Test
	public void test17() throws ScriptException {
		checkResult("6.5", "3+4-5/(7+3)");
	}

	@Test
	public void test18() throws ScriptException {
		checkResult("15.0", "(5^2)-10");
	}

	@Test
	public void test19() throws ScriptException {
		checkResult("-1019.0", "5-2^10");
	}

	@Test
	public void test20() throws ScriptException {
		checkResult("0.0", "(0)");
	}

	@Test
	public void test21() throws ScriptException {
		checkResult("-7.0", "-(3+4)");
	}

	@Test(expected = ScriptException.class)
	public void test22() throws ScriptException {
		checkResult("23.0", "(");
	}

	@Test(expected = ScriptException.class)
	public void test23() throws ScriptException {
		checkResult("23.0", "(4+)5");
	}

	@Test(expected = ScriptException.class)
	public void test24() throws ScriptException {
		checkResult("23.0", "3-");
	}

	@Test(expected = ScriptException.class)
	public void test25() throws ScriptException {
		checkResult("23.0", "5-6*7+");
	}

	@Test(expected = ScriptException.class)
	public void test26() throws ScriptException {
		checkResult("23.0", "/3");
	}

	private void checkResult(String expected, String expression) throws ScriptException {
		BigDecimal result = handler.handle(expression);
		assertEquals(new BigDecimal(expected), result);
	}
}