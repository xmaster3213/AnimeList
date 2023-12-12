package com.example.animelist.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.animelist.R;
import com.example.animelist.utilities.Utilities;
import com.example.animelist.fragments.BottomAppBarFragment;
import com.example.animelist.fragments.HomeFragment;
import com.example.animelist.fragments.SideNavigationDrawerFragment;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        Log.e("idk", "onCreate: " + (savedInstanceState == null));
        if (savedInstanceState == null) {
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

    @Override
    public void onBackPressed() {
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
}
