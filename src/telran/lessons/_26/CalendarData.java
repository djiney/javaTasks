package telran.lessons._26;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class CalendarData
{
	private int year;
	private int month;

	private int weekDay = 1;

	public CalendarData(String ... args)
	{
		load(args);
		validate();
	}

	public int getYear()
	{
		return year;
	}

	public int getMonth()
	{
		return month;
	}

	public int getWeekDay()
	{
		return weekDay;
	}

	private void validate()
	{
		if (year < 0) {
			throw new RuntimeException("Invalid year: " + year);
		}

		if (month < 1 || month > 12) {
			throw new RuntimeException("Month should be in range [1-12]: " + month);
		}
	}

	private void load(String ... args)
	{
		loadCurrentData();

		switch (args.length) {
			case 3:
				parseWeekDay(args[2]);
			case 2:
				month = parseInt(args[1], "Month should be int");
			case 1:
				year = parseInt(args[0], "Year should be int");
			default:
				break;
		}
	}

	private int parseInt(String value, String errorMessage)
	{
		try {
			return Integer.parseInt(value);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(errorMessage + ": " + value);
		}
	}

	private void parseWeekDay(String value)
	{
		try {
			weekDay = DayOfWeek.valueOf(value.toUpperCase()).getValue();
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("Invalid week day: " + value);
		}
	}

	private void loadCurrentData()
	{
		LocalDate current = LocalDate.now();

		year = current.getYear();
		month = current.getMonthValue();
	}
}
