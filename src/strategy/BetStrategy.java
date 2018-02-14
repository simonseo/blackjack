package strategy;

import api.Hand;

public interface BetStrategy {
	/*
	 * Players consider their balance before betting in order to avoid debt.
	 * Bet should always be less than or equal to balance.
	 * In some card games, betting takes place in the middle of the game and thus players must consider their hand.
	 */
	public int bet(int balance);
	public int bet(int balance, Hand hand);
}
