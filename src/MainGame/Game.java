package MainGame;

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

    // Constructor
    public Game() {
        // Initialize all the data
        players = new Player[2];

        ArrayList<Integer> heaps = new ArrayList<>();
        heaps.add(1);
        heaps.add(3);
        heaps.add(5);
        heaps.add(7);
        board = new Board(heaps);

        leaderboard = new Leaderboard();
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
    public void startGame(){
        // Create the scanner
        Scanner scan = new Scanner(System.in);

        // Display the welcome prompt
        System.out.println("=======================================");
        System.out.println("        WELCOME TO THE NIM GAME");
        System.out.println("=======================================");
        System.out.println();
        
        // Display the Main Menu
        System.out.println("New Game (enter \"N\")");
        System.out.println("Resume Game (enter \"R\")");
        System.out.println("Quit (enter \"Q\")");
        System.out.println("----------------------------");
        System.out.print("Enter your option: ");
        String option = scan.next();

        // Check user input
        while (!option.equals("N") && !option.equals("R") && !option.equals("Q")){
            System.out.println("Invalid input!");
            System.out.println();
            System.out.print("Enter your option: ");
            option = scan.next();
        }

        // Option 1 : New Game
        

    }

    public String play(){
        
        
        Player playing = lookForPlayer(currentTurn); //getting name of first player
        Pile newPile = new Pile(); //create new pile
        //Method to display a header with information about the game 
        displayGameInfo(playing, newPile.getAmount());
        boolean cancelGame = false; // to cancel game if true
        //Loop will stop if Pile gets to 1.
        //Meaning that player that goes next will loose
        while(newPile.getAmount()>1){
            //Polymorphically getting the amount been removed by the Players
            int amountTakenByPlayer = playing.move(newPile.getAmount());
            //Handeling a cancel this will exit the game
            if(amountTakenByPlayer == -1){
                cancelGame = true;
                break;
            }
            //Taking the amount from Pile
            newPile.take(amountTakenByPlayer);
            //Logging the turn, amount removed, and remainder.
            System.out.println("Turn: \t" + playing.getName() + "\tRemoved: \t"+ amountTakenByPlayer+ "\tPile: \t" + newPile.getAmount());
            //Looping the players turns
            playing = nextTurn();
        }
        if(cancelGame){
            return null;
        }
        //Since last player Lost, the next turn Player won
        playing = nextTurn();
        return playing.getName();
    }
    
    /**
     * Given a String Name it will return the Player. 
     * @param name Name of Player
     * @return Player with given Name
     */
    private Player lookForPlayer(String name){
        //Loop thorugh Players
        for(int index =0; index < players.length; index++){
            //Getting name and comparing it to Name been looked for
            if(players[index].getName().equals(name)){
                return players[index];
            }
            
        }
        return null;
    }
    
    /**
     * Returns the next player 
     * @return Next Player
     */
    
      private Player nextTurn(){
          // simple conditional to change the turn  between both players
        if(players[0].getName() == currentTurn){
            currentTurn = players[1].getName();
            return players[1];
        }else{
            currentTurn = players[0].getName();
            return players[0]; 
        }
    }
      
      /**
       * Logs a header for the game with the information of the Player, pile size
       * and who goes first.
       * @param first Player that goes first
       * @param pileSize Size of pile when created
       */
      private void displayGameInfo(Player first, int pileSize){
          System.out.println("*****************************************");
          System.out.println("\t\tNim Game\n");
          for(int index = 0; index < players.length; index++){
              System.out.println("Players Name: \t" +players[index].getName());
          }
          System.out.println("First Turn: "+ first.getName());
          System.out.println("Pile Size: "+ pileSize);
          System.out.println("*****************************************");   
      }  
}

