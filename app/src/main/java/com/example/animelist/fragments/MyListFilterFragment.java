package com.example.animelist.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.animelist.R;
import com.example.animelist.model.enums.Format;
import com.example.animelist.model.enums.MediaListSort;
import com.example.animelist.model.enums.MediaListStatus;
import com.example.animelist.model.enums.Season;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MyListFilterFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_list_filter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            DrawerLayout drawerLayout = activity.findViewById(R.id.MyListFilterDrawerLayout);
            ImageView filterButton = activity.findViewById(R.id.MyListFilterButton);

//            Listener for filter button
            filterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawerLayout.openDrawer(GravityCompat.END);
                }
            });

//            Status filter
            List<String> filterList = new ArrayList<>();
            filterList.add(activity.getResources().getString(R.string.any));
            for (MediaListStatus status: MediaListStatus.values()) {
                filterList.add(status.toString(activity.getResources()));
            }
            String defaultValue = activity.getResources().getString(R.string.any);
            setupFilterMenu(filterList, R.id.myListTextViewStatus, activity, defaultValue);
//            Sort filter
            filterList = new ArrayList<>();
            for (MediaListSort sort: MediaListSort.values()) {
                filterList.add(sort.toString(activity.getResources()));
            }
            defaultValue = MediaListSort.SCORE.toString(activity.getResources());
            setupFilterMenu(filterList, R.id.MyListTextViewSort, activity, defaultValue);
        }
    }

    private <T> void setupFilterMenu(List<T> list, int autoCompleteTextViewId, AppCompatActivity activity,
                                     String defaultValue) {
        ArrayAdapter<T> adapter = new ArrayAdapter<>(requireContext(),
                R.layout.filter_item_list, list);
        Log.e("TAG", "onViewCreated: " + list);
        AutoCompleteTextView textView = activity.findViewById(autoCompleteTextViewId);
        textView.setText(defaultValue, false);
        textView.setAdapter(adapter);
    }
}
