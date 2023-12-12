package com.example.animelist.model;

public class UserStatistics {

    private int count;
    private float meanScore;
    private float standardDeviation;
    private int minutesWatched;
    private int episodesWatched;
    private StatusStatistics status;
    private StartYearStatistics startYear;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getMeanScore() {
        return meanScore;
    }

    public void setMeanScore(float meanScore) {
        this.meanScore = meanScore;
    }

    public float getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(float standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    public int getMinutesWatched() {
        return minutesWatched;
    }

    public void setMinutesWatched(int minutesWatched) {
        this.minutesWatched = minutesWatched;
    }

    public int getEpisodesWatched() {
        return episodesWatched;
    }

    public void setEpisodesWatched(int episodesWatched) {
        this.episodesWatched = episodesWatched;
    }

    public StatusStatistics getStatus() {
        return status;
    }

    public void setStatus(StatusStatistics status) {
        this.status = status;
    }

    public StartYearStatistics getStartYear() {
        return startYear;
    }

    public void setStartYear(StartYearStatistics startYear) {
        this.startYear = startYear;
    }
}
