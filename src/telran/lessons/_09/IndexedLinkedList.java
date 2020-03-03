package telran.lessons._09;

import telran.common.interfaces.IndexedList;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

public class IndexedLinkedList<T> implements IndexedList<T>
{
	private Node<T> firstNode;
	private Node<T> lastNode;

	private int size = 0;

	private Predicate<T> predicate;

	public IndexedLinkedList() {}

	public IndexedLinkedList(T[] values)
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
		if (index < 0 || index > this.size) {
			return false;
		}

		Node<T> newNode = new Node<>(element);

		if (firstNode == null) {
			firstNode = lastNode = newNode;
		} else if (index == this.size) {
			lastNode.nextNode = newNode;
			newNode.previousNode = lastNode;

			lastNode = newNode;
		} else {
			addNode(index, newNode);
		}

		size++;
		return true;
	}

	public void addNode(int index, Node<T> newNode)
	{
		Node<T> existingNode = getNodeByIndex(index);
		if (existingNode == null) {
			return;
		}

		if (existingNode.previousNode != null) {
			existingNode.previousNode.setNext(newNode);
			newNode.setPrevious(existingNode.previousNode);
		}

		newNode.setNext(existingNode);
		existingNode.setPrevious(newNode);

		if (index == 0) {
			firstNode = newNode;
		} else if (index == size) {
			lastNode = newNode;
		}
	}

	@Override
	public T set(int index, T element)
	{
		Node<T> node = getNodeByIndex(index);

		if (node == null) {
			return null;
		}

		T result = node.value;
		node.value = element;

		return result;
	}

	@Override
	public T remove(int index)
	{
		Node<T> node = getNodeByIndex(index);
		if (node == null) {
			return null;
		}

		this.removeNode(node);
		return node.value;
	}

	@Override
	public T remove(T pattern)
	{
		return this.remove(
				this.indexOf(pattern)
		);
	}

	private void removeNode(Node<T> node)
	{
		if (node.previousNode != null) {
			node.previousNode.setNext(node.nextNode);
		} else {
			firstNode = node.nextNode;
		}

		if (node.nextNode != null) {
			node.nextNode.setPrevious(node.previousNode);
		} else {
			lastNode = node.previousNode;
		}

		size--;
	}

	@Override
	public T get(int index)
	{
		Node<T> result = getNodeByIndex(index);
		return result == null ? null : result.value;
	}

	private Node<T> getNodeByIndex(int index)
	{
		if (!isIndexValid(index)) {
			return null;
		}

		return index < size / 2 ? getNodeFromLeft(index) : getNodeFromRight(index);
	}

	private Node<T> getNodeFromLeft(int index)
	{
		Node<T> currentNode = firstNode;

		for (int i = 0; i < index; i++) {
			currentNode = currentNode.nextNode;
		}

		return currentNode;
	}

	private Node<T> getNodeFromRight(int index)
	{
		Node<T> currentNode = lastNode;

		for (int i = size - 1; i > index; i--) {
			currentNode = currentNode.previousNode;
		}

		return currentNode;
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
		if (value == null || size == 0) {
			return -1;
		}

		Node<T> node = this.firstNode;

		for (int i = 0; i < this.size; i++) {
			if (value.equals(node.value)) {
				return i;
			}

			node = node.nextNode;
		}

		return -1;
	}

	@Override
	public int lastIndexOf(T value)
	{
		if (value == null) {
			return -1;
		}

		Node<T> node;

		for (int i = this.size - 1; i > 0; i--) {
			node = this.lastNode;
			if (node.value.equals(value)) {
				return i;
			}
		}

		return -1;
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
		this.sort((Comparator<T>) Comparator.naturalOrder());
	}

	@Override
	public void sort(Comparator<T> comparator)
	{

	}

	@Override
	public void setPredicate(Predicate<T> predicate)
	{
		this.predicate = predicate;
	}

	@Override
	public void resetPredicate()
	{
		this.setPredicate(null);
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

		public void setNext(Node<T> nextNode)
		{
			this.nextNode = nextNode;
		}

		public void setPrevious(Node<T> previousNode)
		{
			this.previousNode = previousNode;
		}
	}

	@Override
	public Iterator<T> iterator()
	{
		return new IndexedLinkedListIterator(this.predicate);
	}
	
	private class IndexedLinkedListIterator implements Iterator<T>
	{
		Node<T> currentNode;
		Predicate<T> predicate;

		IndexedLinkedListIterator(Predicate<T> predicate)
		{
			if (predicate == null) {
				predicate = k -> true;
			}

			this.predicate = predicate;
		}

		@Override
		public boolean hasNext()
		{
			if (!checkIndex()) {
				return false;
			}

//			if (currentNode == null) {
//				currentNode = firstNode;
//			}
//
//			while (checkIndex() && !predicate.test(currentNode.value)) {
//				currentIndex++;
//			}

			return checkIndex();
		}

		private boolean checkIndex()
		{
			return false;
//			return currentIndex < size;
		}

		@Override
		public T next()
		{
//			T element = data[currentIndex++];
//			return predicate.test(element) ? element : null;
			return null;
		}

		@Override
		public void remove()
		{
			removeNode(currentNode);
		}
	}
}
