package com.example.animelist.model;

import com.example.animelist.model.enums.RankingsType;
import com.example.animelist.model.enums.Season;

public class Ranking {
    private int rank;
    private RankingsType type;
    private int year;
    private Season season;
    private boolean allTime;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public RankingsType getType() {
        return type;
    }

    public void setType(RankingsType type) {
        this.type = type;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public boolean isAllTime() {
        return allTime;
    }

    public void setAllTime(boolean allTime) {
        this.allTime = allTime;
    }

}
