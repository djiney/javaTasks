package telran.additional._05_ArrayInt.tests;

import org.junit.jupiter.api.Test;
import telran.additional._05_ArrayInt.ArrayInt;

import static org.junit.jupiter.api.Assertions.*;

class ArrayIntTest
{
	@Test
	void testFunctionality()
	{
		ArrayInt array = new ArrayInt(10);

		for (int i = 0; i < array.size(); i++) {
			assertEquals(0, array.get(i));
		}

		int setIndex = 5;
		int setValue = 1827;

		array.set(setIndex, setValue);
		assertEquals(setValue, array.get(setIndex));

		int number = 888;

		array.setAll(number);

		for (int i = 0; i < array.size(); i++) {
			assertEquals(number, array.get(i));
		}

		setIndex = 6;
		setValue = 11;

		array.set(setIndex, setValue);
		assertEquals(setValue, array.get(setIndex));
	}

	@Test
	public void testEdges()
	{
		ArrayInt array = new ArrayInt(10);
		for (int i = 0; i < array.size(); i++) {
			assertEquals(0, array.get(i));
		}

		array.set(0, 12);
		assertEquals(12, array.get(0));

		int edge = array.size() - 1;

		array.set(edge, 222);
		assertEquals(222, array.get(edge));

		assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.get(-1));
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.get(edge + 1));
	}
}