package com.ballardscoreapp.main.ui.faq;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ballardscoreapp.main.R;
import com.ballardscoreapp.main.ui.DetailActivity;
import com.ballardscoreapp.main.util.Constants;
import com.ballardscoreapp.main.util.Globals;
import com.ballardscoreapp.main.util.WebserviceCall;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
//import com.google.ads.AdRequest;
//import com.google.ads.AdSize;
//import com.google.ads.AdView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class FaqMasterFragment extends Fragment {
//    private AdView mAdView;
    String aResponse = "";
    String[] ItemText = {"Is there a formula that can be a",
            "The paper form does from", "Can we assessment be performed",
            "Why does the score increase a", "When assessing a new born with",
            "Do must hospitals that you are ", "cvcv", "Test1"};
    ProgressDialog pd;
    Globals global;
    FrameLayout layout;
    ArrayList<HashMap<String, String>> queAnsList;
    private ListView Faq_list;
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            pd.dismiss();
            String res = (String) msg.obj;
            if (res.equalsIgnoreCase("true")) {
                Faq_list.setAdapter(new FaqAdapter(getActivity(), global.getQueAnsList()));
            } else {
                Toast.makeText(getActivity(), "No Faq found", Toast.LENGTH_SHORT).show();
            }
            Log.i("response from server", "" + aResponse);
        }
    };
    private Runnable testservice = new Runnable() {

        @Override
        public void run() {
            String result = "";
            try {

                WebserviceCall call = new WebserviceCall();
                aResponse = call.getConvertedWeight("GETFAQ");
                result = parseJson(aResponse);

            } catch (Exception e) {
                e.printStackTrace();
            }
            Message msg = new Message();
            msg.obj = result;
            handler.sendMessage(msg);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.faq_frag_act, parent, false);

        DetailActivity activity = (DetailActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("FAQ");
        }

        global = (Globals) getActivity().getApplicationContext();
        Faq_list = (ListView) rootView.findViewById(R.id.faq_list);

//        layout = (FrameLayout) rootView.findViewById(R.id.admob_view);

//        AdView ad = new AdView(getActivity(), AdSize.SMART_BANNER, "ca-app-pub-7124699817614464/7549868151");
//        layout.addView(ad);
//        ad.loadAd(new AdRequest());

//        MobileAds.initialize(rootView.getContext(), Constants.BALLARD_BANNER_ID);
//        // Load an Ad:
//        mAdView = rootView.findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);


        if (global.getQueAnsList().size() > 0) {
            Faq_list.setAdapter(new FaqAdapter(getActivity(), global.getQueAnsList()));
        } else {
            pd = ProgressDialog.show(getActivity(), "", "loading");
            new Thread(null, testservice, "").start();
        }

        /*
        TODO: Move tbe contact form to navigation drawer
        container.findViewById(R.id.message).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Fragment newFragment = new ContactFragment();
                FragmentTransaction transaction = getFragmentManager()
                        .beginTransaction();

                transaction.replace(R.id.contentintab, newFragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });
         */


        Faq_list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                global.pos = arg2;

                Fragment newFragment = new FaqDetailFragment();
                FragmentTransaction transaction = getFragmentManager()
                        .beginTransaction();

                transaction.replace(R.id.container, newFragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

        return rootView;
    }

    protected String parseJson(String result) {
        String success = "error";
        try {

            // Remove HTML tags from the response string
            String json = result.replaceAll("\\<.*?>", "");

            // Format questions and answers properly
            json = json.replace("&nbsp;", "");
            json = json.replaceAll("\\.", ". ").replaceAll("\\.  ", "\\. ");
            json = json.replaceAll("  ", " ");
            json = json.replaceAll(" :", ":");
            json = json.replace("Dr. BallardReferences", "References");

            JSONArray jaray = new JSONArray(json);
            queAnsList = new ArrayList<>();
            if (jaray.length() > 0) {
                for (int i = 0; i < jaray.length(); i++) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    JSONObject job = jaray.getJSONObject(i);
                    String question = job.getString(Constants.QUESTION);
                    String answer = job.getString(Constants.ANSWER);

                    map.put(Constants.QUESTION, question);
                    map.put(Constants.ANSWER, answer);

                    queAnsList.add(map);
                }
                global.setQueAnsList(queAnsList);
            }
            success = "true";
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public void onResume() {
        super.onResume();

        Globals globals = (Globals) getActivity().getApplicationContext();
        globals.faqfrag = true;
    }

    class FaqAdapter extends BaseAdapter {

        LayoutInflater mInflater;
        Context mContext;
        ArrayList<HashMap<String, String>> items;

        public FaqAdapter(Context c, ArrayList<HashMap<String, String>> arrayList) {

            mContext = c;
            mInflater = LayoutInflater.from(c);
            items = arrayList;
        }

        @Override
        public int getCount() {

            return items.size();
        }

        @Override
        public Object getItem(int arg0) {

            return items.get(arg0);
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
                holder.t_text = (TextView) convertView
                        .findViewById(R.id.title_text);
                convertView.setTag(holder);

            } else {

                holder = (ViewHolder) convertView.getTag();

            }
            holder.t_text.setText(items.get(arg0).get(Constants.QUESTION));

            return convertView;
        }

    }

    class ViewHolder {
        TextView t_text;
    }
}
