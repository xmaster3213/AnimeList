package com.example.animelist.model;

import com.google.gson.annotations.SerializedName;

public class BodyAnimeList {

    @SerializedName("data")
    private DataAnimeList dataAnimeList;

    public DataAnimeList getData() {
        return dataAnimeList;
    }

    public void setData(DataAnimeList dataAnimeList) {
        this.dataAnimeList = dataAnimeList;
    }
}
