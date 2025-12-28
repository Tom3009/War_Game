package backend; // Declares the package this class belongs to
import java.io.*; // Imports classes for input and output operations

public class Save { // Declares the Save class
    private static final String SAVE_FILE = "war_game_save.dat"; // Defines a constant for the save file name

    public static boolean saveGame(Game game) { // Method to save the game state
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) { // Creates an ObjectOutputStream to write the game object to a file
            oos.writeObject(game); // Writes the game object to the file
            System.out.println("Game saved successfully to " + SAVE_FILE); // Prints a success message
            return true; // Returns true indicating the save was successful
        } catch (IOException e) { // Catches any IO exceptions
            System.err.println("Error saving game: " + e.getMessage()); // Prints an error message
            e.printStackTrace(); // Prints the stack trace for the exception
            return false; // Returns false indicating the save failed
        }
    }

    public static Game loadGame() { // Method to load the game state
        File file = new File(SAVE_FILE); // Creates a File object for the save file
        if (!file.exists()) { // Checks if the save file does not exist
            System.err.println("No saved game found at " + SAVE_FILE); // Prints an error message
            return null; // Returns null indicating no game was loaded
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) { // Creates an ObjectInputStream to read the game object from the file
            Game game = (Game) ois.readObject(); // Reads the game object from the file
            System.out.println("Game loaded successfully from " + SAVE_FILE); // Prints a success message
            return game; // Returns the loaded game object
        } catch (IOException e) { // Catches any IO exceptions
            System.err.println("Error reading saved game: " + e.getMessage()); // Prints an error message
            e.printStackTrace(); // Prints the stack trace for the exception
        } catch (ClassNotFoundException e) { // Catches any ClassNotFound exceptions
            System.err.println("Error: Saved game format is incompatible. " + e.getMessage()); // Prints an error message
            e.printStackTrace(); // Prints the stack trace for the exception
        } catch (ClassCastException e) { // Catches any ClassCast exceptions
            System.err.println("Error: Saved file does not contain a valid Game object. " + e.getMessage()); // Prints an error message
            e.printStackTrace(); // Prints the stack trace for the exception
        }
        return null; // Returns null if the game could not be loaded
    }
}

