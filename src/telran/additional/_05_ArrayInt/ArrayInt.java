package telran.additional._05_ArrayInt;

import java.util.HashMap;

public class ArrayInt
{
	private int size;
	private int[] data;

	private HashMap<Integer, Boolean> map;

	private int defaultValue = 0;

	public ArrayInt(int length)
	{
		this.size = length;
		this.data = new int[this.size];

		this.map = new HashMap<>();
	}

	public int size()
	{
		return this.size;
	}

	public void setAll(int number)
	{
		this.data = new int[this.size];
		this.defaultValue = number;

		this.map.clear();
	}

	public void set(int index, int value)
	{
		this.checkIndex(index);

		this.data[index] = value;

		if (defaultValue != 0) {
			if (value == 0) {
				this.map.put(index, true);
			} else {
				this.map.remove(index);
			}
		}
	}

	public int get(int index)
	{
		this.checkIndex(index);

		if (!this.map.isEmpty() && this.map.containsKey(index)) {
			return 0;
		}

		return this.data[index] == 0 ? this.defaultValue : this.data[index];
	}

	private void checkIndex(int index) throws ArrayIndexOutOfBoundsException
	{
		if (index < 0 || index > this.size) {
			throw new ArrayIndexOutOfBoundsException();
		}
	}
}
