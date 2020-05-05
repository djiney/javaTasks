package telran.lessons._27;

public interface NumbersBox extends Iterable<Integer>
{
	void addNumber(int number);

	void removeNumber(int number);

	int removeRepeated();

	int removeNumbersInRange(int from, int to);

	int size();
}
