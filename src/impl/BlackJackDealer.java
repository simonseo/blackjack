package impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import api.Card;
import api.Card.Suit;
import api.Card.Value;
import strategy.HitBlackJackDealer;
import strategy.HitStrategy;
import api.Dealer;
import api.Player;

public class BlackJackDealer extends BlackJackPlayer implements Dealer {
	private List<Card> deck;
	private int deckCount;
	private HitStrategy hitStrategy;
	
	public BlackJackDealer(int deckCount) {
		super("Dealer");
		this.deckCount = deckCount;
		this.deck = this.createDeck(deckCount);
		this.shuffle();	
		this.setHitStrategy(new HitBlackJackDealer());
	}
	
	public Player setHitStrategy(HitStrategy hs) {
		this.hitStrategy = hs;
		return this;
	}
	
	private List<Card> createDeck(int deckCount) {
		deck = new ArrayList<Card>();
		for (int i = 0; i < deckCount; i++) {
			for (Suit s : Card.Suit.values()) {
				for (Value v : Card.Value.values()) {
					this.deck.add(new Card(v, s));
				}
			}
		}
		return deck;
	}

	@Override
	public void dealCard(Player player) {
		Card card = this.deck.remove(0);
		player.receive(card);
	}

	@Override
	public void collectCards(Player player) {
		// we don't use the relinquished cards. 
		// just throw it away to garbage collector and make a new deck if necessary
		player.relinquishCards();
		if (this.deck.size() < 0.50 * this.deckCount * 52) {
			// create new deck if deck has 50% of cards left
			System.out.println("Deck is not big enough, refilling deck.");
			this.deck = this.createDeck(this.deckCount);
			this.shuffle();
		}
	}

	@Override
	public void shuffle() {
		Collections.shuffle(this.deck);
	}
	
	@Override
	public boolean requestCard() {
		return this.hitStrategy.hit(getMoney(), getHand());
	}
}