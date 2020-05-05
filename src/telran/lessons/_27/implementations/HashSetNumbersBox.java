package telran.lessons._27.implementations;

import java.util.Collection;
import java.util.HashSet;

public class HashSetNumbersBox extends SetNumberBox
{
	@Override
	protected Collection<Integer> initCollection()
	{
		return new HashSet<>();
	}
}
