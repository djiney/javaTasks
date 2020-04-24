package telran.lessons._23.numbers;

import telran.lessons._23.exceptions.RuleException;

public class Generator
{
	int min;
	int max;

	Rule rule;

	public Rule getRule()
	{
		return rule;
	}

	public void setRule(Rule rule)
	{
		this.rule = rule;
	}

	public Generator(int min, int max, Rule rule)
	{
		if (max < min) {
			throw new IllegalArgumentException();
		}

		this.min = min;
		this.max = max;
		this.rule = rule;
	}

	public int[] generate(int count)
	{
		int[] result = new int[count];
		int number, index = 0;

		while (index < count) {
			number = (int) (max * Math.random());
			try {
				rule.checkRule(number, min, max);
				result[index++] = number;
			} catch (RuleException ignored) {}
		}

		return result;
	}
}
