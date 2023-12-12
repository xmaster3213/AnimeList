package com.example.animelist.model;

import com.google.gson.annotations.SerializedName;

public class BodyUpdateAnimeListEntry {

    @SerializedName("data")
    private DataUpdateAnimeListEntry dataUpdateAnimeListEntry;

    public DataUpdateAnimeListEntry getDataUpdateAnimeListEntry() {
        return dataUpdateAnimeListEntry;
    }

    public void setDataUpdateAnimeListEntry(DataUpdateAnimeListEntry dataUpdateAnimeListEntry) {
        this.dataUpdateAnimeListEntry = dataUpdateAnimeListEntry;
    }
}
