package backend; // Declares the package this class belongs to
import java.io.Serializable; // Imports the Serializable interface

public class Card implements Serializable { // Declares the Card class and indicates it can be serialized
    private static final long serialVersionUID = 1L; // Defines a constant for serialization
    private final Suit suit; // Enum representing the suit of the card
    private final Rank rank; // Enum representing the rank of the card

    public enum Suit { // Enum declaration for card suits
        HEARTS, DIAMONDS, CLUBS, SPADES // The four suits in a standard deck
    }

    public enum Rank { // Enum declaration for card ranks
        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), // Numerical ranks
        JACK(11), QUEEN(12), KING(13), ACE(14); // Face card ranks and ace

        private final int value; // Numerical value for each rank

        Rank(int value) { // Constructor for the Rank enum
            this.value = value; // Sets the numerical value for the rank
        }

        public int getValue() { // Method to get the rank's value
            return value; // Returns the rank's value
        }
    }

    public Card(Suit suit, Rank rank) { // Constructor for the Card class
        this.suit = suit; // Sets the suit of the card
        this.rank = rank; // Sets the rank of the card
    }

    public Suit getSuit() { // Method to get the card's suit
        return suit; // Returns the suit
    }

    public Rank getRank() { // Method to get the card's rank
        return rank; // Returns the rank
    }

    @Override // Annotation indicating this method overrides a superclass method
    public String toString() { // Method to return a string representation of the card
        return rank + " of " + suit; // Returns the rank and suit of the card as a string
    }
}