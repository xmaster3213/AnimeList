package com.example.animelist.model.enums;

import android.content.res.Resources;

import androidx.annotation.NonNull;

import com.example.animelist.R;

public enum MediaListSort {

    SCORE(R.string.score),
    SCORE_DESC(R.string.score_desc),
    PROGRESS(R.string.progress),
    PROGRESS_DESC(R.string.progress_desc),
    STARTED_ON(R.string.started_on),
    STARTED_ON_DESC(R.string.started_on_desc),
    FINISHED_ON(R.string.finished_on),
    FINISHED_ON_DESC(R.string.finished_on_desc);

    private final int nameId;
    private MediaListSort(int nameId) {
        this.nameId = nameId;
    }

    @NonNull
    public String toString(Resources res) {
        return res.getString(nameId);
    }

    public static MediaListSort fromString(Resources res, String stringValue) {
        for (MediaListSort value : MediaListSort.values()) {
            if (value.toString(res).equals(stringValue)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Enum not found for value: " + stringValue);
    }
}
