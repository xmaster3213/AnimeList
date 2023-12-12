package com.example.animelist.model;

import com.example.animelist.model.enums.MediaListStatus;

public class StatusStatistics {

    private int count;
    private float meanScore;
    private int minutesWatched;
    private MediaListStatus status;

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

    public int getMinutesWatched() {
        return minutesWatched;
    }

    public void setMinutesWatched(int minutesWatched) {
        this.minutesWatched = minutesWatched;
    }

    public MediaListStatus getStatus() {
        return status;
    }

    public void setStatus(MediaListStatus status) {
        this.status = status;
    }
}
