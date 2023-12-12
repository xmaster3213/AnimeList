package com.example.animelist.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.animelist.R;
import com.example.animelist.activity.HomeActivity;
import com.example.animelist.activity.MyListActivity;
import com.example.animelist.activity.DiscoverActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Objects;

public class BottomAppBarFragment extends Fragment {

    String currentPage;
    BottomNavigationView bottomAppBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bottom_navigation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            this.bottomAppBar = activity.findViewById(R.id.bottomAppBar);
            bottomAppBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.pageHome:
                            if (Objects.equals(currentPage, "HomeActivity")) {
                                break;
                            }
                            Intent intentHome = new Intent(getActivity(), HomeActivity.class);
                            Log.e("idk", "Home activity called from app bar: ");
                            startActivity(intentHome);
                            break;
                        case R.id.pageMylist:
                            if (Objects.equals(currentPage, "MyListActivity")) {
                                break;
                            }
                            Intent intentMyList = new Intent(getActivity(), MyListActivity.class);
                            startActivity(intentMyList);
                            break;
                        case R.id.pageSearch:
                            if (Objects.equals(currentPage, "DiscoverActivity")) {
                                break;
                            }
                            Intent intentSearch = new Intent(getActivity(), DiscoverActivity.class);
                            startActivity(intentSearch);
                            break;
                    }
                    return true;
                }
            });
            setSelectedItem(activity);
        }
    }

    private void setSelectedItem(AppCompatActivity activity) {
        this.currentPage = activity.getClass().getSimpleName();
        switch (currentPage) {
            case "HomeActivity":
                bottomAppBar.getMenu().getItem(1).setChecked(true);
                break;
            case "DiscoverActivity":
                bottomAppBar.getMenu().getItem(0).setChecked(true);
                break;
            case "MyListActivity":
                bottomAppBar.getMenu().getItem(2).setChecked(true);
                break;

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            setSelectedItem(activity);
        }
    }
}
