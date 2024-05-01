package MainGame;

//************************************************************
// File Name: Leaderboard.java
//
// Purpose: Manages the leaderboard, which stores information 
// about players' scores and rankings. It includes methods for 
// adding scores, retrieving high scores, and updating rankings.
//************************************************************

import java.util.ArrayList;
import java.util.Scanner;

import Nim_Thy.Player;

/**
 * Represents a leaderboard that tracks players' scores and rankings.
 */
public class Leaderboard{
    private ArrayList<Player> players;

    /**
     * Constructs a new leaderboard with an empty list of players.
     */
    public Leaderboard() {
        this.players = new ArrayList<Player>();
    }
    
    // Getter methods
    /**
     * Retrieves the list of players in the leaderboard.
     * @return The list of players.
     */
    public ArrayList<Player> getPlayers(){
    	return players;
    }

    /**
     * Adds a human player to the leaderboard.
     * @param Player The human player to add.
     */
    public void addHumanPlayer(Player Player){
        this.players.add(Player);
    }

    /**
     * Retrieves the top players based on their scores.
     * @param numPlayers The number of top players to retrieve.
     * @return The list of top players.
     */
    public ArrayList<Player> getTopPlayer(int numPlayers) {
        ArrayList<Player> topArray = new ArrayList<>(players);
        sortHumanPlayersDescending(topArray);
        if (numPlayers < topArray.size()) {
            removePlayersAfterIndex(topArray, numPlayers);
        }
        return topArray;
    }

    /**
     * Sorts the list of human players in descending order based on their scores.
     * @param HumanPlayers The list of human players to sort.
     */
    private void sortHumanPlayersDescending(ArrayList<Player> HumanPlayers) {
        int n = HumanPlayers.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (HumanPlayers.get(j).getScoreObject().getScore() < HumanPlayers.get(j + 1).getScoreObject().getScore()) {
                	Player temp = HumanPlayers.get(j);
                    HumanPlayers.set(j, HumanPlayers.get(j + 1));
                    HumanPlayers.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * Removes players from the list after a specified index.
     * @param HumanPlayers The list of human players.
     * @param index The index after which players will be removed.
     */
    private void removePlayersAfterIndex(ArrayList<Player> HumanPlayers, int index) {
        int size = HumanPlayers.size();
        for (int i = size - 1; i >= index; i--) {
            HumanPlayers.remove(i);
        }
    }

    /**
     * Displays the leaderboard with the top players and their ranks.
     * @param numHumanPlayers The number of top players to display.
     */
    public void display(int numHumanPlayers, Scanner scan) {
    	System.out.println("---------------------------------------");
        System.out.println("              LEADERBOARD");
        System.out.println("---------------------------------------");
        System.out.println();
        ArrayList<Player> top_n = getTopPlayer(numHumanPlayers);
        for (int rank = 1 ; rank <= top_n.size() ; rank++) {
        	System.out.println("Rank " + rank + ": " + top_n.get(rank - 1).getScoreObject());
            scan.nextLine();scan.nextLine();scan.nextLine(); // Consume "\n" in Score toString()
        }
    }

    /**
     * Checks if a player with the given name exists in the leaderboard.
     * @param name The name of the player to search for.
     * @return True if the player exists, otherwise false.
     */
    public boolean lookForOldPlayer(String name){
        for (Player player : players){
            if(player.getPlayerName().equals(name)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if a player with the given name and ID exists in the leaderboard.
     * @param name The name of the player to search for.
     * @param HumanPlayerId The ID of the player to search for.
     * @return The index of the player if found, otherwise -1.
     */
    public int lookForOldPlayer(String name, int HumanPlayerId){
        for (int i = 0 ; i < players.size() ; i++){
            if(players.get(i).getPlayerName().equals(name) && players.get(i).getPlayerId() == HumanPlayerId){
                return i;
            }
        }
        return -1;
    }
  
    /**
     * Checks if a game with the given ID exists in the leaderboard.
     * @param id The ID of the game to search for.
     * @return The index of the game if found, otherwise -1.
     */
    public int lookForOldGameID(int id){
    	for (int i = 0 ; i < players.size() ; i++){
            if(players.get(i).getScoreObject().getGameID() == id){
                return i;
            }
        }
        return -1;
    }
}
