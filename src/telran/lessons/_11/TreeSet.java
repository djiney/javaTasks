package telran.lessons._11;

import telran.common.interfaces.Set;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

@SuppressWarnings("unchecked")
public class TreeSet<T> implements Set<T>
{
	Comparator<T> comparator;
	Node<T> root;

	private int size = 0;

	public TreeSet()
	{
		this((Comparator<T>) Comparator.naturalOrder());
	}

	public TreeSet(Comparator<T> comparator)
	{
		this.comparator = comparator;
	}

	public TreeSet(T[] data)
	{
		this();

		for (T value : data) {
		    add(value);
		}
	}

	@Override
	public boolean add(T element)
	{
		Node<T> newNode = new Node<>(element);
		if (!addNode(newNode)) {
			return false;
		}

		size++;
		return true;
	}

	private boolean addNode(Node<T> newNode)
	{
		if (root == null) {
			newNode.parent = null;
			root = newNode;
			return true;
		}

		Node<T> parent = getParent(newNode.value);
		if (parent == null) {
			return false;
		}

		newNode.parent = parent;

		if (comparator.compare(newNode.value, parent.value) < 0) {
			parent.left = newNode;
		} else {
			parent.right = newNode;
		}

		return true;
	}

	private Node<T> getClosestNode(T element)
	{
		Node<T> current = root;
		if (current == null) {
			return null;
		}

		int comparison = comparator.compare(element, current.value);

		while (current.hasNext(comparison)) {
			current = current.getNext(comparison);
			comparison = comparator.compare(element, current.value);
		}

		return current;
	}

	public T getMax()
	{
		Node<T> node = getMaxNode();
		return node == null ? null : node.value;
	}

	public T getMin()
	{
		Node<T> node = getMinNode();
		return node == null ? null : node.value;
	}

	private Node<T> getMinNode()
	{
		Node<T> result = root;
		while (result.left != null) {
			result = result.left;
		}

		return result;
	}

	private Node<T> getMaxNode()
	{
		Node<T> result = root;
		while (result.right != null) {
			result = result.right;
		}

		return result;
	}

	private Node<T> getParent(T element)
	{
		Node<T> result = getClosestNode(element);
		if (result != null && comparator.compare(element, result.value) == 0) {
			result = null;
		}

		return result;
	}

	@Override
	public T remove(T pattern)
	{
		Node<T> node = getClosestNode(pattern);
		if (node == null || comparator.compare(pattern, node.value) != 0) {
			return null;
		}

		size--;

		Node<T> parent = node.parent;
		if (parent != null) {
			parent.removeChild(node);
		} else {
			root = null;
		}

		if (node.left != null) {
			addNode(node.left);
		}

		if (node.right != null) {
			addNode(node.right);
		}

		return node.value;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate)
	{
		int originalSize = size;

		for (T value : this) {
			if (!predicate.test(value)) {
				remove(value);
			}
		}

		return originalSize > size;
	}

	@Override
	public Set<T> filter(Predicate<T> predicate)
	{
		TreeSet<T> result = new TreeSet<>();

		for (T value : this) {
			if (predicate.test(value)) {
				result.add(value);
			}
		}

		return result;
	}

	@Override
	public boolean contains(T pattern)
	{
		return root != null && getParent(pattern) == null;
	}

	@Override
	public int size()
	{
		return size;
	}

	@Override
	public Iterator<T> iterator()
	{
		return new TreeSetIterator();
	}

	private class TreeSetIterator implements Iterator<T>
	{
		Node<T> currentNode;

		T maxValue;

		T lastValue;
		T previousValue;

		public TreeSetIterator()
		{
			goLeftFrom(root);
			maxValue = getMaxNode().value;
		}

		@Override
		public boolean hasNext()
		{
			return previousValue == null || comparator.compare(maxValue, previousValue) > 0;
		}

		@Override
		public T next()
		{
			previousValue = currentNode.value;
			setNextNode();

			return previousValue;
		}

		@Override
		public void remove()
		{
			TreeSet.this.remove(previousValue);
			reset();
		}

		private void reset()
		{
			maxValue = getMaxNode().value;
			if (comparator.compare(previousValue, maxValue) >= 0) {
				return;
			}

			T tempValue = previousValue;
			lastValue = null;

			goLeftFrom(root);

			while (comparator.compare(tempValue, currentNode.value) >= 0) {
				setNextNode();
			}

			lastValue = tempValue;
		}

		private void setNextNode()
		{
			if (currentNode.right != null) {
				goLeftFrom(currentNode.right);
			} else {
				goUp();
			}
		}

		private void goLeftFrom(Node<T> node)
		{
			while (node.left != null) {
				node = node.left;
			}

			setCurrentNode(node);
		}

		private void goUp()
		{
			while (currentNode.parent != null) {
				setCurrentNode(currentNode.parent);
				if (comparator.compare(lastValue, currentNode.value) <= 0) {
					break;
				}
			}
		}

		private void setCurrentNode(Node<T> node)
		{
			currentNode = node;

			if (lastValue == null || comparator.compare(lastValue, currentNode.value) <= 0) {
				lastValue = currentNode.value;
			}
		}
	}

	private static class Node<T>
	{
		T value;

		Node<T> parent;

		Node<T> left;
		Node<T> right;

		Node(T object)
		{
			value = object;
		}

		public boolean hasNext(int comparison)
		{
			if (comparison == 0 ) {
				return false;
			}

			return comparison > 0 ? right != null : left != null;
		}

		public Node<T> getNext(int comparison)
		{
			if (comparison == 0) {
				return this;
			}

			return comparison > 0 ? right : left;
		}

		public void removeChild(Node<T> node)
		{
			if (node.equals(left)) {
				left = null;
			} else if (node.equals(right)) {
				right = null;
			}
		}
	}
}
