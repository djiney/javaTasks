package telran.lessons._27.tests;

import telran.lessons._27.implementations.TreeSetHeadTailNumbersBox;
import telran.lessons._27.implementations.TreeSetNumbersBox;
import telran.lessons._27.tests.performance.SubsetPerformance;

public class Performance
{
	public static void main(String[] args)
	{
		int runsAmount = 10000;
		int numbersAmount = 10000;

		performanceTest(runsAmount, numbersAmount);
	}

	private static void performanceTest(int runsAmount, int numbersAmount)
	{
		SubsetPerformance subset = new SubsetPerformance(
			"Subset", runsAmount, new TreeSetNumbersBox(), numbersAmount
		);

		SubsetPerformance headTail = new SubsetPerformance(
			"HeadTail", runsAmount, new TreeSetHeadTailNumbersBox(), numbersAmount
		);

		subset.run();
		headTail.run();
	}
}
