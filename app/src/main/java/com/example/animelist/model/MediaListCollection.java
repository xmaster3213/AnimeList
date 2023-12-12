package com.example.animelist.model;

import com.example.animelist.model.enums.MediaListStatus;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MediaListCollection {

    @SerializedName("lists")
    private List<AnimeListCollection> animeListCollection;

    public List<AnimeListCollection> getAnimeListCollection() {
        return animeListCollection;
    }

    public void setAnimeListCollection(List<AnimeListCollection> animeListCollection) {
        this.animeListCollection = animeListCollection;
    }

    public AnimeListCollection findByStatus(MediaListStatus status) {
        for (AnimeListCollection collection: animeListCollection) {
            if (collection.getStatus() == status) {
                return collection;
            }
        }
        return null;
    }
}
