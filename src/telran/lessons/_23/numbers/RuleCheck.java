package telran.lessons._23.numbers;

import telran.lessons._23.exceptions.RuleException;

@FunctionalInterface
public interface RuleCheck {
	void run() throws RuleException;
}