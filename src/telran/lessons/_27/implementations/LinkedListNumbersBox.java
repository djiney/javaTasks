package telran.lessons._27.implementations;

import java.util.Collection;
import java.util.LinkedList;

public class LinkedListNumbersBox extends ListNumberBox
{
	@Override
	protected Collection<Integer> initCollection()
	{
		return new LinkedList<>();
	}
}
