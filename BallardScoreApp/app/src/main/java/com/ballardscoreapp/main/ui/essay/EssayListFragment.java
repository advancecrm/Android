package com.ballardscoreapp.main.ui.essay;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ballardscoreapp.main.R;
import com.ballardscoreapp.main.ui.DetailActivity;
import com.ballardscoreapp.main.util.Constants;
//import com.google.ads.AdRequest;
//import com.google.ads.AdSize;
//import com.google.ads.AdView;

public class EssayListFragment extends Fragment {

    String[] ItemText = {"Introduction", "New Ballard Score", "Neuromuscular Maturity", "Summary"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.monograph_frag_act, parent, false);

        ListView listView = (ListView) rootView.findViewById(R.id.monograph_list);
        listView.setAdapter(new MonographAdapter(getActivity(), ItemText));

//        FrameLayout admobView = (FrameLayout) rootView.findViewById(R.id.admob_view);
//        //AdView ad = new AdView(getActivity(), AdSize.SMART_BANNER, "ca-app-pub-7124699817614464/7549868151");
//        AdView ad = new AdView(getActivity(), AdSize.SMART_BANNER, Constants.BALLARD_BANNER_ID);
//        admobView.addView(ad);
//        ad.loadAd(new AdRequest());

        final Intent intent = new Intent(getActivity(), DetailActivity.class);
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                if (position == 0) {
                    intent.putExtra("displayFragment", "IntroductionFragment");
                }
                if (position == 1) {
                    intent.putExtra("displayFragment", "NewScoreFragment");
                }
                if (position == 2) {
                    intent.putExtra("displayFragment", "MaturityFragment");
                }
                if (position == 3) {
                    intent.putExtra("displayFragment", "SummaryFragment");
                }
                startActivity(intent);
            }
        });
        return rootView;
    }

    class MonographAdapter extends BaseAdapter {

        LayoutInflater mInflater;
        Context mContext;
        String[] items;


        public MonographAdapter(Context c, String[] itemText) {
            mContext = c;
            mInflater = LayoutInflater.from(c);
            items = itemText;
        }


        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public Object getItem(int arg0) {
            return items[arg0];
        }

        @Override
        public long getItemId(int arg0) {
            return 0;
        }

        @Override
        public View getView(int arg0, View convertView, ViewGroup arg2) {
            ViewHolder holder;

            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.list_item, null);
                holder = new ViewHolder();
                holder.t_text = (TextView) convertView.findViewById(R.id.title_text);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.t_text.setText(items[arg0]);
            return convertView;
        }

    }

    class ViewHolder {
        TextView t_text;
    }
}
