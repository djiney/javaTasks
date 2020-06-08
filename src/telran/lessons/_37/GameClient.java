package telran.lessons._37;

import telran.lessons._37.applications.ClientGuessGame;
import telran.lessons._37.components.Client;
import telran.lessons._37.interfaces.GuessGame;

import java.util.Scanner;

public class GameClient extends Client
{
	private static final String HOST = "localhost";
	private static final int PORT = 4000;

	boolean debug = false;
	GuessGame game;
	Scanner scanner;

	public GameClient(String host, int port, boolean debug)
	{
		super(host, port);

		game = new ClientGuessGame(this);
		scanner = new Scanner(System.in);

		this.debug = debug;
	}

	public static void main(String[] args)
	{
		Client client = new GameClient(HOST, PORT, args.length > 0);
		client.init();
	}

	@Override
	protected void run()
	{
		String answer = game.startGame();
		if (debug) {
			System.out.println(answer);
		}

		do {
			System.out.println(game.prompt());
			System.out.println(game.move(scanner.nextLine()));
		} while (!game.isFinished());
	}

	@Override
	protected boolean shouldRun()
	{
		System.out.println("Start a new game? y/n");
		return scanner.nextLine().equals("y");
	}
}
