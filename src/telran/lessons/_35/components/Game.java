package telran.lessons._35.components;

import java.io.FileNotFoundException;
import java.util.*;

public class Game
{
	static final int NUMBER_LENGTH = 4;

	boolean debug = false;
	boolean solved = false;

	Map<Integer, Integer> numberMap = new HashMap<>();
	Logger logger;

	public Game()
	{
		logger = new Logger();
	}

	public void enableDebug()
	{
		debug = true;
	}

	public void play() throws FileNotFoundException
	{
		generateNumber();
		logger.setNumberValue(getNumberValue(), debug);

		scan();

		logger.logResults();
		logger.save();
	}

	private void generateNumber()
	{
		new Random().ints(1, 10)
			.distinct()
			.limit(NUMBER_LENGTH)
			.forEach(n -> numberMap.put(n, numberMap.size()));
	}

	private String getNumberValue()
	{
		StringBuilder result = new StringBuilder();
		numberMap.entrySet().stream()
			.sorted(Map.Entry.comparingByValue())
			.forEach(e -> result.append(e.getKey()));

		return result.toString();
	}

	private void scan()
	{
		Scanner scanner = new Scanner(System.in);

		while (!isSolved()) {
			logger.printMessage();
			process(scanner.nextLine());
		}
	}

	private void process(String value)
	{
		int number, position;
		LogEntry entry = new LogEntry(value);

		for (int i = 0; i < NUMBER_LENGTH; i++)
		{
			if (i >= value.length()) {
				break;
			}

			number = Character.getNumericValue(value.charAt(i));
			if (!numberMap.containsKey(number)) {
				continue;
			}

			position = numberMap.get(number);
			if (position == i) {
				entry.addBull();
			} else {
				entry.addCow();
			}
		}

		logger.addLog(entry);
		if (entry.getBulls() == NUMBER_LENGTH) {
			solved = true;
		}
	}

	private boolean isSolved()
	{
		return solved;
	}
}
