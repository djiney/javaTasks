package telran.lessons._23.numbers;

import telran.lessons._23.exceptions.RangeException;
import telran.lessons._23.exceptions.RuleException;

public class DividerRule implements Rule
{
	int divider;

	public DividerRule(int divider)
	{
		this.divider = divider;
	}

	@Override
	public void checkRule(int number, int min, int max) throws RuleException
	{
		min = adjustNumber(min, true);
		max = adjustNumber(max, false);

		if (min > max) {
			throw new RangeException("Impossible range");
		}

		int delta = getDelta(number, min, max);
		if (delta != 0) {
			throw new RuleException(delta);
		}
	}

	private int getDelta(int number, int min, int max)
	{
		if (number > max) {
			return max - number;
		}

		if (number < min) {
			return min - number;
		}

		int delta = -number % divider;
		if (delta == 0) {
			return 0;
		}

		int antiDelta = divider + delta;
		if (-delta < antiDelta) {
			return number + delta >= min ? delta : antiDelta;
		} else {
			return number + antiDelta <= max ? antiDelta : delta;
		}
	}

	private int adjustNumber(int number, boolean isMin)
	{
		int remainder = number % divider;
		if (remainder == 0) {
			return number;
		}

		return number - remainder + (isMin ? divider : 0);
	}
}
