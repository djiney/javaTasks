package telran.lessons._22.tests;

import org.junit.jupiter.api.Test;
import telran.lessons._22.Main;

import static org.junit.jupiter.api.Assertions.*;

class MainTest
{
	byte[] array;

	@Test
	void testMemorySize()
	{
		int size = Main.getAvailableMemoryBlockSize();

		assertDoesNotThrow(() -> array = new byte[size]);
		array = null;

		assertThrows(OutOfMemoryError.class, () -> array = new byte[size + 1]);
	}
}