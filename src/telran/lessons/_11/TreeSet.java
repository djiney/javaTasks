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
		if (root == null) {
			root = new Node<>(element);
		} else if (!addNode(element)) {
			return false;
		}

		size++;
		return true;
	}

	private boolean addNode(T element)
	{
		Node<T> parent = getParent(element);
		if (parent == null) {
			return false;
		}

		Node<T> newNode = new Node<>(element);
		newNode.parent = parent;

		if (comparator.compare(element, parent.value) < 0) {
			parent.left = newNode;
		} else {
			parent.right = newNode;
		}

		return true;
	}

	private Node<T> getParent(T element)
	{
		Node<T> current = root;
		int comparison = comparator.compare(element, current.value);
		
		while (hasNext(current, comparison))
		{
			current = getNext(current, comparison);
			comparison = comparator.compare(element, current.value);
		}

		return current;
	}

	private boolean hasNext(Node<T> node, int comparison)
	{
		if (comparison == 0) {
			return false;
		}

		return comparison > 0 ? node.right != null : node.left != null;
	}

	private Node<T> getNext(Node<T> node, int comparison)
	{
		return comparison > 0 ? node.right : node.left;
	}

	@Override
	public Set<T> filter(Predicate<T> predicate)
	{
		return null;
	}

	@Override
	public T remove(T pattern)
	{
		size--;
		return null;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate)
	{
		return false;
	}

	@Override
	public boolean contains(T pattern)
	{
		return getParent(pattern) == null;
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
	}
}
