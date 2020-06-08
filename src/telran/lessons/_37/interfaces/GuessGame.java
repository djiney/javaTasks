package telran.lessons._37.interfaces;

public interface GuessGame {
	String startGame();
	String prompt();
	String move(String userInput);
	boolean isFinished();
}