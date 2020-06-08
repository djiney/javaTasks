package telran.lessons._35;

import telran.lessons._35.components.OnlineGame;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Online
{
	public static void main(String[] args)
	{
		OnlineGame game = new OnlineGame();
		game.enableDebug();
		game.startGame();

		String value;

		do {
			value = getRandomGuess();
			game.move(value);
		} while (!game.isFinished());
	}

	private static String getRandomGuess()
	{
		Map<Integer, Integer> numberMap = new HashMap<>();

		new Random().ints(1, 10)
			.distinct()
			.limit(4)
			.forEach(n -> numberMap.put(n, numberMap.size()));

		StringBuilder result = new StringBuilder();
		numberMap.entrySet().stream()
			.sorted(Map.Entry.comparingByValue())
			.forEach(e -> result.append(e.getKey()));

		return result.toString();
	}
}
