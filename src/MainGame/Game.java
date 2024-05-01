package MainGame;

//************************************************************
// File Name: Game.java
//
// Purpose: This class manages the overall game flow and 
// interactions between players. It includes methods for starting 
// a new game, taking turns, and determining the winner.
//************************************************************

import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import Nim_Anh.*;
import Nim_Emely.*;
import Nim_Thy.*;

/**
 * The Game class represents a game instance of Nim.
 * It manages the players, the game board, and the game flow.
 */
public class Game {
    // Instance data
    private Player[] players;
    private Board board;
    private String currentTurn;
    private int gameID;

    // Constructors
    /**
     * Constructs a Game object.
     */
    public Game() {
    }

    /**
     * Constructs a Game object with the given leaderboard.
     * Initializes the players and the game board.
     * @param mainLeaderboard The leaderboard used for the game.
     */
    public Game(Leaderboard mainLeaderboard) {
        players = new Player[2];

        // Initialize the game board with default heap sizes
        ArrayList<Integer> heaps = new ArrayList<>();
        heaps.add(1);
        heaps.add(3);
        heaps.add(5);
        heaps.add(7);
        board = new Board(heaps);

        // Generate a unique game ID
        gameID = new Random().nextInt(1000000) + 1;
        if (mainLeaderboard.getPlayers() != null) {
            while (mainLeaderboard.lookForOldGameID(gameID) != -1) {
                gameID = new Random().nextInt(1000000) + 1;
            }
        }
    }

    // Getter methods
    /**
     * Gets the players of the game.
     * @return An array containing the players.
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * Gets the game board.
     * @return The game board.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Gets the game ID.
     * @return The game ID.
     */
    public int getGameID() {
        return gameID;
    }

    // Method to create 2 players
    /**
     * Creates two players for the game, allowing selection between human and AI players.
     * @param mainLeaderboard The leaderboard used to check existing players.
     * @param scan The Scanner object used for user input.
     */
    public void createNewPlayers(Leaderboard mainLeaderboard, Scanner scan) {
        int ai_count = 0; // Counter for AI players

        // Selecting player 1
        System.out.println("\nSelect Human Player (enter \"H\") or AI Player (enter \"A\"): ");
        System.out.print("Player 1: ");
        String player1 = scan.next();

        // Validate user input for player 1
        while (!player1.equalsIgnoreCase("H") && !player1.equalsIgnoreCase("A")) {
            System.out.println("Invalid input!");
            System.out.println();
            System.out.print("Player 1: ");
            player1 = scan.next();
        }

        // Assign type and name for player 1
        if (player1.equalsIgnoreCase("H")) {
            players[0] = signUpOrLogIn(mainLeaderboard, scan);
        } else if (player1.equalsIgnoreCase("A")) {
            players[0] = new AIPlayer();
            ai_count++;
        }

        // Selecting player 2
        System.out.println("\nSelect Human Player (enter \"H\") or AI Player (enter \"A\"): ");
        System.out.print("Player 2: ");
        String player2 = scan.next();

        // Validate user input for player 2
        while (!player2.equalsIgnoreCase("H") && !player2.equalsIgnoreCase("A")) {
            System.out.println("Invalid input!");
            System.out.println();
            System.out.print("Player 2: ");
            player2 = scan.next();
        }

        // Check for duplicate AI players
        while (player2.equalsIgnoreCase("A") && ai_count > 0) {
            System.out.println("You cannot create 2 AI players!");
            System.out.println();
            System.out.print("Player 2: ");
            player2 = scan.next();
        }

        // Assign type and name for player 2
        if (player2.equalsIgnoreCase("H")) {
            players[1] = signUpOrLogIn(mainLeaderboard, scan);
        } else if (player2.equalsIgnoreCase("A")) {
            players[1] = new AIPlayer();
        }
    }

    // signUpOrLogIn method
    /**
     * Prompts the user to sign up or log in, and creates a new HumanPlayer accordingly.
     * @param leaderboard The leaderboard used to check existing players.
     * @param scan The Scanner object used for user input.
     * @return The HumanPlayer object created.
     */
    public HumanPlayer signUpOrLogIn(Leaderboard leaderboard, Scanner scan) {
        scan.nextLine();
        System.out.print("Name of Player: ");
        String name = scan.nextLine().trim();
        while (name.isEmpty()) {
            System.out.println("Invalid input!");
            System.out.println();
            System.out.print("Name of Player: ");
            name = scan.nextLine().trim();
        }
        HumanPlayer newHuman = new HumanPlayer(name);
        while (leaderboard.lookForOldPlayer(name, newHuman.getPlayerId()) != -1) {
            newHuman.setPlayerId(new Random().nextInt(100000000) + 1);
        }
        System.out.println();
        System.out.println("Player's ID: " + newHuman.getPlayerId());
        return newHuman;
    }

    // logIn method
    /**
     * Logs in an existing HumanPlayer.
     * @param leaderboard The leaderboard used to check existing players.
     * @param scan The Scanner object used for user input.
     * @return The HumanPlayer object logged in, or null if no players exist.
     */
    public HumanPlayer logIn(Leaderboard leaderboard, Scanner scan) {
        if (leaderboard.getPlayers().size() == 0) {
            System.out.println("It seems like you don't have an account yet. Would you like to sign up instead?");
            return null;
        }
        scan.nextLine();
        System.out.print("Name of Player: ");
        String name = scan.nextLine().trim();
        while (name.isEmpty()) {
            System.out.println("Invalid input!");
            System.out.println();
            System.out.print("Name of Player: ");
            name = scan.nextLine().trim();
        }
        System.out.println();
        System.out.print("Player's ID: ");
        int id = scan.nextInt();
        while (id == 0) {
            System.out.println("Invalid input!");
            System.out.println();
            System.out.print("Player's ID: ");
            id = scan.nextInt();
        }
        if (leaderboard.lookForOldPlayer(name, id) != -1) {
            System.out.println("The Player doesn't exist! Please try again");
            return null;
        }
        return (HumanPlayer) leaderboard.getPlayers().get(leaderboard.lookForOldPlayer(name, id));
    }

    // startGame method
    /**
     * Sets the current turn of the game.
     * @param firstPlayer The name of the player who starts the game.
     */
    public void startGame(String firstPlayer) {
        this.currentTurn = firstPlayer;
    }

    // play method
    /**
     * Initiates and manages the gameplay.
     * @return The winning player, or null if the game is canceled.
     */
    public Player play() {
        Player playing = lookForPlayer(currentTurn); // Getting name of first player
        displayGameInfo(playing, board.getHeaps().size()); // Display game information
        boolean cancelGame = false; // Flag to cancel game if true

        // Loop until the game is over
        while (!board.isGameOver()) {
            Move currentMove = playing.makeMove(board); // Players take turns making moves
            if (currentMove == null) { // Handle cancelation
                cancelGame = true;
                break;
            }
            board.removeObjects(currentMove.getHeapIndex(), currentMove.getNumObjectsRemoved()); // Remove objects from heap
            System.out.println("\nTurn: " + playing.getPlayerName() + "\t\tRemoved: " + currentMove.getNumObjectsRemoved() + "\tHeap: " + (currentMove.getHeapIndex() + 1)); // Log the turn
            playing = nextTurn(); // Switch turns
        }
        if (cancelGame) {
            return null;
        }
        // Determine the winning player
        playing = nextTurn(); // Since the last player lost, the next turn player won
        return playing;
    }

    // lookForPlayer method
    /**
     * Searches for a player by name.
     * @param name The name of the player to search for.
     * @return The player object if found, otherwise null.
     */
    private Player lookForPlayer(String name) {
        for (int index = 0; index < players.length; index++) {
            if (players[index].getPlayerName().equalsIgnoreCase(name)) {
                return players[index];
            }
        }
        return null;
    }

    // nextTurn method
    /**
     * Switches the turn between players.
     * @return The player whose turn is next.
     */
    private Player nextTurn() {
        if (players[0].getPlayerName().equals(currentTurn)) {
            currentTurn = players[1].getPlayerName();
            return players[1];
        } else {
            currentTurn = players[0].getPlayerName();
            return players[0];
        }
    }

    // displayGameInfo method
    /**
     * Displays game information.
     * @param first The player who starts the game.
     * @param heapSize The size of the game board heaps.
     */
    private void displayGameInfo(Player first, int heapSize) {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n*****************************************");
        System.out.println("\t\tNim Game\n");
        for (int index = 0; index < players.length; index++) {
            System.out.println("Players Name: \t" + players[index].getPlayerName());
        }
        System.out.println("First Turn: " + first.getPlayerName());
        System.out.println("Heap Size: " + heapSize);
        System.out.println("*****************************************");
    }

    // playAgain method
    /**
     * Asks the user if they want to play again.
     * @param scan The Scanner object used for user input.
     * @return True if the user wants to play again, otherwise false.
     */
    public boolean playAgain(Scanner scan) {
        System.out.print("Play again? (Y or N): ");
        String option = scan.next();
        while (!option.equalsIgnoreCase("Y") && !option.equalsIgnoreCase("N")) {
            System.out.println("Invalid input!");
            System.out.println();
            System.out.print("Play again? (Y or N): ");
            option = scan.next();
        }
        return option.equalsIgnoreCase("Y");
    }

    // findIndex method
    /**
     * Linear search function to find the index of a player in the players array.
     * @param player The player to find the index of.
     * @return The index of the player in the array.
     */
    public int findIndex(Player player) {
        int result = 0;
        for (int i = 0; i < players.length; i++) {
            if (players[i] == player) {
                result = i;
            }
        }
        return result;
    }
}
