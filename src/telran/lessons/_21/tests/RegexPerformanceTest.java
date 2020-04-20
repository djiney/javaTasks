package telran.lessons._21.tests;

import telran.lessons._19_0.tests.PerformanceTest;

public class RegexPerformanceTest extends PerformanceTest
{
	String regex;
	String string;

	public RegexPerformanceTest(String testName, Integer runsAmount, String regex, String string)
	{
		super(testName, runsAmount);
		this.regex = regex;
		this.string = string;
	}

	@Override
	protected void runTest()
	{
		//noinspection ResultOfMethodCallIgnored
		string.replaceAll(regex, "");
	}
}
