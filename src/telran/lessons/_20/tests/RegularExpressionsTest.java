package telran.lessons._20.tests;

import org.junit.jupiter.api.Test;

import static telran.lessons._20.RegularExpressions.*;
import static org.junit.jupiter.api.Assertions.*;

class RegularExpressionsTest
{
	@Test
	public void testVariableName()
	{
		String[] match = {"__", "$", "a", "a$f", "__12345_t", "A", "aAc12"};
		String[] noMatch = {"_", "1A_", "ab _", "av_*"};

		testPattern(variableName(), match, noMatch);
	}

	@Test
	public void testIp()
	{
		String[] match = {
			"127.0.0.1",
			"192.168.1.1",
			"192.168.1.255",
			"255.255.255.255",
			"0.0.0.0",
			"000.000.000.000",
			"000.000.000.001",
			"01.1.1.01"
		};

		String[] noMatch = {
			"30.168.1.255.1",
			"127.1",
			"192.168.1.256",
			"1921168.1.254",
			"192.168.1.255.",
			".192.168.1.255",
			"-1.2.3.4",
			"3...3"
		};

		testPattern(ipV4(), match, noMatch);
	}

	@Test
	public void testNumberLessThan256()
	{
		String[] match = {
			"0",
			"000",
			"01",
			"1",
			"12",
			"25",
			"025",
			"123",
			"26",
			"026",
			"249",
			"205",
			"255"
		};

		String[] noMatch = {
			"-1",
			"1111",
			"12#",
			"1 ",
			"a",
			"256",
			"2 7"
		};

		testPattern(numberLessThan256(), match, noMatch);
	}

	@Test
	public void testPhone()
	{
		String[] match = {
			"+972-50-1-22-33-44",
			"+972541223344",
			"057-1223344",
			"058-122-33-44"
		};

		String[] noMatch = {
			"057+1223344",
			"050-1-22-33-445",
			"50-1-22-33-44",
			"972-50-1-22-33-445",
			"+972-050-1-22-33-44",
			"050-1-22-33-4t5",
			"057-122—-3344",
			"051-122-33-44"
		};

		testPattern(phone(), match, noMatch);
	}

	@Test
	public void testEmail()
	{
		String[] match = {
			"yura.granovsky@gmail.com",
			"tt%2@mail.ru",
			"tt_67@co.il.d-d.a-a",
			"t5&4_s@ff.gt"
		};

		String[] noMatch = {
			"yu ra@gmail.com",
			"tt_67@co.il.d-d.a--a",
			"yu,ra@gmail.com",
			"yura@gma il.com",
			"tt%2@ma_il.ru",
			"tt_67@co.il.dd.aa.bb",
			"t5&4_s@ffgt",
		};

		testPattern(email(), match, noMatch);
	}

	@Test
	public void testArithmeticExpression()
	{
		String[] match = {
			"2+3 /7",
			"234+12",
			"234",
			"2",
			"2* 3;",
			"2 + 3 / 7"
		};

		String[] noMatch = {
			"*3 /7",
			"2.5 +8/2",
			"2*5 +8#2"
		};

		testPattern(arithmeticExpression(), match, noMatch);
	}

	private void testPattern(String pattern, String[] match, String[] noMatch)
	{
		for (String value : match) {
			assertTrue(value.matches(pattern), value);
		}

		for (String value : noMatch) {
			assertFalse(value.matches(pattern), value);
		}
	}
}