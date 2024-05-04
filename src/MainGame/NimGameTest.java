package MainGame;

//************************************************************
// File Name: NimGameTest.java            Author: Team
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

        /*
            This loop is the game
            if continue playing is true the game will repeat using same players
            At the begining of each game you can select who first player is.
        */
        while(true){
            // Display the welcome prompt
            System.out.println("===========================================================");
            System.out.println("                  WELCOME TO THE NIM GAME");
            System.out.println("===========================================================");
            System.out.println("                      ! Game rule !");
            System.out.println("!   1) Players take turns removing objects from one heap  !");
            System.out.println("!   2)  On each turn, a player must remove at least       !");
            System.out.println("!             one object from the chosen heap             !");
            System.out.println("!   3) Players can remove as many objects as they want    !");
            System.out.println("!       from the chosen heap, but they cannot             !");
            System.out.println("!  skip their turn or remove objects from multiple heaps  !");
            System.out.println("!   4) The player who removes the last object from        !");
            System.out.println("!               the last heap wins the game               !");
            System.out.println();
            
            // Display the Main Menu
            System.out.println("                        Main Menu");
            System.out.println("-----------------------------------------------------------");
            System.out.println("                    New Game (enter \"N\")");
            System.out.println("                  Leaderboard (enter \"L\")");
            System.out.println("                      Quit (enter \"Q\")");
            System.out.println("-----------------------------------------------------------");
            System.out.print("                   Enter your option: ");
            String option = scan.nextLine();

            // Check user input
            while (!option.equalsIgnoreCase("N") && !option.equalsIgnoreCase("Q") && !option.equalsIgnoreCase("L")){
                System.out.println("                     Invalid input!");
                System.out.println();
                System.out.print("                   Enter your option: ");
                option = scan.nextLine();
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
                scan.nextLine();

                // Check user input
                while (firstTurn != 1 && firstTurn != 2){
                    System.out.println("Invalid input!");
                    System.out.println();
                    System.out.print("Choose the player for the first turn (1 or 2): ");
                    firstTurn = scan.nextInt();
                    scan.nextLine();
                }

                // Start the game
                if (firstTurn == 1)
                    mainGame.startGame(mainGame.getPlayers()[0].getPlayerName());
                else
                    mainGame.startGame(mainGame.getPlayers()[1].getPlayerName());
                
                // Winner is returned as a Player
                Player winner = mainGame.play();

                //Handeling a cancel
                if(winner == null){
                    System.out.println();
                    continue;
                }
                
                // Winner's index in Player array
                int w_index = mainGame.findIndex(winner);
                
                // Find loser's index
                int l_index;
                if (w_index == 0)
                	l_index = 1;
                else
                	l_index = 0;
                
                System.out.println();
                System.out.println();
                System.out.println("Winner is: " + winner.getPlayerName());
                System.out.println();
                
                // Update player's score
                winner.win(mainGame.getGameID());
                mainGame.getPlayers()[l_index].loose(mainGame.getGameID());
                
                // Update the leaderboard
                if (!mainGame.getPlayers()[w_index].getPlayerName().equals("AI Player"))
                	leaderboard.updatePlayer(mainGame.getPlayers()[w_index]);
                if (!mainGame.getPlayers()[l_index].getPlayerName().equals("AI Player"))
                	leaderboard.updatePlayer(mainGame.getPlayers()[l_index]);
                
                // Display the leaderboard
                leaderboard.display(leaderboard.getPlayers().size());
                
                System.out.println();
                System.out.println();
                System.out.println();

            }

            // Option 2: Leaderboard
            else if (option.equalsIgnoreCase("L")){
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println("                     Leaderboard Menu");
                System.out.println("-----------------------------------------------------------");
                if (leaderboard.getPlayers().size() == 0){
                    System.out.println("    Currently, there are no players on the leaderboard. ");
                    System.out.println("         Start playing to see your name here!");
                    System.out.println();
                }
                else{
                    System.out.println("                  All players (enter \"A\")");
                    System.out.println("                  Top players (enter \"T\")");
                    System.out.println("-----------------------------------------------------------");
                    System.out.print("                   Enter your option: ");
                    String l_option = scan.nextLine();

                    // Check user input
                    while (!l_option.equalsIgnoreCase("A") && !l_option.equalsIgnoreCase("T")){
                        System.out.println("                     Invalid input!");
                        System.out.println();
                        System.out.print("                   Enter your option: ");
                        l_option = scan.nextLine();
                    }

                    System.out.println();

                    // Option Display all the players
                    if (l_option.equalsIgnoreCase("A"))
                        leaderboard.display(leaderboard.getPlayers().size());
                    
                    // Option Display top players
                    else if (l_option.equalsIgnoreCase("T")){
                        System.out.print("Enter number of players: ");
                        int top_n = scan.nextInt();
                        scan.nextLine();

                        // Check user input
                        while (top_n <= 0 || top_n > leaderboard.getPlayers().size()){
                            System.out.println("Invalid input!");
                            System.out.println();
                            System.out.print("Enter number of players: ");
                            top_n = scan.nextInt();
                            scan.nextLine();
                        }

                        leaderboard.display(top_n);
                    }
                
                }
                System.out.println();
                System.out.println();
                System.out.println();
            }

            // Option 3: Quit
            else if (option.equalsIgnoreCase("Q")){
                break;
            }

        }
        
        // Display the end prompt
        System.out.println("===========================================================");
        System.out.println("                   THANK YOU FOR PLAYING");
        System.out.println("===========================================================");
        System.out.println("                     CISC 230 project 1");
        System.out.println("                 Created by Anh, Emely, Thy");
        
    }

}
