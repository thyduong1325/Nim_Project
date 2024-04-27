package MainGame;

import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import MainGame.*;
import Nim_Anh.*;
import Nim_Emely.*;
import Nim_Thy.*;

public class Game{
    // Instance data
    private Player[] players;
    private Board board;
    private Leaderboard leaderboard;
    private String currentTurn;
    private int gameId;

    // Constructors
    public Game() {
        // Initialize all the data
        players = new Player[2];

        ArrayList<Integer> heaps = new ArrayList<>();
        heaps.add(1);
        heaps.add(3);
        heaps.add(5);
        heaps.add(7);
        board = new Board(heaps);

        gameId = new Random().nextInt(1000000) + 1;
        while (leaderboard.lookForOldGameId(gameId)){
            gameId = new Random().nextInt(1000000) + 1;
        }
    }

    public Game(Leaderboard mainLeaderboard) {
        // Initialize all the data
        players = new Player[2];

        ArrayList<Integer> heaps = new ArrayList<>();
        heaps.add(1);
        heaps.add(3);
        heaps.add(5);
        heaps.add(7);
        board = new Board(heaps);

        leaderboard = mainLeaderboard;

        gameId = new Random().nextInt(1000000) + 1;
        while (leaderboard.lookForOldGameId(gameId)){
            gameId = new Random().nextInt(1000000) + 1;
        }
    }

    public void createNewPlayers(){
        // Create the scanner
        Scanner scan = new Scanner(System.in);

        // Count AI Player
        int ai_count = 0;

        // Start with selecting player
        System.out.println("Select Human Player (enter \"H\") or AI Player (enter \"A\"): ");
        
        //------------------------------------------------------------------------------------
        // Selection for player 1
        System.out.print("Player 1: ");
        String player1 = scan.next();

        // Check user input
        while (!player1.equals("H") && !player1.equals("A")){
            System.out.println("Invalid input!");
            System.out.println();
            System.out.print("Player 1: ");
            player1 = scan.next();
        }

        // Assign type and name for player 1
        if (player1.equals("H")){
            System.out.print("Name of Player 1: ");
            String name1 = scan.next();
            while (name1.length() == 0 || name1.charAt(0) == ' '){
                System.out.println("Invalid input!");
                System.out.println();
                System.out.print("Name of Player 1: ");
                name1 = scan.next();
            }
            while (leaderboard.lookForOldPlayer(name1)){
                System.out.println("This name is already exist!");
                System.out.println();
                System.out.print("Name of Player 1: ");
                name1 = scan.next();
            }
            players[0] = new HumanPlayer(name1);
        }
        else if (player1.equals("A")){
            players[0] = new AIPlayer();
            ai_count++;
        }

        System.out.println();

        //------------------------------------------------------------------------------------
        // Selection for player 2
        System.out.print("Player 2: ");
        String player2 = scan.next();

        // Check user input
        while (!player2.equals("H") && !player2.equals("A")){
            System.out.println("Invalid input!");
            System.out.println();
            System.out.print("Player 2: ");
            player2 = scan.next();
        }

        // Assign type and name for player 2
        if (player2.equals("H")){
            System.out.print("Name of Player 2: ");
            String name2 = scan.next();
            while (name2.length() == 0 || name2.charAt(0) == ' '){
                System.out.println("Invalid input!");
                System.out.println();
                System.out.print("Name of Player 2: ");
                name2 = scan.next();
            }
            while (leaderboard.lookForOldPlayer(name2)){
                System.out.println("This name is already exist!");
                System.out.println();
                System.out.print("Name of Player 1: ");
                name2 = scan.next();
            }
            players[0] = new HumanPlayer(name2);
        }
        
        while (player2.equals("A") && ai_count > 0){
            System.out.println("You cannot create 2 AI players!");
            System.out.println();
            System.out.print("Player 2: ");
            player2 = scan.next();
        }
        if (player2.equals("A")){
            players[1] = new AIPlayer();
        }

        // Close the scanner
        scan.close();
    }

    // startGame method
    public void startGame(String firstPlayer){
        this.currentTurn = firstPlayer;
    }

    // play method
    public String play(){
        // Getting name of first player
        Player playing = lookForPlayer(currentTurn);

        //Method to display a header with information about the game 
        displayGameInfo(playing, board.getHeaps().size());


        boolean cancelGame = false; // to cancel game if true

        //Loop will stop if Heap gets to 1.
        //Meaning that player that goes next will loose
        while(board.getHeaps().size() > 1){
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
            System.out.println("Turn: \t" + playing.getPlayerName() + "\tRemoved: \t"+ currentMove.getNumObjectsRemoved() + "\tHeap: \t" + (currentMove.getHeapIndex() + 1));
            
            //Looping the players turns
            playing = nextTurn();
        }
        if(cancelGame){
            return null;
        }
        //Since last player Lost, the next turn Player won
        playing = nextTurn();
        return playing.getPlayerName();
    }
    
    private Player lookForPlayer(String name){
        //Loop thorugh Players
        for(int index = 0; index < players.length; index++){
            //Getting name and comparing it to Name been looked for
            if(players[index].getPlayerName().equals(name)){
                return players[index];
            }
            
        }
        return null;
    }
    
    
    private Player nextTurn(){
          // simple conditional to change the turn  between both players
        if(players[0].getPlayerName() == currentTurn){
            currentTurn = players[1].getPlayerName();
            return players[1];
        }else{
            currentTurn = players[0].getPlayerName();
            return players[0]; 
        }
    }
      
      private void displayGameInfo(Player first, int HeapSize){
          System.out.println("*****************************************");
          System.out.println("\t\tNim Game\n");
          for(int index = 0; index < players.length; index++){
              System.out.println("Players Name: \t" +players[index].getPlayerName());
          }
          System.out.println("First Turn: "+ first.getPlayerName());
          System.out.println("Heap Size: "+ HeapSize);
          System.out.println("*****************************************");   
      }  
}

