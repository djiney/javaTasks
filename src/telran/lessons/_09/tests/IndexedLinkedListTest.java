package telran.lessons._09.tests;

import org.junit.jupiter.api.BeforeEach;
import telran.common.test.IndexedListTest;
import telran.lessons._09.IndexedLinkedList;

class IndexedLinkedListTest extends IndexedListTest
{
	@BeforeEach
	@Override
	public void setUp()
	{
		list = new IndexedLinkedList<>(this.initialNumbers);
	}
}