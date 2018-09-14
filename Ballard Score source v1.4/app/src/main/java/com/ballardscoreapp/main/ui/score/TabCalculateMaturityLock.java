package com.ballardscoreapp.main.ui.score;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.ballardscoreapp.main.R;
import com.ballardscoreapp.main.util.Constants;

public class TabCalculateMaturityLock extends Activity implements OnClickListener {

    ImageView fullscreen, physical_btn, calculate_btn;
    TextView header;
    Editor editor;
    boolean checkfullscreen = false;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.neurotablemain);
        sp = getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);

        fullscreen = (ImageView) findViewById(R.id.full_screen_btn);
        physical_btn = (ImageView) findViewById(R.id.physical_mature_btn);
        calculate_btn = (ImageView) findViewById(R.id.calc_maturity_rating_btn);

        fullscreen.setOnClickListener(this);
        physical_btn.setOnClickListener(this);
        calculate_btn.setOnClickListener(this);

        header = (TextView) findViewById(R.id.header_tv);


    }

    public void openAlert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                TabCalculateMaturityLock.this);
        alertDialogBuilder.setTitle("Alert !");
        alertDialogBuilder
                .setMessage("Please Unlock App..")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.full_screen_btn:
                checkfullscreen = sp.getBoolean(Constants.FULLLSCREENCHECK, false);
                if (checkfullscreen) {
                    Log.e("Inside full_screen_btn", "ifff");
                    fullscreen.setImageResource(R.drawable.fullscreen);
                    editor = sp.edit();
                    editor.putBoolean(Constants.FULLLSCREENCHECK, false);
                    editor.commit();
                    header.setVisibility(View.VISIBLE);
                } else {
                    Log.e("Inside full_screen_btn", "else");
                    fullscreen.setImageResource(R.drawable.close);
                    editor = sp.edit();
                    editor.putBoolean(Constants.FULLLSCREENCHECK, true);
                    editor.commit();
                    header.setVisibility(View.GONE);
                }
                break;

            case R.id.physical_mature_btn:

                TabCalculateMaturityLock.this.finish();
                startActivity(new Intent(TabCalculateMaturityLock.this, TabFullScreenLock.class));

                break;

            case R.id.calc_maturity_rating_btn:
                openAlert();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkfullscreen = sp.getBoolean(Constants.FULLLSCREENCHECK, false);
        if (checkfullscreen) {
            Log.v("resme", "ifff");
            fullscreen.setImageResource(R.drawable.close);
            header.setVisibility(View.GONE);
        } else {
            Log.v("resme", "else");
            fullscreen.setImageResource(R.drawable.fullscreen);
            header.setVisibility(View.VISIBLE);
        }
    }

}
