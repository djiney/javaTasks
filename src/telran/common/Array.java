package telran.common;

import java.util.Arrays;

public class Array
{
	public static final int DEFAULT_SIZE = 16;
	public static final int INCREMENT_INDEX = 2;

	private Object[] data;
	private int size = 0;

	public Array(int capacity)
	{
		this.data = new Object[capacity];
	}

	public Array()
	{
		this(Array.DEFAULT_SIZE);
	}

	public void add(Object element)
	{
		this.checkCapacity();
		this.data[size++] = element;
	}

	public boolean add(int index, Object element)
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

	public Object get(int index)
	{
		return this.isIndexValid(index) ? this.data[index] : null;
	}

	public int size()
	{
		return size;
	}

	private void checkCapacity()
	{
		if (this.data.length == this.size) {
			this.increaseCapacity();
		}
	}

	private void increaseCapacity()
	{
		this.data = Arrays.copyOf(this.data, this.data.length * Array.INCREMENT_INDEX);
	}

	private boolean isIndexValid(int index)
	{
		return index >= 0 && index < this.size;
	}

	public Object set(int index, Object element)
	{
		if (!this.isIndexValid(index)) {
			return null;
		}

		Object result = this.data[index];
		this.data[index] = element;

		return result;
	}

	public Object remove(int index)
	{
		if (!this.isIndexValid(index)) {
			return null;
		}

		Object result = this.data[index];

		System.arraycopy(this.data, index + 1, this.data, index, --this.size - index);
		this.data[this.size] = null;

		return result;
	}
}
