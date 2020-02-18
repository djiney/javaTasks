package telran.common;

import java.util.Arrays;

public class Array
{
	public static final int DEFAULT_SIZE = 16;
	public static final int INCREMENT_INDEX = 2;

	protected Object[] data;
	protected int size = 0;

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

	public int indexOf(Object value)
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

	public int lastIndexOf(Object value)
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
}
