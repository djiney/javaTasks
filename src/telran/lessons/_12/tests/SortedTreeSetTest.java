package telran.lessons._12.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.common.interfaces.SortedSet;
import telran.lessons._11.tests.TreeSetTest;
import telran.lessons._12.SortedTreeSet;

import static org.junit.jupiter.api.Assertions.*;

public class SortedTreeSetTest extends TreeSetTest
{
	SortedTreeSet<Integer> sortedSet;

	@BeforeEach
	public void setUp()
	{
		set = new SortedTreeSet<>(initialNumbers);
		sortedSet = new SortedTreeSet<>(initialNumbers);
	}

	@Test
	public void testEdges()
	{
		assertEquals(-8, sortedSet.getMin());
		assertEquals(75, sortedSet.getMax());

		sortedSet = new SortedTreeSet<>();
		assertEquals(0, sortedSet.size());

		assertNull(sortedSet.getMin());
		assertNull(sortedSet.getMax());
	}

	@Test
	public void testSubset()
	{
		SortedSet<Integer> result = sortedSet.subset(-8, true, 30, false);
		Integer[] expected = {-8, 10};
		compareSetToArray(result, expected);

		result = sortedSet.subset(-8, true, 30, true);
		expected = new Integer[]{-8, 10, 30};
		compareSetToArray(result, expected);

		result = sortedSet.subset(10, false, 70, true);
		expected = new Integer[]{30, 70};
		compareSetToArray(result, expected);

		result = sortedSet.subset(90, false, 70, true);
		expected = new Integer[]{};
		compareSetToArray(result, expected);
	}

	@Test
	public void testTail()
	{
		SortedSet<Integer> result = sortedSet.tail(-108, false);
		compareSetToArray(result, initialNumbers);

		result = sortedSet.tail(-108, true);
		compareSetToArray(result, initialNumbers);

		result = sortedSet.tail(-8, true);
		Integer[] expected = {-8, 10, 30, 70, 75};
		compareSetToArray(result, expected);

		result = sortedSet.tail(30, false);
		expected = new Integer[]{70, 75};
		compareSetToArray(result, expected);

		result = sortedSet.tail(130, false);
		expected = new Integer[]{};
		compareSetToArray(result, expected);

		result = sortedSet.tail(130, true);
		expected = new Integer[]{};
		compareSetToArray(result, expected);
	}

	@Test
	public void testHead()
	{
		SortedSet<Integer> result = sortedSet.head(108, false);
		compareSetToArray(result, initialNumbers);

		result = sortedSet.head(108, true);
		compareSetToArray(result, initialNumbers);

		result = sortedSet.head(75, true);
		Integer[] expected = {-8, 10, 30, 70, 75};
		compareSetToArray(result, expected);

		result = sortedSet.head(30, false);
		expected = new Integer[]{-8, 10};
		compareSetToArray(result, expected);

		result = sortedSet.head(-130, false);
		expected = new Integer[]{};
		compareSetToArray(result, expected);

		result = sortedSet.head(-130, true);
		expected = new Integer[]{};
		compareSetToArray(result, expected);
	}
}