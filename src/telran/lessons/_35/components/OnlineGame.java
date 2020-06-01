package telran.lessons._35.components;

import java.io.FileNotFoundException;

public class OnlineGame extends Game
{
	public void startGame()
	{
		generateNumber();
		logger.setNumberValue(getNumberValue(), debug);
	}

	public boolean guess(String value)
	{
		logger.printMessage();
		process(value);

		return isSolved();
	}

	public void endGame() throws FileNotFoundException
	{
		logger.logResults();
		logger.save();
	}
}
