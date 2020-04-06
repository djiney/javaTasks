package telran.lessons._18.tests;

import org.junit.jupiter.api.Test;
import telran.lessons._18.Encoder;

import javax.management.BadAttributeValueExpException;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

class EncoderTest
{
	@Test
	public void testCode() throws BadAttributeValueExpException
	{
		Encoder encoder = new Encoder("9876543210");
		assertEquals("876", encoder.code(123));

		encoder = new Encoder("0123456789abcdef");
		assertEquals("7b", encoder.code(123));

		encoder = new Encoder("0123456789");
		assertEquals("123", encoder.code(123));

		encoder = new Encoder("+-");
		assertEquals("-+-", encoder.code(5));

		assertThrows(BadAttributeValueExpException.class, () -> new Encoder("+"));
		assertThrows(BadAttributeValueExpException.class, () -> new Encoder("++"));
	}

	@Test
	public void testBase64() throws BadAttributeValueExpException
	{
		testBase64("ace");
		testBase64("abcd");
		testBase64("");
		testBase64("8");
		testBase64("Lorem ipsum dolor sit amet, consectetur adipisicing elit 76123 849!@#+1-");
	}

	private void testBase64(String string) throws BadAttributeValueExpException
	{
		String encodedString = new String(Base64.getEncoder().encode(string.getBytes()));

		Encoder encoder = new Encoder();
		assertEquals(encodedString, encoder.codeText(string));
		assertEquals(string, encoder.decodeText(encodedString));
	}
}