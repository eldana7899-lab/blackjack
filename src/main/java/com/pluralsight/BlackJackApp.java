package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

// Blackjack: closest to 21 without going over wins
public class BlackJackApp {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println();
        System.out.println("========== WELCOME TO BLACKJACK ==========");
        System.out.println();

        // 1. Get the players
        int numberOfPlayers = askForNumberOfPlayers();
        ArrayList<Player> players = new ArrayList<>();

        for (int i = 1; i <= numberOfPlayers; i++) {
            System.out.print("Enter the name of player " + i + ": ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                name = "Player " + i; // no name typed
            }
            players.add(new Player(name));
        }

        // 2. Make and shuffle the deck
        Deck deck = new Deck();
        deck.shuffle();

        // 3. Deal 2 cards each
        for (int round = 0; round < 2; round++) {
            for (Player player : players) {
                player.getHand().deal(deck.deal());
            }
        }

        // 4. Show starting hands
        System.out.println();
        System.out.println("---------- Starting Hands ----------");
        for (Player player : players) {
            showHand(player);
        }

        // 5. Each player's turn
        for (Player player : players) {
            takeTurn(player, deck);
        }

        // 6. Show final hands and the winner
        System.out.println();
        System.out.println("---------- Final Hands ----------");
        for (Player player : players) {
            showHand(player);
        }

        announceWinner(players);
        scanner.close();
    }

    // Ask how many players (1-7)
    private static int askForNumberOfPlayers() {
        while (true) {
            System.out.print("How many players? (1-7): ");
            String input = scanner.nextLine().trim();
            try {
                int number = Integer.parseInt(input);
                if (number >= 1 && number <= 7) {
                    return number;
                }
            } catch (NumberFormatException e) {
                // not a number
            }
            System.out.println("Please enter a number from 1 to 7.");
        }
    }

    // Hit = take a card, Stay = stop
    private static void takeTurn(Player player, Deck deck) {
        System.out.println();
        System.out.println("---------- " + player.getName() + "'s Turn ----------");

        while (true) {
            showHand(player);

            if (player.getHand().getValue() == 21) {
                System.out.println(player.getName() + " has 21!");
                return;
            }

            System.out.print("Hit or Stay? (H/S): ");
            String choice = scanner.nextLine().trim().toUpperCase();

            if (choice.equals("H")) {
                Card card = deck.deal();
                player.getHand().deal(card);
                System.out.println(player.getName() + " drew the " + card.getValue() + " of " + card.getSuit());

                if (player.isBusted()) {
                    showHand(player);
                    System.out.println(player.getName() + " BUSTED!");
                    return;
                }
            } else if (choice.equals("S")) {
                System.out.println(player.getName() + " stays.");
                return;
            } else {
                System.out.println("Please type H to Hit or S to Stay.");
            }
        }
    }

    // Print a player's cards and total
    private static void showHand(Player player) {
        Hand hand = player.getHand();
        System.out.println(player.getName() + ": " + hand + "  (Total: " + hand.getValue() + ")");
    }

    // Highest score 21 or under wins
    private static void announceWinner(ArrayList<Player> players) {
        int bestScore = 0;
        ArrayList<Player> winners = new ArrayList<>();

        for (Player player : players) {
            int score = player.getHand().getValue();

            if (player.isBusted()) {
                continue; // over 21 can't win
            }

            if (score > bestScore) {
                bestScore = score;
                winners.clear();
                winners.add(player);
            } else if (score == bestScore) {
                winners.add(player); // a tie
            }
        }

        System.out.println();
        if (winners.isEmpty()) {
            System.out.println("Everyone busted. Nobody wins this time!");
        } else if (winners.size() == 1) {
            System.out.println("The winner is " + winners.get(0).getName() + " with " + bestScore + "!");
        } else {
            String names = "";
            for (int i = 0; i < winners.size(); i++) {
                names += winners.get(i).getName();
                if (i < winners.size() - 1) {
                    names += " and ";
                }
            }
            System.out.println("It's a tie between " + names + " with " + bestScore + "!");
        }
        System.out.println();
    }
}
