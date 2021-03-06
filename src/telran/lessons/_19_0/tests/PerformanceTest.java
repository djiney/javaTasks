package telran.lessons._19_0.tests;

public abstract class PerformanceTest
{
	private final String testName;
	private final Integer runsAmount;

	public PerformanceTest(String testName, Integer runsAmount) {
		this.testName = testName;
		this.runsAmount = runsAmount;
	}

	public void run()
	{
		double startTime = System.currentTimeMillis();

		for (int i = 0; i < runsAmount; i++) {
			runTest();
		}

		long endTime = System.currentTimeMillis();

		System.out.format(
			"Test %s [total runs: %d] performed in %d milliseconds",
			testName,
			runsAmount,
			(int) (endTime - startTime)
		);
		System.out.println();
	}

	protected abstract void runTest();
}
