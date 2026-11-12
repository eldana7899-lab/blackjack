package com.pluralsight;


public class CardApp {

    public static void main(String[] args) {

        // make the deck
        Deck deck = new Deck();

        deck.shuffle();

        System.out.println("Deck Size:" + deck.getSize());

        // make a hand
        Hand hand1 = new Hand();



        // deal 5 cards
        for (int i = 0; i < 5; i++) {
            // take a card from the deck
            Card card = deck.deal();
            // put it in the hand
            hand1.deal(card);
        }

        // print the total
        System.out.println("This hand is worth " + hand1.getValue());
    }

}
