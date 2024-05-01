package MainGame;

import java.util.ArrayList;
import Nim_Thy.Player;

public class Leaderboard{
    private ArrayList<Player> players;

    public Leaderboard() {
        this.players = new ArrayList<Player>();
    }
    
    // Getter methods
    public ArrayList<Player> getPlayers(){
    	return players;
    }

    public void addHumanPlayer(Player Player){
        this.players.add(Player);
    }

    public ArrayList<Player> getTopPlayer(int numPlayers) {
        ArrayList<Player> topArray = new ArrayList<>(players);
        sortHumanPlayersDescending(topArray);
        if (numPlayers < topArray.size()) {
            removePlayersAfterIndex(topArray, numPlayers);
        }
        return topArray;
    }

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

    private void removePlayersAfterIndex(ArrayList<Player> HumanPlayers, int index) {
        int size = HumanPlayers.size();
        for (int i = size - 1; i >= index; i--) {
            HumanPlayers.remove(i);
        }
    }

    public void display(int numHumanPlayers) {
    	System.out.println("---------------------------------------");
        System.out.println("              LEADERBOARD");
        System.out.println("---------------------------------------");
        System.out.println();
        ArrayList<Player> top_n = getTopPlayer(numHumanPlayers);
        for (int rank = 1 ; rank <= top_n.size() ; rank++) 
        	System.out.println("Rank " + rank + ": " + top_n.get(rank - 1).getScoreObject());
    }

    public boolean lookForOldPlayer(String name){
        for (Player player : players){
            if(player.getPlayerName().equals(name)){
                return true;
            }
        }
        return false;
    }
    
	public int lookForOldPlayer(String name, int HumanPlayerId){
	        for (int i = 0 ; i < players.size() ; i++){
	            if(players.get(i).getPlayerName().equals(name) && players.get(i).getPlayerId() == HumanPlayerId){
	                return i;
	            }
	        }
	        return -1;
	    }
  
    
    public int lookForOldGameID(int id){
    	for (int i = 0 ; i < players.size() ; i++){
            if(players.get(i).getScoreObject().getGameID() == id){
                return i;
            }
        }
        return -1;
    }
}
