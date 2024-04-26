package MainGame;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import MainGame.*;
import Nim_Anh.*;
import Nim_Emely.*;
import Nim_Thy.*;

public class NimGameTest {
    public static void main(String[] args) {
        // Create the scanner
        Scanner scan = new Scanner(System.in);

        // Create 
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

        // Option 1 : New Game










        
        //Creation of a 2 players empty array
        Player[] players = new Player[2]; 
        boolean continuePlaying = true;
        
        //Using JOption Panels to get information about type
        //of players and their names
        players[0] = createPlayer();
        players[1] = createPlayer();
        //quit game if cancel if pressed
        if(players[0] == null || players[1] == null){
            continuePlaying = false; 
        }
        /*
            This loop is the game
            if continue playing is true the game will repeat using same players
            At the begining of each game you can select who first player is.
        */
        while(continuePlaying){
            //Selecting first Player
            String firstTurn = selectFirstPlayer(players);
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
    
    /**
     * This function ask the user to select a type of Player and assign a name.
     * If null is returned in name or type this function will return as result.
     * @return A player object created base on the selection of user.
    */
    public static Player createPlayer(){
        //Options array of players
        String[] typePlayers = {"Human","BelowAverageComputer","SmartComputer"};
        
        //Getting user input for Player
        String type = (String)JOptionPane.showInputDialog(null,"Select Player:","Create Player",JOptionPane.QUESTION_MESSAGE,null,typePlayers,typePlayers[0]);
        String name = JOptionPane.showInputDialog(type +"'s name: ");
        
        //Return a null if cancel
        if(type == null || name == null){
            return null;
        }
        
        //Depending on type selected a new type of Player will be created
        if(type.equals(typePlayers[0])){
            return  new Human(name); 
        }
        if(type.equals(typePlayers[1])){
            return  new BelowAverageComputer(name);
        }
        if(type.equals(typePlayers[2])){
            return  new SmartComputer(name);
        }else{
            return null;
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
