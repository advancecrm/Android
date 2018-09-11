package com.ballardscoreapp.main.ui.training;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

public class TrainingListFragment extends Fragment {

    String[] mSections = {"Neuromuscular Training", "Physical Training"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.monograph_frag_act, parent, false);

        ListView listView = (ListView) rootView.findViewById(R.id.monograph_list);
        listView.setAdapter(new SectionListAdapter(getActivity(), mSections));

        FrameLayout admobView = (FrameLayout) rootView.findViewById(R.id.admob_view);
        AdView ad = new AdView(getActivity(), AdSize.SMART_BANNER, "ca-app-pub-7124699817614464/7549868151");
        admobView.addView(ad);
        ad.loadAd(new AdRequest());

        final Intent intent = new Intent(getActivity(), DetailActivity.class);
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                if (position == 0) {
                    intent.putExtra("displayFragment", "TrainingNeuroFragment");
                }
                if (position == 1) {
                    intent.putExtra("displayFragment", "TrainingPhysicalFragment");
                }
                startActivity(intent);
            }
        });
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
