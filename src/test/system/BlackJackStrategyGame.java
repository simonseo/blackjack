package test.system;

import java.util.ArrayList;
import java.util.Collection;
import api.Player;
import api.Table;
import impl.BlackJackPlayer;
import impl.BlackJackTable;
import strategy.BetStrategy;
import strategy.BetLikeRichPerson;
import strategy.BetRandomAmount;
import strategy.BetUniformAmount;
import strategy.HitStrategy;
import strategy.HitCowardlyBlackJackPlayer;
import strategy.HitRisktakingBlackJackPlayer;


public class BlackJackStrategyGame {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Please provide the number of players");
            System.exit(1);
        }

        int numberOfPlayers = Integer.parseInt(args[0]);
        int decks = (args.length > 1) ? Integer.parseInt(args[1]) : 1;

        Collection<Player> players = new ArrayList<Player>();
        
        for (int i = 0; i < numberOfPlayers; i++ ) {
        		BetStrategy bs = null;
        		HitStrategy hs = null;
        		
        		switch (i%3) {
        		case 0:
        			bs = new BetLikeRichPerson();
        		case 1:
        			bs = new BetRandomAmount();
        		case 2:
        			bs = new BetUniformAmount(1+i*2);
        		}
        		
        		
        		switch (i%2) {
        		case 0:
        			hs = new HitRisktakingBlackJackPlayer();
        		case 1:
        			hs = new HitCowardlyBlackJackPlayer();
        		}
        		
        		Player p = ((BlackJackPlayer) new BlackJackPlayer("Player"+i, 1+i*12)
        				.setBetStrategy(bs))
        				.setHitStrategy(hs);
        		players.add(p);
        }
        
        Table table = new BlackJackTable(players, decks);

        for (int i = 0; !table.isGameOver(); i++) {
            System.out.println("Round " + i);
            System.out.println(table);
            table.round();
        }
        System.out.println("Game Over");
    }
}
