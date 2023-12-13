package com.example.animelist.model;

import com.google.gson.annotations.SerializedName;

public class BodyCharacter {

    @SerializedName("data")
    private DataCharacter dataCharacter;

    public DataCharacter getDataCharacter() {
        return dataCharacter;
    }

    public void setDataCharacter(DataCharacter dataCharacter) {
        this.dataCharacter = dataCharacter;
    }
}
