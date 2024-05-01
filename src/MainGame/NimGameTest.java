package MainGame;

//************************************************************
// File Name: NimGameTest.java            Author: Uyen Thy Duong
//
// Purpose: This class represents the main entry point for the Nim game.
// It handles user interaction, game setup, and gameplay flow.
//************************************************************

import java.util.Scanner;
import Nim_Thy.*;

public class NimGameTest {
    // Main leaderboard in memory
    private static Leaderboard leaderboard = new Leaderboard();

    // Main game
    public static void main(String[] args) {
        // Create the scanner
        Scanner scan = new Scanner(System.in);

        // Create a sentinel
        boolean continuePlaying = true;

        /*
            This loop is the game
            if continue playing is true the game will repeat using same players
            At the begining of each game you can select who first player is.
        */
        while(continuePlaying){
            // Display the welcome prompt
            System.out.println("===========================================================");
            System.out.println("                  WELCOME TO THE NIM GAME");
            System.out.println("===========================================================");
            System.out.println("                       ! Game rule !");
            System.out.println("! Remove the last object of the last heap to win the game !");
            System.out.println();
            
            // Display the Main Menu
            System.out.println("                         Main Menu");
            System.out.println("-----------------------------------------------------------");
            System.out.println("                    New Game (enter \"N\")");
            System.out.println("                      Quit (enter \"Q\")");
            System.out.println("-----------------------------------------------------------");
            System.out.print("                    Enter your option: ");
            String option = scan.next();

            // Check user input
            while (!option.equalsIgnoreCase("N") && !option.equalsIgnoreCase("Q")){
                System.out.println("Invalid input!");
                System.out.println();
                System.out.print("Enter your option: ");
                option = scan.next();
            }
            
            System.out.println();
            

            // Option 1: New Game
            if (option.equalsIgnoreCase("N")){
                // Start a new game
                Game mainGame = new Game(leaderboard);

                // Create players
                mainGame.createNewPlayers(leaderboard, scan);
                System.out.println();

                //Selecting first Player
                System.out.print("Choose the player for the first turn (1 or 2): ");
                int firstTurn = scan.nextInt();

                // Check user input
                while (firstTurn != 1 && firstTurn != 2){
                    System.out.println("Invalid input!");
                    System.out.println();
                    System.out.print("Choose the player for the first turn (1 or 2): ");
                    firstTurn = scan.nextInt();
                }

                // Start the game
                if (firstTurn == 1)
                    mainGame.startGame(mainGame.getPlayers()[0].getPlayerName());
                else
                    mainGame.startGame(mainGame.getPlayers()[1].getPlayerName());
                
                // Winner is returned as a Player
                Player winner = mainGame.play();
                
                // Winner's index in Player array
                int w_index = mainGame.findIndex(winner);
                
                // Find loser's index
                int l_index;
                if (w_index == 0)
                	l_index = 1;
                else
                	l_index = 0;
                
                    
                //Handeling a cancel
                if(winner == null){
                    break;
                }
                System.out.println("\n\nWinner is: " + winner.getPlayerName());
                
                // Update player's score
                winner.win(mainGame.getGameID());
                mainGame.getPlayers()[l_index].loose(mainGame.getGameID());
                
                // Update the leaderboard
                if (!mainGame.getPlayers()[w_index].getPlayerName().equals("AI Player"))
                	leaderboard.addHumanPlayer(mainGame.getPlayers()[w_index]);
                if (!mainGame.getPlayers()[l_index].getPlayerName().equals("AI Player"))
                	leaderboard.addHumanPlayer(mainGame.getPlayers()[l_index]);
                
                // Display the leaderboard
                leaderboard.display(leaderboard.getPlayers().size());
                
                System.out.println();
                System.out.println();
                System.out.println();

            }

            // Option 2: Quit
            else if (option.equalsIgnoreCase("Q")){
                break;
            }

        }
        
        // Display the end prompt
        System.out.println("===========================================================");
        System.out.println("                  THANK YOU FOR PLAYING");
        System.out.println("===========================================================");
        System.out.println("                   CISC 230 project 1");
        System.out.println("               Created by Anh, Emely, Thy");
        
    }

}
