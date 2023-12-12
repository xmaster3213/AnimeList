package com.example.animelist.model.enums;

import android.content.res.Resources;

import androidx.annotation.NonNull;

import com.example.animelist.R;

public enum Format {
    TV(R.string.tv),
    TV_SHORT(R.string.tv_short),
    MOVIE(R.string.movie),
    SPECIAL(R.string.special),
    OVA(R.string.ova),
    ONA(R.string.ona),
    MUSIC(R.string.music);

    private final int nameId;
    private Format(int nameId) {
        this.nameId = nameId;
    }

    @NonNull
    public String toString(Resources res) {
        return res.getString(nameId);
    }

    public static Format fromString(Resources res, String stringValue) {
        for (Format value : Format.values()) {
            if (value.toString(res).equals(stringValue)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Enum not found for value: " + stringValue);
    }
}