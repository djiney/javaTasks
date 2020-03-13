package telran.common.interfaces;

import java.util.function.Predicate;

public interface Set<T> extends Iterable<T>
{
	boolean add(T element);
	Set<T> filter(Predicate<T> predicate);
	T remove(T pattern);
	boolean removeIf(Predicate<T> predicate);
	boolean contains(T pattern);

	int size();
}
