package telran.lessons._25;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class WorkingDays implements TemporalAdjuster
{
	int daysNumber;
	DayOfWeek[] daysOff;

	public WorkingDays(int daysNumber, DayOfWeek[] daysOff)
	{
		this.daysNumber = daysNumber;
		this.daysOff = daysOff;
	}

	@Override
	public Temporal adjustInto(Temporal temporal)
	{
		if (daysNumber == 0) {
			return temporal;
		}

		temporal = temporal.plus(1, ChronoUnit.DAYS);
		int days = daysNumber;

		while (days > 0) {
			temporal = temporal.plus(1, ChronoUnit.DAYS);
			if (!isDayOff(temporal.get(ChronoField.DAY_OF_WEEK))) {
				days--;
			}
		}

		return temporal;
	}

	private boolean isDayOff(int day)
	{
		if (daysOff.length == 0) {
			return false;
		}

		for (DayOfWeek dayOff : daysOff) {
		    if (day == dayOff.ordinal()) {
		    	return true;
		    }
		}

		return false;
	}
}
