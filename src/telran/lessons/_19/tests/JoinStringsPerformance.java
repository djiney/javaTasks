package telran.lessons._19.tests;

public class JoinStringsPerformance extends PerformanceTest
{
	public final String STRING_VALUE = "Hello, world";

	String[] strings;
	JoinStrings joinStrings;

	public JoinStringsPerformance(String testName, Integer runsAmount, JoinStrings joinStrings, int stringsCount)
	{
		super(testName, runsAmount);
		this.joinStrings = joinStrings;
		generateStrings(stringsCount);
	}

	@Override
	protected void runTest()
	{
		joinStrings.join(strings, "");
	}

	private void generateStrings(int count)
	{
		strings = new String[count];
		for (int i = 0; i < count; i++) {
			strings[i] = STRING_VALUE;
		}
	}
}
