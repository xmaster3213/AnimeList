package com.example.animelist.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.animelist.R;
import com.example.animelist.fragments.BottomAppBarFragment;
import com.example.animelist.fragments.DiscoverFilterFragment;
import com.example.animelist.fragments.DiscoverFragment;
import com.example.animelist.fragments.SideNavigationDrawerFragment;
import com.example.animelist.fragments.noConnectionFragment;
import com.example.animelist.utilities.ApiServiceSingleton;
import com.example.animelist.utilities.NetworkUtilities;
import com.example.animelist.utilities.Utilities;
import com.example.animelist.fragments.LoginFragment;

public class LoginActivity extends AppCompatActivity implements noConnectionFragment.noConnectionFragmentListener {

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
        if (ApiServiceSingleton.getInstance() != null) {
            super.onBackPressed();
        }
    }

    @Override
    public void showPageContent() {
        Utilities.insertFragment(this, R.id.generalFragmentContainer, new LoginFragment(),
                LoginFragment.class.getSimpleName(), false);
    }
}
