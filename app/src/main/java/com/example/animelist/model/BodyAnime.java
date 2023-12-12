package com.example.animelist.model;

import com.google.gson.annotations.SerializedName;

public class BodyAnime {

    @SerializedName("data")
    private DataAnime dataAnime;

    public DataAnime getDataAnime() {
        return dataAnime;
    }

    public void setDataAnime(DataAnime dataAnime) {
        this.dataAnime = dataAnime;
    }
}
