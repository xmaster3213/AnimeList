package com.example.animelist.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.animelist.R;
import com.example.animelist.utilities.ApiServiceSingleton;
import com.example.animelist.utilities.Utilities;
import com.example.animelist.fragments.LoginFragment;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        if (savedInstanceState == null) {
            Utilities.insertFragment(this, R.id.generalFragmentContainer, new LoginFragment(),
                    LoginFragment.class.getSimpleName(), false);
        }
    }

    @Override
    public void onBackPressed() {
        if (ApiServiceSingleton.getInstance() != null) {
            super.onBackPressed();
        }
    }
}
