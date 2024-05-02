package Nim_Anh;

//************************************************************
// File Name: Score.java            Author: Anh Bui
//
// Purpose: The Score class encapsulates information about a 
// player's score in a game. It allows for storing and retrieving 
// the player's name, score value, and associated game ID. The 
// class provides methods to access and modify this information, 
// ensuring proper encapsulation and abstraction. The toString() 
// method is overridden to generate a formatted string representation 
// of the Score object, which can be useful for displaying or 
// logging player scores. This class represents a player's score 
// in a game, including their name, score value, and optionally a
// game ID.
//************************************************************

public class Score {
    private String playerName; // The name of the player associated with the score
    private int score = 0; // The player's score initialized to zero
    private int gameID; // The ID of the game (if applicable)

    // Constructor to initialize a Score object with player name and score
    public Score(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }
    
    // Constructor to initialize a Score object with player name, score, and game ID
    public Score(String playerName, int score, int gameID) {
        this.playerName = playerName;
        this.score = score;
        this.gameID = gameID;
    }

    // Getter method to retrieve the player's name
    public String getPlayerName() {
        return playerName;
    }

    // Setter method to set the player's name
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    // Getter method to retrieve the player's score
    public int getScore() {
        return score;
    }

    // Setter method to set the player's score
    public void setScore(int score) {
        this.score = score;
    }

    // Getter method to retrieve the game ID associated with the score
    public int getGameID() {
        return gameID;
    }

    // Setter method to set the game ID associated with the score
    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    // Override toString() method to return a formatted string representation of the Score object
    public String toString(){
        return "\nPlayer Name: " + playerName + "\nScore: " + score + "\nGameID: " + gameID ;
    }
}
