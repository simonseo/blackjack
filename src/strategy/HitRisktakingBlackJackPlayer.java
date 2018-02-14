package strategy;

import api.Hand;
import impl.BlackJackHand;

public class HitRisktakingBlackJackPlayer implements HitStrategy {
	/*
	 * (non-Javadoc)
	 * @see strategy.HitStrategy#hit(int, api.Hand)
	 */
	@Override
	public boolean hit(int balance, Hand hand) {
		if (!hand.isValid()) return false;
		int value = hand.valueOf();
		switch (value) {
			case 21:
			case 20:
				return false;
			case 19:
			case 18:
			case 17:
			case 16:
				return ((BlackJackHand) hand).hasAce() ? true : false;
			default:
				return true;
		}
	}

}
