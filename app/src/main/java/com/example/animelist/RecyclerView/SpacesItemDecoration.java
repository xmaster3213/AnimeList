package com.example.animelist.RecyclerView;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private final int horizontal;
    private final int vertical;

    public SpacesItemDecoration(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.left = horizontal;
        outRect.right = horizontal;
        outRect.top = vertical;
        outRect.bottom = vertical;
    }
}