package com.example.animelist.model;

import com.google.gson.annotations.SerializedName;

public class BodyAnimeListCollection {

    @SerializedName("data")
    private DataAnimeListCollection dataAnimeListCollection;

    public DataAnimeListCollection getDataAnimeListCollection() {
        return dataAnimeListCollection;
    }

    public void setDataAnimeListCollection(DataAnimeListCollection dataAnimeListCollection) {
        this.dataAnimeListCollection = dataAnimeListCollection;
    }
}
