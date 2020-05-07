package telran.lessons._27.implementations;

import java.util.Collection;
import java.util.TreeSet;

public class TreeSetNumbersBox extends SetNumberBox
{
	@Override
	protected Collection<Integer> initCollection()
	{
		return new TreeSet<>();
	}

	@Override
	public int removeNumbersInRange(int from, int to)
	{
		int oldSize = size();
		((TreeSet<Integer>) data).subSet(from, true, to, true).clear();

		return oldSize - data.size();
	}
}
