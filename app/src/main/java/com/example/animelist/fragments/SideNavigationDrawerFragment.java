package com.example.animelist.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.animelist.R;
import com.example.animelist.activity.LoginActivity;
import com.example.animelist.activity.ProfileActivity;
import com.example.animelist.model.BodyUser;
import com.example.animelist.network.DataViewModel;
import com.example.animelist.utilities.ApiServiceSingleton;
import com.example.animelist.utilities.SharedViewModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

public class SideNavigationDrawerFragment extends Fragment {

    DrawerLayout drawerLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.side_navigation_drawer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            drawerLayout = activity.findViewById(R.id.drawerLayout);
            MaterialToolbar topAppBar = activity.findViewById(R.id.topAppBar);
            activity.setSupportActionBar(topAppBar);
//            ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
//                    activity,
//                    drawerLayout,
//                    topAppBar,
//                    R.string.side_navigation_drawer_open,
//                    R.string.side_navigation_drawer_close
//            );
//            drawerLayout.addDrawerListener(actionBarDrawerToggle);
//            actionBarDrawerToggle.syncState();

            String currentClass = activity.getClass().getSimpleName();
            Log.e("TAG", "onViewCreated: " + currentClass);
//            Navigation activities
            if (currentClass.equals("HomeActivity") || currentClass.equals("DiscoverActivity") || currentClass.equals("MyListActivity")) {
                NavigationView navigationView = activity.findViewById(R.id.navigationView);
                navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                    Move to Activity Login if Logout button pressed.
                        switch (item.getItemId()) {
                            case R.id.sideNavigationMenuLogout:
                                ApiServiceSingleton.deleteApiService();
                                CookieManager.getInstance().removeAllCookies(null);
                                CookieManager.getInstance().flush();
                                Intent intent = new Intent(activity, LoginActivity.class);
                                startActivity(intent);
                                break;
                        }
                        return true;
                    }
                });
//            Move to Activity Profile if Profile button pressed.
                View headerView = navigationView.getHeaderView(0);
                headerView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(activity, ProfileActivity.class);
                        startActivity(intent);
                    }
                });

                topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawerLayout.open();
                    }
                });

                DataViewModel dataViewModel = new ViewModelProvider(activity).get(DataViewModel.class);
                dataViewModel.getUserInfo().observe(activity, new Observer<BodyUser>() {
                    @Override
                    public void onChanged(BodyUser bodyUser) {
                        SharedViewModel sharedViewModel = new ViewModelProvider(activity).get(SharedViewModel.class);
                        sharedViewModel.setViewer(bodyUser.getData().getViewer());
                        TextView textView = activity.findViewById(R.id.sideHeaderNavigationDrawerUserName);
                        String username = bodyUser.getData().getViewer().getName();
                        textView.setText(username);
                        ImageView imageView = activity.findViewById(R.id.sideHeaderNavigationDrawerUserImage);
                        String imageUrl = bodyUser.getData().getViewer().getAvatar().getLarge();
                        Picasso.get().load(imageUrl).into(imageView);
                    }
                });
            } else {
                topAppBar.setNavigationIcon(R.drawable.baseline_arrow_back_32);
                topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activity.finish();
                    }
                });
            }



        }
    }

    public boolean isDrawerOpen() {
        return drawerLayout.isOpen();
    }

    public void closeDrawer() {
        if (isDrawerOpen()) {
            drawerLayout.close();
        }
    }
}
