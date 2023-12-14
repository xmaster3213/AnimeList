package com.example.animelist.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animelist.R;
import com.example.animelist.activity.AnimeActivity;
import com.example.animelist.fragments.addToListFragment;
import com.example.animelist.model.Anime;
import com.example.animelist.model.AnimeListCollection;
import com.example.animelist.model.AnimeListEntry;
import com.example.animelist.utilities.Utilities;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.squareup.picasso.Picasso;

public class AnimeEntryViewHolder extends RecyclerView.ViewHolder {

    ImageView animeImage;
    TextView animeTitle;
    TextView animeScore;
    TextView animeProgress;
    LinearProgressIndicator animeProgressIndicator;
    ImageView editButton;
    AnimeListEntry currentAnimeEntry;

    public AnimeEntryViewHolder(@NonNull View itemView) {
        super(itemView);
        animeImage = itemView.findViewById(R.id.animeEntryImage);
        animeTitle = itemView.findViewById(R.id.animeEntryTitle);
        animeScore = itemView.findViewById(R.id.AnimeEntryScore);
        animeProgress = itemView.findViewById(R.id.animeEntryEpisodeProgress);
        animeProgressIndicator = itemView.findViewById(R.id.animeEntryEpisodeProgressIndicator);
        editButton = itemView.findViewById(R.id.editButton);
    }

    public void bindData(AnimeListEntry animeEntry, Context context) {
        currentAnimeEntry = animeEntry;
        animeTitle.setText(animeEntry.getAnime().getTitle().getRomaji());
        String title = context.getString(R.string.score) + ": " + animeEntry.getScore().toString();
        animeScore.setText(title);
        Log.e("TAG", "bindData: " + animeEntry.getAnime().getId());
        Integer episodes = 0;
        if (animeEntry.getAnime().getEpisodes() != null) {
            episodes = animeEntry.getAnime().getEpisodes();
        }
        String progress = animeEntry.getProgress().toString() + "/" +
                episodes.toString() + " " +
                context.getString(R.string.episode_abbreviation);
        animeProgress.setText(progress);
        animeProgressIndicator.setMax(episodes);
        animeProgressIndicator.setProgressCompat(animeEntry.getProgress(), false);
        String imageUrl = animeEntry.getAnime().getCoverImage().getLarge();
        Picasso.get().load(imageUrl).into(animeImage);
        animeTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendAnimeIntent(v);
            }
        });
        animeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendAnimeIntent(v);
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Anime anime = animeEntry.getAnime();
                anime.setAnimeListEntry(animeEntry);
                Utilities.insertFragment((AppCompatActivity) context, R.id.editAnimeContainer,
                        new addToListFragment(anime),
                        addToListFragment.class.getSimpleName(), true);
            }
        });
    }

    private void sendAnimeIntent(View v) {
        if (currentAnimeEntry != null && currentAnimeEntry.getAnime() != null) {
            Intent intent = new Intent(v.getContext(), AnimeActivity.class);
            intent.putExtra("animeId", currentAnimeEntry.getMediaId());
            v.getContext().startActivity(intent);
        }
    }

}
