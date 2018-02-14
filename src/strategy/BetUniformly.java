package strategy;

import api.Hand;

public class BetUniformly implements BetStrategy {
	
	private int defaultWager;

	public BetUniformly(int defaultWager) {
		this.defaultWager = defaultWager;
	}
	
	@Override
	public int bet(int balance) {
		return Math.min(this.defaultWager, balance);
	}

	@Override
	public int bet(int balance, Hand hand) {
		return bet(balance);
	}

}
;