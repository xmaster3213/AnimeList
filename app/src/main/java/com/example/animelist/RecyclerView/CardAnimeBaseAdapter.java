package com.example.animelist.RecyclerView;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animelist.R;
import com.example.animelist.model.Anime;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CardAnimeBaseAdapter extends RecyclerView.Adapter<CardAnimeBaseViewHolder> {

    private List<Anime> animesList = new ArrayList<>();

    @NonNull
    @Override
    public CardAnimeBaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_anime_base,
                parent, false);
        return new CardAnimeBaseViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardAnimeBaseViewHolder holder, int position) {
        Anime currentAnime = animesList.get(position);
        holder.bindData(currentAnime);
    }

    @Override
    public int getItemCount() {
        return animesList.size();
    }

    public void setData(List<Anime> list) {
        this.animesList = new ArrayList<>(list);
        notifyDataSetChanged();
        Log.e("idk", "Data set: ");
    }
}
