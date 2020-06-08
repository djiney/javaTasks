package telran.lessons._37;

import telran.lessons._35.components.OnlineGame;
import telran.lessons._37.components.Server;
import telran.lessons._37.interfaces.GuessGame;

public class GameServer extends Server
{
	private static final int PORT = 4000;

	GuessGame game;

	public GameServer(int port)
	{
		super(port);
		game = new OnlineGame();
	}

	public static void main(String[] args)
	{
		Server server = new GameServer(PORT);
		server.init();
	}

	@Override
	protected String parseRequest(String request)
	{
		String[] data = request.split("#");

		String command = data[0];
		String payload = data.length > 1 ? data[1] : null;

		return switch (command)
			{
				case "start"
					-> game.startGame();

				case "prompt"
					-> game.prompt();

				case "move"
					-> game.move(payload);

				case "is_finished"
					-> game.isFinished() ? "true" : "false";

				default
					-> "Unknown request";
		};
	}
}
