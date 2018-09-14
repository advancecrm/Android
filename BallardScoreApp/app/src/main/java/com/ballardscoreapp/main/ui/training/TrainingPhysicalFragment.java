package com.ballardscoreapp.main.ui.training;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ballardscoreapp.main.R;
import com.ballardscoreapp.main.ui.DetailActivity;
import com.ballardscoreapp.main.ui.WebviewFragment;
import com.ballardscoreapp.main.util.Constants;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
//import com.google.ads.AdRequest;
//import com.google.ads.AdSize;
//import com.google.ads.AdView;

public class TrainingPhysicalFragment extends Fragment {
    private AdView mAdView;
    String[] mSections = {"1 Skin", "2 Lanugo", "3 Plantar Surface", "4 Breast",
            "5 Eye/Ear", "6 Genitals-male", "7 Genitals-female"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, parent, false);

        DetailActivity activity = (DetailActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Physical Training");
        }

        ListView listView = (ListView) rootView.findViewById(R.id.listview);
        listView.setAdapter(new SectionListAdapter(getActivity(), mSections));
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Bundle arguments = new Bundle();
                arguments.putString("displayFragment", mSections[position]);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                WebviewFragment f = new WebviewFragment();
                f.setArguments(arguments);
                ft.replace(R.id.container, f);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

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

    private class SectionListAdapter extends ArrayAdapter<String> {

        public SectionListAdapter(Context context, String[] sections) {
            super(context, 0, sections);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());

            View rootView;
            if (convertView != null) {
                rootView = convertView;
            } else {
                rootView = inflater.inflate(R.layout.list_item, parent, false);
            }

            TextView titleView = (TextView) rootView.findViewById(R.id.title_text);
            titleView.setText(getItem(position));

            return rootView;
        }
    }

}
