package telran.lessons._17.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.lessons._11.tests.TreeSetTest;
import telran.lessons._17.BalancedTreeSet;

import static org.junit.jupiter.api.Assertions.*;

class BalancedTreeSetTest extends TreeSetTest
{
	@BeforeEach
	public void setUp()
	{
		set = new BalancedTreeSet<>(initialNumbers);
	}

	@Test
	public void balanceTest()
	{
		Integer[] testNumbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		set = new BalancedTreeSet<>(testNumbers);

		((BalancedTreeSet<Integer>) set).print();
	}
}