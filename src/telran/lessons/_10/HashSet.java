package telran.lessons._10;

import telran.common.interfaces.IndexedList;
import telran.common.interfaces.Set;
import telran.lessons._09.IndexedLinkedList;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.function.Predicate;

@SuppressWarnings("unchecked")
public class HashSet<T> implements Set<T>
{
	private static final int DEFAULT_SIZE = 3;
	private static final double FACTOR = 0.75;
	private static final int INCREMENT_INDEX = 2;

	IndexedList<T>[] hashTable;
	int size = 0;

	public HashSet()
	{
		this(DEFAULT_SIZE);
	}

	public HashSet(int defaultSize)
	{
		hashTable = new IndexedList[defaultSize];
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

		if (size > FACTOR * hashTable.length) {
			reInitTable();
		}

		getList(element).add(element);
		return true;
	}

	private void reInitTable()
	{
		HashSet<T> temp = new HashSet<>(hashTable.length * INCREMENT_INDEX);
		for (IndexedList<T> list : hashTable)
		{
			if (list == null) {
				continue;
			}

			for (T value : list)
			{
				temp.add(value);
			}
		}

		hashTable = temp.hashTable;
	}

	@Override
	public Set<T> filter(Predicate<T> predicate)
	{
		Set<T> newSet = new HashSet<>(size);

		for (T element : this) {
			if (predicate.test(element)) {
				newSet.add(element);
			}
		}

		return newSet;
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

		IndexedList<T> list = hashTable[index];
		if (list == null) {
			hashTable[index] = list = new IndexedLinkedList<>();
		}

		return list;
	}

	private int getTableIndex(T pattern)
	{
		return Math.abs(pattern.hashCode()) % hashTable.length;
	}

	@Override
	public Iterator<T> iterator()
	{
		return new HashSetIterator();
	}

	public T[] toArray()
	{
		T[] result = (T[]) Array.newInstance(Integer.class, size);

		int i = 0;
		for (T value : this) {
		    result[i++] = value;
		}

		return result;
	}

	private class HashSetIterator implements Iterator<T>
	{
		int currentIndex = 0;

		int currentList = 0;
		Iterator<T> iterator;

		@Override
		public boolean hasNext()
		{
			return currentIndex < size;
		}

		@Override
		public T next()
		{
			while (hasNext() && (iterator == null || !iterator.hasNext())) {
				if (hashTable[currentList++] != null) {
					iterator = hashTable[currentList - 1].iterator();
				}
			}

			currentIndex++;

			return iterator == null ? null : iterator.next();
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
