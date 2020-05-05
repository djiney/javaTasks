package telran.lessons._27.implementations;

import telran.lessons._27.NumbersBox;

import java.util.Collection;
import java.util.Iterator;

public abstract class BaseNumberBox implements NumbersBox
{
	protected Collection<Integer> data;

	protected abstract Collection<Integer> initCollection();

	public BaseNumberBox()
	{
		this.data = initCollection();
	}

	@Override
	public int size()
	{
		return data.size();
	}

	@Override
	public void addNumber(int number)
	{
		data.add(number);
	}

	@Override
	public void removeNumber(int number)
	{
		data.remove(number);
	}

	@Override
	public Iterator<Integer> iterator()
	{
		return data.iterator();
	}

	@Override
	public int removeNumbersInRange(int from, int to)
	{
		int oldSize = size();
		Collection<Integer> newData = initCollection();

		data.stream()
			.filter(value -> value < from || value > to)
			.forEach(newData::add);

		data = newData;
		return oldSize - size();
	}
}
