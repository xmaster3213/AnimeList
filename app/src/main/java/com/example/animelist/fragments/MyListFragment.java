package com.example.animelist.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animelist.R;
import com.example.animelist.RecyclerView.AnimeEntryCollectionAdapter;
import com.example.animelist.model.AnimeListCollection;
import com.example.animelist.model.BodyAnimeListCollection;
import com.example.animelist.model.MediaListCollection;
import com.example.animelist.model.Viewer;
import com.example.animelist.model.enums.MediaListSort;
import com.example.animelist.model.enums.MediaListStatus;
import com.example.animelist.network.DataViewModel;
import com.example.animelist.utilities.SharedViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyListFragment extends Fragment {

    private Integer userId = null;
    private MediaListSort sort = null;
    private MediaListCollection animeListCollections;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_list_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            RecyclerView entryCollection = activity.findViewById(R.id.recyclerViewAnimeEntryCollection);

            AnimeEntryCollectionAdapter collectionAdapter = new AnimeEntryCollectionAdapter();
            entryCollection.setAdapter(collectionAdapter);
            DataViewModel dataViewModel = new ViewModelProvider(activity).get(DataViewModel.class);

            SharedViewModel sharedViewModel = new ViewModelProvider(activity).get(SharedViewModel.class);
            sharedViewModel.getViewer().observe(activity, new Observer<Viewer>() {
                @Override
                public void onChanged(Viewer viewer) {
                    userId = viewer.getId();
                    getAnimeListCollection(dataViewModel, activity, collectionAdapter);
                }
            });

//            status filter
            AutoCompleteTextView textViewStatus = activity.findViewById(R.id.myListTextViewStatus);
            textViewStatus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    String selectedValue = (String) adapterView.getItemAtPosition(position);
                    showCollectionByStatus(activity, selectedValue, collectionAdapter);
                }
            });
//            sort filter
            AutoCompleteTextView textViewSort = activity.findViewById(R.id.MyListTextViewSort);
            textViewSort.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    String selectedValue = (String) adapterView.getItemAtPosition(position);
                    sort = MediaListSort.fromString(activity.getResources(), selectedValue);
                    getAnimeListCollection(dataViewModel, activity, collectionAdapter);
                }
            });
        }
    }

    private void getAnimeListCollection(DataViewModel dataViewModel, AppCompatActivity activity,
                                   AnimeEntryCollectionAdapter contentAdapter) {
        if (userId != null) {
            dataViewModel.getAnimeListCollection(userId, sort)
                    .observe(activity, new Observer<BodyAnimeListCollection>() {
                        @Override
                        public void onChanged(BodyAnimeListCollection bodyAnimeListCollection) {
                            animeListCollections = bodyAnimeListCollection
                                    .getDataAnimeListCollection().getMediaListCollection();
                            contentAdapter.setData(animeListCollections.getAnimeListCollection());
                            dataViewModel.getanimeListCollection().removeObserver(this);
                        }
                    });
        }
    }

    private void showCollectionByStatus(AppCompatActivity activity, String selectedValue,
                                        AnimeEntryCollectionAdapter contentAdapter) {
        if (userId != null) {
            if (Objects.equals(selectedValue, activity.getResources().getString(R.string.any))) {
                contentAdapter.setData(animeListCollections.getAnimeListCollection());
            } else {
                MediaListStatus status = MediaListStatus.fromString(activity.getResources(), selectedValue);
                AnimeListCollection collection = animeListCollections.findByStatus(status);
                List<AnimeListCollection> list = new ArrayList<>();
                if (collection != null) {
                    list.add(collection);
                }
                contentAdapter.setData(list);
            }
        }
    }
}
