package telran.lessons._19_1;

import telran.common.Array;
import telran.lessons._09.IndexedLinkedList;
import telran.lessons._19_1.tests.IndexedListOperations;

public class Main
{
	public static void main(String[] args)
	{
		int runsAmount = 10000;
		int numbersAmount = 100000;

		for (int i = 0; i <= 5; i++) {
			performanceTest(i * 20, runsAmount, numbersAmount);
		}
	}

	private static void performanceTest(int probability, int runsAmount, int numbersAmount)
	{
		IndexedListOperations arrayTest = new IndexedListOperations(
			"Array", runsAmount, new Array<>(), probability, numbersAmount
		);
		IndexedListOperations linkedTest = new IndexedListOperations(
			"LinkedList", runsAmount, new IndexedLinkedList<>(), probability, numbersAmount
		);

		arrayTest.run();
		linkedTest.run();
	}
}
