package telran.lessons._21;

import telran.lessons._21.tests.RegexPerformanceTest;

public class Main
{
	public static void main(String[] args)
	{
		int runsAmount = 1000000;
		String string = "abcd%&88888888888888888888888888888888888888888t12g*";

		performanceTest(string, runsAmount);
	}

	private static void performanceTest(String string, int runsAmount)
	{
		RegexPerformanceTest first = new RegexPerformanceTest(
			"First", runsAmount, "[^\\p{Alpha}]", string
		);
		RegexPerformanceTest second = new RegexPerformanceTest(
			"Second", runsAmount, "[^\\p{Alpha}]*", string
		);

		first.run();
		second.run();
	}
}
