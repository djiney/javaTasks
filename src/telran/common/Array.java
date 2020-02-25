package telran.common;

import telran.common.comparators.NativeComparator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public class Array<K>
{
	public static final int DEFAULT_SIZE = 16;
	public static final int INCREMENT_INDEX = 2;

	protected K[] data;
	protected int size = 0;

	public Array(int capacity)
	{
		//noinspection unchecked
		this.data = (K[]) new Object[capacity];
	}

	public Array()
	{
		this(Array.DEFAULT_SIZE);
	}

	public void add(K element)
	{
		this.checkCapacity();
		this.data[size++] = element;
	}

	public boolean add(int index, K element)
	{
		if (index < 0 || index > this.size) {
			return false;
		}

		this.checkCapacity();

		System.arraycopy(this.data, index, this.data, index + 1, this.size - index);

		this.data[index] = element;
		size++;

		return true;
	}

	public K get(int index)
	{
		return this.isIndexValid(index) ? this.data[index] : null;
	}

	public int size()
	{
		return size;
	}

	protected void checkCapacity()
	{
		if (this.data.length == this.size) {
			this.increaseCapacity();
		}
	}

	protected void increaseCapacity()
	{
		this.data = Arrays.copyOf(this.data, this.data.length * Array.INCREMENT_INDEX);
	}

	protected boolean isIndexValid(int index)
	{
		return index >= 0 && index < this.size;
	}

	public K set(int index, K element)
	{
		if (!this.isIndexValid(index)) {
			return null;
		}

		K result = this.data[index];
		this.data[index] = element;

		return result;
	}

	public K remove(int index)
	{
		if (!this.isIndexValid(index)) {
			return null;
		}

		K result = this.data[index];

		System.arraycopy(this.data, index + 1, this.data, index, --this.size - index);
		this.data[this.size] = null;

		return result;
	}

	public int indexOf(K value)
	{
		if (value == null) {
			return -1;
		}

		for (int i = 0; i < this.size; i++) {
			if (value.equals(this.data[i])) {
				return i;
			}
		}

		return -1;
	}

	public int lastIndexOf(K value)
	{
		if (value == null) {
			return -1;
		}

		for (int i = this.size - 1; i >= 0; i--) {
			if (value.equals(this.data[i])) {
				return i;
			}
		}

		return -1;
	}

	public int binarySearch(K pattern, Comparator<K> comparator)
	{
		if (this.data.length < 1) {
			return -1;
		}

		int left = 0;
		int right = this.size - 1;
		int middle = (left + right) / 2;

		while (left <= right && !pattern.equals(this.data[middle]))
		{
			if (comparator.compare(pattern, this.data[middle]) < 0) {
				right = middle - 1;
			} else {
				left = middle + 1;
			}
			middle = (left + right) / 2;
		}

		return left > right ? -left - 1 : middle;
	}

	@SuppressWarnings("unchecked")
	public int binarySearch(Comparable<K> pattern)
	{
		return this.binarySearch((K) pattern, new NativeComparator<K>());
	}

	public void sort(Comparator<K> comparator)
	{
		boolean flSort;
		int length = size;

		do {
			flSort = true;
			length--;
			for (int i = 0; i < length; i++)
			{
				if (comparator.compare(this.data[i], this.data[i + 1]) > 0)
				{
					K tmp = this.data[i];
					this.data[i] = this.data[i + 1];
					this.data[i + 1] = tmp;
					flSort = false;
				}
			}
		} while (!flSort);
	}

	public void sort()
	{
		this.sort(new NativeComparator<K>());
	}

	public Array<K> filter(Predicate<K> predicate)
	{
		Array<K> result = new Array<>(this.size);
		K element;

		for (int i = 0; i < this.size; i++) {
			element = this.data[i];
			if (predicate.test(element)) {
				result.add(element);
			}
		}

		return result;
	}

	public boolean removeIf(Predicate<K> predicate)
	{
		int length = this.size;
		this.size = 0;

		//noinspection unchecked
		K[] newArray = (K[]) new Object[this.data.length];

		for (int i = 0; i < length; i++) {
			if (!predicate.test(this.data[i])) {
				newArray[this.size++] = this.data[i];
			}
		}

		this.data = newArray;
		return length > size;
	}
}
