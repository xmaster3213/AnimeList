package com.example.animelist.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animelist.R;
import com.example.animelist.RecyclerView.CardAnimeBaseAdapter;
import com.example.animelist.model.BodyAnimeList;
import com.example.animelist.network.DataViewModel;

public class HomeFragment extends Fragment {

    private CardAnimeBaseAdapter adapter;
    private RecyclerView recyclerView;
    CardAnimeBaseAdapter adapterPopularSeason;
    CardAnimeBaseAdapter adapterAdded;
    CardAnimeBaseAdapter adapterPopularAllTime;
    CardAnimeBaseAdapter adapterUpcoming;
    private static final String LOG = "Home-Fragment";

    DataViewModel dataViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataViewModel = new ViewModelProvider(this).get(DataViewModel.class);
        return inflater.inflate(R.layout.home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

            FragmentActivity activity = getActivity();
            if (activity != null) {
                adapterPopularSeason = new CardAnimeBaseAdapter();
                adapterAdded = new CardAnimeBaseAdapter();
                adapterPopularAllTime = new CardAnimeBaseAdapter();
                adapterUpcoming = new CardAnimeBaseAdapter();
                RecyclerView recyclerView = view.findViewById(R.id.recyclerViewAnimePopularThisSeason);
                recyclerView.setAdapter(adapterPopularSeason);
                recyclerView = view.findViewById(R.id.recyclerViewJustAdded);
                recyclerView.setAdapter(adapterAdded);
                recyclerView = view.findViewById(R.id.recyclerViewAllTimePopular);
                recyclerView.setAdapter(adapterPopularAllTime);
                recyclerView = view.findViewById(R.id.recyclerViewUpcomingNextSeason);
                recyclerView.setAdapter(adapterUpcoming);
                dataViewModel.getAnimesPopularThisSeason().observe(getViewLifecycleOwner(), new Observer<BodyAnimeList>() {
                    @Override
                    public void onChanged(BodyAnimeList bodyAnimeList) {
                        if (bodyAnimeList != null) {
                            adapterPopularSeason.setData(bodyAnimeList.getData().getPage().getAnimes());
                            Log.e("idk", "getAnimesPopularThisSeason: ");
                        }
                        dataViewModel.getAnimesPopularThisSeasonLiveData().removeObserver(this);
                    }
                });
                dataViewModel.getAnimesJustAdded().observe(getViewLifecycleOwner(), new Observer<BodyAnimeList>() {
                    @Override
                    public void onChanged(BodyAnimeList bodyAnimeList) {
                        if (bodyAnimeList != null) {
                            adapterAdded.setData(bodyAnimeList.getData().getPage().getAnimes());
                            Log.e("idk", "getAnimesJustAdded: ");
                        }
                        dataViewModel.getAnimesJustAddedLiveData().removeObserver(this);
                    }
                });
                dataViewModel.getAnimesAllTimePopular().observe(getViewLifecycleOwner(), new Observer<BodyAnimeList>() {
                    @Override
                    public void onChanged(BodyAnimeList bodyAnimeList) {
                        if (bodyAnimeList != null) {
                            adapterPopularAllTime.setData(bodyAnimeList.getData().getPage().getAnimes());
                            Log.e("idk", "getAnimesAllTimePopular: ");
                        }
                        dataViewModel.getAnimesAllTimePopularLiveData().removeObserver(this);
                    }
                });
                dataViewModel.getAnimesUpcomingNextSesaon().observe(getViewLifecycleOwner(), new Observer<BodyAnimeList>() {
                    @Override
                    public void onChanged(BodyAnimeList bodyAnimeList) {
                        if (bodyAnimeList != null) {
                            adapterUpcoming.setData(bodyAnimeList.getData().getPage().getAnimes());
                            Log.e("idk", "getAnimesUpcomingNextSeaon: ");
                        }
                        dataViewModel.getAnimesUpcomingNextSeasonLiveData().removeObserver(this);
                    }
                });
            } else {
                Log.e(LOG, "Activity is null");
            }

    }

}
