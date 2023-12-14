package com.example.animelist.utilities;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.DecimalFormat;

public class SimpleValueFormatter extends ValueFormatter {
    private final DecimalFormat format = new DecimalFormat("0");

    @Override
    public String getAxisLabel(float value, AxisBase axis) {
        return format.format(value);
    }

    @Override
    public String getPointLabel(Entry entry) {
        return format.format(entry.getY());
    }


}
