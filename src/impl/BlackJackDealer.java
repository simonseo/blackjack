package impl;

import java.util.ArrayList;
import java.util.List;

import api.Card;
import api.Card.Suit;
import api.Card.Value;
import api.Dealer;
import api.Hand;
import api.Player;

public class BlackJackDealer extends BlackJackPlayer implements Dealer {
	private List<Card> deck;
	
	public BlackJackDealer(int deckCount) {
		super();
		deck = new ArrayList<Card>();
		for (int i = 0; i < deckCount; i++) {
			for (Suit s : Card.Suit.values()) {
				for (Value v : Card.Value.values()) {
					deck.add(new Card(v, s));
				}
			}
		}
		this.shuffle();
		
	}

	@Override
	public void dealCard(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collectCards(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Hand getHand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void shuffle() {
		// TODO Auto-generated method stub
		
	}
		
}