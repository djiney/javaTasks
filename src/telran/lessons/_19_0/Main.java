package telran.lessons._19_0;

import telran.lessons._19_0.components.JoinStringsImplBuilder;
import telran.lessons._19_0.components.JoinStringsImplString;
import telran.lessons._19_0.tests.JoinStringsPerformance;

public class Main
{
	public static void main(String[] args)
	{
		int runsAmount = 1000;
		int stringsAmount = 1000;

		JoinStringsPerformance concatenationTest = new JoinStringsPerformance("Concatenation", runsAmount, new JoinStringsImplString(), stringsAmount);
		JoinStringsPerformance builderTest = new JoinStringsPerformance("Builder", runsAmount, new JoinStringsImplBuilder(), stringsAmount);

		concatenationTest.run();
		builderTest.run();
	}
}
