package com.example.animelist.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.animelist.R;
import com.example.animelist.fragments.AnimeFragment;
import com.example.animelist.fragments.ProfileFragment;
import com.example.animelist.fragments.SideNavigationDrawerFragment;
import com.example.animelist.utilities.Utilities;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        if (savedInstanceState == null) {
//            Add navigation drawer to the activity
            Utilities.insertFragment(this, R.id.generalFragmentContainer,
                    new SideNavigationDrawerFragment(),
                    SideNavigationDrawerFragment.class.getSimpleName(), false);
//            Add page content to the activity, Profile fragment in this case
            Utilities.insertFragment(this, R.id.navigationFragmentContainer,
                    new ProfileFragment(), ProfileFragment.class.getSimpleName(), false);
        }

    }

}
