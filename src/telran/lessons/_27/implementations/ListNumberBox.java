package telran.lessons._27.implementations;

import java.util.Collection;

public abstract class ListNumberBox extends BaseNumberBox
{
	@Override
	public int removeRepeated()
	{
		Collection<Integer> oldData = data;
		data = initCollection();

		oldData.stream()
			.distinct()
			.forEach(data::add);

		return oldData.size() - size();
	}
}
