package com.pluralsight;

// A Player has a name and a Hand of cards
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

    // A player busts when their hand goes over 21
    public boolean isBusted() {
        return hand.getValue() > 21;
    }
}
