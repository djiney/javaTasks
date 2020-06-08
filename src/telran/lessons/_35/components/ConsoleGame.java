package telran.lessons._35.components;

import java.util.Scanner;

public class ConsoleGame extends Game
{
	public void play()
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
			logger.prompt();
			process(scanner.nextLine());
		}
	}
}
