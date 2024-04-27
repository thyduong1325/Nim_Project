package MainGame;

import java.util.Scanner;

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
        System.out.println("              Main Menu");
        System.out.println("---------------------------------------");
        System.out.println("New Game (enter \"N\")");
        System.out.println("Resume Game (enter \"R\")");
        System.out.println("Quit (enter \"Q\")");
        System.out.println("---------------------------------------");
        System.out.print("Enter your option: ");
        String option = scan.next();

        // Check user input
        while (!option.equalsIgnoreCase("N") && !option.equalsIgnoreCase("R") && !option.equalsIgnoreCase("Q")){
            System.out.println("Invalid input!");
            System.out.println();
            System.out.print("Enter your option: ");
            option = scan.next();
        }

        // Option 1: New Game
        if (option.equalsIgnoreCase("N")){
            // Start a new game
            Game mainGame = new Game(leaderboard);

            // Create players
            mainGame.createNewPlayers(leaderboard);

        /*
            This loop is the game
            if continue playing is true the game will repeat using same players
            At the begining of each game you can select who first player is.
        */
        while(continuePlaying){
            //Selecting first Player
                System.out.print("Choose the player for the first turn (1 or 2): ");
                int firstTurn = scan.nextInt();

                // Check user input
                while (firstTurn != 1 || firstTurn != 2){
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
                
                // Winner is returned as a String
                String winner = mainGame.play();
                
            //Handeling a cancel
            if(winner == null){
                break;
            }
            System.out.println("\n\nWinner is: " + winner );
                
            // Asking to play again
                continuePlaying = mainGame.playAgain(scan);
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
    
}
