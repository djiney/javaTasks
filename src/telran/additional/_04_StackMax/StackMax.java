package telran.additional._04_StackMax;

import java.util.*;

public class StackMax extends Stack
{
	protected Stack stack;
	protected int maxValue = Integer.MIN_VALUE;

	public StackMax(int capacity)
	{
		super(capacity);
		this.stack = new Stack();
	}

	public void push(int value)
	{
		super.push(value);

		if (value >= this.maxValue) {
			this.addNewMax(value);
		}
	}

	public int pop()
	{
		int result = super.pop();

		if (result == this.maxValue) {
			this.setPreviousMax();
		}

		return result;
	}

	protected void addNewMax(int element)
	{
		this.stack.push(this.maxValue);
		this.maxValue = element;
	}

	protected void setPreviousMax()
	{
		this.maxValue = this.stack.pop();
	}

	public int max()
	{
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}

		return this.maxValue;
	}
}
