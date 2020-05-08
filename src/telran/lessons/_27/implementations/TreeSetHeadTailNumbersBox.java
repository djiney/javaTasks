package telran.lessons._27.implementations;

import java.util.Collection;
import java.util.TreeSet;

public class TreeSetHeadTailNumbersBox extends SetNumberBox
{
	@Override
	protected Collection<Integer> initCollection()
	{
		return new TreeSet<>();
	}

	@Override
	public int removeNumbersInRange(int from, int to)
	{
		TreeSet<Integer> oldData = (TreeSet<Integer>) data;

		data = initCollection();
		data.addAll(oldData.headSet(from, false));
		data.addAll(oldData.tailSet(to, false));

		return oldData.size() - data.size();
	}
}
