package com.example.animelist.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.animelist.R;
import com.example.animelist.fragments.AnimeFragment;
import com.example.animelist.fragments.BottomAppBarFragment;
import com.example.animelist.fragments.DiscoverFragment;
import com.example.animelist.fragments.SideNavigationDrawerFragment;
import com.example.animelist.utilities.Utilities;

public class AnimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        Log.e("TAG", "onCreate: anime activity started");
        if (savedInstanceState == null) {
//            Add navigation drawer to the activity
            Utilities.insertFragment(this, R.id.generalFragmentContainer,
                    new SideNavigationDrawerFragment(),
                    SideNavigationDrawerFragment.class.getSimpleName(), false);
//            Add page content to the activity, Anime fragment in this case
            Utilities.insertFragment(this, R.id.navigationFragmentContainer,
                    new AnimeFragment(), AnimeFragment.class.getSimpleName(), false);
        }

    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Log.e("TAG", "onBackPressed: " + fragmentManager.getBackStackEntryCount());
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
