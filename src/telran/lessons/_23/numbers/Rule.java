package telran.lessons._23.numbers;

import telran.lessons._23.exceptions.RuleException;

public interface Rule
{
	void checkRule(int number, int min, int max) throws RuleException;
}
