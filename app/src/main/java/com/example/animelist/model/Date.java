package com.example.animelist.model;

import androidx.annotation.NonNull;

import java.text.DateFormat;
import java.util.Calendar;

public class Date {
    private Integer year;
    private Integer month;
    private Integer day;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    @NonNull
    @Override
    public String toString() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getYear(), getMonth(), getDay());
        return DateFormat.getDateInstance().format(calendar.getTime());
    }

    public String toJsonString() {
        return "{\n" +
                "    \"year\": getYear(),\n" +
                "    \"month\": getMonth(),\n" +
                "    \"day\": getDay()\n" +
                "  }";
    }

    public void setDateFromTimeInMillisecond(long time) {
        Calendar selectedDate = Calendar.getInstance();
        selectedDate.setTimeInMillis(time);
        this.setYear(selectedDate.get(Calendar.YEAR));
        this.setMonth(selectedDate.get(Calendar.MONTH));
        this.setYear(selectedDate.get(Calendar.DAY_OF_MONTH));
    }

    public long getTimeInMillisecond() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getYear(), getMonth(), getDay());
        return calendar.getTimeInMillis();
    }
}
