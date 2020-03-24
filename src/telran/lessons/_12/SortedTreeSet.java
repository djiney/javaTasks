package telran.lessons._12;

import telran.common.interfaces.SortedSet;
import telran.lessons._11.TreeSet;

import java.util.Comparator;

public class SortedTreeSet<T> extends TreeSet<T> implements SortedSet<T>
{
	public SortedTreeSet()
	{
		super();
	}

	public SortedTreeSet(Comparator<T> comparator)
	{
		super(comparator);
	}

	public SortedTreeSet(T[] data)
	{
		super(data);
	}

	@Override
	public SortedSet<T> subset(T from, boolean isFromIncluded, T to, boolean isToIncluded)
	{
		SortedTreeSet<T> result = new SortedTreeSet<>();
		int comparison;

		Node<T> current = from != null ? getClosestNode(from) : getMinFrom(root);

		if (from != null && current != null) {
			comparison = comparator.compare(current.value, from);
			if (!isFromIncluded && comparison == 0) {
				current = getNext(current);
			} else if (comparison < 0) {
				return result;
			}
		}

		while (current != null)
		{
			if (to != null) {
				comparison = comparator.compare(current.value, to);
				if ((!isToIncluded && comparison == 0) || comparison > 0) {
					break;
				}
			}

			result.add(current.value);
			current = getNext(current);
		}

		return result;
	}

	protected Node<T> getNext(Node<T> node)
	{
		return node.right != null ? getMinFrom(node.right) : getLeftParentFrom(node);
	}

	@Override
	public SortedSet<T> head(T key, boolean isIncluded)
	{
		return subset(null, false, key, isIncluded);
	}

	@Override
	public SortedSet<T> tail(T key, boolean isIncluded)
	{
		return subset(key, isIncluded, null, false);
	}
}
