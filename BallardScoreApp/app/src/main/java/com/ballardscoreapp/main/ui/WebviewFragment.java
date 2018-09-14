package com.ballardscoreapp.main.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.ballardscoreapp.main.R;
import com.ballardscoreapp.main.util.Constants;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
//import com.google.ads.AdRequest;
//import com.google.ads.AdSize;
//import com.google.ads.AdView;

public class WebviewFragment extends Fragment {
    private AdView mAdView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.webview, parent, false);

        String sectionName = getArguments().getString("displayFragment");

        DetailActivity activity = (DetailActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(sectionName);
        }

        boolean isAppUnlocked = true;
        WebView webView = (WebView) rootView.findViewById(R.id.webview);
        if (!isAppUnlocked) {
            webView.getSettings().setLoadsImagesAutomatically(false);
        }
        if (sectionName != null) {
            String pageName = sectionName.replaceAll("\\s", "").replace("/", "").replace("-", "");
            pageName = pageName.substring(1, pageName.length());
            String url = String.format("file:///android_asset/html/%s.html", pageName);
            webView.loadUrl(url);
        }

//        FrameLayout layout = (FrameLayout) rootView.findViewById(R.id.admob_view);
//        AdView ad = new AdView(getActivity(), AdSize.SMART_BANNER, "ca-app-pub-7124699817614464/7549868151");
//        layout.addView(ad);
//        ad.loadAd(new AdRequest());
        MobileAds.initialize(rootView.getContext(), Constants.BALLARD_BANNER_ID);
        // Load an Ad:
        mAdView = rootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        return rootView;
    }
}
