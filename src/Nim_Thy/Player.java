package Nim_Thy;

//************************************************************
// File Name: Player.java            Author: Uyen Thy Duong
//
// Purpose: Represents a player in the game. Each player 
// has a name, and possibly other attributes such as a unique 
// identifier or gameplay statistics.
//************************************************************

import java.util.Random;
import Nim_Anh.*;
import Nim_Emely.*;

public abstract class Player {
	// Create the random
	Random gen = new Random();
	
	// Instance data
	protected String name;
	protected int playerId;
	protected Score score;

	// Constructors
	public Player(String in_name) {
		// Check the in_name
		boolean check = false;
		
		// Check if the in_name is null or not
		if (in_name == null) {
			System.out.println("Invalid name!");
		}
		
		// Check if the in_name has valid characters or not
		for (int i = 0 ; i < in_name.length(); i++) {
			if (in_name.charAt(i) != ' ') {
				check = true;
			}
		}
		
		// Assign to the name of the player
		if (check) {
			this.name = in_name;
			this.score = new Score(name, 0);
			this.playerId = gen.nextInt(100000000) + 1;
		}
			
		else
			System.out.println("Invalid name!");
	}
	
	
	// Getter methods
	public String getPlayerName() {
		return this.name;
	}
	
	public int getPlayerId() {
		return this.playerId;
	}
	
	public Score getScoreObject() {
		return this.score;
	}
	
	
	// Setter mothod - Fix the name
	public void setPlayerName(String in_name) {
		// Check the in_name
		boolean check = false;
		
		// Check if the in_name is null or not
		if (in_name == null) {
			System.out.println("Invalid name!");
		}
		
		// Check if the in_name has valid characters or not
		for (int i = 0 ; i < in_name.length(); i++) {
			if (in_name.charAt(i) != ' ') {
				check = true;
			}
		}
		
		// Assign to the name of the player
		if (check) {
			this.name = in_name;
		}
			
		else
			System.out.println("Invalid name!");
	}
	
	public void setPlayerId(int id) {
		this.playerId = id;
	}
	
	
	// Win method - Update the score
	public void win(int game_id) {
		score.setScore(score.getScore() + 1);
		score.setGameID(game_id);
	}

	public void loose(int game_id){
		score.setGameID(game_id);
	}

	// Player make move
	public abstract Move makeMove(Board board);
}
