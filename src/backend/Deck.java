package backend; // Declares the package this class belongs to

import java.util.ArrayList; // Imports the ArrayList class
import java.util.Collections; // Imports the Collections class
import java.util.List; // Imports the List interface
import java.io.Serializable; // Imports the Serializable interface

public class Deck implements Serializable { // Declares the Deck class and indicates it can be serialized
    private static final long serialVersionUID = 1L; // Defines a constant for serialization
    
    private List<Card> cards; // List to store the cards in the deck

    public Deck() { // Constructor for the Deck class
        cards = new ArrayList<>(); // Initializes the cards list
        initializeDeck(); // Calls the method to initialize the deck
    }

    private void initializeDeck() { // Method to initialize the deck with cards
        cards.clear(); // Clears any existing cards to avoid duplicates
        for (Card.Suit suit : Card.Suit.values()) { // Loops through each suit
            for (Card.Rank rank : Card.Rank.values()) { // Loops through each rank
                cards.add(new Card(suit, rank)); // Adds a new card to the deck
                System.out.println("Added card: " + rank + " of " + suit); // Debug statement to print added card
            }
        }
        System.out.println("Deck initialized with " + cards.size() + " cards."); // Debug statement to print deck size
    }

    public void shuffle() { // Method to shuffle the deck
        Collections.shuffle(cards); // Shuffles the cards list
    }

    public Card drawCard() { // Method to draw a card from the deck
        if (cards.isEmpty()) { // Checks if the deck is empty
            return null; // Returns null if the deck is empty
        }
        return cards.remove(cards.size() - 1); // Removes and returns the top card from the deck
    }

    public boolean isEmpty() { // Method to check if the deck is empty
        return cards.isEmpty(); // Returns true if the deck is empty, false otherwise
    }

    public int size() { // Method to get the size of the deck
        return cards.size(); // Returns the number of cards in the deck
    }

    @Override // Annotation indicating this method overrides a superclass method
    public String toString() { // Method to return a string representation of the deck
        return "Deck: " + cards.size() + " cards"; // Returns the size of the deck as a string
    }
}