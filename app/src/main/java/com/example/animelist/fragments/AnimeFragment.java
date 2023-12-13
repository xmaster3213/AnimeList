package com.example.animelist.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.animelist.R;
import com.example.animelist.RecyclerView.AnimeTabAdapter;
import com.example.animelist.model.Anime;
import com.example.animelist.model.BodyAnime;
import com.example.animelist.model.enums.MediaListStatus;
import com.example.animelist.network.DataViewModel;
import com.example.animelist.utilities.SharedViewModel;
import com.example.animelist.utilities.Utilities;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.squareup.picasso.Picasso;

public class AnimeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.anime, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            int animeId = activity.getIntent().getIntExtra("animeId", -1);
//            no animeId found, return back to previews page.
            if (animeId == -1) {
                activity.finish();
            }
            TabLayout tabLayout = activity.findViewById(R.id.animeTabLayout);
            ViewPager2 viewPager = activity.findViewById(R.id.animeViewPager);
            AnimeTabAdapter tabAdapter = new AnimeTabAdapter(activity);
            viewPager.setAdapter(tabAdapter);
            new TabLayoutMediator(tabLayout, viewPager,
                    (tab, position) -> {
                        switch (position) {
                            case 0:
                                tab.setText(R.string.tab_1);
                                tab.setContentDescription(R.string.tab_1_description);
                                break;
                            case 1:
                                tab.setText(R.string.tab_2);
                                tab.setContentDescription(R.string.tab_2_description);
                                break;
                            case 2:
                                tab.setText(R.string.tab_3);
                                tab.setContentDescription(R.string.tab_3_description);
                                break;
                            default:
                                break;
                        }
                    }).attach();
            DataViewModel dataViewModel = new ViewModelProvider(activity).get(DataViewModel.class);
            dataViewModel.getAnime(animeId).observe(activity, new Observer<BodyAnime>() {
                        @Override
                        public void onChanged(BodyAnime bodyAnime) {
                            Anime anime = bodyAnime.getDataAnime().getAnime();
                            Button addToListButton = activity.findViewById(R.id.animeButtonEditEntry);
                            addToListButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Utilities.insertFragment(activity, R.id.addToListContainer,
                                            new addToListFragment(anime),
                                            addToListFragment.class.getSimpleName(), true);
                                }
                            });
                            SharedViewModel sharedViewModel = new ViewModelProvider(activity).get(SharedViewModel.class);
                            sharedViewModel.setAnime(anime);
                            TextView textView = activity.findViewById(R.id.animeTitle);
                            textView.setText(anime.getTitle().getRomaji());
                            sharedViewModel.getAnime().observe(activity, new Observer<Anime>() {
                                @Override
                                public void onChanged(Anime anime) {
                                    if (anime.getAnimeListEntry() != null) {
                                        String status = anime.getAnimeListEntry().getStatus()
                                                .toString(activity.getResources());
                                        addToListButton.setText(status);
                                    }
                                }
                            });

                            ImageView animeBanner = activity.findViewById(R.id.animeBanner);
                            Picasso.get().load(anime.getBannerImage()).into(animeBanner);
                            ImageView animeCover = activity.findViewById(R.id.animeImage);
                            Picasso.get().load(anime.getCoverImage().getLarge()).into(animeCover);
                        }
                    });
        }
    }

}
