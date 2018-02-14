package strategy;

import api.Hand;

public interface HitStrategy {
	/* 
	 * Players take into consideration their hand (how might a new card benefit them) 
	 * and their balance (how much risk can one take) 
	 */
	public boolean hit(int balance, Hand hand);
	
}
