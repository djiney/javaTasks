package telran.additional._04_StackMax;

import telran.common.Array;
import java.util.*;

public class StackMax
{
	public static final int DEFAULT_SIZE = 16;
	public static final int INCREMENT_INDEX = 2;

	private int[] data;
	private int size = 0;

	private int maxValue = Integer.MIN_VALUE;

	private Map<Integer, int[]> map;

	public StackMax(int capacity)
	{
		this.map = new HashMap<>();
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

		if (value >= this.maxValue) {
			this.addNewMax(value);
		}
	}

	public int pop()
	{
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}

		int result = this.data[--size];
		this.data[size] = 0;

		if (result == this.maxValue) {
			this.maxValue = this.getPreviousMax();
		}

		return result;
	}

	private void addNewMax(int element)
	{
		int[] mapElement = {this.maxValue, 0};

		if (this.map.containsKey(element)) {
			mapElement = this.map.get(this.maxValue);
		} else {
			this.map.put(element, mapElement);
		}

		mapElement[1]++;

		this.maxValue = element;
	}

	private int getPreviousMax()
	{
		if (!this.map.containsKey(this.maxValue)) {
			return Integer.MIN_VALUE;
		}

		int[] result = this.map.get(this.maxValue);
		if (--result[1] > 0) {
			return this.maxValue;
		}

		this.map.remove(this.maxValue);
		return result[0];
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

		return this.maxValue;
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
