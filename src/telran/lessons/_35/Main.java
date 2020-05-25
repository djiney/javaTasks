package telran.lessons._35;

import telran.lessons._35.components.Game;

import java.io.FileNotFoundException;

public class Main
{
	public static void main(String[] args) throws FileNotFoundException
	{
		Game game = new Game();
		if (args.length > 0) {
			game.enableDebug();
		}

		game.play();
	}
}
