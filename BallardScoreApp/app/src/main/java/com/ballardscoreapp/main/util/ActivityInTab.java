package com.ballardscoreapp.main.util;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.ballardscoreapp.main.R;

public class ActivityInTab extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_tab);
    }

    protected void displayFragment(Fragment newFragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        String tag = newFragment.getClass().getSimpleName();
        ft.replace(R.id.contentintab, newFragment, tag);
        ft.addToBackStack(null);
        ft.commit();
    }
}
