package strategy;

import api.Hand;

public class BetRandomAmount implements BetStrategy {

	@Override
	public int bet(int balance) {
		int randAmount = (int) (balance * Math.random());
		int betAmount = Math.max(1, randAmount);
		assert (betAmount > 0 || balance == 0) && betAmount <= balance : "balance " + balance + " betAmount " + betAmount;
		return betAmount;
	}

	@Override
	public int bet(int balance, Hand hand) {
		return bet(balance);
	}


}
