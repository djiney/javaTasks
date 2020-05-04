package telran.lessons._25;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeOperationsAppl
{
	public static void main(String[] args)
	{
		LocalDate current = LocalDate.now();
		System.out.println(current.with(new WorkingDays(4, new DayOfWeek[]{DayOfWeek.SATURDAY, DayOfWeek.SUNDAY})));

		displayTimezoneId("Canada");

		displayCompleteAge("1799-06-06");
		displayCompleteAge("06/06/1799");
		displayCompleteAge("06.06.1799"); // Easy to implement
	}

	public static void displayCompleteAge(String birthDateString)
	{
		LocalDate birthDate = parseDate(birthDateString);
		LocalDate startDate = LocalDate.now();

		Period period = Period.between(birthDate, startDate);

		System.out.printf(
			"Years: %d\nMonths: %d\nDays: %d\n",
			period.getYears(),
			period.getMonths(),
			period.getDays()
		);
	}

	private static LocalDate parseDate(String value)
	{
		String[] formats = {"yyyy-MM-dd", "dd/MM/yyyy"};
		int index = 0;

		LocalDate birthDate = null;
		while (birthDate == null) {
			try {
				birthDate = LocalDate.parse(value, DateTimeFormatter.ofPattern(formats[index++]));
			} catch (DateTimeParseException e) {
				if (index >= formats.length) {
					throw e;
				}
			}
		}

		return birthDate;
	}

	public static void displayTimezoneId(String zonePattern)
	{
		ZoneId.getAvailableZoneIds()
			.stream()
			.filter((z) -> z.toLowerCase().contains(zonePattern.toLowerCase()))
			.forEach(System.out::println);
	}
}
