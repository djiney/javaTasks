package telran.lessons._010;

import telran.common.interfaces.IndexedList;
import telran.common.interfaces.Set;
import telran.lessons._09.IndexedLinkedList;

import java.util.Iterator;
import java.util.function.Predicate;

@SuppressWarnings("unchecked")
public class HashSet<T> implements Set<T>
{
	private static final int DEFAULT_SIZE = 3;
	private static final double FACTOR = 0.75;
	private static final int INCREMENT_INDEX = 2;

	IndexedList<T>[] hastTable;
	int size = 0;

	public HashSet()
	{
		this(DEFAULT_SIZE);
	}

	public HashSet(int defaultSize)
	{
		hastTable = new IndexedList[defaultSize];
	}

	public HashSet(T[] elements)
	{
		this(DEFAULT_SIZE);

		for (T element : elements) {
		    add(element);
		}
	}

	@Override
	public boolean add(T element)
	{
		if (contains(element)) {
			return false;
		}

		size++;

		if (size > FACTOR * hastTable.length) {
			reInitTable();
		}

		getList(element).add(element);
		return true;
	}

	private void reInitTable()
	{
		HashSet<T> temp = new HashSet<>(hastTable.length * INCREMENT_INDEX);
		for (IndexedList<T> list : hastTable)
		{
			if (list == null) {
				continue;
			}

			for (T value : list)
			{
				temp.add(value);
			}
		}

		hastTable = temp.hastTable;
	}

	@Override
	public Set<T> filter(Predicate<T> predicate)
	{
		Iterator<T> iterator = iterator();

		while (iterator.hasNext()) {
			if (!predicate.test(iterator.next())) {
				iterator.remove();
			}
		}

		return this;
	}

	@Override
	public T remove(T pattern)
	{
		T result = getList(pattern).remove(pattern);
		if (result != null) {
			size--;
		}

		return result;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate)
	{
		Iterator<T> iterator = iterator();
		boolean result = false;

		while (iterator.hasNext()) {
			if (predicate.test(iterator.next())) {
				iterator.remove();
				result = true;
			}
		}

		return result;
	}

	@Override
	public boolean contains(T pattern)
	{
		return getList(pattern).indexOf(pattern) >= 0;
	}

	@Override
	public int size()
	{
		return size;
	}

	private IndexedList<T> getList(T pattern)
	{
		int index = getTableIndex(pattern);

		IndexedList<T> list = hastTable[index];
		if (list == null) {
			hastTable[index] = list = new IndexedLinkedList<>();
		}

		return list;
	}

	private int getTableIndex(T pattern)
	{
		return Math.abs(pattern.hashCode()) % hastTable.length;
	}

	@Override
	public Iterator<T> iterator()
	{
		return new IndexedLinkedListIterator();
	}

	private class IndexedLinkedListIterator implements Iterator<T>
	{
		int currentIndex = 0;

		int currentList = 0;
		Iterator<T> iterator;

		IndexedLinkedListIterator() {}

		@Override
		public boolean hasNext()
		{
			return currentIndex < size;
		}

		@Override
		public T next()
		{
			loadIterator();
			currentIndex++;

			return iterator == null ? null : iterator.next();
		}

		private void loadIterator()
		{
			while (this.hasNext() && (iterator == null || !iterator.hasNext())) {
				if (hastTable[currentList++] != null) {
					iterator = hastTable[currentList - 1].iterator();
				}
			}
		}

		@Override
		public void remove()
		{
			iterator.remove();
			size--;
			currentIndex--;
		}
	}
}
