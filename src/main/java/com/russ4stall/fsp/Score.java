package com.russ4stall.fsp;

/**
 * @author Russ Forstall
 */
public class Score {
    private String homeTeam;
    private String awayTeam;
    private int homeTeamScore;
    private int awayTeamScore;
    //private Date gameDate;
    private String gameDate;

    public Score() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Score score = (Score) o;

        if (homeTeamScore != score.homeTeamScore) return false;
        if (awayTeamScore != score.awayTeamScore) return false;
        if (homeTeam != null ? !homeTeam.equals(score.homeTeam) : score.homeTeam != null) return false;
        if (awayTeam != null ? !awayTeam.equals(score.awayTeam) : score.awayTeam != null) return false;
        return gameDate != null ? gameDate.equals(score.gameDate) : score.gameDate == null;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public String getGameDate() {
        return gameDate;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }
}
