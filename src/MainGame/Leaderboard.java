package MainGame;

import java.util.ArrayList;

import Nim_Anh.Score;

public class Leaderboard{
    private ArrayList<Score> scores;

    public Leaderboard() {
        this.scores = new ArrayList<>();
    }

    public void addScore(Score score){
        this.scores.add(score);
    }

    public ArrayList<Score> getTopScore(int numScores) {
        ArrayList<Score> topArray = new ArrayList<>(scores);
        sortScoresDescending(topArray);
        if (numScores < topArray.size()) {
            removeScoresAfterIndex(topArray, numScores);
        }
        return topArray;
    }

    private void sortScoresDescending(ArrayList<Score> scores) {
        int n = scores.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (scores.get(j).getScore() < scores.get(j + 1).getScore()) {
                    Score temp = scores.get(j);
                    scores.set(j, scores.get(j + 1));
                    scores.set(j + 1, temp);
                }
            }
        }
    }

    private void removeScoresAfterIndex(ArrayList<Score> scores, int index) {
        int size = scores.size();
        for (int i = size - 1; i >= index; i--) {
            scores.remove(i);
        }
    }

    public void display(int numScores) {
        ArrayList<Score> top_n = getTopScore(numScores);
        for (Score score : top_n) 
            System.out.println(score);
    }
}