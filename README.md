# War_Game
Java-based War card game featuring a graphical user interface, object-oriented design, and save/load functionality for persistent gameplay.
Project Overview

This project is a Java-based implementation of the classic War card game, featuring a graphical user interface (GUI) that allows a player to compete against the computer (or another player). The application focuses on clean object-oriented design, interactive gameplay, and persistent game state management, enabling users to save and resume games seamlessly.



Game Description

War is a simple two-player card game played with a standard 52-card deck. The deck is shuffled and divided evenly between the players. In each round, both players reveal the top card of their deck; the player with the higher card wins the round and collects all played cards. Aces are treated as the highest value, and suits are ignored. When two cards of equal value are revealed, a “war” occurs, during which additional face-down and face-up cards are played until a winner is determined. The game continues until one player collects all cards.


Solution Approach

The application is built using strict object-oriented programming principles. Core entities such as cards, players, decks, and game state are modeled as separate classes with encapsulated attributes and dedicated behaviors. Inheritance and abstraction are used to structure shared logic, while collections (arrays and lists) manage cards, players, and game data dynamically. Randomization is applied to shuffle and distribute the deck fairly at the start of each game.

Graphical User Interface

The game includes an interactive GUI controlled via mouse and keyboard input. The interface visually displays player and computer cards, round outcomes, and total scores. A menu bar provides essential controls such as starting a new game, saving the current game state, and loading a previously saved session. Images for cards and interface elements are managed within the project structure to ensure consistent rendering.

Save and Load Functionality

A dedicated persistence component allows the game state to be saved to and loaded from files. This enables players to pause gameplay and resume later without losing progress, including card distribution, scores, and current game status.

Key Features

The project includes full game logic for standard War rules, support for repeated wars, automatic win detection, and proper handling of edge cases such as players running out of cards during a war. All variables are kept private, static methods are avoided in the game logic, and the codebase is fully UTF-8 encoded. Clear console and GUI feedback is provided to enhance user experience.




Technologies and Concepts

Language: Java

Paradigm: Object-Oriented Programming

UI: Java-based GUI (event-driven)

Data Structures: Arrays and Lists

Persistence: File-based save/load system

Concepts: Encapsulation, inheritance, abstraction, randomness, GUI design

Notes

The project is designed to be extensible. Additional features such as sound effects, alternative card designs, multiplayer enhancements, or advanced animations can be integrated without major structural changes.
