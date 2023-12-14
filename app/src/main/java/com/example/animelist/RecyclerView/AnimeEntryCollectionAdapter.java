package com.example.animelist.RecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animelist.R;
import com.example.animelist.model.Anime;
import com.example.animelist.model.AnimeListCollection;
import com.example.animelist.model.AnimeListEntry;

import java.util.ArrayList;
import java.util.List;

public class AnimeEntryCollectionAdapter extends RecyclerView.Adapter<AnimeEntryCollectionViewHolder>{

    private List<AnimeListCollection> collections = new ArrayList<>();
    private Context context;

    @NonNull
    @Override
    public AnimeEntryCollectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_list_collection,
                parent, false);
        this.context = parent.getContext();
        return new AnimeEntryCollectionViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeEntryCollectionViewHolder holder, int position) {
        AnimeListCollection currentCollection = collections.get(position);
        holder.bindData(currentCollection, context);
    }

    @Override
    public int getItemCount() {
        return collections.size();
    }

    public void setData(List<AnimeListCollection> list) {
        this.collections = new ArrayList<>(list);
        notifyDataSetChanged();
    }
}
