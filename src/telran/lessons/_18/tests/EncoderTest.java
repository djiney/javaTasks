package telran.lessons._18.tests;

import org.junit.jupiter.api.Test;
import telran.lessons._18.Encoder;

import javax.management.BadAttributeValueExpException;

import static org.junit.jupiter.api.Assertions.*;

class EncoderTest
{
	@Test
	public void testCode() throws BadAttributeValueExpException
	{
		Encoder encoder = new Encoder("9876543210");
		assertEquals("876", encoder.code(123));

		encoder = new Encoder("+-");
		assertEquals("-+-", encoder.code(5));

		assertThrows(BadAttributeValueExpException.class, () -> new Encoder("+"));
		assertThrows(BadAttributeValueExpException.class, () -> new Encoder("++"));
	}
}