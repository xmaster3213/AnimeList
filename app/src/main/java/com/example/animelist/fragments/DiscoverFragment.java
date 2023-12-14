package com.example.animelist.fragments;

import static com.example.animelist.utilities.Utilities.calculateNoOfColumns;
import static com.example.animelist.utilities.Utilities.calculateSpacing;
import static com.example.animelist.utilities.Utilities.dpToPixels;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animelist.R;
import com.example.animelist.RecyclerView.CardAnimeBaseAdapter;
import com.example.animelist.RecyclerView.SpacesItemDecoration;
import com.example.animelist.model.BodyAnimeList;
import com.example.animelist.model.enums.Format;
import com.example.animelist.model.enums.Season;
import com.example.animelist.network.DataViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class DiscoverFragment extends Fragment {

    private static final int CARD_WIDTH = 120;
    private static final int CARD_STROKE = 1;
    private static final int CARD_MARING = 8;
    //    yearSelected = -1 if "any" selected
    private int yearSelected = -1;
    //    seasonSelected = null if "any" selected
    private Season seasonSelected = null;
    //    formatSelected = null if "any" selected
    private Format formatSelected = null;
    private String search = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.discover_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            RecyclerView contentRecyclerView = activity.findViewById(R.id.recyclerViewDiscoverContent);
            int cardWidth = CARD_WIDTH + CARD_MARING * 2 + CARD_STROKE * 2;
            int nOfColumns = calculateNoOfColumns(activity, cardWidth);
            GridLayoutManager layoutManager = new GridLayoutManager(activity, nOfColumns);
            contentRecyclerView.setLayoutManager(layoutManager);
            int spacing = calculateSpacing(activity, nOfColumns, cardWidth);
            contentRecyclerView.addItemDecoration(new SpacesItemDecoration(dpToPixels(activity,
                    spacing), 0));
            CardAnimeBaseAdapter contentAdapter = new CardAnimeBaseAdapter();
            contentRecyclerView.setAdapter(contentAdapter);
            DataViewModel dataViewModel = new ViewModelProvider(activity).get(DataViewModel.class);

//            search bar event listener
            TextInputLayout textInputLayout = activity.findViewById(R.id.searchBar);
            textInputLayout.setEndIconOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText editText = textInputLayout.getEditText();
                    editText.clearFocus();
                    InputMethodManager in = (InputMethodManager)getActivity().getSystemService(activity.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    String newSearch = editText.getText().toString();
                    if (!newSearch.equals(search)) {
                        search = newSearch;
                        getAnimesFiltered(dataViewModel, activity, contentAdapter);
                    }
                }
            });

            textInputLayout.getEditText().setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        EditText editText = textInputLayout.getEditText();
                        editText.clearFocus();
                        InputMethodManager in = (InputMethodManager)getActivity().getSystemService(activity.INPUT_METHOD_SERVICE);
                        in.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                        String newSearch = editText.getText().toString();
                        if (!newSearch.equals(search)) {
                            search = newSearch;
                            getAnimesFiltered(dataViewModel, activity, contentAdapter);
                            return true;
                        }}
                    return false;
                }
            });

//            load default state
            getAnimesFiltered(dataViewModel, activity, contentAdapter);

//            year filter
            AutoCompleteTextView textViewYear = activity.findViewById(R.id.discoverFilterTextViewYear);
            textViewYear.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    String selectedValue = (String) adapterView.getItemAtPosition(position);
                    if (Objects.equals(selectedValue, activity.getResources().getString(R.string.any))) {
                        yearSelected = -1;
                    } else {
                        yearSelected = Integer.parseInt(selectedValue);
                    }
                    getAnimesFiltered(dataViewModel, activity, contentAdapter);
                }
            });
//            season filter
            AutoCompleteTextView textViewSeason = activity.findViewById(R.id.discoverFilterTextViewSeason);
            textViewSeason.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    String selectedValue = (String) adapterView.getItemAtPosition(position);
                    if (Objects.equals(selectedValue, activity.getResources().getString(R.string.any))) {
                        seasonSelected = null;
                    } else {
                        seasonSelected = Season.fromString(activity.getResources(), selectedValue);
                    }
                    getAnimesFiltered(dataViewModel, activity, contentAdapter);
                }
            });
//            format filter
            AutoCompleteTextView textViewFormat = activity.findViewById(R.id.discoverFilterTextViewFormat);
            textViewFormat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    String selectedValue = (String) adapterView.getItemAtPosition(position);
                    if (Objects.equals(selectedValue, activity.getResources().getString(R.string.any))) {
                        formatSelected = null;
                    } else {
                        formatSelected = Format.fromString(activity.getResources(), selectedValue);
                    }
                    getAnimesFiltered(dataViewModel, activity, contentAdapter);
                }
            });
        }
    }

    private void getAnimesFiltered(DataViewModel dataViewModel, AppCompatActivity activity,
                                   CardAnimeBaseAdapter contentAdapter) {
        dataViewModel.getAnimesFiltered(yearSelected, seasonSelected, formatSelected, search)
                .observe(activity, new Observer<BodyAnimeList>() {
                    @Override
                    public void onChanged(BodyAnimeList bodyAnimeList) {
                        contentAdapter.setData(bodyAnimeList.getData().getPage().getAnimes());
                        dataViewModel.getAnimesFilter().removeObserver(this);
                    }
                });
    }

}
