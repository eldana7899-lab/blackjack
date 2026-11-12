package com.pluralsight;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    // A Card is dealt to the Hand and the Hand stores it.
    // We flip it face up so the player can see it.
    public void deal(Card card) {
        if (!card.isFaceUp()) {
            card.flip();
        }
        cards.add(card);
    }

    public int getSize() {
        return cards.size();
    }

    // Adds up the points of every card in the hand.
    // An Ace counts as 11, but if that would make the hand go over 21,
    // the Ace counts as 1 instead (we take away 10).
    public int getValue() {
        int value = 0;
        int aces = 0;

        for (Card card : cards) {
            value += card.getPointValue();
            if (card.getValue().equals("A")) {
                aces++;
            }
        }

        // Turn Aces from 11 into 1 until we are not busted (or run out of Aces)
        while (value > 21 && aces > 0) {
            value -= 10;
            aces--;
        }

        return value;
    }

    // Shows the cards like: K of Hearts, 7 of Clubs
    @Override
    public String toString() {
        String text = "";
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            text += card.getValue() + " of " + card.getSuit();
            if (i < cards.size() - 1) {
                text += ", ";
            }
        }
        return text;
    }
}
