package com.example.animelist.model.enums;

import android.content.res.Resources;

import androidx.annotation.NonNull;

import com.example.animelist.R;

public enum Role {
    MAIN(R.string.main),
    SUPPORTING(R.string.supporting),
    BACKGROUND(R.string.background);

    private final int nameId;
    private Role(int nameId) {
        this.nameId = nameId;
    }

    @NonNull
    public String toString(Resources res) {
        return res.getString(nameId);
    }

    public static Role fromString(Resources res, String stringValue) {
        for (Role value : Role.values()) {
            if (value.toString(res).equals(stringValue)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Enum not found for value: " + stringValue);
    }
}
