package com.example.animelist.model;

import com.google.gson.annotations.SerializedName;

public class DataCharacter {

    @SerializedName("Character")
    private Character character;

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
}
