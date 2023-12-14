package com.example.animelist.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.animelist.R;
import com.example.animelist.model.Anime;
import com.example.animelist.model.BodyAnime;
import com.example.animelist.model.Date;
import com.example.animelist.network.DataViewModel;
import com.example.animelist.utilities.SharedViewModel;

public class AnimeTabOverviewFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.anime_tab_overview_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            SharedViewModel sharedViewModel = new ViewModelProvider(activity).get(SharedViewModel.class);
            sharedViewModel.getAnime().observe(getViewLifecycleOwner(), new Observer<Anime>() {
                @Override
                public void onChanged(Anime anime) {
                    TextView textView = activity.findViewById(R.id.avgRating);
                    textView.setText(Integer.toString(anime.getAverageScore()));
                    textView = activity.findViewById(R.id.format);
                    textView.setText(anime.getFormat().toString()
                            .replace("_", " "));
                    textView = activity.findViewById(R.id.status);
                    textView.setText(anime.getMediaStatus().toString()
                            .replace("_", " ").toLowerCase());
                    textView = activity.findViewById(R.id.startDate);
                    if (anime.getStartDate().getYear() == null) {
                        textView.setText(activity.getResources().getString(R.string.anime_date_not_added));
                    } else {
                        textView.setText(anime.getStartDate().toString());
                    }
                    textView = activity.findViewById(R.id.endDate);
                    if (anime.getEndDate().getYear() == null) {
                        textView.setText(activity.getResources().getString(R.string.anime_date_not_added));
                    } else {
                        textView.setText(anime.getEndDate().toString());
                    }
                    textView = activity.findViewById(R.id.animeDescription);
                    textView.setText(anime.getDescription());
                }
            });
        }
    }
}
