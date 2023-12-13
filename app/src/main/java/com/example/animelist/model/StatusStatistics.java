package com.example.animelist.model;

import com.example.animelist.model.enums.MediaListStatus;

public class StatusStatistics {

    private Integer count;
    private Float meanScore;
    private Integer minutesWatched;
    private MediaListStatus status;

    public Float getHoursWatched() {
        if (minutesWatched == null) {
            return null;
        } else {
            return (float) minutesWatched / 60;
        }
    }

    public Float getDaysWatched() {
        if (minutesWatched == null) {
            return null;
        } else {
            return (float) getHoursWatched() / 24;
        }
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Float getMeanScore() {
        return meanScore;
    }

    public void setMeanScore(Float meanScore) {
        this.meanScore = meanScore;
    }

    public Integer getMinutesWatched() {
        return minutesWatched;
    }

    public void setMinutesWatched(Integer minutesWatched) {
        this.minutesWatched = minutesWatched;
    }

    public MediaListStatus getStatus() {
        return status;
    }

    public void setStatus(MediaListStatus status) {
        this.status = status;
    }
}
