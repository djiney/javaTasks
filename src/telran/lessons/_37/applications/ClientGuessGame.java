package telran.lessons._37.applications;

import telran.lessons._37.components.Client;
import telran.lessons._37.interfaces.GuessGame;

public class ClientGuessGame implements GuessGame
{
	private final Client client;

	public ClientGuessGame(Client client)
	{
		this.client = client;
	}

	@Override
	public String startGame()
	{
		return client.send("start");
	}

	@Override
	public String prompt()
	{
		return client.send("prompt");
	}

	@Override
	public String move(String userInput)
	{
		return client.send("move#" + userInput);
	}

	@Override
	public boolean isFinished()
	{
		return client.send("is_finished").equals("true");
	}
}
