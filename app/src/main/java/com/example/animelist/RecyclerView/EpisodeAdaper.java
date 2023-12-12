package com.example.animelist.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animelist.R;
import com.example.animelist.model.Edges;
import com.example.animelist.model.Episode;

import java.util.ArrayList;
import java.util.List;

public class EpisodeAdaper extends RecyclerView.Adapter<EpisodeViewHolder> {

    private List<Episode> episodeList = new ArrayList<>();


    @NonNull
    @Override
    public EpisodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_episode,
                parent, false);
        return new EpisodeViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeViewHolder holder, int position) {
        Episode currentEpisode = episodeList.get(position);
        holder.bindData(currentEpisode);
    }

    @Override
    public int getItemCount() {
        return episodeList.size();
    }

    public void setData(List<Episode> list) {
        this.episodeList = new ArrayList<>(list);
        notifyDataSetChanged();
        Log.e("idk", "Data set: ");
    }
}
