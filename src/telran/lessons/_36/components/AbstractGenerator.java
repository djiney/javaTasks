package telran.lessons._36.components;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractGenerator<T> implements Generator<T>
{
	protected final String BASE_FILE_PATH = "src\\telran\\lessons\\_36\\data\\";
	private final SecureRandom random = new SecureRandom();

	public String getFileName()
	{
		return BASE_FILE_PATH + getName();
	}

	public List<T> generate()
	{
		return Stream.generate(this::generateModel)
			.distinct()
			.limit(getCount())
			.collect(Collectors.toList());
	}

	protected abstract long getCount();
	protected abstract String getName();
	protected abstract T generateModel();

	protected int getChance()
	{
		return getRandomNumber(0, 100);
	}

	protected int getRandomNumber(int min, int max)
	{
		return (int) (min + Math.random() * (max - min));
	}

	public <E extends Enum<?>> E randomEnum(Class<E> enumClass){
		int x = random.nextInt(enumClass.getEnumConstants().length);
		return enumClass.getEnumConstants()[x];
	}
}
