package strategy;

import api.Hand;

public class BetRandomAmount implements BetStrategy {

	@Override
	public int bet(int balance) {
		return (int) (balance * Math.random());
	}

	@Override
	public int bet(int balance, Hand hand) {
		return bet(balance);
	}


}
