package telran.lessons._09.tests;

import org.junit.jupiter.api.BeforeEach;
import telran.common.test.IndexedListTest;
import telran.lessons._09.IndexedLikedList;

class IndexedLikedListTest extends IndexedListTest
{
	@BeforeEach
	@Override
	public void setUp()
	{
		list = new IndexedLikedList<>(this.initialNumbers);
	}
}