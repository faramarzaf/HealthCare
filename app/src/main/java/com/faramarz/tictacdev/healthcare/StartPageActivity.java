package com.faramarz.tictacdev.healthcare;


import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.faramarz.tictacdev.healthcare.CustomDialogs.CustomExitDialog;
import com.faramarz.tictacdev.healthcare.StarterPageFragments.HomeFragment;
import com.faramarz.tictacdev.healthcare.StarterPageFragments.ProfileFragment;
import com.faramarz.tictacdev.healthcare.StarterPageFragments.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class StartPageActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private BottomNavigationView bottomNavigation;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        bind();
        setSupportActionBar(toolbar);
        // ButterKnife.bind(this);
        setNavigationPages();


    }

    void bind() {
        toolbar = findViewById(R.id.toolbar);
        bottomNavigation = findViewById(R.id.navigation);

    }

    void setNavigationPages() {
        loadFragment(new HomeFragment());
        bottomNavigation.inflateMenu(R.menu.menu_nav);
        fragmentManager = getSupportFragmentManager();
        bottomNavigation.getMenu().findItem(R.id.item_2).setChecked(true);
        bottomNavigation.setSelectedItemId(R.id.item_2);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                switch (id) {
                    case R.id.item_1:
                        fragment = new ProfileFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.item_2:
                        fragment = new HomeFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.item_3:
                        fragment = new SettingsFragment();
                        loadFragment(fragment);
                        return true;

                }
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frame_container, fragment).commit();
                return true;
            }
        });

    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        showExitDialog();
    }

    private void showExitDialog() {
        CustomExitDialog customExitDialog = new CustomExitDialog();
        customExitDialog.setCancelable(false);
        customExitDialog.show(getSupportFragmentManager(), "CUSTOM_DIALOG");

    }



}
