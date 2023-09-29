package Week06.FinalProject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class warAdvanced extends warBase implements cardGame {
	
	@Override
	public void play() {
		int roundCounter = 1;
		while((player1.handSize() > 0 && player2.handSize() > 0) && roundCounter < 10000) {
			System.out.println("-------------------------------------");
			System.out.println("Round " + roundCounter);
			System.out.println("-------------------------------------");
			round();
			displayScore();
			
			roundCounter++;
		}
		
		System.out.println("Game has concluded...");
	}

	@Override
	public void round() {
		
		List<Card> currentRoundCards = new ArrayList<>();
		recursiveRound(currentRoundCards);
		
	}
	
	private void recursiveRound(List<Card> currentRoundCards) {
		if(currentRoundCards.size() > 1) {
			List<Card> declareWarCards = declareWar();
			for(Card card : declareWarCards) {
				currentRoundCards.add(card);
			}
		}
		
		Card player1Card = player1.flip();
		Card player2Card = player2.flip();
		
		System.out.println(String.format("[%s]", player1.getName()));
		player1Card.describe();
		System.out.println(String.format("[%s]", player2.getName()));
		player2Card.describe();
		
		currentRoundCards.add(player1Card);
		currentRoundCards.add(player2Card);
		
		if(player1Card.getValue() == player2Card.getValue()) {
			System.out.println("Draw - each play another card");
			recursiveRound(currentRoundCards);
		} else if(player1Card.getValue() > player2Card.getValue()) {
			System.out.println("Hand goes to " + player1.getName());
			player1.pickUp(currentRoundCards);
		} else {
			System.out.println("Hand goes to " + player2.getName());
			player2.pickUp(currentRoundCards);
		}
	}
	
	private List<Card> declareWar() {
		// Each player plays 3 cards face down and then flips the forth
		List<Card> declareWarCards = new ArrayList<>();
		for(int i = 0; i < 3; i++) {
			if(player1.handSize() > 1)
				declareWarCards.add(player1.flip());
			
			if(player2.handSize() > 1)
				declareWarCards.add(player2.flip());
		}
		System.out.println("I... Declare... War!");
		return declareWarCards; 
	}
	
//	private boolean enoughToDeclareWar(Player player) {
//		if(player.handSize() <= 3) {
//			return false;
//		}
//		return true;
//	}

	@Override
	public void displayWinner() {
		// TODO Auto-generated method stub
		if(player1.handSize() == 0 || player2.handSize() == 0) {
			if(player1.handSize() == 0) {
				System.out.println(player2.getName() + " won!");
			} else {
				System.out.println(player1.getName() + " won!");
			}
		} else {
			if(player1.handSize() == player2.handSize()) {
				System.out.println("A DRAW!");
			} else if (player1.handSize() > player2.handSize()) {
				System.out.println(player2.getName() + " won!");
			} else {
				System.out.println(player1.getName() + " won!");
			}
		}
	}

	@Override
	public void displayScore() {
		System.out.println(String.format("%s [%s cards] vs [%s cards] %s", player1.getName(), player1.handSize(), 
				player2.handSize(), player2.getName()));
	}

}
