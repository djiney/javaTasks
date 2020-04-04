package telran.lessons._17;

import telran.lessons._11.TreeSet;

import java.util.ArrayList;
import java.util.Comparator;

public class BalancedTreeSet<T> extends TreeSet<T>
{
	private static final double BALANCING_THRESHOLD = 1.2;

	int searchIterations = 0;

	public BalancedTreeSet()
	{
		super();
	}

	public BalancedTreeSet(Comparator<T> comparator)
	{
		super(comparator);
	}

	public BalancedTreeSet(T[] data)
	{
		super(data);
	}

	@Override
	public boolean add(T element)
	{
		if (!super.add(element)) {
			return false;
		}

		if (balancingRequired()) {
			balance();
		}

		return true;
	}

	private boolean balancingRequired()
	{
		if (size == 0 || searchIterations == 0) {
			return false;
		}

		int log = (int) (Math.log(size) / Math.log(2));
		if (log == 0) {
			return false;
		}

		return (double) searchIterations / log > BALANCING_THRESHOLD;
	}

	protected Node<T> getParent(T element)
	{
		searchIterations = 0;
		return super.getParent(element);
	}

	protected Node<T> getClosestNode(T element)
	{
		Node<T> current = root;
		if (current == null) {
			return null;
		}

		int comparison = comparator.compare(element, current.value);

		while (current.hasNext(comparison))
		{
			current = current.getNext(comparison);
			comparison = comparator.compare(element, current.value);
			searchIterations++;
		}

		return current;
	}

	private void balance()
	{
		ArrayList<Node<T>> list = new ArrayList<>(size);
		fillList(root, list);

		root = balance(list, 0, size - 1, null);
	}

	private Node<T> balance(ArrayList<Node<T>> list, int indexFrom, int indexTo, Node<T> root)
	{
		if (indexFrom > indexTo) {
			return null;
		}

		int indexMiddle = (indexFrom + indexTo) / 2;
		Node<T> node = list.get(indexMiddle);

		node.parent = root;

		node.left = balance(list, indexFrom, indexMiddle - 1, node);
		node.right = balance(list, indexMiddle + 1, indexTo, node);

		return node;
	}

	private void fillList(Node<T> node, ArrayList<Node<T>> list)
	{
		if (node != null) {
			fillList(node.left, list);
			list.add(node);
			fillList(node.right, list);
		}
	}
}
