package com.example.animelist.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.animelist.R;
import com.example.animelist.model.BodyUser;
import com.example.animelist.model.StartYearStatistics;
import com.example.animelist.model.StatusStatistics;
import com.example.animelist.model.UserStatistics;
import com.example.animelist.model.Viewer;
import com.example.animelist.network.DataViewModel;
import com.example.animelist.utilities.SimpleValueFormatter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            ImageView userImage = activity.findViewById(R.id.userImage);
            ImageView userBanner = activity.findViewById(R.id.userBanner);
            TextView userName = activity.findViewById(R.id.userName);
            TextView totalAnime = activity.findViewById(R.id.totalAnimeValue);
            TextView episodesWatched = activity.findViewById(R.id.episodesWatchedValue);
            TextView daysWatched = activity.findViewById(R.id.daysWatchedValue);
            TextView meanScore = activity.findViewById(R.id.meanScoreValue);
            TextView standardDeviation = activity.findViewById(R.id.standardDeviationValue);

            List<TextView> legendStatuses = new ArrayList<>();
            legendStatuses.add(activity.findViewById(R.id.legendStatus1));
            legendStatuses.add(activity.findViewById(R.id.legendStatus2));
            legendStatuses.add(activity.findViewById(R.id.legendStatus3));
            legendStatuses.add(activity.findViewById(R.id.legendStatus4));
            List<TextView> legendValues = new ArrayList<>();
            legendValues.add(activity.findViewById(R.id.legendValue1));
            legendValues.add(activity.findViewById(R.id.legendValue2));
            legendValues.add(activity.findViewById(R.id.legendValue3));
            legendValues.add(activity.findViewById(R.id.legendValue4));
            List<ConstraintLayout> legendContainer = new ArrayList<>();
            legendContainer.add(activity.findViewById(R.id.legendContainer1));
            legendContainer.add(activity.findViewById(R.id.legendContainer2));
            legendContainer.add(activity.findViewById(R.id.legendContainer3));
            legendContainer.add(activity.findViewById(R.id.legendContainer4));

//            Pie chart for status
            PieChart pieChart = activity.findViewById(R.id.chartStatus);
            pieChart.setUsePercentValues(true);
            pieChart.getDescription().setEnabled(false);
            pieChart.getLegend().setEnabled(false);
            pieChart.setExtraOffsets(5, 10, 5, 5);

            pieChart.setTouchEnabled(false);
            pieChart.setDrawHoleEnabled(false);
            pieChart.setTransparentCircleRadius(0f);
            pieChart.setRotationEnabled(false);
            pieChart.setDrawEntryLabels(false);

//            Linear chart for year
            LineChart chartLinear = view.findViewById(R.id.chartYear);
            Resources.Theme theme = activity.getTheme();
            TypedValue typedValue = new TypedValue();
            SimpleValueFormatter formatter = new SimpleValueFormatter();

            chartLinear.setTouchEnabled(true);
            chartLinear.setDrawGridBackground(true);

            chartLinear.getDescription().setEnabled(false);
            chartLinear.getLegend().setEnabled(false);
            chartLinear.getAxisLeft().setEnabled(false);
            chartLinear.getAxisRight().setEnabled(false);

            chartLinear.getAxisLeft().setDrawGridLines(false);
            chartLinear.getXAxis().setDrawGridLines(false);
            chartLinear.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
            chartLinear.getXAxis().setValueFormatter(formatter);
            theme.resolveAttribute(R.attr.graph_text_color, typedValue, true);
            chartLinear.getXAxis().setTextColor(typedValue.data);
            chartLinear.getXAxis().setGranularity(1f);

            chartLinear.setHighlightPerDragEnabled(false);
            chartLinear.setHighlightPerTapEnabled(false);

            chartLinear.setDrawGridBackground(false);

//            Add data
            DataViewModel dataViewModel = new ViewModelProvider(activity).get(DataViewModel.class);
            dataViewModel.getUserInfo().observe(activity, new Observer<BodyUser>() {
                @Override
                public void onChanged(BodyUser bodyUser) {

//                    Update profile interface with user infos
                    Viewer viewer = bodyUser.getData().getViewer();
                    userName.setText(viewer.getName());
                    if (!(viewer.getAvatar().getLarge() == null || Objects.equals(viewer.getAvatar().getLarge(), ""))) {
                        Picasso.get().load(viewer.getAvatar().getLarge()).into(userImage);
                    }
                    if (!(viewer.getBannerImage() == null || Objects.equals(viewer.getBannerImage(), ""))) {
                        Picasso.get().load(viewer.getBannerImage()).into(userBanner);
                    }
                    UserStatistics userStatistics = bodyUser.getData().getViewer()
                            .getStatistics().getAnimeStatistics();
                    totalAnime.setText(userStatistics.getCount() != null ?
                            userStatistics.getCount().toString() : "0");
                    episodesWatched.setText(userStatistics.getEpisodesWatched() != null ?
                            userStatistics.getEpisodesWatched().toString() : "0");
                    daysWatched.setText(userStatistics.getDaysWatched() != null ?
                            String.format("%.2f", userStatistics.getDaysWatched()) : "0");
                    meanScore.setText(userStatistics.getMeanScore() != null ?
                            userStatistics.getMeanScore().toString() : "0");
                    standardDeviation.setText(userStatistics.getStandardDeviation() != null ?
                            userStatistics.getStandardDeviation().toString() : "0");

//                    Add data to pie chart
                    List<PieEntry> entriesPie = new ArrayList<>();
                    float sum = 0;
                    for (StatusStatistics statusStatistics: userStatistics.getStatusStatistics()) {
                        if (statusStatistics.getStatus() != null) {
                            PieEntry pieEntry = new PieEntry(statusStatistics.getCount(),
                                    statusStatistics.getStatus().toString(activity.getResources()));
                            entriesPie.add(pieEntry);
                            sum += statusStatistics.getCount();
                        }
                    }

                    for (int i = 0; i < entriesPie.size(); i++) {
                        legendStatuses.get(i).setText(entriesPie.get(i).getLabel());
                        legendValues.get(i).setText(String.format("%.1f", entriesPie.get(i).getValue() / sum * 100) + "%");
                        legendContainer.get(i).setVisibility(View.VISIBLE);
                    }

                    PieDataSet setPie = new PieDataSet(entriesPie, activity.getString(R.string.status_distribution));

                    ArrayList<Integer> colors = new ArrayList<>();
                    colors.add(activity.getColor(R.color.orange));
                    colors.add(activity.getColor( R.color.light_blue));
                    colors.add(activity.getColor(R.color.purple));
                    colors.add(activity.getColor(R.color.green));
                    for (int c : ColorTemplate.PASTEL_COLORS)
                        colors.add(c);
                    setPie.setColors(colors);

                    PieData dataPie = new PieData(setPie);
                    dataPie.setDrawValues(false);
                    dataPie.setValueTextSize(20f);

                    pieChart.setData(dataPie);
                    pieChart.invalidate(); // refresh

//                    Add data to linear chart
                    ArrayList<Entry> entriesLinear = new ArrayList<>();

                    for (StartYearStatistics startYearStatistics: userStatistics.getStartYearStatistics()) {
                        if (startYearStatistics.getStartYear() != null) {
                            Entry linearEntry = new Entry(startYearStatistics.getStartYear(), startYearStatistics.getCount());
                            entriesLinear.add(linearEntry);
                        }
                    }

                    Collections.sort(entriesLinear, new Comparator<Entry>() {
                        @Override
                        public int compare(Entry o1, Entry o2) {
                            return (int) (o1.getX() - o2.getX());
                        }
                    });

                    LineDataSet setLinear;

                    // create a dataset and give it a type
                    setLinear = new LineDataSet(entriesLinear, "StartYear");

                    setLinear.setDrawIcons(false);

                    // draw dashed line
                    setLinear.setMode(LineDataSet.Mode.CUBIC_BEZIER);

                    // color line and points
                    theme.resolveAttribute(R.attr.accent_color, typedValue, true);
                    setLinear.setColor(typedValue.data);
                    theme.resolveAttribute(R.attr.graph_dot_color, typedValue, true);
                    setLinear.setCircleColor(typedValue.data);

                    setLinear.setLineWidth(4f);
                    setLinear.setCircleRadius(6f);
                    setLinear.setDrawCircleHole(false);

                    setLinear.setDrawFilled(true);
                    setLinear.setFillAlpha(255);
                    theme.resolveAttribute(R.attr.fill_color, typedValue, true);
                    setLinear.setFillColor(typedValue.data);

                    theme.resolveAttribute(R.attr.graph_text_color, typedValue, true);
                    setLinear.setValueTextColor(typedValue.data);
                    setLinear.setValueFormatter(formatter);

                    // text size of values
                    setLinear.setValueTextSize(14f);


                    // set the filled area
                    ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                    dataSets.add(setLinear); // add the data sets

                    // create a data object with the data sets
                    LineData dataLinear = new LineData(dataSets);

                    // set data
                    chartLinear.setData(dataLinear);
                    chartLinear.invalidate();

                }
            });
        }
    }
}
