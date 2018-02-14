package impl;

import api.Card;
import api.Hand;
import api.Player;
import strategy.BetStrategy;
import strategy.BetUniformAmount;
import strategy.HitRisktakingBlackJackPlayer;
import strategy.HitStrategy;

public class BlackJackPlayer implements Player {
	private Hand hand;
	private int wallet;
	private String name;
	private boolean isPlaying;
	private boolean isBlackjack;
	private BetStrategy betStrategy;
	private HitStrategy hitStrategy;

	public BlackJackPlayer() {
		this.hand = new BlackJackHand();
		this.wallet = 100;
		this.name = "John Doe";
		this.setBetStrategy(new BetUniformAmount(5));
		this.setHitStrategy(new HitRisktakingBlackJackPlayer());
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
	
	public Player setBetStrategy(BetStrategy bs) {
		this.betStrategy = bs;
		return this;
	}
	
	public Player setHitStrategy(HitStrategy hs ) {
		this.hitStrategy = hs;
		return this;
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
		int wager = this.betStrategy.bet(this.getMoney());
		assert wager > 0 || this.getMoney() == 0;
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
		if (isBlackjack) {
			System.out.println(this.getName() + " got a BLACKJACK!!!!!!!!!!!!!! CONGRATS!!!");
		}
	}
	
	public boolean blackjack() {
		return this.isBlackjack;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return this.name + " (" + this.getMoney() + ")";
	}

	@Override
	public boolean requestCard() {
		return this.hitStrategy.hit(getMoney(), getHand());
	}

	@Override
	public int numberOfCards() {
		return this.getHand().getCards().size();
	}

}