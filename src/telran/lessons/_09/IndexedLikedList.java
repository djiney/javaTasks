package telran.lessons._09;

import telran.common.interfaces.IndexedList;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

public class IndexedLikedList<T> implements IndexedList<T>
{
	private Node<T> firstNode;
	private Node<T> lastNode;

	private int size = 0;

	public IndexedLikedList()
	{

	}

	public IndexedLikedList(int dummyNumber)
	{

	}

	public IndexedLikedList(T[] values)
	{
		for (T value : values) {
		    add(value);
		}
	}

	@Override
	public void add(T element)
	{
		Node<T> newNode = new Node<>(element);
		size++;

		if (firstNode == null) {
			firstNode = lastNode = newNode;
		} else {
			lastNode.nextNode = newNode;
			newNode.previousNode = lastNode;

			lastNode = newNode;
		}
	}

	@Override
	public boolean add(int index, T element)
	{
		return false;
	}

	@Override
	public T set(int index, T element)
	{
		return null;
	}

	@Override
	public T remove(int index)
	{
		return null;
	}

	@Override
	public T remove(T pattern)
	{
		return null;
	}

	@Override
	public T get(int index)
	{
		if (!isIndexValid(index)) {
			return null;
		}

		return index < size / 2 ? getFromLeft(index) : getFromRight(index);
	}

	private T getFromLeft(int index)
	{
		Node<T> currentNode = firstNode;

		for (int i = 0; i < index; i++) {
			currentNode = currentNode.nextNode;
		}

		return currentNode.value;
	}

	private T getFromRight(int index)
	{
		Node<T> currentNode = lastNode;

		for (int i = size - 1; i > index; i--) {
			currentNode = currentNode.previousNode;
		}

		return currentNode.value;
	}

	private boolean isIndexValid(int index)
	{
		return index >= 0 && index < size;
	}

	@Override
	public int size()
	{
		return size;
	}

	@Override
	public int indexOf(T value)
	{
		return 0;
	}

	@Override
	public int lastIndexOf(T value)
	{
		return 0;
	}

	@Override
	public int binarySearch(Comparable<T> pattern)
	{
		return 0;
	}

	@Override
	public int binarySearch(T pattern, Comparator<T> comparator)
	{
		return 0;
	}

	@Override
	public IndexedList<T> filter(Predicate<T> predicate)
	{
		return null;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate)
	{
		return false;
	}

	@Override
	public void sort()
	{

	}

	@Override
	public void sort(Comparator<T> comparator)
	{

	}

	@Override
	public void setPredicate(Predicate<T> predicate)
	{
		
	}

	@Override
	public void resetPredicate()
	{

	}

	private static class Node<T>
	{
		public Node<T> nextNode;
		public Node<T> previousNode;

		public T value;

		public Node(T value)
		{
			this.value = value;
		}
	}

	@Override
	public Iterator<T> iterator()
	{
		return new IndexedLinkedListIterator();
	}
	
	private class IndexedLinkedListIterator implements Iterator<T>
	{
		@Override
		public boolean hasNext()
		{
			return false;
		}

		@Override
		public T next()
		{
			return null;
		}

		@Override
		public void remove()
		{

		}
	}
}
