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
import com.example.animelist.model.Edges;

import java.util.ArrayList;
import java.util.List;

public class CharacterAdaper extends RecyclerView.Adapter<CharacterViewHolder> {

    private List<Edges> characterEdgeList = new ArrayList<>();
    private Context context;

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_character,
                parent, false);
        this.context = parent.getContext();
        return new CharacterViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        Edges currentEdge = characterEdgeList.get(position);
        holder.bindData(currentEdge, context);
    }

    @Override
    public int getItemCount() {
        return characterEdgeList.size();
    }

    public void setData(List<Edges> list) {
        this.characterEdgeList = new ArrayList<>(list);
        notifyDataSetChanged();
    }
}
