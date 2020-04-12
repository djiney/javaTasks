package telran.lessons._20.tests;

import telran.common.interfaces.IndexedList;
import telran.lessons._19.tests.PerformanceTest;

public class IndexedListOperations extends PerformanceTest
{
	int getProbability;
	IndexedList<Integer> list;
	int numbersAmount;

	public IndexedListOperations(String testName, Integer runsAmount, IndexedList<Integer> list, int getProbability, int numbersAmount)
	{
		super(testName + ", probability: " + getProbability, runsAmount);

		this.getProbability = getProbability;
		this.list = list;
		this.numbersAmount = numbersAmount;

		fillList();
	}

	private void fillList()
	{
		for (int i = 0; i < numbersAmount; i++) {
			list.add(100);
		}
	}

	@Override
	protected void runTest()
	{
		if (getProbability > getChance(100)) {
			runGetAtRandomIndex();
		} else {
			runRemoveAddFirst();
		}
	}

	private void runRemoveAddFirst()
	{
		list.remove(0);
		list.add(0, 100);
	}

	private void runGetAtRandomIndex()
	{
		callRandomGet();
		callRandomGet();
	}

	private void callRandomGet()
	{
		list.get(getChance(numbersAmount));
	}

	private int getChance(int multiplier)
	{
		return (int) (multiplier * Math.random());
	}
}
