package Week06.FinalProject;

import java.util.ArrayList;
import java.util.List;

public class Player {
	
	private List<Card> hand = new ArrayList<>();
	private int score;
	private String name;
	
	public Player(String name, List<Card> hand) {
		this.name = name;
		this.hand = hand;
	}
	
	public void describe() {
		System.out.println("Player - " + name);
		for(Card card : hand) {
			card.describe();
		}
	}
	
	public Card flip() {
		Card flipped = hand.get(0);
		hand.remove(0);
		
		return flipped;
	}
	
	public void draw(Deck deck) {
		hand.add(deck.draw());
	}
	
	public void incrementScore() {
		score++; 
	}

}
