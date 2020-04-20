package telran.lessons._21;

import telran.lessons._20.RegularExpressions;

public class Calculator
{
	static public double calculate(String expression)
	{
		if (!expression.matches(RegularExpressions.arithmeticExpression())) {
			return Double.NaN;
		}

		String[] numbers = expression.trim().split("[-+/* ]+");
		String[] operations = expression.trim().split("[\\d. ]+");

		double result = Double.parseDouble(numbers[0]);

		for (int i = 1; i < numbers.length; i++) {
			result = calculate(result, Double.parseDouble(numbers[i]), operations[i]);

			if (Double.isNaN(result) || result == Double.POSITIVE_INFINITY) {
				break;
			}
		}

		return result;
	}

	private static double calculate(double value, double number, String operation)
	{
		switch (operation) {
			case "/":
				return value / number;
			case "*":
				return value * number;
			case "+":
				return value + number;
			case "-":
				return value - number;
			default:
				return Double.NaN;
		}
	}

	public static boolean checkParenthesis(String expression)
	{
		// Подразумеваем, что в строке нет ничего, кроме скобок
		expression = expression.replaceAll("[^(){}\\[\\]]", "");
		if (expression.length() % 2 != 0) {
			return false;
		}

		String newExpression = "";

		while (true) {
			newExpression = expression.replaceAll("\\(\\)|\\{}|\\[]", "");
			if (expression.equals(newExpression)) {
				break;
			}

			expression = newExpression;
		}

		return expression.length() == 0;
	}
}
