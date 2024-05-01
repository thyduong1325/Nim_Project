package Nim_Anh;

//************************************************************
// File Name: Move.java            Author: Anh Bui
//
// Purpose: This Move class provides a structured way to represent 
// and manipulate moves within our game. By encapsulating data 
// (heap index and number of objects removed) and providing methods 
// to access and modify this data, the class promotes encapsulation 
// and abstraction, making it easier to manage game moves within a 
// larger game implementation. This class represents a move in a game,
// specifically for a game like Nim where objects are removed from heaps.
//************************************************************

public class Move {
    private int HIndex; // The index of the heap from which objects are being removed
    private int numObjectsRemoved; // The number of objects (or stones) removed from the heap

    // Constructor to initialize a Move object with a heap index and number of objects removed
    public Move(int heapIndex, int numObjectsRemoved) {
        this.HIndex = heapIndex;
        this.numObjectsRemoved = numObjectsRemoved;
    }

    // Getter method to retrieve the heap index of the move
    public int getHeapIndex() {
        return HIndex;
    }

    // Setter method to set the heap index of the move
    public void setHeapIndex(int heapIndex) {
        this.HIndex = heapIndex;
    }

    // Getter method to retrieve the number of objects removed in the move
    public int getNumObjectsRemoved() {
        return numObjectsRemoved;
    }

    // Setter method to set the number of objects removed in the move
    public void setNumObjectsRemoved(int numObjectsRemoved) {
        this.numObjectsRemoved = numObjectsRemoved;
    }
}
