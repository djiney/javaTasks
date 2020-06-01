package telran.lessons._35;

import telran.lessons._35.components.ConsoleGame;
import java.io.FileNotFoundException;

public class Console
{
	public static void main(String[] args) throws FileNotFoundException
	{
		ConsoleGame game = new ConsoleGame();
		if (args.length > 0) {
			game.enableDebug();
		}

		game.play();
	}
}
