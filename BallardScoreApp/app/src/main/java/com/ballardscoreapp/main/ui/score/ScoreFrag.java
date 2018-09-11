package com.ballardscoreapp.main.ui.score;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ballardscoreapp.main.R;
import com.ballardscoreapp.main.payment.IabHelper;
import com.ballardscoreapp.main.util.ActionBar;
import com.ballardscoreapp.main.util.Constants;
import com.ballardscoreapp.main.util.Globals;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

public class ScoreFrag extends Fragment {

    private static final String PRODUCT_ID_UNLOCK = "com.ballard.app.unlock";
    Button reset;
    Button Maturity_rating_table, Calculate_maturity_rate;
    SharedPreferences sp;
    TextView week_tv, score_tv;
    AdView adView;
    LinearLayout layout;
    Globals global;
    boolean first = true;
    boolean paused;
    int rrcount = 0;
    boolean buycheck = false, fullscreencheck = false;
    TextView checkTv;
    String isphone = "";
    private ActionBar ab;
    private IabHelper mHelper;


    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.score_frag_act, parent, false);

        sp = getActivity().getSharedPreferences(Constants.PREFS_NAME,
                Context.MODE_PRIVATE);

        checkTv = (TextView) rootView.findViewById(R.id.header);
        isphone = checkTv.getText().toString();


        global = (Globals) getActivity().getApplicationContext();

        score_tv = (TextView) rootView.findViewById(R.id.ed_one);
        week_tv = (TextView) rootView.findViewById(R.id.ed_two);
        Maturity_rating_table = (Button) rootView
                .findViewById(R.id.maturity_rating_table);
        Calculate_maturity_rate = (Button) rootView
                .findViewById(R.id.calculate_maturity_rate);
        LinearLayout layout = (LinearLayout) rootView
                .findViewById(R.id.admob_view);

        //AdSize adsize = new AdSize(LayoutParams.MATCH_PARENT, 120);

        AdView ad = new AdView(getActivity(), AdSize.SMART_BANNER,
                "ca-app-pub-7124699817614464/7549868151");
        layout.addView(ad);
        ad.loadAd(new AdRequest());

        Calculate_maturity_rate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (CalulateMaturityRatingFragment.act_obj != null) {
                    CalulateMaturityRatingFragment.act_obj.finish();
                }
                startActivity(new Intent(getActivity(), CalulateMaturityRatingFragment.class));
            }
        });

        Maturity_rating_table.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Fragment newFragment = new MaturityRatingFragment();
                FragmentTransaction transaction = getFragmentManager()
                        .beginTransaction();
                transaction.replace(R.id.contentintab, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        reset = (Button) rootView.findViewById(R.id.reset_button);
        reset.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                global.isPhyCalculated = false;
                global.isNeuroCalculated = false;

                global.isPosture = false;
                global.isSquare = false;
                global.isarm = false;
                global.isPop = false;
                global.isScarf = false;
                global.isHeel = false;
                global.isSkin = false;
                global.isEye = false;
                global.ismale_female = false;
                global.isBreast = false;
                global.isPlant = false;
                global.isLanugo = false;

                score_tv.setText("-");
                week_tv.setText("-");
                reset.setVisibility(View.GONE);
            }
        });

        return rootView;
    }

    private int CalculateWeeks(int sc) {
        int weeks = 0;
        if (sc == -10 || sc == -9) {
            weeks = 20;
            // if (sc==-10 || sc==-9)
        } else if (sc == -8 || sc == -7 || sc == -6) {
            weeks = 21;
            // weekLbl.text=@"21";
        } else if (sc == -5 || sc == -4) {
            weeks = 22;
            // weekLbl.text=@"22";
        } else if (sc == -3 || sc == -2 || sc == -1) {
            weeks = 23;
            // weekLbl.text=@"23";
        } else if (sc == 0 || sc == 1) {
            weeks = 24;
            // weekLbl.text=@"24";
        } else if (sc == 2 || sc == 3 || sc == 4) {
            weeks = 25;
            // weekLbl.text=@"25";
        } else if (sc == 5 || sc == 6) {
            weeks = 26;
            // weekLbl.text=@"26";
        } else if (sc == 7 || sc == 8 || sc == 9) {
            weeks = 27;
            // weekLbl.text=@"27";
        } else if (sc == 11 || sc == 10) {
            weeks = 28;
            // weekLbl.text=@"28";
        } else if (sc == 12 || sc == 13 || sc == 14) {
            weeks = 29;
            // weekLbl.text=@"29";
        } else if (sc == 15 || sc == 16) {
            weeks = 30;
            // weekLbl.text=@"30";
        } else if (sc == 17 || sc == 18 || sc == 19) {
            weeks = 31;
            // weekLbl.text=@"31";
        } else if (sc == 20 || sc == 21) {
            weeks = 32;
            // weekLbl.text=@"32";
        } else if (sc == 22 || sc == 23 || sc == 24) {
            weeks = 33;
            // weekLbl.text=@"33";
        } else if (sc == 25 || sc == 26) {
            weeks = 34;
            // weekLbl.text=@"34";
        } else if (sc == 27 || sc == 28 || sc == 29) {
            weeks = 35;
            // weekLbl.text=@"35";
        } else if (sc == 30 || sc == 31) {
            weeks = 36;
            // weekLbl.text=@"36";
        } else if (sc == 32 || sc == 33 || sc == 34) {
            weeks = 37;
            // weekLbl.text=@"37";
        } else if (sc == 35 || sc == 36) {
            weeks = 38;
            // weekLbl.text=@"38";
        } else if (sc == 37 || sc == 38 || sc == 39) {
            weeks = 39;
            // weekLbl.text=@"39";
        } else if (sc == 40 || sc == 41) {
            weeks = 40;
            // weekLbl.text=@"40";
        } else if (sc == 42 || sc == 43 || sc == 44) {
            weeks = 41;
            // weekLbl.text=@"41";
        } else if (sc == 45 || sc == 46) {
            weeks = 42;
            // weekLbl.text=@"42";
        } else if (sc == 47 || sc == 48 || sc == 49) {
            weeks = 43;
            // weekLbl.text=@"43";
        } else if (sc == 50) {
            weeks = 44;
            // weekLbl.text=@"4";
        }

        return weeks;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (global.isPhyCalculated && global.isNeuroCalculated) {
            global.totalScore = global.phyScore + global.neuroScore;
            int weeks = CalculateWeeks(global.totalScore);

            score_tv.setText("" + global.totalScore);
            week_tv.setText("" + weeks);
            reset.setVisibility(View.VISIBLE);
        }

    }
}