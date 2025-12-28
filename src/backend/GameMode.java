package backend; // Declares the package this class belongs to

public class GameMode { // Declares the GameMode class
    public enum Mode { // Declares an enum named Mode within GameMode class
        SINGLE_PLAYER, // Enum constant for single-player mode
        MULTIPLAYER // Enum constant for multiplayer mode
    }

    private Mode mode; // Variable to store the current game mode

    public GameMode() { // Default constructor for GameMode class
        this.mode = Mode.SINGLE_PLAYER; // Sets the default mode to single-player
    }

    public GameMode(Mode mode) { // Parameterized constructor for GameMode class
        this.mode = mode; // Sets the mode to the provided value
    }

    public Mode getMode() { // Method to get the current game mode
        return mode; // Returns the current mode
    }

    public void setMode(Mode mode) { // Method to set the game mode
        this.mode = mode; // Sets the mode to the provided value
    }
}