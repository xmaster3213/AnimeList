package com.example.animelist.fragments;

import static com.example.animelist.utilities.Utilities.dpToPixels;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animelist.R;
import com.example.animelist.RecyclerView.CharacterAdaper;
import com.example.animelist.RecyclerView.EpisodeAdaper;
import com.example.animelist.RecyclerView.SpacesItemDecoration;
import com.example.animelist.model.Anime;
import com.example.animelist.utilities.SharedViewModel;

public class AnimeTabEpisodesFragment extends Fragment {

    private Anime anime;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.anime_tab_episodes_layout, container, false);
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
                            RecyclerView recyclerView = activity.findViewById(R.id.recyclerViewEpisodes);
                            recyclerView.addItemDecoration(new SpacesItemDecoration(0, dpToPixels(activity, 10)));
                            EpisodeAdaper adapter = new EpisodeAdaper();
                            recyclerView.setAdapter(adapter);
                            adapter.setData(anime.getEpisodesList());
                        }
                    });
        }
    }
}
