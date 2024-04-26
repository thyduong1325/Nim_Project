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
	public Move makeMove(Board board, Scanner scanner) {
        System.out.println("Your turn, " + super.getPlayerName() + ":");
        
        board.display();
        
        System.out.print("Enter heap index: ");
        int heapIndex = scanner.nextInt();
        
        System.out.println();
        
        System.out.print("Enter number of objects to remove: ");
        int objectsToRemove = scanner.nextInt();
        
        return new Move(heapIndex, objectsToRemove);
    }

}
