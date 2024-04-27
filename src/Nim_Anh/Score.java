package Nim_Anh;

public class Score {
    private String playerName;
    private int score = 0;
    private int gameID;

    public Score(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }
    
    public Score(String playerName, int score, int gameID) {
        this.playerName = playerName;
        this.score = score;
        this.gameID = gameID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String toString(){
        return "Player Name: " + playerName + "\nScore: " + score + "\nGameID: " + gameID + "\n";
    }
}
