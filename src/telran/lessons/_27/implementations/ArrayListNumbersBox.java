package telran.lessons._27.implementations;

import java.util.ArrayList;
import java.util.Collection;

public class ArrayListNumbersBox extends ListNumberBox
{
	@Override
	protected Collection<Integer> initCollection()
	{
		return new ArrayList<>();
	}
}
