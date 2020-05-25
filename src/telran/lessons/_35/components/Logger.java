package telran.lessons._35.components;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Logger
{
	private final static String FILE_PATH = "src\\telran\\lessons\\_35\\logs\\";

	PrintStream writer;
	List<LogEntry> log;
	String numberValue;

	public Logger()
	{
		log = new ArrayList<>();
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

	public void printMessage()
	{
		log("\nEnter the number:");
	}

	public void logResults()
	{
		log(
			"The game is solved in %d turns, correct number is: %s",
			log.size(),
			numberValue
		);
	}

	public void addLog(LogEntry entry)
	{
		log.add(entry);
		printLog();
	}

	public void save() throws FileNotFoundException
	{
		writer = new PrintStream(getFileName());
		writer.println("Number set: " + numberValue);
		log.forEach(writer::println);
		writer.println("Total turns: " + log.size());
		writer.close();
	}

	private String getFileName()
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

	private void printLog()
	{
		log.forEach(System.out::println);
	}

	private void log(String format, Object ... values)
	{
		System.out.printf(format, values);
	}
}
