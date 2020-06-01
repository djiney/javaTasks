package telran.lessons._35.components;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class ConsoleGame extends Game
{
	public void play() throws FileNotFoundException
	{
		generateNumber();
		logger.setNumberValue(getNumberValue(), debug);

		scan();

		logger.logResults();
		logger.save();
	}

	private void scan()
	{
		Scanner scanner = new Scanner(System.in);

		while (!isSolved()) {
			logger.printMessage();
			process(scanner.nextLine());
		}
	}
}
