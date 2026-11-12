package com.pluralsight;

public class Card {
    private String suit;
    private String value;
    private boolean isFaceUp;

    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
        this.isFaceUp = false;
    }

    public String getSuit() {
        // hidden if face down
        if (isFaceUp) {
            return suit;
        } else {
            return "#";
        }
    }

    public String getValue() {
        // hidden if face down
        if (isFaceUp) {
            // like A, K, Q, 10, 9
            return value;
        } else {
            return "#";
        }
    }

    public int getPointValue() {
        // hidden if face down
        if (isFaceUp) {
            switch (this.value) {
                case "A":
                    return 11;
                case "K":
                case "Q":
                case "J":
                    return 10;
                case "10":
                case "9":
                case "8":
                case "7":
                case "6":
                case "5":
                case "4":
                case "3":
                case "2":
                    return Integer.parseInt(this.value);
            }
        }

        // face down = 0 points
        return 0;
    }

    public boolean isFaceUp() {
        return isFaceUp;
    }

    public void flip() {
        isFaceUp = !isFaceUp;
    }
}

