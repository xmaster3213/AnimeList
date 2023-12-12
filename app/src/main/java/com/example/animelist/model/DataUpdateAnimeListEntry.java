package com.example.animelist.model;

import com.google.gson.annotations.SerializedName;

public class DataUpdateAnimeListEntry {

    @SerializedName("SaveMediaListEntry")
    private AnimeListEntry animeListEntry;

    public AnimeListEntry getAnimeListEntry() {
        return animeListEntry;
    }

    public void setAnimeListEntry(AnimeListEntry animeListEntry) {
        this.animeListEntry = animeListEntry;
    }
}
