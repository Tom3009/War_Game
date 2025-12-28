package backend; // Declares the package this class belongs to

import java.io.IOException; // Imports the IOException class
import java.io.Serializable; // Imports the Serializable interface
import java.util.ArrayList; // Imports the ArrayList class
import java.util.List; // Imports the List interface

public class Player implements Serializable { // Declares the Player class and indicates it can be serialized
    private static final long serialVersionUID = 1L; // Defines a constant for serialization
    private String name; // Variable to store the player's name
    private int id; // Variable to store the player's ID
    private List<Card> cardPile; // List to store the player's cards

    public Player(String name, int id) { // Constructor for the Player class
        this.name = name; // Sets the player's name
        this.id = id; // Sets the player's ID
        this.cardPile = new ArrayList<>(); // Initializes the card pile
    }

    public void addCard(Card card) { // Method to add a card to the player's pile
        cardPile.add(card); // Adds the card to the pile
    }

    public Card playCard() { // Method for the player to play a card
        if (cardPile.isEmpty()) { // Checks if the card pile is empty
            return null; // Returns null if the pile is empty
        }
        return cardPile.remove(0); // Removes and returns the top card from the pile
    }

    public boolean hasCards() { // Method to check if the player has cards
        return !cardPile.isEmpty(); // Returns true if the pile is not empty, false otherwise
    }

    public int getCardCount() { // Method to get the number of cards in the player's pile
        return cardPile.size(); // Returns the size of the card pile
    }

    // Getters and setters
    public String getName() { // Method to get the player's name
        return name; // Returns the player's name
    }

    public void setName(String name) { // Method to set the player's name
        this.name = name; // Sets the player's name
    }

    public int getId() { // Method to get the player's ID
        return id; // Returns the player's ID
    }

    public Card peekTopCard() { // Method to peek at the top card without removing it
        if (!cardPile.isEmpty()) { // Checks if the pile is not empty
            return cardPile.get(0); // Returns the top card
        }
        return null; // Returns null if the pile is empty
    }

    @Override // Annotation indicating this method overrides a superclass method
    public String toString() { // Method to return a string representation of the player
        return name + " (ID: " + id + ") - Cards: " + cardPile.size(); // Returns the player's name, ID, and card count as a string
    }

    public void clearCards() { // Method to clear the player's cards
        cardPile.clear(); // Clears the card pile
    }

    // Implement serialization
    private void writeObject(java.io.ObjectOutputStream out) throws IOException { // Custom serialization method
        out.defaultWriteObject(); // Performs default serialization
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException { // Custom deserialization method
        in.defaultReadObject(); // Performs default deserialization
        if (cardPile == null) { // Checks if the card pile is null
            cardPile = new ArrayList<>(); // Initializes the card pile
        }
    }
}