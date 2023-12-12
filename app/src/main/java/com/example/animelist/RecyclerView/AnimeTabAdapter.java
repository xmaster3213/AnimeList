package com.example.animelist.RecyclerView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.animelist.fragments.AnimeTabCharactersFragment;
import com.example.animelist.fragments.AnimeTabEpisodesFragment;
import com.example.animelist.fragments.AnimeTabOverviewFragment;
import com.example.animelist.model.Anime;

public class AnimeTabAdapter extends FragmentStateAdapter {

    private static final int N_TABS = 3;

    public AnimeTabAdapter(FragmentActivity activity) {
        super(activity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new AnimeTabOverviewFragment();
            case 1:
                return new AnimeTabCharactersFragment();
            case 2:
                return new AnimeTabEpisodesFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return N_TABS;
    }
}
