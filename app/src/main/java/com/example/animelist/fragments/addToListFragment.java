package com.example.animelist.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.animelist.R;
import com.example.animelist.model.Anime;
import com.example.animelist.model.AnimeListEntry;
import com.example.animelist.model.BodyUpdateAnimeListEntry;
import com.example.animelist.model.Date;
import com.example.animelist.model.enums.MediaListStatus;
import com.example.animelist.network.DataViewModel;
import com.example.animelist.utilities.SharedViewModel;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.slider.Slider;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class addToListFragment extends Fragment {

    private final Anime anime;
    private final AnimeListEntry changedEntry;

    public  addToListFragment(Anime anime) {
        this.anime = anime;
        changedEntry = new AnimeListEntry();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_to_list_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            AnimeListEntry animeListEntry = anime.getAnimeListEntry();
            changedEntry.setMediaId(anime.getId());
            if (animeListEntry != null) {
                changedEntry.setId(animeListEntry.getId());
            }
//            back on background press
            View background = activity.findViewById(R.id.addToListBackground);
            background.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onBackPressed();
                }
            });
//            setting up status
            ArrayList<String> list = new ArrayList<>();
            for (MediaListStatus status : MediaListStatus.values()) {
                list.add(status.toString(activity.getResources()));
            }
            String defaultStatus = MediaListStatus.CURRENT.toString(activity.getResources());
            if (animeListEntry != null && animeListEntry.getStatus() != null) {
                defaultStatus = animeListEntry.getStatus().toString(activity.getResources());
            }
            setupFilterMenu(list, R.id.addToListStatus, activity,
                    defaultStatus);
            AutoCompleteTextView textView = activity.findViewById(R.id.addToListStatus);
            textView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    changedEntry.setStatus(MediaListStatus.fromString(activity.getResources(), s.toString()));
                }
            });
//            setting up score
            Slider score = activity.findViewById(R.id.sliderScore);
            if (animeListEntry != null && animeListEntry.getScore() != null) {
                score = activity.findViewById(R.id.sliderScore);
                score.setValue(animeListEntry.getScore());
            }
            score.addOnChangeListener((slider, value, fromUser) -> {
                changedEntry.setScore((int) value);
            });
//            setting up episode progress
            TextView episodeMaxVal = activity.findViewById(R.id.episodeMaxVal);
            episodeMaxVal.setText(Integer.toString(anime.getEpisodes()));
            Slider episodeSlider = activity.findViewById(R.id.sliderEpisode);
            episodeSlider.setValueTo(anime.getEpisodes());
            if (animeListEntry != null && animeListEntry.getProgress() != null) {
                episodeSlider.setValue(animeListEntry.getProgress());
            }
            episodeSlider.addOnChangeListener(((slider, value, fromUser) -> {
                changedEntry.setProgress((int) value);
            }));
//            setting up start date
            TextInputLayout startDate = activity.findViewById(R.id.startDateInputLayout);
            if (animeListEntry != null && animeListEntry.getStartedAt() != null && animeListEntry.getStartedAt().getYear() != null) {
                startDate.getEditText().setText(animeListEntry.getStartedAt().toString());
                MaterialDatePicker<Long> datePicker = setupDatePicker(activity, startDate,
                        animeListEntry.getStartedAt().getTimeInMillisecond());
                datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @Override
                    public void onPositiveButtonClick(Long selection) {
                        Date newDate = new Date();
                        newDate.setDateFromTimeInMillisecond(selection);
                        changedEntry.setStartedAt(newDate);
                        startDate.getEditText().setText(newDate.toString());
                    }
                });
            } else {
                MaterialDatePicker<Long> datePicker = setupDatePicker(activity, startDate,
                        MaterialDatePicker.todayInUtcMilliseconds());
                datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @Override
                    public void onPositiveButtonClick(Long selection) {
                        Date newDate = new Date();
                        newDate.setDateFromTimeInMillisecond(selection);
                        changedEntry.setStartedAt(newDate);
                        startDate.getEditText().setText(newDate.toString());
                    }
                });

            }
//            setting up finish date
            TextInputLayout finishDate = activity.findViewById(R.id.finishDateInputLayout);
            if (animeListEntry != null && animeListEntry.getCompletedAt() != null && animeListEntry.getCompletedAt().getYear() != null) {
                finishDate.getEditText().setText(animeListEntry.getCompletedAt().toString());
                MaterialDatePicker<Long> datePicker = setupDatePicker(activity, finishDate,
                        animeListEntry.getCompletedAt().getTimeInMillisecond());
                datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @Override
                    public void onPositiveButtonClick(Long selection) {
                        Date newDate = new Date();
                        newDate.setDateFromTimeInMillisecond(selection);
                        changedEntry.setCompletedAt(newDate);
                        finishDate.getEditText().setText(newDate.toString());
                    }
                });

            } else {
                MaterialDatePicker<Long> datePicker = setupDatePicker(activity, finishDate,
                        MaterialDatePicker.todayInUtcMilliseconds());
                datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @Override
                    public void onPositiveButtonClick(Long selection) {
                        Date newDate = new Date();
                        newDate.setDateFromTimeInMillisecond(selection);
                        changedEntry.setCompletedAt(newDate);
                        finishDate.getEditText().setText(newDate.toString());
                    }
                });
            }
//            setting up save button
            Button save = activity.findViewById(R.id.saveAnimeEntry);
            Fragment me = this;
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataViewModel dataViewModel = new ViewModelProvider(activity).get(DataViewModel.class);
                    dataViewModel.updateAnimeEntry(changedEntry).observe(getViewLifecycleOwner(),
                            new Observer<BodyUpdateAnimeListEntry>() {
                        @Override
                        public void onChanged(BodyUpdateAnimeListEntry bodyUpdateAnimeListEntry) {
                            SharedViewModel sharedViewModel = new ViewModelProvider(activity)
                                    .get(SharedViewModel.class);
                            anime.setAnimeListEntry(bodyUpdateAnimeListEntry
                                    .getDataUpdateAnimeListEntry()
                                    .getAnimeListEntry());
                            sharedViewModel.setAnime(anime);
                            activity.onBackPressed();
                        }
                    });
                }
            });
        }
    }

    private <T> void setupFilterMenu(List<T> list, int autoCompleteTextViewId,
                                     AppCompatActivity activity, String defaultValue) {
        ArrayAdapter<T> adapter = new ArrayAdapter<>(requireContext(),
                R.layout.filter_item_list, list);
        AutoCompleteTextView textView = activity.findViewById(autoCompleteTextViewId);
        textView.setText(defaultValue, false);
        textView.setAdapter(adapter);
    }

    private MaterialDatePicker<Long> setupDatePicker(AppCompatActivity activity, TextInputLayout textInputLayout,
                                 long defaultDate) {
        MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText(R.string.select_date)
                .setSelection(defaultDate)
                .build();
        textInputLayout.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.show(activity.getSupportFragmentManager(), "DatePicker");
            }
        });
        return datePicker;
    }
}
