package MainGame;

import java.util.ArrayList;

import Nim_Anh.Score;

public class Leaderboard{
    private ArrayList<Score> scores;

    public void addScore(Score score){
        this.scores.add(score);
    }

    public ArrayList<Score> getTopScore(int numScores){
        for (int i = 0 ; i < scores.size() - 1 ; i++){
            for (int j = i+ 1 ; j < scores.size() ; j++){
                if (scores.get(j).getScore() > scores.get(i).getScore()){
                    Score tmp = scores.get(i);
                    scores.add(i, scores.get(j));
                    scores.remove(scores.get(i + 1));
                    scores.add(j, tmp);
                    scores.remove(scores.get(j+1));
                }
            }
        }
        return scores.subList(0, numScores);
    }
}