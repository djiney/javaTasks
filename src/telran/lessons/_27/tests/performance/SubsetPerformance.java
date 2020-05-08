package telran.lessons._27.tests.performance;

import telran.lessons._19_0.tests.PerformanceTest;
import telran.lessons._27.implementations.SetNumberBox;

public class SubsetPerformance extends PerformanceTest
{
	final SetNumberBox set;
	final int numbersCount;

	public SubsetPerformance(String testName, Integer runsAmount, SetNumberBox set, int numbersCount)
	{
		super(testName, runsAmount);
		this.set = set;
		this.numbersCount = numbersCount;

		fillSet();
	}

	private void fillSet()
	{
		for (int i = 0; i < numbersCount; i++) {
			set.addNumber(i);
		}
	}

	@Override
	protected void runTest()
	{
		set.removeNumbersInRange(numbersCount / 2 - 1, numbersCount / 2 + 1);
	}
}
