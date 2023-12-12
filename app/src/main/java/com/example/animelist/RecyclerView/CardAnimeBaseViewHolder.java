package com.example.animelist.RecyclerView;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animelist.R;
import com.example.animelist.activity.AnimeActivity;
import com.example.animelist.model.Anime;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

public class CardAnimeBaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView animeImage;
    TextView animeTitle;
    Anime currentAnime;

    public CardAnimeBaseViewHolder(@NonNull View itemView) {
        super(itemView);
        animeImage = itemView.findViewById(R.id.card_base_anime_image);
        animeTitle = itemView.findViewById(R.id.card_base_anime_title);
        itemView.setOnClickListener(this);
    }

    public void bindData(Anime anime) {
        currentAnime = anime;
        animeTitle.setText(anime.getTitle().getRomaji());
        String imageUrl = anime.getCoverImage().getLarge();
        Picasso.get().load(imageUrl).into(animeImage);
        Log.e("idk", "Data binded: ");
    }

    @Override
    public void onClick(View v) {
        Log.e("TAG", "onClick: Anime selected");
        if (currentAnime != null) {
            Intent intent = new Intent(v.getContext(), AnimeActivity.class);
            intent.putExtra("animeId", currentAnime.getId());
            v.getContext().startActivity(intent);
        }
    }
}
