package telran.lessons._09;

import telran.common.interfaces.IndexedList;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

@SuppressWarnings("unchecked")
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
		add(size, element);
	}

	@Override
	public boolean add(int index, T element)
	{
		if (index < 0 || index > size) {
			return false;
		}

		Node<T> newNode = new Node<>(element);

		if (firstNode == null) {
			firstNode = lastNode = newNode;
		} else if (index == size) {
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
			existingNode.previousNode.nextNode = newNode;
			newNode.previousNode = existingNode.previousNode;
		}

		newNode.nextNode = existingNode;
		existingNode.previousNode = newNode;

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

		removeNode(node);
		return node.value;
	}

	@Override
	public T remove(T pattern)
	{
		return remove(
			  indexOf(pattern)
		);
	}

	private void removeNode(Node<T> node)
	{
		if (node.previousNode != null) {
			node.previousNode.nextNode = node.nextNode;
		} else {
			firstNode = node.nextNode;
		}

		if (node.nextNode != null) {
			node.nextNode.previousNode = node.previousNode;
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
		if (isIndexInValid(index)) {
			return null;
		}

		return index < size / 2 && !hasLoop() ? getNodeFromLeft(index) : getNodeFromRight(index);
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

	private boolean isIndexInValid(int ... indexes)
	{
		for (int index : indexes) {
			if (index < 0 || index >= size) {
				return true;
			}
		}

		return false;
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

		Node<T> node = firstNode;

		for (int i = 0; i < size; i++) {
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

		for (int i = size - 1; i > 0; i--) {
			node = lastNode;
			if (node.value.equals(value)) {
				return i;
			}
		}

		return -1;
	}

	@Override
	public int binarySearch(Comparable<T> pattern)
	{
		return binarySearch((T) pattern, (Comparator<T>) Comparator.naturalOrder());
	}

	@Override
	public int binarySearch(T pattern, Comparator<T> comparator)
	{
		if (pattern == null || size == 0) {
			return -1;
		}

		T previousValue = firstNode.value;
		Node<T> node = firstNode.nextNode;

		if (pattern.equals(previousValue)) {
			return 0;
		}

		int i;

		for (i = 1; i < size; i++) {
			if (pattern.equals(node.value)) {
				return i;
			}

			if (comparator.compare(node.value, previousValue) < 0) {
				break;
			}

			previousValue = node.value;
			node = node.nextNode;
		}

		return -i - 1;
	}

	@Override
	public IndexedList<T> filter(Predicate<T> predicate)
	{
		IndexedLinkedList<T> result = new IndexedLinkedList<>();

		Node<T> node = firstNode;

		for (int i = 0; i < size; i++) {
			if (predicate.test(node.value)) {
				result.add(node.value);
			}

			node = node.nextNode;
		}

		return result;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate)
	{
		int originalSize = size;

		Node<T> node = lastNode;

		for (int i = originalSize - 1; i >= 0; i--) {
			if (predicate.test(node.value)) {
				removeNode(node);
			}

			node = node.previousNode;
		}

		return originalSize > size;
	}

	public void setLoop(int indexFrom, int indexTo)
	{
		if (isIndexInValid(indexFrom, indexTo) || indexFrom < indexTo) {
			return;
		}

		Node<T> nodeFrom = getNodeByIndex(indexFrom);
		Node<T> nodeTo = getNodeByIndex(indexTo);

		if (nodeFrom == null || nodeTo == null) {
			return;
		}

		nodeFrom.nextNode = nodeTo;
	}

	public boolean hasLoop()
	{
		if (isEmpty()) {
			return false;
		}

		Node<T> previousNode = null;
		Node<T> currentNode = lastNode;

		while (currentNode.previousNode != null) {
			if (currentNode.nextNode != previousNode) {
				return true;
			}

			previousNode = currentNode;
			currentNode = currentNode.previousNode;
		}

		return false;
	}

	public void clearLoop()
	{
		if (isEmpty()) {
			return;
		}

		Node<T> previousNode = null;
		Node<T> currentNode = lastNode;

		while (currentNode.previousNode != null) {
			currentNode.nextNode = previousNode;

			previousNode = currentNode;
			currentNode = currentNode.previousNode;
		}
	}

	public boolean isEmpty()
	{
		return firstNode == null; // size == 0
	}

	@Override
	public void sort()
	{
		sort((Comparator<T>) Comparator.naturalOrder());
	}

	@Override
	public void sort(Comparator<T> comparator)
	{
		boolean flSort;
		int length = size;
		Node<T> node;

		do {
			flSort = true;
			length--;
			node = firstNode;

			for (int i = 0; i < length; i++)
			{
				if (comparator.compare(node.value, node.nextNode.value) > 0)
				{
					T tmp = node.value;
					node.value = node.nextNode.value;
					node.nextNode.value = tmp;
					flSort = false;
				}

				node = node.nextNode;
			}
		} while (!flSort);
	}

	@Override
	public void setPredicate(Predicate<T> predicate)
	{
		this.predicate = predicate;
	}

	@Override
	public void resetPredicate()
	{
		setPredicate(null);
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
		return new IndexedLinkedListIterator(predicate);
	}
	
	private class IndexedLinkedListIterator implements Iterator<T>
	{
		Node<T> currentNode = firstNode;
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
			while (currentNode != null && !predicate.test(currentNode.value)) {
				currentNode = currentNode.nextNode;
			}

			return currentNode != null;
		}

		@Override
		public T next()
		{
			T element = currentNode.value;
			currentNode = currentNode.nextNode;

			return predicate.test(element) ? element : null;
		}

		@Override
		public void remove()
		{
			Node<T> nodeToRemove = currentNode == null ? lastNode : currentNode.previousNode;
			removeNode(nodeToRemove);
		}
	}
}
