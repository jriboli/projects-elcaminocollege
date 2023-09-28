package Week06.FinalProject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Deck {

	private final List<String> faceValues = new ArrayList<>(Arrays.asList("two", "three", "four", "five", "six", "seven",
			"eight", "nine", "ten", "jack", "queen", "king", "ace"));
	private final List<String> suiteValues = new ArrayList<>(Arrays.asList("hearts", "clubs", "diamonds", "spades"));
	
	private Random rand = new Random();	
	private List<Card> cards = new ArrayList<>();
	
	public Deck() {
		// create deck - 52 cards
	}
	
	public void shuffle() {
		List<Card> shuffledCards = new ArrayList<>();
		
		while(shuffledCards.size() < cards.size()) {
			int rand_int = rand.nextInt(cards.size());
			Card randCard = cards.get(rand_int);
			if(!checkIfExists(randCard, shuffledCards)) {
				shuffledCards.add(randCard);
			}
		}
		
		cards = shuffledCards;
	}
	
	public Card draw() {
		Card drawn = cards.get(0);
		cards.remove(0);
		
		return drawn;
	}
	
	private boolean checkIfExists(Card c, List<Card> cards) {
		for(Card card : cards) {
			if(c.equals(card)) {
				return true;
			}
		}
		return false;
	}
	
	private void initializeDeck() {
		for(String suite : suiteValues) { 
			for(String face : faceValues) {
				cards.add(new Card(suite, face));
			}
		}
		
	}
}
