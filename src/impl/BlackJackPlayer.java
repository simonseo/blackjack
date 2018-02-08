package impl;

import api.Card;
import api.Hand;
import api.Player;

public class BlackJackPlayer implements Player {
	private Hand hand;
	private int wallet;
	private String name;
	private int defaultWager;
	private boolean isPlaying;
	private boolean isBlackjack;

	public BlackJackPlayer() {
		this.hand = new BlackJackHand();
		this.wallet = 100;
		this.name = "John Doe";
		this.defaultWager = 5;
	}

	public BlackJackPlayer(String name) {
		this();
		this.name = name;
	}

	public BlackJackPlayer(String name, int startingMoney) {
		this();
		assert startingMoney >= 0;
		this.wallet = startingMoney;
		this.name = name;
	}

	@Override
	public int compareTo(Player o) {
        int nameCompare = this.getName().compareTo(o.getName());
        int handCompare = this.getHand().compareTo(o.getHand());
        int walletCompare = this.getMoney() - o.getMoney();
        
		return nameCompare != 0 ? nameCompare : 
			handCompare != 0 ? handCompare : 
				walletCompare;
	}

	@Override
	public void receive(Card card) {
		this.getHand().addCard(card);
	}

	@Override
	public Hand getHand() {
		return this.hand;
	}

	@Override
	public Hand relinquishCards() {
		Hand rel = this.getHand();
		this.hand = new BlackJackHand();
		return rel;
	}

	@Override
	public int placeWager() {
		assert this.getMoney() > 0;
		int wager = Math.min(this.defaultWager, this.getMoney());
		assert wager > 0;
		this.wallet -= wager;
		assert this.getMoney() >= 0;
		return wager;
	}

	@Override
	public void payOut(int money) {
		this.wallet += money;
	}

	@Override
	public int getMoney() {
		return this.wallet;
	}
	
	public boolean hasMoney() {
		return this.getMoney() > 0;
	}
	
	public void playing(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}
	
	public boolean playing() {
		return this.isPlaying;
	}

	public void blackjack(boolean isBlackjack) {
		this.isBlackjack = isBlackjack;
	}
	
	public boolean blackjack() {
		return this.isBlackjack;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public boolean requestCard() {
		if (!this.getHand().isValid()) return false;
		int value = this.getHand().valueOf();
		switch (value) {
			case 21:
			case 20:
			case 19:
			case 18:
				return false;
			case 17:
			case 16:
				return ((BlackJackHand) this.getHand()).hasAce() ? true : false;
			default:
				return true;
		}
	}

	@Override
	public int numberOfCards() {
		return this.getHand().getCards().size();
	}

}