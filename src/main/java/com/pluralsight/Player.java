package com.pluralsight;

// A player has a name and cards
public class Player {
    private String name;
    private Hand hand;

    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    // True if the hand is over 21
    public boolean isBusted() {
        return hand.getValue() > 21;
    }
}
