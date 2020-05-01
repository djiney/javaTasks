package telran.lessons._23.numbers;

import telran.lessons._23.exceptions.RangeException;
import telran.lessons._23.exceptions.RuleException;

public class PrimeNumbersRule implements Rule
{
	@Override
	public void checkRule(int number, int min, int max) throws RuleException
	{
		min = min < 1 ? 2 : adjustNumber(min, true);
		max = adjustNumber(max, false);

		if (min > max) {
			throw new RangeException("Impossible range");
		}

		int delta = 0;

		if (number < min) {
			delta = min - number ;
		} else if (number > max) {
			delta = max - number;
		} else if (isPrime(number)) {
			return;
		} else {
			int decreasedNumber = number;
			int increasedNumber = number;

			while (decreasedNumber > min || increasedNumber < max) {
				if (decreasedNumber > min && isPrime(--decreasedNumber)) {
					delta = decreasedNumber - number;
					break;
				}

				if (increasedNumber < max && isPrime(++increasedNumber)) {
					delta = increasedNumber - number;
					break;
				}
			}
		}

		if (delta != 0) {
			throw new RuleException(delta);
		}
	}

	private int adjustNumber(int number, boolean increase)
	{
		while (number > 2 && !isPrime(number)) {
			if (increase) {
				number++;
			} else {
				number--;
			}
		}

		return number;
	}

	private boolean isPrime(int number)
	{
		if (number < 1) {
			return false;
		}

		for (int i = 2; i <= number / 2; i++) {
			if (number % i == 0) {
				return false;
			}
		}

		return true;
	}
}
