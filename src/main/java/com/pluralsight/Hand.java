package com.pluralsight;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    // Add a card and turn it face up
    public void deal(Card card) {
        if (!card.isFaceUp()) {
            card.flip();
        }
        cards.add(card);
    }

    public int getSize() {
        return cards.size();
    }

    // Add up the points (Ace = 11, or 1 if 11 would go over 21)
    public int getValue() {
        int value = 0;
        int aces = 0;

        for (Card card : cards) {
            value += card.getPointValue();
            if (card.getValue().equals("A")) {
                aces++;
            }
        }

        // Over 21? Count an Ace as 1 instead
        while (value > 21 && aces > 0) {
            value -= 10;
            aces--;
        }

        return value;
    }

    // Show cards like: K of Hearts, 7 of Clubs
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
