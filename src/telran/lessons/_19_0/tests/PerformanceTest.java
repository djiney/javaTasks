package telran.lessons._19_0.tests;

public abstract class PerformanceTest
{
	private String testName;
	private Integer runsAmount;

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
			"Test %s [total runs: %d] performed in %f milliseconds",
			testName,
			runsAmount,
			endTime - startTime
		);
		System.out.println();
	}

	protected abstract void runTest();
}
