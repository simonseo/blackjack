package impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import api.Card;
import api.Hand;

public class	BlackJackHand implements Hand {
	public final int BLACKJACK = 21;
	private List<Card> cards = new ArrayList<Card>();
	
	@Override
	public int compareTo(Hand o) {
		int tv = this.valueOf(), ov = o.valueOf();
		assert (long)(tv - ov) == (int)(long)(tv - ov); //check overflow
		return tv - ov;
	}

	@Override
	public void addCard(Card card) {
		this.cards.add(card);
	}

	@Override
	public List<Card> getCards() {
		List<Card> readonlyCards = Collections.unmodifiableList(this.cards);
		return readonlyCards;
	}

	@Override
	public boolean isValid() {
		assert this.valueOf() > 0;
		return this.valueOf() > BLACKJACK ? false : true;
	}

	@Override
	public boolean isWinner() {
		return this.valueOf() == BLACKJACK; 
	}

	@Override
	public int valueOf() {
		// " A hand's value is the sum of the card values. " - Wikipedia
		int handValue = 0, aceCount = 0;
		for (Card c : this.getCards()) {
			if (c.getValue() == Card.Value.ACE) aceCount++;
			handValue += c.getValue().getValue();
		}

		// try to return a "soft hand" that counts ACE as 11
		while (aceCount > 0 && handValue < BLACKJACK) {
			handValue += 10;
			aceCount--;
		}
		assert this.getCards().size() == 0 || handValue > 0;
		return handValue;
	}
	
	public boolean hasAce() {
		for (Card c : this.getCards()) {
			if (c.getValue() == Card.Value.ACE) return true;
		}
		return false;
	}
}