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
//import com.google.ads.AdRequest;
//import com.google.ads.AdSize;
//import com.google.ads.AdView;

public class TrainingNeuroFragment extends Fragment {

    String[] mSections = {"1 Posture", "2 Square Window", "3 Arm Recoil",
            "4 Popliteal Angle", "5 Scarf Sign", "6 Heel To Ear"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, parent, false);

        DetailActivity activity = (DetailActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Neuromuscular Training");
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
