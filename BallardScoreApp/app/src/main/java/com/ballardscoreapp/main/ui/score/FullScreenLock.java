package com.ballardscoreapp.main.ui.score;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ballardscoreapp.main.R;
import com.ballardscoreapp.main.util.Constants;
import com.ballardscoreapp.main.util.Globals;

public class FullScreenLock extends Activity implements OnClickListener {
    WebView webView;
    String s = "";
    ImageView neuro_img, calculate_img;
    SharedPreferences sp;
    boolean checkfullscreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.physicalmaturitysheet);

        neuro_img = (ImageView) findViewById(R.id.neuro_img);
        calculate_img = (ImageView) findViewById(R.id.calculatescore);
        sp = getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);
        checkfullscreen = sp.getBoolean(Constants.FULLLSCREENCHECK, false);
        neuro_img.setOnClickListener(this);
        calculate_img.setOnClickListener(this);

        Globals globals = (Globals) getApplicationContext();
        TextView header = (TextView) findViewById(R.id.header_tv);
        if (checkfullscreen) {
            header.setVisibility(View.GONE);
        } else {
            header.setVisibility(View.VISIBLE);
        }


    }

    public void openAlert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                FullScreenLock.this);
        alertDialogBuilder.setTitle("Alert !");
        alertDialogBuilder.setMessage("Please Unlock App..")
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

            case R.id.neuro_img:
                FullScreenLock.this.finish();
                startActivity(new Intent(FullScreenLock.this, CalculateMaturityLock.class));
                break;
            case R.id.calculatescore:
                openAlert();
                break;

        }
    }
}
