package telran.additional._04_StackMax;

import telran.common.Array;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class StackMax
{
	public static final int DEFAULT_SIZE = 16;
	public static final int INCREMENT_INDEX = 2;

	private int[] data;
	private int size = 0;
	private int max;

	/*

	write class StackMax with the methods
	 void push(int value) - adds value at the top of a stack,
	 int pop() - removes a number from the top of a stack returns it (in the case of the empty stack it should throw "NoSuchElementException"),
	 boolean isEmpty() - return true if a stack is empty,
	 int max() - return maximal value of a stack (in the case of the empty stack it should throw "NoSuchElementException").

	 All methods should be written with the complexity O[1]. You may apply the Java collections

	* */

	public StackMax(int capacity)
	{
		this.data = new int[capacity];
	}

	public StackMax()
	{
		this(Array.DEFAULT_SIZE);
	}

	public void push(int value)
	{
		this.checkCapacity();
		this.data[size++] = value;

		if (value > this.max) {
		    this.max = value;
        }
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

	public int max()
	{
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

		return this.max;
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
}
