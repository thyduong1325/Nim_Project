package Nim_Thy;

//************************************************************
// File Name: HumanPlayer.java            Author: Uyen Thy Duong
//
// Purpose: A subclass of Player that represents a human player. 
// This class may include methods for interacting with the player 
// to input moves.
//************************************************************

import java.util.Scanner;
import Nim_Emely.Board;
import Nim_Anh.Move;

public class HumanPlayer extends Player{
	// Constructor
	public HumanPlayer(String in_name) {
		super(in_name);
	}
	
	// The player take turn and make move
    @Override
	public Move makeMove(Board board) {
        // Create the scanner
        Scanner scanner = new Scanner(System.in);

        // Display the prompt and board
        System.out.println("Your turn, " + super.getPlayerName() + ":");
        
        board.display();
        
        // Take user input for Heap index
        System.out.print("Enter heap index: ");
        int heapIndex = scanner.nextInt() - 1;

        // Check user input
        while (heapIndex < 0 || heapIndex >= board.getHeaps().size()){
            System.out.println("Invalid input!");
            System.out.println();
            System.out.print("Enter heap index: ");
            heapIndex = scanner.nextInt() - 1;
        }
        
        System.out.println();
       
        // Take user input for number of objects
        System.out.print("Enter number of objects to remove (-1 to give up): ");
        int objectsToRemove = scanner.nextInt();

        // Check user input
        while (objectsToRemove < -1 || objectsToRemove > board.getHeaps().get(heapIndex)){
            System.out.println("Invalid input!");
            System.out.println();
            System.out.print("Enter number of objects to remove: ");
            objectsToRemove = scanner.nextInt();
        }
        
        if (objectsToRemove == -1){
            scanner.close();
            return null;
        }
        
        // Close the scanner
        scanner.close();
        return new Move(heapIndex, objectsToRemove);
    }

}
