package com.ballardscoreapp.main.ui;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ballardscoreapp.main.R;
import com.ballardscoreapp.main.payment.IabHelper;
import com.ballardscoreapp.main.payment.IabResult;
import com.ballardscoreapp.main.payment.Inventory;
import com.ballardscoreapp.main.ui.essay.EssayListFragment;
import com.ballardscoreapp.main.ui.score.ScoreFrag;
import com.ballardscoreapp.main.ui.training.TrainingListFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String PRODUCT_ID_UNLOCK = "com.ballard.app.unlock";
    private IabHelper mHelper;
    private boolean mUnlocked = false;

    public static Activity thisact;
    private FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_activity_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        thisact = this;

        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.contentintab);

        mTabHost.addTab(mTabHost.newTabSpec("Score").setIndicator("SCORE"),
                ScoreFrag.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("Training").setIndicator("TRAINING"),
                TrainingListFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("Video").setIndicator("VIDEO"),
                VideoFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("Essay").setIndicator("ESSAY"),
                EssayListFragment.class, null);

        for (int i = 0; i < mTabHost.getTabWidget().getChildCount(); i++) {
            View tab = mTabHost.getTabWidget().getChildAt(i);
            TextView tabTitle = (TextView) tab.findViewById(android.R.id.title);
            tabTitle.setTextColor(Color.WHITE);
            tabTitle.setSingleLine(true);
            tabTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initBillingHelper();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_score) {
            mTabHost.setCurrentTabByTag("Score");
        } else if (id == R.id.nav_training) {
            mTabHost.setCurrentTabByTag("Training");
        } else if (id == R.id.nav_video) {
            mTabHost.setCurrentTabByTag("Video");
        } else if (id == R.id.nav_essay) {
            mTabHost.setCurrentTabByTag("Essay");
        } else if (id == R.id.nav_faq) {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("displayFragment", "FaqMasterFragment");
            startActivity(intent);
        } else if (id == R.id.nav_unlock) {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("displayFragment", "UnlockFragment");
            startActivity(intent);
        } else if (id == R.id.nav_feedback) {
            Uri storeUri = Uri.parse("market://details?id=" + getPackageName());
            Intent intent = new Intent(Intent.ACTION_VIEW, storeUri);
            startActivity(intent);
            Toast.makeText(this, "Please scroll to the bottom, to the Reviews section", Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_contact) {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("displayFragment", "ContactFragment");
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initBillingHelper() {
        String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1aylmg5342yHQ5Jl0t94WG0Ngvne3UoRAtWUJERiFT5I+uDvCejOymGjs0lqHwRdkynWpDYWUhwGWYl6XYQay5nYusCddJBtxl+JFMTlbyTXR/D4Jt24zRgjil3sHgnHkTKNOr5XJrh7fvJuBxNS9+KGXIfpVUDJCvt+Ckan5bRSnNAYHRe1TlLX1+tZayz1R0NIrN6f8PqvbQJyJqOP9ip1qAzQUh3LoXDfnB/OOgpH8TMWzLstkMyKrjlq5loWmu/0VnY9NXfzZsMa4w3dZbG8Qgb5UpayoaDTe4olhOYZFKMNkkQe157U7OHI38iyF7pPMZm3+x9IRLjWVx+9FQIDAQAB";

        // compute your public key and store it in base64EncodedPublicKey
        mHelper = new IabHelper(this, base64EncodedPublicKey);

        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                if (result.isSuccess()) {
                    List<String> purchaseList = new ArrayList<String>();
                    purchaseList.add(PRODUCT_ID_UNLOCK);
                    mHelper.queryInventoryAsync(true, purchaseList,
                            new IabHelper.QueryInventoryFinishedListener() {
                                @Override
                                public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
                                    if (result.isFailure()) {
                                        // handle error
                                        return;
                                    }

                                    mUnlocked = inventory.hasPurchase(PRODUCT_ID_UNLOCK);
                                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                                    prefs.edit().putBoolean("unlocked", mUnlocked).apply();
                                }
                            });
                }
            }
        });
    }

    public boolean isAppUnlocked() {
        return mUnlocked;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        FragmentManager manager = getSupportFragmentManager();
        UnlockFragment fragment = (UnlockFragment) manager.findFragmentByTag("UnlockFragment");
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

}