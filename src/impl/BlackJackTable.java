package impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import api.Player;
import api.Table;

public class BlackJackTable extends Table {
	private List<Player> players;

	public BlackJackTable(int numberOfPlayers, int decks) {
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
		return false;
	}

	@Override
	public String toString() {
		// Blackjack table with players asdf, asdf, asdf
		String result = "Blackjack table with players ";
		for (Player p : this.getPlayers()) {
			result += p.getName() + ", ";
		}
		return result.substring(0, result.length()-2);
	}

	@Override
	protected void collectCards() {
		for (Player p : this.getPlayers()) {
			dealer.collectCards(p);
		}
		dealer.collectCards((BlackJackPlayer) dealer);
	}

	@Override
	protected void dealTable() {
		for (Player p : this.getPlayers()) {
			dealer.dealCard(p);
			dealer.dealCard(p);
		}
		dealer.dealCard((BlackJackPlayer) dealer);
		dealer.dealCard((BlackJackPlayer) dealer);
	}

	@Override
	protected void collectBets() {
		for (Player p : this.getPlayers()) {
			wagers.put(p, p.placeWager());
		}
	}

	@Override
	protected void playerTurns() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void playerEvaluations() {
		// TODO Auto-generated method stub
		
	}
	
}