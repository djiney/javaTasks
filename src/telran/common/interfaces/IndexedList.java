package telran.common.interfaces;

import java.util.Comparator;
import java.util.function.Predicate;

public interface IndexedList<K> extends Iterable<K>
{
	void add(K element);
	boolean add(int index, K element);

	K set(int index, K element);

	K remove(int index);
	K remove(K pattern);

	K get(int index);

	int size();

	int indexOf(K value);
	int lastIndexOf(K value);

	int binarySearch(Comparable<K> pattern);
	int binarySearch(K pattern, Comparator<K> comparator);

	IndexedList<K> filter(Predicate<K> predicate);
	boolean removeIf(Predicate<K> predicate);
}
