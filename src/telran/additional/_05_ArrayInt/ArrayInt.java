package telran.additional._05_ArrayInt;

import java.util.HashMap;

public class ArrayInt
{
	private int size;
	private HashMap<Integer, Integer> map;

	private int defaultValue = 0;

	public ArrayInt(int length)
	{
		this.size = length;
		this.map = new HashMap<>();
	}

	public int size()
	{
		return this.size;
	}

	public void setAll(int number)
	{
		this.defaultValue = number;
		this.map.clear();
	}

	public void set(int index, int value)
	{
		this.checkIndex(index);
		this.map.put(index, value);
	}

	public int get(int index)
	{
		this.checkIndex(index);

		if (!this.map.containsKey(index)) {
			return this.defaultValue;
		}

		return this.map.get(index);
	}

	private void checkIndex(int index) throws ArrayIndexOutOfBoundsException
	{
		if (index < 0 || index >= this.size) {
			throw new ArrayIndexOutOfBoundsException();
		}
	}
}
