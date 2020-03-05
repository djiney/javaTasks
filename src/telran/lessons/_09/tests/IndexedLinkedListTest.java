package telran.lessons._09.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.common.test.IndexedListTest;
import telran.lessons._09.IndexedLinkedList;

import static org.junit.jupiter.api.Assertions.*;

class IndexedLinkedListTest extends IndexedListTest
{
	@BeforeEach
	@Override
	public void setUp()
	{
		list = new IndexedLinkedList<>(this.initialNumbers);
	}

	@Test
	public void testLoop()
	{
		IndexedLinkedList<Integer> indexedList = (IndexedLinkedList<Integer>) list;
		indexedList.setLoop(1, 2);
		assertFalse(indexedList.hasLoop());

		indexedList.setLoop(2, 1);
		assertTrue(indexedList.hasLoop());

		int iteration = 0;
		int iterationLimit = 100;

		for (Integer value : indexedList)
		{
		    if (++iteration == iterationLimit) {
		    	break;
		    }
		}

		assertEquals(iterationLimit, iteration);

		int expectedValue = -8;
		int expectedIndex = 1;
		assertEquals(expectedValue, indexedList.get(expectedIndex));

		expectedValue = 30;
		expectedIndex = 4;
		assertEquals(expectedValue, indexedList.get(expectedIndex));
	}

	@Test
	public void additionalLoopTest()
	{
		IndexedLinkedList<Integer> indexedList = (IndexedLinkedList<Integer>) list;
		indexedList.setLoop(1, 1);
		assertTrue(indexedList.hasLoop());

		int iteration = 0;
		int iterationLimit = 100;

		for (Integer value : indexedList)
		{
			if (iteration == 0) {
				assertEquals(initialNumbers[0], value);
			} else {
				assertEquals(initialNumbers[1], value);
			}

			if (++iteration == iterationLimit) {
				break;
			}
		}
	}
}