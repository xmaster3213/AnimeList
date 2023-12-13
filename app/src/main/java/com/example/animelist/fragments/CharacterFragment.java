package com.example.animelist.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.animelist.R;
import com.example.animelist.model.BodyCharacter;
import com.example.animelist.model.Character;
import com.example.animelist.network.DataViewModel;
import com.squareup.picasso.Picasso;

public class CharacterFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.character, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            int characterId = activity.getIntent().getIntExtra("characterId", -1);

//            Character id was not passed
            if (characterId == -1) {
                activity.finish();
            }

            TextView name = activity.findViewById(R.id.characterName);
            TextView gender = activity.findViewById(R.id.characterGenderValue);
            TextView age = activity.findViewById(R.id.characterAgeValue);
            TextView description = activity.findViewById(R.id.characterDescription);
            ImageView image = activity.findViewById(R.id.characterImage);

            DataViewModel dataViewModel = new ViewModelProvider(activity).get(DataViewModel.class);
            dataViewModel.getCharacter(characterId).observe(activity, new Observer<BodyCharacter>() {
                @Override
                public void onChanged(BodyCharacter bodyCharacter) {
                    Character character = bodyCharacter.getDataCharacter().getCharacter();
                    String unknown = activity.getString(R.string.unknown);
                    String noDescription = activity.getString(R.string.no_description);
                    name.setText(character.getName().getFull() != null ? character.getName().getFull() : unknown);
                    gender.setText(character.getGender() != null ? character.getGender() : unknown);
                    age.setText(character.getAge() != null ? character.getAge() : unknown);
                    description.setText(character.getDescription() != null ? character.getDescription() : noDescription);
                    String imageUrl = character.getImage().getLarge();
                    if (imageUrl != null) {
                        Picasso.get().load(imageUrl).into(image);
                    }
                }
            });
        }
    }

}
