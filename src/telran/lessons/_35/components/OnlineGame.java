package telran.lessons._35.components;

import telran.lessons._37.interfaces.GuessGame;

public class OnlineGame extends Game implements GuessGame
{
	@Override
	public String startGame()
	{
		solved = false;

		generateNumber();
		String value = getNumberValue();

		logger = new Logger(true);
		logger.setNumberValue(value, false);

		System.out.println("New game initiated: " + value);

		return value;
	}

	@Override
	public String prompt()
	{
		return logger.prompt();
	}

	@Override
	public String move(String value)
	{
		process(value);

		if (isSolved()) {
			logger.logResults();
			logger.save();
		}

		return logger.peak();
	}

	@Override
	public boolean isFinished()
	{
		return isSolved();
	}
}
