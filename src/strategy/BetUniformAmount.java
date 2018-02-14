package strategy;

import api.Hand;

public class BetUniformAmount implements BetStrategy {
	
	private int defaultWager;

	public BetUniformAmount(int defaultWager) {
		assert defaultWager > 0;
		this.defaultWager = defaultWager;
	}
	
	@Override
	public int bet(int balance) {
		int betAmount = Math.min(this.defaultWager, balance);
		assert (betAmount > 0 || balance == 0) && betAmount <= balance : "balance " + balance + " betAmount " + betAmount;
		return betAmount;
	}

	@Override
	public int bet(int balance, Hand hand) {
		return bet(balance);
	}

}
;