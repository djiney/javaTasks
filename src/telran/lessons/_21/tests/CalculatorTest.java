package telran.lessons._21.tests;

import org.junit.jupiter.api.Test;
import telran.lessons._21.Calculator;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest
{
	@Test
	public void testCalculate()
	{
		assertEquals(10, Calculator.calculate("2 * 10 - 5 * 2 / 3"));
		assertEquals(10, Calculator.calculate(" 2 * 10 - 5 * 2 / 3 "));

		assertEquals(Double.POSITIVE_INFINITY, Calculator.calculate("2+5/0"));
		assertEquals(Double.NaN, Calculator.calculate("/3"));
		assertEquals(Double.NaN, Calculator.calculate(" 2 * 10 - 5 * 2 # 3 "));
	}

	@Test
	public void testCalculateFloat()
	{
		assertEquals(21, Calculator.calculate("2 * 10.5"));
		assertEquals(4.14, Calculator.calculate("10.5 + 2.75 / 3.2"), 2);
	}

	@Test
	public void testParenthesis()
	{
		assertTrue(Calculator.checkParenthesis("()"));
		assertTrue(Calculator.checkParenthesis("(())"));
		assertTrue(Calculator.checkParenthesis("(()())"));
		assertTrue(Calculator.checkParenthesis("(()()((())))"));

		assertFalse(Calculator.checkParenthesis("(()"));
		assertFalse(Calculator.checkParenthesis("(()"));
		assertFalse(Calculator.checkParenthesis("())"));
		assertFalse(Calculator.checkParenthesis("(()()"));
	}
}