package Nim_Thy;

//************************************************************
// File Name: AIPlayer2.java            Author: Uyen Thy Duong
//
// Purpose: A subclass of Player that represents an 
// AI-controlled player. This class includes methods for 
// implementing AI logic to make optimal moves to remaove the
// last object from the last heap.
//************************************************************


import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import Nim_Emely.Board;
import Nim_Anh.Move;

public class AIPlayer2 extends Player{
	// Constructor
	public AIPlayer2() {
		super("AI Player");
	}
	
	
	// Method to make a move
	public Move makeMove(Board board) {
        int nimSum = calculateNimSum(board.getHeaps());

        // If the nim sum is zero, make a random move
        if (nimSum == 0) {
            return makeRandomMove(board);
        }

        // Find the heap and number of objects to remove to make the nim sum zero
        for (int i = 0; i < board.getHeaps().size(); i++) {
            int targetXOR = nimSum ^ board.getHeaps().get(i);
            if (targetXOR < board.getHeaps().get(i)) {
                int objectsToRemove = board.getHeaps().get(i) - targetXOR;
                // Check if removing objects doesn't leave only one object in the last heap
                if (board.getTotalObjects() - objectsToRemove > 1 || !isLastHeap(board, i)) {
                    return new Move(i, objectsToRemove);
                }
            }
        }

        // If unable to find a winning move without leaving only one object in the last heap, make a random move
        return makeRandomMove(board);
    }

    // Helper method to calculate the Nim sum of all heaps
    private int calculateNimSum(ArrayList<Integer> heaps) {
        int nimSum = 0;
        for (int heap : heaps) {
            nimSum ^= heap;
        }
        return nimSum;
    }

    // Helper method to check if the heap index is the last heap
    private boolean isLastHeap(Board board, int heapIndex) {
        return heapIndex == board.getHeaps().size() - 1;
    }

    // Helper method to make a random move
    private Move makeRandomMove(Board board) {
    	// Create the random
        Random gen = new Random();
        int heapIndex = gen.nextInt(board.getHeaps().size());
        int objectsToRemove = gen.nextInt(board.getHeaps().get(heapIndex)) + 1;
        return new Move(heapIndex, objectsToRemove);
    }
}
