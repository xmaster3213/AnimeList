package com.example.animelist.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animelist.R;
import com.example.animelist.activity.AnimeActivity;
import com.example.animelist.model.Anime;
import com.example.animelist.model.AnimeListCollection;
import com.squareup.picasso.Picasso;

public class AnimeEntryCollectionViewHolder extends RecyclerView.ViewHolder {

    TextView collectionTitle;
    RecyclerView recyclerView;
    AnimeListCollection currentCollection;
    AnimeEntryAdapter animeEntryAdapter;

    public AnimeEntryCollectionViewHolder(@NonNull View itemView) {
        super(itemView);
        collectionTitle = itemView.findViewById(R.id.collectionTitle);
        recyclerView = itemView.findViewById(R.id.recyclerViewAnimeEntryCard);
        animeEntryAdapter = new AnimeEntryAdapter();
        recyclerView.setAdapter(animeEntryAdapter);
    }

    public void bindData(AnimeListCollection collection, Context context) {
        currentCollection = collection;
        collectionTitle.setText(collection.getStatus().toString(context.getResources()));
        animeEntryAdapter.setData(collection.getEntries());
        Log.e("idk", "Data binded: ");
    }
}
