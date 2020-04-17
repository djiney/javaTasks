package telran.lessons._19_0.tests;

import org.junit.jupiter.api.Test;
import telran.lessons._19_0.components.JoinStringsImplBuilder;
import telran.lessons._19_0.components.JoinStringsImplString;

import static org.junit.jupiter.api.Assertions.*;

class MainTest
{
	String[] strings = {"Hello", "World!"};
	String delimiter = ", ";
	String result = "Hello, World!";

	@Test
	public void builderTest()
	{
		JoinStringsImplBuilder component = new JoinStringsImplBuilder();
		assertEquals(result, component.join(strings, delimiter));
	}

	@Test
	public void concatenationTest()
	{
		JoinStringsImplString component = new JoinStringsImplString();
		assertEquals(result, component.join(strings, delimiter));
	}
}