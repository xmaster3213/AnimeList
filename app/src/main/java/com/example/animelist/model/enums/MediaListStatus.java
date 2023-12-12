package com.example.animelist.model.enums;

import android.content.res.Resources;

import androidx.annotation.NonNull;

import com.example.animelist.R;

public enum MediaListStatus {

    CURRENT(R.string.current),
    PLANNING(R.string.planning),
    COMPLETED(R.string.completed),
    DROPPED(R.string.dropped);

    private final int nameId;
    private MediaListStatus(int nameId) {
        this.nameId = nameId;
    }

    @NonNull
    public String toString(Resources res) {
        return res.getString(nameId);
    }

    public static MediaListStatus fromString(Resources res, String stringValue) {
        for (MediaListStatus value : MediaListStatus.values()) {
            if (value.toString(res).equals(stringValue)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Enum not found for value: " + stringValue);
    }
}
