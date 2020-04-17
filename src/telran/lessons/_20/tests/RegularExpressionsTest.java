package telran.lessons._20.tests;

import org.junit.jupiter.api.Test;

import static telran.lessons._20.RegularExpressions.*;
import static org.junit.jupiter.api.Assertions.*;

class RegularExpressionsTest
{
	@Test
	public void testVariableName()
	{
		assertTrue("__".matches(variableName()));
		assertTrue("$".matches(variableName()));
		assertTrue("a".matches(variableName()));
		assertTrue("a$f".matches(variableName()));
		assertTrue("__12345_t".matches(variableName()));
		assertTrue("A".matches(variableName()));
		assertTrue("aAc12".matches(variableName()));

		assertFalse("_".matches(variableName()));
		assertFalse("1A_".matches(variableName()));
		assertFalse("ab _".matches(variableName()));
		assertFalse("av_*".matches(variableName()));
	}

	@Test
	public void testIp()
	{
		assertTrue("127.0.0.1".matches(ipV4()));
		assertTrue("192.168.1.1".matches(ipV4()));
		assertTrue("192.168.1.255".matches(ipV4()));
		assertTrue("255.255.255.255".matches(ipV4()));
		assertTrue("0.0.0.0".matches(ipV4()));
		assertTrue("000.000.000.000".matches(ipV4()));
		assertTrue("000.000.000.001".matches(ipV4()));
		assertTrue("01.1.1.01".matches(ipV4()));

		assertFalse("30.168.1.255.1".matches(ipV4()));
		assertFalse("127.1".matches(ipV4()));
		assertFalse("192.168.1.256".matches(ipV4()));
		assertFalse("1921168.1.254".matches(ipV4()));
		assertFalse("192.168.1.255.".matches(ipV4()));
		assertFalse(".192.168.1.255".matches(ipV4()));
		assertFalse("-1.2.3.4".matches(ipV4()));
		assertFalse("3...3".matches(ipV4()));
	}

	@Test
	public void testNumberLessThan256()
	{
		assertTrue("0".matches(numberLessThan256()));
		assertTrue("000".matches(numberLessThan256()));
		assertTrue("01".matches(numberLessThan256()));
		assertTrue("1".matches(numberLessThan256()));
		assertTrue("12".matches(numberLessThan256()));
		assertTrue("25".matches(numberLessThan256()));
		assertTrue("025".matches(numberLessThan256()));
		assertTrue("123".matches(numberLessThan256()));
		assertTrue("26".matches(numberLessThan256()));
		assertTrue("026".matches(numberLessThan256()));
		assertTrue("249".matches(numberLessThan256()));
		assertTrue("205".matches(numberLessThan256()));
		assertTrue("255".matches(numberLessThan256()));

		assertFalse("-1".matches(numberLessThan256()));
		assertFalse("1111".matches(numberLessThan256()));
		assertFalse("12#".matches(numberLessThan256()));
		assertFalse("1 ".matches(numberLessThan256()));
		assertFalse("a".matches(numberLessThan256()));
		assertFalse("256".matches(numberLessThan256()));
		assertFalse("2 7".matches(numberLessThan256()));

	}
}