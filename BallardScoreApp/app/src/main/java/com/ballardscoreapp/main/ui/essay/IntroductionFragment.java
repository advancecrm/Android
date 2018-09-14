package com.ballardscoreapp.main.ui.essay;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.ballardscoreapp.main.R;
import com.ballardscoreapp.main.ui.DetailActivity;
import com.ballardscoreapp.main.util.Constants;
import com.ballardscoreapp.main.util.Globals;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
//import com.google.ads.AdRequest;
//import com.google.ads.AdSize;
//import com.google.ads.AdView;


public class IntroductionFragment extends Fragment {
    private AdView mAdView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.webview, parent, false);

//        FrameLayout layout = (FrameLayout) rootView.findViewById(R.id.admob_view);
//        //AdView ad = new AdView(getActivity(), AdSize.SMART_BANNER, "ca-app-pub-7124699817614464/7549868151");
//        AdView ad = new AdView(getActivity(), AdSize.SMART_BANNER, Constants.BALLARD_BANNER_ID);
//        layout.addView(ad);
//        ad.loadAd(new AdRequest());
        MobileAds.initialize(rootView.getContext(), Constants.BALLARD_BANNER_ID);
        // Load an Ad:
        mAdView = rootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        WebView webView = (WebView) rootView.findViewById(R.id.webview);
        webView.loadUrl("file:///android_asset/html/NewBallardScore.html");

        DetailActivity activity = (DetailActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Introduction");
        }

        boolean isAppUnlocked = true; // TODO
        if (!isAppUnlocked) {
            webView.getSettings().setLoadsImagesAutomatically(false);
        }

        Globals globals = (Globals) getActivity().getApplicationContext();
        globals.monofrag = false;

        return rootView;
    }
}