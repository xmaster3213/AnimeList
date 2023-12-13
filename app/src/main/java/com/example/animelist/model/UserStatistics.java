package com.example.animelist.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserStatistics {

    private Integer count;
    private Float meanScore;
    private Float standardDeviation;
    private Integer minutesWatched;
    private Integer episodesWatched;
    @SerializedName("statuses")
    private List<StatusStatistics> statusStatistics;
    @SerializedName("startYears")
    private List<StartYearStatistics> startYearStatistics;

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

    public Float getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(Float standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    public Integer getMinutesWatched() {
        return minutesWatched;
    }

    public void setMinutesWatched(Integer minutesWatched) {
        this.minutesWatched = minutesWatched;
    }

    public Integer getEpisodesWatched() {
        return episodesWatched;
    }

    public void setEpisodesWatched(Integer episodesWatched) {
        this.episodesWatched = episodesWatched;
    }

    public List<StatusStatistics> getStatusStatistics() {
        return statusStatistics;
    }

    public void setStatusStatistics(List<StatusStatistics> statusStatistics) {
        this.statusStatistics = statusStatistics;
    }

    public List<StartYearStatistics> getStartYearStatistics() {
        return startYearStatistics;
    }

    public void setStartYearStatistics(List<StartYearStatistics> startYearStatistics) {
        this.startYearStatistics = startYearStatistics;
    }
}
