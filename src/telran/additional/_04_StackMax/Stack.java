package telran.additional._04_StackMax;

import telran.common.Array;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class Stack
{
	public static final int DEFAULT_SIZE = 16;
	public static final int INCREMENT_INDEX = 2;

	protected int[] data;
	protected int size = 0;

	public Stack(int capacity)
	{
		this.data = new int[capacity];
	}

	public Stack()
	{
		this(Array.DEFAULT_SIZE);
	}

	public void push(int value)
	{
		this.checkCapacity();
		this.data[size++] = value;
	}

	public int pop()
	{
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}

		int result = this.data[--size];
		this.data[size] = 0;

		return result;
	}

	public boolean isEmpty()
	{
		return this.size == 0;
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
}
