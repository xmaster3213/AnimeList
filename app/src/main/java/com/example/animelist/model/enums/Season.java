package com.example.animelist.model.enums;

import android.content.res.Resources;

import androidx.annotation.NonNull;

import com.example.animelist.R;

public enum Season {
    WINTER(R.string.winter),
    SPRING(R.string.spring),
    SUMMER(R.string.summer),
    FALL(R.string.fall);
    private final int nameId;
    private Season(int nameId) {
        this.nameId = nameId;
    }

    @NonNull
    public String toString(Resources res) {
        return res.getString(nameId);
    }

    public static Season fromString(Resources res, String stringValue) {
        for (Season value : Season.values()) {
            if (value.toString(res).equals(stringValue)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Enum not found for value: " + stringValue);
    }

}
