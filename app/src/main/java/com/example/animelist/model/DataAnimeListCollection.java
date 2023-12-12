package com.example.animelist.model;

import com.google.gson.annotations.SerializedName;

public class DataAnimeListCollection {

    @SerializedName("MediaListCollection")
    private MediaListCollection mediaListCollection;

    public MediaListCollection getMediaListCollection() {
        return mediaListCollection;
    }

    public void setMediaListCollection(MediaListCollection mediaListCollection) {
        this.mediaListCollection = mediaListCollection;
    }
}
