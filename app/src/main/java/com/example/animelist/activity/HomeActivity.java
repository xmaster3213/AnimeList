package com.example.animelist.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.animelist.R;
import com.example.animelist.fragments.DiscoverFilterFragment;
import com.example.animelist.fragments.DiscoverFragment;
import com.example.animelist.fragments.noConnectionFragment;
import com.example.animelist.utilities.NetworkUtilities;
import com.example.animelist.utilities.Utilities;
import com.example.animelist.fragments.BottomAppBarFragment;
import com.example.animelist.fragments.HomeFragment;
import com.example.animelist.fragments.SideNavigationDrawerFragment;

public class HomeActivity extends AppCompatActivity implements noConnectionFragment.noConnectionFragmentListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        if (savedInstanceState == null) {
            if (!NetworkUtilities.isNetworkConnected(this)) {
                Utilities.insertFragment(this, R.id.generalFragmentContainer,
                        new noConnectionFragment(),
                        noConnectionFragment.class.getSimpleName(), false);
            } else {
                showPageContent();
            }
        }

    }

    @Override
    public void onBackPressed() {
        closeNavigationOrDefault();
    }

    private void closeNavigationOrDefault() {
        Fragment drawerFragment = getSupportFragmentManager().findFragmentById(R.id.generalFragmentContainer);
        if (drawerFragment instanceof SideNavigationDrawerFragment) {
            if (((SideNavigationDrawerFragment) drawerFragment).isDrawerOpen()) {
                ((SideNavigationDrawerFragment) drawerFragment).closeDrawer();
            } else {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void showPageContent() {
//            Add navigation drawer to the activity
        Utilities.insertFragment(this, R.id.generalFragmentContainer,
                new SideNavigationDrawerFragment(),
                SideNavigationDrawerFragment.class.getSimpleName(), false);
//            Add page content to the activity, Home fragment in this case
        Utilities.insertFragment(this, R.id.navigationFragmentContainer,
                new HomeFragment(), HomeFragment.class.getSimpleName(), false);
//            Add bottom app bar to the activity
        Utilities.insertFragment(this, R.id.bottomAppBarFragmentContainer,
                new BottomAppBarFragment(), BottomAppBarFragment.class.getSimpleName(),
                false);
    }

}
