package com.example.animelist.model;

import com.google.gson.annotations.SerializedName;

public class DataAnime {

    @SerializedName("Media")
    private Anime anime;

    public Anime getAnime() {
        return anime;
    }

    public void setAnime(Anime anime) {
        this.anime = anime;
    }
}
