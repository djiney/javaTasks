package telran.common;

import java.util.Arrays;

public class Array
{
	public static final int  DEFAULT_SIZE = 16;
	public static final int  INCREMENT_INDEX = 2;

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
		if (this.data.length == this.size) {
			this.increaseCapacity();
		}

		this.data[size++] = element;
	}

	public void put(Object element, int index)
	{
		/* --- */
	}

	public Object get(int index)
	{
		if (index >= this.size || index < 0) {
			return null;
		}

		return this.data[index];
	}

	public int getSize()
	{
		return size;
	}

	private void increaseCapacity()
	{
		this.data = Arrays.copyOf(this.data, this.data.length * Array.INCREMENT_INDEX);
	}
}
