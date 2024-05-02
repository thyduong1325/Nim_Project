package MainGame;
//************************************************************
// File Name: Game.java                     Author: Team
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
 * This class represents the core logic of the Nim game.
 * It orchestrates player interactions, game setup, and gameplay flow.
 */
public class Game{
    // Instance data
    private Player[] players;
    private Board board;
    private String currentTurn;
    private int gameID;


    // Constructors
    public Game() {

    }

    /**
     * Constructs a new game with a given leaderboard.
     * @param mainLeaderboard The main leaderboard to be used.
     */
    public Game(Leaderboard mainLeaderboard) {
        // Initialize all the data
        players = new Player[2];

        ArrayList<Integer> heaps = new ArrayList<>();
        heaps.add(1);
        heaps.add(3);
        heaps.add(5);
        heaps.add(7);
        board = new Board(heaps);

        gameID = new Random().nextInt(1000000) + 1;
        if (mainLeaderboard.getPlayers() != null) {
        	while (mainLeaderboard.lookForOldGameID(gameID) != -1){
            gameID = new Random().nextInt(1000000) + 1;
        	}
        }
        
    }


    // Getter methods
    public Player[] getPlayers(){
        return players;
    }

    public Board geBoard(){
        return board;
    }

    public int getGameID(){
        return gameID;
    }


    // Method to create 2 players
    public void createNewPlayers(Leaderboard mainLeaderboard, Scanner scan){
        // Count AI Player
        int ai_count = 0;

        // Start with selecting player
        System.out.println("Select Human Player (enter \"H\") or AI Player (enter \"A\"): ");
        
        //------------------------------------------------------------------------------------
        // Selection for player 1
        System.out.print("Player 1: ");
        String player1 = scan.nextLine();

        // Check user input
        while (!player1.equalsIgnoreCase("H") && !player1.equalsIgnoreCase("A")){
            System.out.println("Invalid input!");
            System.out.println();
            System.out.print("Player 1: ");
            player1 = scan.nextLine();
        }

        // Assign type and name for player 1
        if (player1.equalsIgnoreCase("H")){
            System.out.print("Sign Up or Log In (\"S\" or \"L\"): ");
            String option = scan.nextLine();
            System.out.println();

            // Check user input
            while (!option.equalsIgnoreCase("S") && !option.equalsIgnoreCase("L")){
                System.out.println("Invalid input!");
                System.out.println();
                System.out.print("Sign Up or Log In (\"S\" or \"L\"): ");
                option = scan.nextLine();
                System.out.println();
            }
            while (mainLeaderboard.getPlayers().size() == 0 && option.equalsIgnoreCase("L")){
                System.out.println("It seems like you don't have an account yet. Would you like to sign up instead?");
                System.out.println();
                System.out.print("Sign Up or Log In (\"S\" or \"L\"): ");
                option = scan.nextLine();
                System.out.println();
            }
            
            // Sign up option
            if (option.equalsIgnoreCase("S"))
            	players[0] = signUp(mainLeaderboard, scan);
            
            // Log in option
            else if (option.equalsIgnoreCase("L"))
                players[0] = logIn(mainLeaderboard, scan);
   
        }
        else if (player1.equalsIgnoreCase("A")){
            players[0] = new AIPlayer();
            ai_count++;
        }

        System.out.println();
        System.out.println("Select Human Player (enter \"H\") or AI Player (enter \"A\"): ");
        
        //------------------------------------------------------------------------------------
        // Selection for player 2
        System.out.print("Player 2: ");
        String player2 = scan.nextLine();

        // Check user input
        while (!player2.equalsIgnoreCase("H") && !player2.equalsIgnoreCase("A")){
            System.out.println("Invalid input!");
            System.out.println();
            System.out.print("Player 2: ");
            player2 = scan.nextLine();
        }
        
        while (player2.equalsIgnoreCase("A") && ai_count > 0){
            System.out.println("You cannot create 2 AI players!");
            System.out.println();
            System.out.print("Player 2: ");
            player2 = scan.nextLine();
        }

        // Assign type and name for player 2
        if (player2.equalsIgnoreCase("H")){
        	System.out.print("Sign Up or Log In (\"S\" or \"L\"): ");
            String option = scan.nextLine();
            System.out.println();
            
            // Check user input
            while (!option.equalsIgnoreCase("S") && !option.equalsIgnoreCase("L")){
                System.out.println("Invalid input!");
                System.out.println();
                System.out.print("Sign Up or Log In (\"S\" or \"L\"): ");
                option = scan.nextLine();
                System.out.println();
            }
            while (mainLeaderboard.getPlayers().size() == 0 && option.equalsIgnoreCase("L")){
                System.out.println("It seems like you don't have an account yet. Would you like to sign up instead?");
                System.out.println();
                System.out.print("Sign Up or Log In (\"S\" or \"L\"): ");
                option = scan.nextLine();
                System.out.println();
            }
            
            // Sign up option
            if (option.equalsIgnoreCase("S"))
            	players[1] = signUp(mainLeaderboard, scan);
            
            // Log in option
            else if (option.equalsIgnoreCase("L"))
                players[1] = logIn(mainLeaderboard, scan);
   
        }

        if (player2.equalsIgnoreCase("A")){
            players[1] = new AIPlayer();
        }

    }
    
    // signUp method
    public HumanPlayer signUp(Leaderboard leaderboard, Scanner scan) {
    	System.out.print("Name of Player: ");
        String name = scan.nextLine();
        while (name.isEmpty()){
            System.out.println("Invalid input!");
            System.out.println();
            System.out.print("Name of Player: ");
            name = scan.nextLine();
        }
        HumanPlayer new_human = new HumanPlayer(name);
        while (leaderboard.lookForOldPlayer(name, new_human.getPlayerId()) != -1){
			new_human.setPlayerId(new Random().nextInt(100000000) + 1);
        }
        
        System.out.println();
        System.out.println("Player's ID: " + new_human.getPlayerId());
        
        return new_human;
    }
    
    // logIn method
    public HumanPlayer logIn(Leaderboard leaderboard, Scanner scan) {
    	System.out.print("Name of Player: ");
        String name = scan.nextLine().trim();
        while (name.isEmpty()){
            System.out.println("Invalid input!");
            System.out.println();
            System.out.println("Name of Player: ");
            name = scan.nextLine().trim();
        }
        
        System.out.println();
        
        System.out.print("Player's ID: ");
        int id = scan.nextInt();
        scan.nextLine();
        while (id == 0){
            System.out.println("Invalid input!");
            System.out.println();
            System.out.print("Player's ID: ");
            id = scan.nextInt();
            scan.nextLine();
        }
        
        while (leaderboard.lookForOldPlayer(name, id) == -1) {
        	System.out.println("The Player doesn't exist! Please try again");
            System.out.println();
        	System.out.print("Name of Player: ");
            name = scan.nextLine().trim();
            while (name.isEmpty()){
                System.out.println("Invalid input!");
                System.out.println();
                System.out.println("Name of Player: ");
                name = scan.nextLine().trim();
            }
            
            System.out.println();
            
            System.out.print("Player's ID: ");
            id = scan.nextInt();
            scan.nextLine();
            while (id == 0){
                System.out.println("Invalid input!");
                System.out.println();
                System.out.print("Player's ID: ");
                id = scan.nextInt();
                scan.nextLine();
            }
        }
        
        return (HumanPlayer) leaderboard.getPlayers().get(leaderboard.lookForOldPlayer(name, id));
    }

    // startGame method
    public void startGame(String firstPlayer){
        this.currentTurn = firstPlayer;
    }

    // play method
    public Player play(){
        // Getting name of first player
        Player playing = lookForPlayer(currentTurn);

        //Method to display a header with information about the game 
        displayGameInfo(playing, board.getHeaps().size());


        boolean cancelGame = false; // to cancel game if true

        //Loop will stop if Heap gets to 1.
        //Meaning that player that goes next will loose
        while(!board.isGameOver()){
            // Players take turn making the move
            Move currentMove = playing.makeMove(board);

            //Handeling a cancel this will exit the game
            if(currentMove == null){
                cancelGame = true;
                break;
            }

            //Taking the amount from Heap of the board
            board.removeObjects(currentMove.getHeapIndex(), currentMove.getNumObjectsRemoved());

            //Logging the turn, amount removed, and remainder.
            System.out.println();
            System.out.println("Turn: " + playing.getPlayerName() + "\t\tRemoved: "+ currentMove.getNumObjectsRemoved() + "\tHeap: " + (currentMove.getHeapIndex() + 1));
            
            //Looping the players turns
            playing = nextTurn();
        }
        if(cancelGame){
            return null;
        }
        //Since last player Lost, the next turn Player won
        playing = nextTurn();
        return playing;
    }
    
    private Player lookForPlayer(String name){
        //Loop thorugh Players
        for(int index = 0; index < players.length; index++){
            //Getting name and comparing it to Name been looked for
            if(players[index].getPlayerName().equalsIgnoreCase(name)){
                return players[index];
            }
            
        }
        return null;
    }
    
    
    private Player nextTurn(){
          // simple conditional to change the turn  between both players
        if(players[0].getPlayerName().equals(currentTurn)){
            currentTurn = players[1].getPlayerName();
            return players[1];
        }else{
            currentTurn = players[0].getPlayerName();
            return players[0]; 
        }
    }
      
    private void displayGameInfo(Player first, int HeapSize){
        System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();
        System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();
        System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();
        System.out.println("*****************************************");
        System.out.println("\t\tNim Game");
        System.out.println();
        for(int index = 0; index < players.length; index++){
            System.out.println("Players Name: \t" +players[index].getPlayerName());
        }
        System.out.println("First Turn: "+ first.getPlayerName());
        System.out.println("Heap Size: "+ HeapSize);
        System.out.println("*****************************************");   
    }
    
    // Linear-search function to find the index of an element 
    public int findIndex(Player player) {
    	int result = 0;
    	// Create a for loop through the players array
    	for (int i = 0 ; i < players.length ; i++) {
    		if (players[i] == player) 
    			result = i;
    	}
    	return result;

    } 
}
