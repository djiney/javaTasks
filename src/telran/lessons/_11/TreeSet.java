package telran.lessons._11;

import telran.common.interfaces.Set;

import java.util.*;
import java.util.function.Predicate;

@SuppressWarnings("unchecked")
public class TreeSet<T> implements Set<T>
{
	protected Comparator<T> comparator;
	protected Node<T> root;

	protected int size = 0;

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

	public int height()
	{
		return height(root);
	}

	private int height(Node<T> node)
	{
		if (node == null) {
			return 0;
		}

		return 1 + Math.max(height(node.left), height(node.right));
	}

	public int width()
	{
		return width(root);
	}

	private int width(Node<T> node)
	{
		if (node == null) {
			return 0;
		}

		if (node.isFinal()) {
			return 1;
		}

		return width(node.right) + width(node.left);
	}

	public void print()
	{
		Printer printer = new Printer();
		printer.print();
	}

	public void printRotated()
	{
		Printer printer = new Printer();
		printer.printRotated();
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

	protected boolean addNode(Node<T> newNode)
	{
		if (root == null) {
			setAsRoot(newNode);
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

	protected void setAsRoot(Node<T> node)
	{
		root = node;
		if (node != null) {
			node.parent = null;
		}
	}

	protected Node<T> getClosestNode(T element)
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

	protected Node<T> getMinNode()
	{
		Node<T> result = root;
		while (result != null && result.left != null) {
			result = result.left;
		}

		return result;
	}

	protected Node<T> getMaxNode()
	{
		Node<T> result = root;
		while (result != null && result.right != null) {
			result = result.right;
		}

		return result;
	}

	protected Node<T> getParent(T element)
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

		T result = node.value;
		removeNode(node);

		return result;
	}

	public Node<T> removeNode(Node<T> node)
	{
		Node<T> nextNode = null;

		if (node.isJunction()) {
			Node<T> substitute = getMinFrom(node.right);
			node.value = substitute.value;

			nextNode = node;
			node = substitute;
		}

		removeLinearNode(node);
		size--;

		return nextNode;
	}

	protected void removeLinearNode(Node<T> node)
	{
		Node<T> nextNode = null;

		if (!node.isFinal()) {
			nextNode = node.left == null ? node.right : node.left;
		}

		if (node.parent != null) {
			node.parent.replaceChild(node, nextNode);
		} else {
			setAsRoot(nextNode);
		}
	}

	@Override
	public boolean removeIf(Predicate<T> predicate)
	{
		int originalSize = size;

		Iterator<T> iterator = iterator();
		T value;

		while(iterator.hasNext())
		{
			value = iterator.next();
			if (predicate.test(value)) {
				iterator.remove();
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

	protected Node<T> getMinFrom(Node<T> node)
	{
		while (node != null && node.left != null) {
			node = node.left;
		}

		return node;
	}

	protected Node<T> getLeftParentFrom(Node<T> node)
	{
		while (node.parent != null && node.parent.right == node) {
			node = node.parent;
		}

		return node.parent;
	}

	@Override
	public Iterator<T> iterator()
	{
		return new TreeSetIterator();
	}

	protected class TreeSetIterator implements Iterator<T>
	{
		Node<T> currentNode = getMinFrom(root);
		Node<T> previousNode;

		@Override
		public boolean hasNext()
		{
			return currentNode != null;
		}

		@Override
		public T next()
		{
			previousNode = currentNode;
			currentNode = currentNode.right != null ? getMinFrom(currentNode.right) : getLeftParentFrom(currentNode);

			return previousNode.value;
		}

		@Override
		public void remove()
		{
			Node<T> nextNode = TreeSet.this.removeNode(previousNode);
			if (nextNode != null) {
				currentNode = nextNode;
			}
		}
	}

	protected static class Node<T>
	{
		public T value;

		public Node<T> parent;

		public Node<T> left;
		public Node<T> right;

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

		public boolean isJunction()
		{
			return left != null && right != null;
		}

		public boolean isFinal()
		{
			return left == null && right == null;
		}

		public void replaceChild(Node<T> oldChild, Node<T> newChild)
		{
			if (oldChild == left) {
				left = newChild;
			} else if (oldChild == right) {
				right = newChild;
			}

			if (newChild != null) {
				newChild.parent = this;
			}
		}
	}

	protected class Printer
	{
		ArrayList<ArrayList<ListNode>> list = new ArrayList<>();
		int currentIndex = 0;

		public void print()
		{
			parseTree(root, 0);
			printList();
		}

		private void printList()
		{
			StringBuilder value;
			int lastIndex;

			for (ArrayList<ListNode> listNodes : list)
			{
				value = new StringBuilder();
				lastIndex = 0;

				for (ListNode node : listNodes)
				{
					value.append("  ".repeat(node.index - lastIndex)).append(node.value);
					lastIndex = node.index;
				}

				System.out.println(value);
			}
		}

		private void parseTree(Node<T> node, int level)
		{
			if (node != null) {
				if (list.size() <= level) {
					list.add(level, new ArrayList<>());
				}

				ListNode listNode = new ListNode(node.value);
				list.get(level).add(listNode);

				parseTree(node.left, level + 1);
				listNode.index = currentIndex++;
				parseTree(node.right, level + 1);
			}
		}

		public void printRotated()
		{
			printRotated(root, 0);
		}

		private void printRotated(Node<T> node, int level)
		{
			if (node != null) {
				printRotated(node.right, level + 1);
				printRotated(node.value, level);
				printRotated(node.left, level + 1);
			}
		}

		private void printRotated(T value, int level)
		{
			System.out.println("   ".repeat(level) + value);
		}

		private class ListNode
		{
			T value;
			int index;

			public ListNode(T value)
			{
				this.value = value;
			}
		}
	}
}
