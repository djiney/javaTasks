package telran.lessons._35.components;

import java.time.LocalDate;

public class LogEntry
{
	int cows;
	int bulls;
	String number;

	LocalDate date = LocalDate.now();

	public LogEntry(String number)
	{
		this.number = number;
	}

	@Override
	public String toString()
	{
		return String.format("   %s (%d cows, %d bulls)", number, cows, bulls);
	}

	public void addCow()
	{
		cows++;
	}

	public void addBull()
	{
		bulls++;
	}

	public int getBulls()
	{
		return bulls;
	}
}
