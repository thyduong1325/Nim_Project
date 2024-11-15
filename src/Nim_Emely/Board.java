package Nim_Emely;

//************************************************************
// File Name: Board.java            Author: Emely Cortez Hernandez
//
// Purpose: Represents the game board, which consists of a certain 
// number of heaps or piles of objects (e.g., sticks, stones). This 
// class includes methods for initializing the board, displaying
// the current state, and updating the board after each turn.
//************************************************************


import java.util.ArrayList;

public class Board {
    private ArrayList<Integer> heaps;  // Declare an ArrayList to store the number of objects in each heap.
    private int max; // Declare the max value of the arraylist
    private int[] heapSpace;

    // Constructor to initialize the board with the number of objects per heap
    public Board(ArrayList<Integer> initialHeaps) {
        this.heaps = new ArrayList<>(initialHeaps);  // Create a new ArrayList from another to ensure it's a separate object.
        this.max = heaps.get(heaps.size() - 1); // Create the max value of the arraylist
        this.heapSpace = new int[heaps.size()];
        for (int i = 0; i < heapSpace.length; i++){
            heapSpace[i] = (int) ((max - heaps.get(i)) / 2);
        }
    }

    // Display the current state of the board
    public void display() {
        System.out.println("Current state of the board:");  // Print a header to show the board's current state.
        for (int i = 0; i < heaps.size(); i++) {  // Loop through each heap.
            System.out.print("Heap " + (i + 1) + ":   ");  // Print the number of objects in each heap using string concatenation.
            if (heaps.get(i) == 0)
                    System.out.println();
            else
                System.out.print("    ".repeat(heapSpace[i]));
            for (int j = 0; j < heaps.get(i); j++){
                if (j == heaps.get(i) -1)
                    System.out.println("|");
                else
                    System.out.print(String.format("%-4s", "|"));
            }
        }
    }

    // Method to make a move: remove a number of objects from a specified heap
    public void removeObjects(int heapIndex, int numberOfObjects) {
        // Check if the move is valid
        if (heapIndex < 0 || heapIndex >= heaps.size() || heaps.get(heapIndex) < numberOfObjects || numberOfObjects < 1) {
        	// Check if the specified heap index is valid, if there are enough objects to remove, and if at least one object is being removed

            System.out.println("Invalid move. Please try again.");  // Print error message if the move is invalid.
        }

        // Update the heap after a valid move
        heaps.set(heapIndex, heaps.get(heapIndex) - numberOfObjects);  // Subtract the number of objects removed from the specified heap.
    }

    // Check if the game is over (i.e., all heaps are empty)
    public boolean isGameOver() {
        for (int heap : heaps) {  // Iterate over each heap in the list.
            if (heap > 0) {  // Check if there are still objects in the heap.
                return false;  // If any heap has objects, return false.
            }
        }
        return true;  // If no heaps have objects, return true indicating the game is over.
    }

    // Getter methods
    public ArrayList<Integer> getHeaps(){
        return heaps;
    }
}
