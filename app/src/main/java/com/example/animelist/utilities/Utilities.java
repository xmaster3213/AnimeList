package com.example.animelist.utilities;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.animelist.model.enums.Season;
import com.example.animelist.network.ApiService;
import com.example.animelist.network.OAuth2Client;

public class Utilities {

    public static void insertFragment(AppCompatActivity activity, int containerViewId,
                                      Fragment fragment, String tag, boolean addToBackStack) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container_view with this fragment
        transaction.replace(containerViewId, fragment, tag);

        //add the transaction to the back stack so the user can navigate back except for the HomeFragment
        if (addToBackStack) {
            transaction.addToBackStack(tag);
        }

        // Commit the transaction
        transaction.commit();
    }

    public static Season getSeasonGivenMonth(int month) {
        month = month + 1;
        switch (month) {
            case 12:
            case 1:
            case 2:
                return Season.WINTER;
            case 3:
            case 4:
            case 5:
                return Season.SPRING;
            case 6:
            case 7:
            case 8:
                return Season.SUMMER;
            case 9:
            case 10:
            case 11:
                return Season.FALL;
            default:
                Log.e("Animelist", "Utilies.getSeasonGivenMonth(): invalid month number");
                return null;
        }
    }

    public static int calculateNoOfColumns(Context context, float columnWidthDp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (screenWidthDp / columnWidthDp);
        if (noOfColumns <= 0) {
            return 1;
        } else {
            return noOfColumns;
        }
    }

    public static int calculateSpacing(Context context, int nOfColumns, float columnWidthDp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;
        float notOccupiedScreen = screenWidthDp - (nOfColumns * columnWidthDp);
        return (int) (notOccupiedScreen / (nOfColumns + 1));
    }

    public static int dpToPixels(Context context, int dp) {
        return dp * (context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

}
