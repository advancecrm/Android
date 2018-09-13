package com.ballardscore.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.ballardscore.myfirstapp.util.Constants;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class DisplayMessageActivity extends AppCompatActivity {
    //<!-- AdMob Android Test Interstitial App Id: ca-app-pub-3940256099942544/1033173712  -->
    private InterstitialAd mInterstitialAd;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView2);
        textView.setText(message);

        MobileAds.initialize(this, Constants.SAMPLE_BANNER_ID);
        // Load an Ad:
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //Initialize Interstitial ad
        MobileAds.initialize(this, Constants.BALLARD_INTERST_ID);
        // Load an Ad:
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(Constants.BALLARD_INTERST_ID);
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

    }

    @Override
    public void onBackPressed() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    finish();
                }
            });
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
            super.onBackPressed();
        }
    }
}
