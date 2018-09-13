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
//import com.google.ads.AdRequest;
//import com.google.ads.AdSize;
//import com.google.ads.AdView;

public class MaturityFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.webview, parent, false);

        DetailActivity activity = (DetailActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Neuromuscular");
        }

        WebView webView = (WebView) rootView.findViewById(R.id.webview);
        webView.loadUrl("file:///android_asset/html/neuromuscularcat.html");

//        FrameLayout layout = (FrameLayout) rootView.findViewById(R.id.admob_view);
//        //AdView ad = new AdView(getActivity(), AdSize.SMART_BANNER, "ca-app-pub-7124699817614464/7549868151");
//        AdView ad = new AdView(getActivity(), AdSize.SMART_BANNER, Constants.BALLARD_BANNER_ID);
//        layout.addView(ad);
//        ad.loadAd(new AdRequest());

        return rootView;
    }


}
