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
}
