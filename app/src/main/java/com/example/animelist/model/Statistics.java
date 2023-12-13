package com.example.animelist.model;

import com.google.gson.annotations.SerializedName;

public class Statistics {

    @SerializedName("anime")
    private UserStatistics animeStatistics;

    public UserStatistics getAnimeStatistics() {
        return animeStatistics;
    }

    public void setAnimeStatistics(UserStatistics animeStatistics) {
        this.animeStatistics = animeStatistics;
    }
}
