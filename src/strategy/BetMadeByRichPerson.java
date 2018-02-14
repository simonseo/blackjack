package strategy;

import api.Hand;

public class BetMadeByRichPerson implements BetStrategy {
	/*
	 * (non-Javadoc)
	 * Hypothetically some rich people bet all in every time.
	 * @see strategy.BetStrategy#bet(int)
	 */

	@Override
	public int bet(int balance) {
		return balance;
	}

	@Override
	public int bet(int balance, Hand hand) {
		return bet(balance);
	}

}
