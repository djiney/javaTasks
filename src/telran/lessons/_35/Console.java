package telran.lessons._35;

import telran.lessons._35.components.ConsoleGame;

public class Console
{
	public static void main(String[] args)
	{
		ConsoleGame game = new ConsoleGame();
		if (args.length > 0) {
			game.enableDebug();
		}

		game.play();
	}
}
