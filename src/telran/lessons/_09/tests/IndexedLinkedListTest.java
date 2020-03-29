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

		for (Integer ignored : indexedList)
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

		indexedList.clearLoop();
		assertFalse(indexedList.hasLoop());

		iteration = 0;
		for (Integer ignored : indexedList)
		{
			++iteration;
		}

		assertEquals(initialNumbers.length, iteration);
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

	@Test
	public void testGetLoopedNode()
	{
		IndexedLinkedList<Integer> indexedList = (IndexedLinkedList<Integer>) list;

		int loopFrom = 4;
		int loopTo = 4;

		indexedList.setLoop(loopFrom, loopTo);
		assertTrue(indexedList.hasLoop());
		assertEquals(loopFrom, indexedList.getLoopedNode());

		indexedList.clearLoop();
		assertFalse(indexedList.hasLoop());

		loopFrom = 3;
		loopTo = 1;

		indexedList.setLoop(loopFrom, loopTo);
		assertTrue(indexedList.hasLoop());
		assertEquals(loopFrom, indexedList.getLoopedNode());
	}

	@Test
	public void testReverse()
	{
		((IndexedLinkedList<Integer>) list).reverse();

		int i = initialNumbers.length - 1;
		for (Integer value : list) {
		    assertEquals(value, initialNumbers[i--]);
		}
	}

		@Test
	public void testCommonLinked()
	{
		IndexedLinkedList<Integer> indexedList1 = (IndexedLinkedList<Integer>) list;

		Integer[] numbers = {1, 2, 3, 4, 5, 7, 121, 23};
		IndexedLinkedList<Integer> indexedList2 = new IndexedLinkedList<>(numbers);

		IndexedLinkedList.Node<Integer> node = indexedList1.getNodeByIndex(2);
		IndexedLinkedList.Node<Integer> commonNode = indexedList2.getNodeByIndex(5);

		node.nextNode = commonNode;

		assertEquals(commonNode, getFirstCommonNode(indexedList1, indexedList2));
	}

	public static <T> IndexedLinkedList.Node<T> getFirstCommonNode(IndexedLinkedList<T> firstList, IndexedLinkedList<T> secondList)
	{
		int firstListSize = getRealSize(firstList),
			secondListSize = getRealSize(secondList);

		if (firstListSize == firstList.size() && secondListSize == secondList.size()) {
			return null;
		}

		int difference = Math.abs(firstListSize - secondListSize);

		IndexedLinkedList.Node<T> firstNode = firstList.firstNode;
		IndexedLinkedList.Node<T> secondNode = secondList.firstNode;

		while (firstNode != secondNode)
		{
			if (difference-- > 0) {
				if (firstListSize > secondListSize) {
					firstNode = firstNode.nextNode;
				} else {
					secondNode = secondNode.nextNode;
				}
			} else {
				firstNode = firstNode.nextNode;
				secondNode = secondNode.nextNode;
			}
		}

		return firstNode;
	}

	private static <T> int getRealSize(IndexedLinkedList<T> list)
	{
		IndexedLinkedList.Node<T> current = list.firstNode;
		int realSize = 0;

		while (current.nextNode != null) {
			current = current.nextNode;
			realSize++;
		}

		return realSize;
	}
}