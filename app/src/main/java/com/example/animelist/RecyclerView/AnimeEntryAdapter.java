package com.example.animelist.RecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animelist.R;
import com.example.animelist.model.AnimeListCollection;
import com.example.animelist.model.AnimeListEntry;

import java.util.ArrayList;
import java.util.List;

public class AnimeEntryAdapter extends RecyclerView.Adapter<AnimeEntryViewHolder> {

    private List<AnimeListEntry> animeList;
    private Context context;

    @NonNull
    @Override
    public AnimeEntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.anime_entry_card,
                parent, false);
        this.context = parent.getContext();
        return new AnimeEntryViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeEntryViewHolder holder, int position) {
        AnimeListEntry currentEntry = animeList.get(position);
        holder.bindData(currentEntry, context);
    }

    @Override
    public int getItemCount() {
        return animeList.size();
    }

    public void setData(List<AnimeListEntry> list) {
        this.animeList = new ArrayList<>(list);
        notifyDataSetChanged();
    }
}
