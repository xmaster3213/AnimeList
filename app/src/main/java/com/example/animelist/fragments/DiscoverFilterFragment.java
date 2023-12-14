package com.example.animelist.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.animelist.R;
import com.example.animelist.model.enums.Format;
import com.example.animelist.model.enums.Season;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DiscoverFilterFragment extends Fragment {

    DrawerLayout drawerLayout;
    private final int MIN_YEAR = 2000;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.discover_filter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            drawerLayout = activity.findViewById(R.id.discoverFilterDrawerLayout);
            ImageView filterButton = activity.findViewById(R.id.discoverFilterButton);

//            Listener for filter button
            filterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawerLayout.openDrawer(GravityCompat.END);
                }
            });

//            Year filter
            int nextYear = Calendar.getInstance().get(Calendar.YEAR) + 1;
            List<String> filterList = new ArrayList<>();
            filterList.add(activity.getResources().getString(R.string.any));
            for (int year = nextYear; year >= MIN_YEAR; year--) {
                filterList.add(Integer.toString(year));
            }
            setupFilterMenu(filterList, R.id.discoverFilterTextViewYear, activity);
//            Season filter
            filterList = new ArrayList<>();
            filterList.add(activity.getResources().getString(R.string.any));
            for (Season season: Season.values()) {
                filterList.add(season.toString(activity.getResources()));
            }
            setupFilterMenu(filterList, R.id.discoverFilterTextViewSeason, activity);
//            Format filter
            filterList = new ArrayList<>();
            filterList.add(activity.getResources().getString(R.string.any));
            for (Format format: Format.values()) {
                filterList.add(format.toString(activity.getResources()));
            }
            setupFilterMenu(filterList, R.id.discoverFilterTextViewFormat, activity);
        }
    }

    private <T> void setupFilterMenu(List<T> list, int autoCompleteTextViewId, AppCompatActivity activity) {
        ArrayAdapter<T> adapter = new ArrayAdapter<>(requireContext(),
                R.layout.filter_item_list, list);
        AutoCompleteTextView textView = activity.findViewById(autoCompleteTextViewId);
        textView.setText(activity.getResources().getString(R.string.any), false);
        textView.setAdapter(adapter);
    }

}
