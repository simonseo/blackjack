# Release Update - February 15, 2018

Affected files are as follows:

```
src/
├── impl
│   ├── AnotherBlackJackPlayer.java (deprecated)
│   ├── BlackJackDealer.java (updated)
│   ├── BlackJackPlayer.java (updated)
│   └── BlackJackTable.java (updated)
├── strategy
│   ├── BetLikeRichPerson.java (new)
│   ├── BetRandomAmount.java (new)
│   ├── BetStrategy.java (new)
│   ├── BetUniformAmount.java (new)
│   ├── HitBlackJackDealer.java (new)
│   ├── HitCowardlyBlackJackPlayer.java (new)
│   ├── HitRisktakingBlackJackPlayer.java (new)
│   └── HitStrategy.java (new)
└── test
    └── system
        └── BlackJackStrategyGame.java (new)
```

## New

- Strategy Pattern was incorporated into betting and hitting behaviour of dealer and player objects.
- `BetStrategy.java` and `HitStrategy.java` serve as interfaces for betting and hitting strategies
- There are 3 betting behaviours: `BetLikeRichPerson.java` which bets everything each time, `BetUniformAmount.java` which bets the same amount that was initially set, and `BetRandomAmount.java` which bets any random amount that the player holds.
- There are 3 betting behaviours: `HitBlackJackDealer.java` which is intended for dealers to use, `HitCowardlyBlackJackPlayer.java` which changes behaviour according to the amount of money a player has, and `HitRisktakingBlackJackPlayer.java` which is less reluctant to hit even with high hand values.
- Now players (and by inheritance, dealer) have `public Player setBetStrategy(BetStrategy bs)` and `public Player setHitStrategy(HitStrategy hs)` methods that is used to give each player a strategy. To keep method signatures unified between player and dealer, return type is intentionally abstract. Because the methods return `this` as a `Player`, setting bet and hit strategies can be chained to instantiation like so: 
    ```java
    Player p = ((BlackJackPlayer) new BlackJackPlayer().setBetStrategy(bs)).setHitStrategy(hs);
    ```


## Improvements

- BlackJackTable has a new constructor that allows it to be initialized with a list of players with user-defined attributes and behaviours.
- `AnotherBlackJackPlayer.java` is deprecated as a result of adopting Strategy Pattern.

## Fixes

- Assertions in `BlackJackPlayer.java` that tested if a player had money before placing a wager were removed.
