package com.example.animelist.model;

import com.google.gson.annotations.SerializedName;

public class DataUser {

    @SerializedName("Viewer")
    private Viewer viewer;

    public Viewer getViewer() {
        return viewer;
    }

    public void setViewer(Viewer viewer) {
        this.viewer = viewer;
    }
}
