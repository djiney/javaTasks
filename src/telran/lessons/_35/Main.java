package telran.lessons._35;

import telran.lessons._35.components.Game;

import java.io.FileNotFoundException;

public class Main
{
	public static void main(String[] args) throws FileNotFoundException
	{
		Game game = new Game();
//		game.enableDebug();
		game.play();
	}
}
