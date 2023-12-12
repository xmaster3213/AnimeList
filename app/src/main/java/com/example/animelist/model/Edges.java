package com.example.animelist.model;

import com.example.animelist.model.enums.Role;
import com.google.gson.annotations.SerializedName;

public class Edges {

    private int id;
    private Role role;
    private String name;
    @SerializedName("node")
    private Character character;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
}
