package com.example.animelist.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animelist.R;
import com.example.animelist.activity.AnimeActivity;
import com.example.animelist.activity.CharacterActivity;
import com.example.animelist.model.Character;
import com.example.animelist.model.Edges;
import com.squareup.picasso.Picasso;

public class CharacterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView characterImage;
    TextView characterName;
    TextView characterRole;
    Edges currentCharacterEdge;

    public CharacterViewHolder(@NonNull View itemView) {
        super(itemView);
        characterImage = itemView.findViewById(R.id.characterImage);
        characterName = itemView.findViewById(R.id.characterName);
        characterRole = itemView.findViewById(R.id.characterRole);
    }

    public void bindData(Edges characterEdge, Context context) {
        currentCharacterEdge = characterEdge;
        Log.e("TAG", "bindData: " + characterEdge);
        String name;
        if (currentCharacterEdge.getName() == null) {
            name = currentCharacterEdge.getCharacter().getName().getFull();
        } else {
            name = currentCharacterEdge.getName();
        }
        characterName.setText(name);
        characterRole.setText(currentCharacterEdge.getRole().toString(context.getResources()));
        String imageUrl = characterEdge.getCharacter().getImage().getLarge();
        Picasso.get().load(imageUrl).into(characterImage);
        Log.e("idk", "Data binded: ");
    }

    @Override
    public void onClick(View v) {
        if (currentCharacterEdge != null) {
            Intent intent = new Intent(v.getContext(), CharacterActivity.class);
            intent.putExtra("characterId", currentCharacterEdge.getCharacter().getId());
            v.getContext().startActivity(intent);
        }
    }
}
