package com.example.animelist.RecyclerView;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animelist.R;
import com.example.animelist.activity.CharacterActivity;
import com.example.animelist.model.Edges;
import com.example.animelist.model.Episode;
import com.squareup.picasso.Picasso;

public class EpisodeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView episodeImage;
    TextView episodeTitle;
    Episode currentEpisode;

    public EpisodeViewHolder(@NonNull View itemView) {
        super(itemView);
        episodeImage = itemView.findViewById(R.id.episodeImage);
        episodeTitle = itemView.findViewById(R.id.episodeTitle);
    }

    public void bindData(Episode episode) {
        currentEpisode = episode;
        episodeTitle.setText(currentEpisode.getTitle());
        String imageUrl = currentEpisode.getThumbnail();
        Picasso.get().load(imageUrl).into(episodeImage);
        Log.e("idk", "Data binded: ");
    }

    @Override
    public void onClick(View v) {
        if (currentEpisode != null) {
//            TODO: Make intent to browser to open the currentEpisode.getUrl()
        }
    }
}
