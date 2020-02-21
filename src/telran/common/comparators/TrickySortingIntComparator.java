package telran.common.comparators;

import java.util.Comparator;
import java.util.Objects;

public class TrickySortingIntComparator implements Comparator<Integer>
{
	@Override
	public int compare(Integer firstNumber, Integer secondNumber)
	{
		if (Objects.equals(firstNumber, secondNumber)) {
			return 0;
		}

		if (this.isOdd(firstNumber) && this.isOdd(secondNumber)) {
			return this.toInt(firstNumber < secondNumber);
		} else if (!this.isOdd(firstNumber) && !this.isOdd(secondNumber)) {
			return this.toInt(firstNumber > secondNumber);
		} else if (this.isOdd(firstNumber) && !this.isOdd(secondNumber)) {
			return -1;
		} else {
			return 1;
		}
	}

	private int toInt(boolean value)
	{
		return value ? -1 : 1;
	}

	private boolean isOdd(Integer number)
	{
		return number % 2 > 0;
	}
}
