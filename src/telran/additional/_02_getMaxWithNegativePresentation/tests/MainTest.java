package telran.additional._02_getMaxWithNegativePresentation.tests;

import org.junit.jupiter.api.Test;
import telran.additional._02_getMaxWithNegativePresentation.Main;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest
{
	@Test
	public void testValueNotFound()
	{
		short[] array = {1, 2, 3, -4};

		assertEquals(-1, Main.getMaxWithNegativePresentation(array));
	}

	@Test
	public void testValueFound()
	{
		short[] array = {1, -1, 2, 3, 4, -3};

        assertEquals(3, Main.getMaxWithNegativePresentation(array));
	}

	@Test
	public void testEmptyArray()
	{
		short[] array = {};

        assertEquals(-1, Main.getMaxWithNegativePresentation(array));
	}
}