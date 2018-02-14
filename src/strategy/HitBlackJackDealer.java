package strategy;

import api.Hand;

public class HitBlackJackDealer implements HitStrategy {
	/*
	 * BlackJackDealer hits if hand is less than 17 but never if over 17. 
	 * (non-Javadoc)
	 * @see strategy.HitStrategy#hit(int, api.Hand)
	 */
	@Override
	public boolean hit(int balance, Hand hand) {
		return (hand.isValid() && hand.valueOf() < 17) ? true : false;
	}

}
