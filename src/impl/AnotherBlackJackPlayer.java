package impl;

import api.Card;
import api.Hand;
import api.Player;

public class AnotherBlackJackPlayer implements Player {
	Hand hand;
	int wallet;
	String name;
	int defaultWager;

	public AnotherBlackJackPlayer() {
		this.hand = new BlackJackHand();
		this.wallet = 100;
		this.name = "Jane Doe";
		this.defaultWager = 5;
	}

	public AnotherBlackJackPlayer(String name) {
		this();
		this.name = name;
	}

	public AnotherBlackJackPlayer(String name, int startingMoney) {
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

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public boolean requestCard() {
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