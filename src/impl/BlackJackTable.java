package impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import api.Hand;
import api.Player;
import api.Table;

public class BlackJackTable extends Table {
	private List<Player> players;

	public BlackJackTable(int numberOfPlayers, int decks) {
		wagers = new HashMap<Player, Integer>();
		dealer = new BlackJackDealer(decks);
		players = new ArrayList<Player>();
		for (int i = 0; i < numberOfPlayers; i++) {
			players.add(new BlackJackPlayer("Player"+i));
		}
	}

	@Override
	public Collection<Player> getPlayers() {
		List<Player> readonlyPlayers = Collections.unmodifiableList(this.players);
		return readonlyPlayers;
	}

	@Override
	public boolean isGameOver() {
		// there are no players
		if (this.getPlayers().size() == 0) return true;
		
		// no player has money
		for (Player p : this.getPlayers()) {
			if (((BlackJackPlayer) p).hasMoney()) return false;
		}
		return true;
	}

	@Override
	public String toString() {
		// Blackjack table with players asdf, asdf, asdf
		String result = "Blackjack table with players ";
		for (Player p : this.getPlayers()) {
			if (((BlackJackPlayer) p).hasMoney()) {
				result += p.getName() + ", ";	
			}
		}
		return result.substring(0, result.length()-2);
	}

	@Override
	protected void collectBets() {
		wagers.clear(); // make sure wagers is empty
		for (Player p : this.getPlayers()) {
			((BlackJackPlayer) p).blackjack(false);
			if (((BlackJackPlayer) p).hasMoney()) {
				wagers.put(p, p.placeWager());
				((BlackJackPlayer) p).playing(true);
			}
		}
	}

	@Override
	protected void dealTable() {
		for (Player p : this.getPlayers()) {
			if (((BlackJackPlayer) p).playing()) {
				dealer.dealCard(p);
				dealer.dealCard(p);
				((BlackJackPlayer) p).blackjack(p.getHand().isWinner());
			}
		}
		dealer.dealCard((BlackJackPlayer) dealer);
		dealer.dealCard((BlackJackPlayer) dealer);
		((BlackJackPlayer) dealer).blackjack(dealer.getHand().isWinner());
	}


	@Override
	protected void playerTurns() {
		for (Player p : this.getPlayers()) {
			if (((BlackJackPlayer) p).playing()) {
				while (p.requestCard()) {
					dealer.dealCard(p);
				}
			}
		}
		while (((BlackJackDealer) dealer).requestCard()) {
			dealer.dealCard((Player) dealer);
		}
	}

	@Override
	protected void playerEvaluations() {
		Hand dHand = dealer.getHand();
		for (Player p : this.getPlayers()) {
			if (((BlackJackPlayer) p).playing()) { // for each participating player
				assert wagers.containsKey(p);
				int bet = wagers.get(p);
				double ratio = 0;
				Hand pHand = p.getHand();
				int handCompare = pHand.compareTo(dHand); 
				if (	    ( pHand.isValid() && handCompare > 0 ) || //"Players win by not busting and having a total higher than the dealer,
						( pHand.isValid() && !dHand.isValid() ) || //or not busting and having the dealer bust,
						( pHand.isWinner() && !dHand.isWinner() ) || //or getting a blackjack without the dealer getting a blackjack." -Wikipedia
						( ((BlackJackPlayer) p).blackjack() && !((BlackJackPlayer) dealer).blackjack() ) ) { //player is blackjack
					ratio = (pHand.isWinner()) ? 1.0 + 1.5 : 1.0 + 1.0;
				} else if (handCompare == 0) {
					ratio = 1.0;
				} else if ( !pHand.isValid() || handCompare < 0) {
					ratio = 0.0;
				}
				p.payOut((int) (bet*ratio));
			}
		}
		wagers.clear();
	}
	
	@Override
	protected void collectCards() {
		for (Player p : this.getPlayers()) {
			if (((BlackJackPlayer) p).playing()) {
				dealer.collectCards(p);
				((BlackJackPlayer) p).playing(false);
			}
		}
		dealer.collectCards((BlackJackPlayer) dealer);
	}
	
}