package telran.common;

import java.util.Comparator;

public class SortedArray extends Array
{
//	public void add(Object element)
//	{
//		int index = this.binarySearch(element);
//
//		this.checkCapacity();
//
//		System.arraycopy(this.data, index, this.data, index + 1, this.size - index);
//
//		this.data[index] = element;
//		size++;
//	}

//	public int binarySearch(Object pattern)
//	{
//		return this.binarySearch(pattern, new Compara);
//	}

	public int binarySearch(Object pattern, Comparator<Object> comparator)
	{
		if (this.data.length < 1) {
			return -1;
		}

		int left = 0;
		int right = this.size - 1;
		int middle = (left + right) / 2;

		while (left <= right && !pattern.equals(this.data[middle])) {
			if (comparator.compare(this.data[left], this.data[right]) < 0) {
				right = middle - 1;
			} else {
				left = middle + 1;
			}
			middle = (left + right) / 2;
		}

		return left > right ? -left - 1 : middle;
	}
}
