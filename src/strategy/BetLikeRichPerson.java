package strategy;

import api.Hand;

public class BetLikeRichPerson implements BetStrategy {
	/*
	 * (non-Javadoc)
	 * Hypothetically some rich people bet all-in every time.
	 * @see strategy.BetStrategy#bet(int)
	 */

	@Override
	public int bet(int balance) {
		int betAmount = balance;
		assert (betAmount > 0 || balance == 0) && betAmount <= balance : "balance " + balance + " betAmount " + betAmount;
		return betAmount;
	}

	@Override
	public int bet(int balance, Hand hand) {
		return bet(balance);
	}

}
