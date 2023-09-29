package Week06.FinalProject;

import java.util.Scanner;

public class App {
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Would you like to play a game?");
		System.out.println("Enter your name:");
		String playerName = scanner.next();
		
		var warTheGame = new warAdvanced();
		warTheGame.deal(playerName);
		warTheGame.play();
		warTheGame.displayWinner();
	}
}
