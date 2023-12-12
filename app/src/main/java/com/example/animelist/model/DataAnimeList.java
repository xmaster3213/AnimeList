package com.example.animelist.model;

import com.google.gson.annotations.SerializedName;

public class DataAnimeList {

    @SerializedName("Page")
    private Page page;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
