package telran.common.interfaces;

public interface SortedSet<T> extends Set<T>
{
	T getMin();
	T getMax();

	SortedSet<T> subset(T from, boolean isFromIncluded, T to, boolean isToIncluded);

	SortedSet<T> head(T key, boolean isIncluded);
	SortedSet<T> tail(T key, boolean isIncluded);
}
