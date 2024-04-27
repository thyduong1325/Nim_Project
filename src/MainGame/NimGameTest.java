package MainGame;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Nim_Anh.*;
import Nim_Emely.*;
import Nim_Thy.*;

public class NimGameTest {
    // Main leaderboard in memory
    private static Leaderboard leaderboard;

    // Main game
    public static void main(String[] args) {
        // Create the scanner
        Scanner scan = new Scanner(System.in);

        // Create a sentinel
        boolean continuePlaying = true;

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

        // Option 1: New Game
        if (option.equalsIgnoreCase("N")){
            // Start a new game
            Game maiGame = new Game(leaderboard);

            // Create players
            maiGame.createNewPlayers();

            /*
                This loop is the game
                if continue playing is true the game will repeat using same players
                At the begining of each game you can select who first player is.
            */
            while(continuePlaying){
                //Selecting first Player
                System.out.print("Choose the player for the first turn (1 or 2): ");
                int firstTurn = scan.nextInt();
                // if User press Cancel break out of the game
                if(firstTurn == null){
                    break;
                }
                // Creating the New Game and sending players and First turn
                Nim newGame = new Nim(players,firstTurn);
                //winner is returned as a String
                String winner = newGame.play();
                //Handeling a cancel
                if(winner == null){
                    break;
                }
                System.out.println("\n\nWinner is: " + winner );
                // Asking to play again
                continuePlaying = playAgain();
            }
        }

        // Option 2: Resume Game
        else if (option.equalsIgnoreCase("R")){

        }

        // Option 3: Quit
        else if (option.equalsIgnoreCase("Q")){
            System.out.println("Thank you for playing!");
            System.out.println("CISC 230 project 1.");
            System.out.println("Created by Anh, Emely, Thy!");
        }

        
    }
    
  
    
    /**
     * 
     * @param players Array of players playing
     * @return The name of the player that will play first
     */
    public static String selectFirstPlayer(Player[] players){
        //Options of the Players name
        String[] playersNames = {players[0].getName(),players[1].getName()};
        //Getting input for which player is first
        String firstTurn = (String)JOptionPane.showInputDialog(null,"Select Player Name: ","Playing First",JOptionPane.QUESTION_MESSAGE,null,playersNames,playersNames[0]);
        return firstTurn; 
    }
    
    /**
     * Would you like to play Again.
     * @return True if you want to play again
     */
    public static boolean playAgain(){
        //Getting input from user [YES, NOT]
        int again = JOptionPane.showConfirmDialog(null, "Play Again","Another Game",JOptionPane.YES_NO_OPTION );
        //Return true if YES is selected
        if(again == JOptionPane.YES_OPTION){
            return true;
        }else{
            return false;
        }
    
    }
    
    
}
