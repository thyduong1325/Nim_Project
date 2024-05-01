package Nim_Thy;

//************************************************************
// File Name: AIPlayer.java            Author: Uyen Thy Duong
//
// Purpose: A subclass of Player that represents an 
// AI-controlled player. This class includes methods for 
// implementing AI logic to make optimal moves to remaove the
// last object from the last heap.
//************************************************************


import java.util.ArrayList;
import java.util.Random;
import Nim_Emely.Board;
import Nim_Anh.Move;

public class AIPlayer extends Player{
	// Constructor
	public AIPlayer() {
		super("AI Player");
		super.setPlayerId(0);
	}
	
	
	/*
	 * In the game of Nim, winning by removing the last object from 
	 * the last heap relies on understanding and manipulating the concept 
	 * of Nim sum.
	 * 
	 * 1. Understanding Nim Sum:
	 * - The Nim sum is a binary XOR operation performed on the number of 
	 * objects in each heap.
	 * - It represents the overall state of the game and determines which 
	 * player has the winning strategy.
	 * - When the Nim sum is zero, it means the current player is in a 
	 * losing position, and their opponent has a winning strategy.
	 * 
	 * 2. Game Strategy:
	 * - The winning strategy in Nim revolves around ensuring that your 
	 * opponent is left with a Nim sum of zero on their turn.
	 * - If it's your turn and the Nim sum is already zero, you want to 
	 * avoid disturbing that balance, as any move you make will give your 
	 * opponent a winning strategy.
	 * - Conversely, if it's your turn and the Nim sum is non-zero, you 
	 * aim to strategically reduce the Nim sum to zero, thereby forcing 
	 * your opponent into a losing position.
	 * 
	 * 3. Removing the Last Object from the Last Heap:
	 * - The crucial move to win the game is to leave your opponent with 
	 * the last object in the last heap.
	 * - Achieving this involves making a series of moves that strategically 
	 * manipulate the Nim sum to ensure that your opponent is forced into 
	 * this losing position.
	 * - By carefully calculating the Nim sum and making moves that lead to a 
	 * Nim sum of zero, you set up the conditions where your opponent must take 
	 * the last object from the last heap, securing your victory.
	 * 
	 * 4. AI Strategy:
	 * - When designing an AI player for Nim, the primary goal is to create a 
	 * strategy that consistently leads to winning positions.
	 * - The AI evaluates the current state of the game, calculates the Nim sum, 
	 * and then makes moves that either maintain a winning position or force the 
	 * opponent into a losing position.
	 * - The AI player should prioritize moves that lead to a Nim sum of zero for 
	 * the opponent, ultimately aiming to leave the opponent with the last object 
	 * in the last heap, ensuring victory.
	 * 
	 */
	
	// Method to make a move
	@Override
    public Move makeMove(Board board) {
		// Display the prompt and board
		System.out.println();
        System.out.println("---------");
		System.out.println("A.I. turn:");
		System.out.println();
        
        board.display();
        
        int nimSum = calculateNimSum(board.getHeaps());

        // If the current xorSum is zero, the AI will make a random move
        if (nimSum == 0) {
            return makeRandomMove(board);
        }

        // Find the heap and the number of objects to remove to make the xorSum zero
        for (int i = 0; i < board.getHeaps().size(); i++) {
            int targetXOR = board.getHeaps().get(i) ^ nimSum;
            if (targetXOR < board.getHeaps().get(i)) {
                int objectsToRemove = board.getHeaps().get(i) - targetXOR;
                return new Move(i, objectsToRemove);
            }
        }

        // If no winning move is found, make a random move
        return makeRandomMove(board);
    }

    // Method to calculate the XOR sum of the heaps
    private int calculateNimSum(ArrayList<Integer> heaps) {
        int nimSum = 0;
        for (int heap : heaps) {
        	nimSum ^= heap;
        }
        return nimSum;
    }

    // Method to make a random move
    private Move makeRandomMove(Board board) {
    	// Create the random
        Random gen = new Random();
        int heapIndex = gen.nextInt(board.getHeaps().size());
		while (board.getHeaps().get(heapIndex) == 0){
			heapIndex = gen.nextInt(board.getHeaps().size());
		}
        int objectsToRemove = gen.nextInt(board.getHeaps().get(heapIndex)) + 1;
        return new Move(heapIndex, objectsToRemove);
    }
}
