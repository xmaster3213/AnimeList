package com.example.animelist.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.animelist.R;
import com.example.animelist.fragments.BottomAppBarFragment;
import com.example.animelist.fragments.DiscoverFilterFragment;
import com.example.animelist.fragments.DiscoverFragment;
import com.example.animelist.fragments.SideNavigationDrawerFragment;
import com.example.animelist.utilities.Utilities;

public class DiscoverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        if (savedInstanceState == null) {
//            Add navigation drawer to the activity
            Utilities.insertFragment(this, R.id.generalFragmentContainer,
                    new SideNavigationDrawerFragment(),
                    SideNavigationDrawerFragment.class.getSimpleName(), false);
//            Add content filter to the activity
            Utilities.insertFragment(this, R.id.navigationFragmentContainer,
                    new DiscoverFilterFragment(), DiscoverFilterFragment.class.getSimpleName(), false);
//            Add page content to the activity, Discover fragment in this case
            Utilities.insertFragment(this, R.id.discoverFilterFragmentContainer,
                    new DiscoverFragment(), DiscoverFragment.class.getSimpleName(), false);
//            Add bottom app bar to the activity
            Utilities.insertFragment(this, R.id.bottomAppBarFragmentContainer,
                    new BottomAppBarFragment(), BottomAppBarFragment.class.getSimpleName(),
                    false);
        }

    }
}
