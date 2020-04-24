package telran.lessons._23.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.lessons._23.exceptions.RangeException;
import telran.lessons._23.exceptions.RuleException;
import telran.lessons._23.numbers.DividerRule;
import telran.lessons._23.numbers.Generator;

class GeneratorRulesTests
{
	DividerRule divider10 = new DividerRule(10);
	int min = 100, max = 10000, nNumbers = 10000;

	@Test
	void testGenerate()
	{
		Generator generator = new Generator(min, max, divider10);
		int[] array = generator.generate(nNumbers);

		assertEquals(nNumbers, array.length);

		for (int num : array) {
			assertTrue(num % 10 == 0 && num >= min && num <= max);
		}

		assertThrows(IllegalArgumentException.class, () -> new Generator(max, min, divider10));
	}

	@Test
	void testDivider()
	{
		assertThrows(RangeException.class, () -> divider10.checkRule(12, 11, 19));
		assertThrows(RangeException.class, () -> divider10.checkRule(12, 19, 5));


		min = 10;

		assertDoesNotThrow(() -> divider10.checkRule(10, min, max));

		checkExceptionDelta(12, min, max, -2);
		checkExceptionDelta(9, min, max, 1);

		checkExceptionDelta(10, 11, max, 10);
		checkExceptionDelta(12, 11, max, 8);
		checkExceptionDelta(9, 11, max, 11);

		assertDoesNotThrow(() -> divider10.checkRule(100, min, 100));

		checkExceptionDelta(102, min, 100, -2);
		checkExceptionDelta(99, min, 100, 1);

		checkExceptionDelta(98, min, 99, -8);
		checkExceptionDelta(100, min, 99, -10);
	}

	private void checkExceptionDelta(int number, int min, int max, int delta)
	{
		try {
			divider10.checkRule(number, min, max);
			fail("Expected RuleException");
		} catch (RuleException e) {
			assertEquals(delta, e.getDelta());
		}
	}
}
