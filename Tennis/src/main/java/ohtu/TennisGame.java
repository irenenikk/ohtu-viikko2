package ohtu;

import static ohtu.Score.*;

public class TennisGame {
    
    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == this.player1Name)
            player1Score += 1;
        else
            player2Score += 1;
    }

    public String getScore() {
        String score = "";
        int tempScore=0;
        if (player1Score == player2Score)
        {
            score = getPlayerScore(player1Score, true);
        }
        else if (player1Score >=4 || player2Score >=4)
        {
            score = getAdvantage(player1Score, this.player1Name, player2Score, player2Name);
        }
        else
        {
            score = getPlayerScore(player1Score, false);
            score += "-" + getPlayerScore(player2Score, false);
        }
        return score;
    }

    private String getPlayerScore(int score, boolean all) {
        switch (score)
        {
            case LOVE:
                return  "Love" + (all? "-All" : "");
            case FIFTEEN:
                return  "Fifteen" + (all? "-All" : "");
            case THIRTY:
                return  "Thirty" + (all? "-All" : "");
            case FORTY:
                return  "Forty" + (all? "-All" : "");
            default:
                return  "Deuce";
        }

    }

    private String getAdvantage(int score1, String name1, int score2, String name2) {
        int difference = player1Score - player2Score;
        if (difference==1) return "Advantage " + name1;
        else if (difference ==-1) return "Advantage " + name2;
        else if (difference>=2) return  "Win for " + name1;
        else return "Win for " + name2;
    }
}