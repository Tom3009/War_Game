package backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Game implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Player> players;
    private Deck deck;
    private int currentPlayerIndex;
    private List<Card> cardsInPlay;
    private List<Player> roundPlayers;
    private boolean warScenario;

    public Game(List<String> playerNames) {
        players = new ArrayList<>();
        for (int i = 0; i < playerNames.size(); i++) {
            players.add(new Player(playerNames.get(i), i));
        }
        deck = new Deck();
        currentPlayerIndex = 0;
    }

    public void startGame() {
        deck = new Deck();
        deck.shuffle();
        dealCards();
        currentPlayerIndex = 0;
        warScenario = false;
    }

    private void dealCards() {
        for (Player player : players) {
            player.clearCards();
        }
        while (!deck.isEmpty()) {
            for (Player player : players) {
                Card card = deck.drawCard();
                if (card != null) {
                    player.addCard(card);
                }
            }
        }
    }

    public void startRound() {
        if (isGameOver()) return;
        cardsInPlay = new ArrayList<>();
        roundPlayers = new ArrayList<>(players);

        boolean hasComputerPlayer = players.stream().anyMatch(p -> p.getName().equalsIgnoreCase("Computer"));

        if (hasComputerPlayer) {
            for (Player player : players) {
                if (player.getName().equalsIgnoreCase("Computer")) {
                    playCard(player);
                }
            }
        }
    }

    public boolean playCard(Player player) {
        if (!roundPlayers.contains(player)) return false;
        Card playedCard = player.playCard();
        if (playedCard != null) {
            cardsInPlay.add(playedCard);
            roundPlayers.remove(player);
            //System.out.println(player.getName() + " played: " + playedCard);
            return true;
        } else {
            System.out.println(player.getName() + " has no cards to play");
            return false;
        }
    }

    public boolean isRoundComplete() {
        return roundPlayers.isEmpty();
    }

    public boolean isWarScenario() {
        return warScenario;
    }

    public void resolveRound() {
        if (cardsInPlay.size() == 2) {
            Player roundWinner = determineRoundWinner(cardsInPlay, players);
            if (roundWinner != null) {
                for (Card card : cardsInPlay) {
                    roundWinner.addCard(card);
                }
                System.out.println("Round winner: " + roundWinner.getName());
                cardsInPlay.clear();
                warScenario = false;
            } else {
                System.out.println("It's a tie! War scenario triggered.");
            }
        } else {
            System.out.println("No cards were played this round");
        }
    }

    public void resolveWar() {
        List<Card> warCards = new ArrayList<>();
        List<Player> activePlayers = new ArrayList<>();

        for (Player player : players) {
            if (player.hasCards()) {
                activePlayers.add(player);
                for (int i = 0; i < 2 && player.hasCards(); i++) {
                    warCards.add(player.playCard());
                }

                if (player.hasCards()) {
                    Card faceUpCard = player.playCard();
                    cardsInPlay.add(faceUpCard);
                }
            }
        }

        if (activePlayers.size() > 1) {
            Player warWinner = determineRoundWinner(cardsInPlay, activePlayers);
            for (Card card : cardsInPlay) {
                warWinner.addCard(card);
            }
            for (Card card : warCards) {
                warWinner.addCard(card);
            }

            System.out.println("War winner: " + warWinner.getName()); //this text should be in the GUI
        } else if (activePlayers.size() == 1) {
            Player lastPlayer = activePlayers.get(0);
            for (Card card : cardsInPlay) {
                lastPlayer.addCard(card);
            }
            for (Card card : warCards) {
                lastPlayer.addCard(card);
            }
            System.out.println("Last player standing wins the war: " + lastPlayer.getName());
        } else {
            System.out.println("No players left with cards. The game is over.");
        }

        cardsInPlay.clear();
        warScenario = false;
    }

    private Player determineRoundWinner(List<Card> cardsInPlay, List<Player> activePlayers) {
        Map<Player, Card> playerCards = new HashMap<>();
        for (Player player : activePlayers) {
            Card topCard = player.peekTopCard();
            if (topCard != null) {
                playerCards.put(player, topCard);
            }
        }

        if (playerCards.size() != 2) {
            throw new IllegalArgumentException("There should be exactly two cards and two players in a round.");
        }

        Player player1 = null;
        Player player2 = null;
        Card card1 = null;
        Card card2 = null;

        for (Map.Entry<Player, Card> entry : playerCards.entrySet()) {
            if (player1 == null) {
                player1 = entry.getKey();
                card1 = entry.getValue();
            } else {
                player2 = entry.getKey();
                card2 = entry.getValue();
            }
        }

        System.out.println(player1.getName() + " played: " + card1);
        System.out.println(player2.getName() + " played: " + card2);

        if (card1.getRank().getValue() > card2.getRank().getValue()) {
            return player1;
        } else if (card1.getRank().getValue() < card2.getRank().getValue()) {
            return player2;
        } else {
            // In case of a tie, handle the war scenario
            warScenario = true;
            return null;
        }
    }

    public boolean isGameOver() {
        return players.stream().filter(Player::hasCards).count() <= 1;
    }

    public Player getWinner() {
        for (Player player : players) {
            if (player.hasCards()) {
                return player;
            }
        }
        return null;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int index) {
        this.currentPlayerIndex = index;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        if (deck == null) {
            deck = new Deck();
        }
    }
}

