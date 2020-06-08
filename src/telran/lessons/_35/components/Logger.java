package telran.lessons._35.components;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("UnusedReturnValue")
public class Logger
{
	protected final static String FILE_PATH = "src\\telran\\lessons\\_35\\logs\\";

	PrintStream writer;
	List<LogEntry> log;
	String numberValue;

	boolean isSilent = false;

	public Logger(boolean isSilent)
	{
		log = new ArrayList<>();
		this.isSilent = isSilent;
	}

	public Logger()
	{
		this(false);
	}

	public String peak()
	{
		if (log.isEmpty()) {
			return null;
		}

		return log.get(log.size() - 1).toString();
	}

	public void setNumberValue(String numberValue, boolean show)
	{
		this.numberValue = numberValue;

		if (show) {
			log(
				"Debug enabled, the number is %s",
				numberValue
			);
		}
	}

	public String logResults()
	{
		return log(
			"The game is solved in %d turns, correct number is: %s",
			log.size(),
			numberValue
		);
	}

	public String prompt()
	{
		return log("Enter number of %d unrepeated digits [1-9]:", Game.NUMBER_LENGTH);
	}

	public void addLog(LogEntry entry)
	{
		log.add(entry);
		printLog();
	}

	public void save()
	{
		try {
			writer = new PrintStream(getFileName());
		} catch (FileNotFoundException e) {
			return;
		}

		writer.println("Number set: " + numberValue);
		log.forEach(writer::println);
		writer.println("Total turns: " + log.size());
		writer.close();
	}

	protected String getFileName()
	{
		LocalDateTime date = LocalDateTime.now();

		return String.format(
			"%s%d-%d-%d_%d_%d_%d.txt",
			FILE_PATH,
			date.getYear(),
			date.getMonthValue(),
			date.getDayOfMonth(),
			date.getHour(),
			date.getMinute(),
			log.size()
		);
	}

	protected void printLog()
	{
		if (!isSilent) {
			log.forEach(e -> log(e.toString()));
		}
	}

	protected String log(String format, Object ... values)
	{
		String value = String.format(format, values);

		if (!isSilent) {
			System.out.println(value);
		}

		return value;
	}
}
