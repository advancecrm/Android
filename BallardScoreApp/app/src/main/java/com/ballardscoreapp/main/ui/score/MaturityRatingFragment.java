package com.ballardscoreapp.main.ui.score;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.ballardscoreapp.main.R;
import com.ballardscoreapp.main.util.ActionBar;


public class MaturityRatingFragment extends Fragment {

    WebView web_view;

    ActionBar ab;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        container = (LinearLayout) inflater.inflate(R.layout.maturity_rating_webview, container, false);

        ab = (ActionBar) container.findViewById(R.id.action_bar);

        web_view = (WebView) container.findViewById(R.id.maturity_rating_webview);
        web_view.getSettings().setJavaScriptEnabled(true);
        web_view.loadUrl("file:///android_asset/html/maturityTableView.html");
        ab.setActionText("MATURITY RATING");

        return container;
    }

}
