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
        Scanner scan = new Scanner(System.in);

        // Display the prompt and board
        System.out.println();
        System.out.println("---------");
        System.out.println("Your turn, " + super.getPlayerName() + ":");
        System.out.println();
        board.display();
        System.out.println();
        // Take user input for Heap index
        System.out.print("Enter heap index: ");
        int heapIndex = scan.nextInt() - 1;
        scan.nextLine();

        // Check user input
        while (heapIndex < 0 || heapIndex >= board.getHeaps().size() || board.getHeaps().get(heapIndex) == 0){
            System.out.println("Invalid input!");
            System.out.println();
            System.out.print("Enter heap index: ");
            heapIndex = scan.nextInt() - 1;
            scan.nextLine();
        }
        
        System.out.println();
       
        // Take user input for number of objects
        System.out.print("Enter number of objects to remove (-1 to give up): ");
        int objectsToRemove = scan.nextInt();

        // Check user input
        while (objectsToRemove < -1 || objectsToRemove > board.getHeaps().get(heapIndex)){
            System.out.println("Invalid input!");
            System.out.println();
            System.out.print("Enter number of objects to remove (-1 to give up): ");
            objectsToRemove = scan.nextInt();
        }

        while (objectsToRemove == 0){
            System.out.println("You have to remove 1 or more objects from the heap.");
            System.out.println();
            System.out.print("Enter number of objects to remove (-1 to give up): ");
            objectsToRemove = scan.nextInt();
        }
        
        if (objectsToRemove == -1){
            scan.close();
            return null;
        }
        return new Move(heapIndex, objectsToRemove);
    }

}
