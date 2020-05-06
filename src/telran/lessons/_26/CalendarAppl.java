package telran.lessons._26;

import java.time.*;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class CalendarAppl
{
	private static final Locale locale = Locale.UK;
	private static final int columnWidth = 4;

	private static CalendarData data;

	public static void main(String[] args)
	{
		try {
			data = new CalendarData(args);
			printCalendar();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void printCalendar()
	{
		printTitle();
		printDaysOfWeek();
		printDates();
	}

	private static void printDates()
	{
		int firstDayOfWeek = getFirstDayOfWeek() % 7;
		int lastDay = getLastDay();

		printOffset((firstDayOfWeek - 1) * columnWidth);
		for (int day = 1; day <= lastDay; day++)
		{
			System.out.printf("%" + columnWidth + "d", day);
			if (firstDayOfWeek++ == 7) {
				firstDayOfWeek = 1;
				System.out.println();
			}
		}
	}

	private static int getFirstDayOfWeek()
	{
		LocalDate date = LocalDate.of(data.getYear(), data.getMonth(), 1);
		return date.getDayOfWeek().getValue() - data.getWeekDay() + 8;
	}

	private static int getLastDay()
	{
		return YearMonth.of(data.getYear(), data.getMonth()).lengthOfMonth();
	}

	private static void printOffset(int offset)
	{
		String spaces = " ".repeat(offset);
		System.out.print(spaces);
	}

	private static void printDaysOfWeek()
	{
		printOffset(columnWidth / 2);

		List<DayOfWeek> values = Arrays.asList(DayOfWeek.values());

		if (data.getWeekDay() > 1) {
			Collections.rotate(values, 8 - data.getWeekDay());
		}

		for (DayOfWeek dayOfWeek : values) {
			System.out.print(dayOfWeek.getDisplayName(TextStyle.SHORT, locale) + " ");
		}

		System.out.println();
	}

	private static void printTitle()
	{
		System.out.printf(
			"\t%s, %d\n",
			Month.of(data.getMonth()).getDisplayName(TextStyle.FULL, locale),
			data.getYear()
		);
	}
}
