package telran.lessons._18.tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class MainTest
{
	@Test
	public void testInteger()
	{
		Integer A = 500;
		Integer B = 100;
		Integer C = 500;
		Integer D = 100;


		assertEquals(A, C);
		assertTrue(A == C);

		// Up to 128
		assertEquals(B, D);
		assertTrue(B == D);
	}

	@Test
	public void testEqualDouble()
	{
		double a = Double.NaN;
		double b = Double.NaN;

		assertTrue(Double.isNaN(a) && Double.isNaN(b));

		double c = 3.14159;
		double d = 3.14159065;

		assertFalse(c == d);
		assertEquals(c, d, 0.01);
	}

	@Test
	public void testString()
	{
		String a = "Hello";
		String b = "Hello";
		String c = "Hello";

		assertEquals(a, b);
		assertTrue(a == b);

		assertEquals(a, c);
		assertFalse(a == c);
	}

	@Test
	public void test()
	{

	}
}