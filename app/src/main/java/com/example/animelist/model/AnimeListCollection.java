package com.example.animelist.model;

import com.example.animelist.model.enums.MediaListStatus;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AnimeListCollection {

    private String name;
    private MediaListStatus status;
    private List<AnimeListEntry> entries;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MediaListStatus getStatus() {
        return status;
    }

    public void setStatus(MediaListStatus status) {
        this.status = status;
    }

    public List<AnimeListEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<AnimeListEntry> entries) {
        this.entries = entries;
    }
}
