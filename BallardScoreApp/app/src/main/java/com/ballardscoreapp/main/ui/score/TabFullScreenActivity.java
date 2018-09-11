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
import com.ballardscoreapp.main.ui.MainActivity;
import com.ballardscoreapp.main.util.Constants;
import com.ballardscoreapp.main.util.Globals;

public class TabFullScreenActivity extends Activity implements OnClickListener {
    WebView webView;
    SharedPreferences sp;
    Globals globals;
    TextView skin1_tv, skin2_tv, skin3_tv, skin4_tv, skin5_tv, skin6_tv, skin7_tv;
    TextView lanugo1_tv, lanugo2_tv, lanugo3_tv, lanugo4_tv, lanugo5_tv, lanugo6_tv;
    TextView plantar1_tv, plantar2_tv, plantar3_tv, plantar4_tv, plantar5_tv, plantar6_tv;
    TextView breast1_tv, breast2_tv, breast3_tv, breast4_tv, breast5_tv, breast6_tv;
    TextView eye1_tv, eye2_tv, eye3_tv, eye4_tv, eye5_tv, eye6_tv;
    TextView male1_tv, male2_tv, male3_tv, male4_tv, male5_tv, male6_tv;
    TextView female1_tv, female2_tv, female3_tv, female4_tv, female5_tv, female6_tv, score_tv;

    ImageView neuro_img, calculate_img;
    boolean checkfullscreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.physicalmaturitysheet);
        sp = getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);
        globals = (Globals) getApplicationContext();
        checkfullscreen = sp.getBoolean(Constants.FULLLSCREENCHECK, false);
        score_tv = (TextView) findViewById(R.id.calculated_score);

        skin1_tv = (TextView) findViewById(R.id.score1);
        skin2_tv = (TextView) findViewById(R.id.score2);
        skin3_tv = (TextView) findViewById(R.id.score3);
        skin4_tv = (TextView) findViewById(R.id.score4);
        skin5_tv = (TextView) findViewById(R.id.score5);
        skin6_tv = (TextView) findViewById(R.id.score6);
        skin7_tv = (TextView) findViewById(R.id.score7);

        lanugo1_tv = (TextView) findViewById(R.id.lanugo1);
        lanugo2_tv = (TextView) findViewById(R.id.lanugo2);
        lanugo3_tv = (TextView) findViewById(R.id.lanugo3);
        lanugo4_tv = (TextView) findViewById(R.id.lanugo4);
        lanugo5_tv = (TextView) findViewById(R.id.lanugo5);
        lanugo6_tv = (TextView) findViewById(R.id.lanugo6);

        plantar1_tv = (TextView) findViewById(R.id.plant1);
        plantar2_tv = (TextView) findViewById(R.id.plant2);
        plantar3_tv = (TextView) findViewById(R.id.plant3);
        plantar4_tv = (TextView) findViewById(R.id.plant4);
        plantar5_tv = (TextView) findViewById(R.id.plant5);
        plantar6_tv = (TextView) findViewById(R.id.plant6);

        breast1_tv = (TextView) findViewById(R.id.breast1);
        breast2_tv = (TextView) findViewById(R.id.breast2);
        breast3_tv = (TextView) findViewById(R.id.breast3);
        breast4_tv = (TextView) findViewById(R.id.breast4);
        breast5_tv = (TextView) findViewById(R.id.breast5);
        breast6_tv = (TextView) findViewById(R.id.breast6);

        eye1_tv = (TextView) findViewById(R.id.eye1);
        eye2_tv = (TextView) findViewById(R.id.eye2);
        eye3_tv = (TextView) findViewById(R.id.eye3);
        eye4_tv = (TextView) findViewById(R.id.eye4);
        eye5_tv = (TextView) findViewById(R.id.eye5);
        eye6_tv = (TextView) findViewById(R.id.eye6);

        male1_tv = (TextView) findViewById(R.id.male1);
        male2_tv = (TextView) findViewById(R.id.male2);
        male3_tv = (TextView) findViewById(R.id.male3);
        male4_tv = (TextView) findViewById(R.id.male4);
        male5_tv = (TextView) findViewById(R.id.male5);
        male6_tv = (TextView) findViewById(R.id.male6);

        female1_tv = (TextView) findViewById(R.id.female1);
        female2_tv = (TextView) findViewById(R.id.female2);
        female3_tv = (TextView) findViewById(R.id.female3);
        female4_tv = (TextView) findViewById(R.id.female4);
        female5_tv = (TextView) findViewById(R.id.female5);
        female6_tv = (TextView) findViewById(R.id.female6);

        neuro_img = (ImageView) findViewById(R.id.neuro_img);
        calculate_img = (ImageView) findViewById(R.id.calculatescore);

        skin1_tv.setOnClickListener(this);
        skin2_tv.setOnClickListener(this);
        skin3_tv.setOnClickListener(this);
        skin4_tv.setOnClickListener(this);
        skin5_tv.setOnClickListener(this);
        skin6_tv.setOnClickListener(this);
        skin7_tv.setOnClickListener(this);
        plantar1_tv.setOnClickListener(this);
        plantar2_tv.setOnClickListener(this);
        plantar3_tv.setOnClickListener(this);
        plantar4_tv.setOnClickListener(this);
        plantar5_tv.setOnClickListener(this);
        plantar6_tv.setOnClickListener(this);
        lanugo1_tv.setOnClickListener(this);
        lanugo2_tv.setOnClickListener(this);
        lanugo3_tv.setOnClickListener(this);
        lanugo4_tv.setOnClickListener(this);
        lanugo5_tv.setOnClickListener(this);
        lanugo6_tv.setOnClickListener(this);
        eye1_tv.setOnClickListener(this);
        eye2_tv.setOnClickListener(this);
        eye3_tv.setOnClickListener(this);
        eye4_tv.setOnClickListener(this);
        eye5_tv.setOnClickListener(this);
        eye6_tv.setOnClickListener(this);
        breast1_tv.setOnClickListener(this);
        breast2_tv.setOnClickListener(this);
        breast3_tv.setOnClickListener(this);
        breast4_tv.setOnClickListener(this);
        breast5_tv.setOnClickListener(this);
        breast6_tv.setOnClickListener(this);
        male1_tv.setOnClickListener(this);
        male2_tv.setOnClickListener(this);
        male3_tv.setOnClickListener(this);
        male4_tv.setOnClickListener(this);
        male5_tv.setOnClickListener(this);
        male6_tv.setOnClickListener(this);
        female1_tv.setOnClickListener(this);
        female2_tv.setOnClickListener(this);
        female3_tv.setOnClickListener(this);
        female4_tv.setOnClickListener(this);
        female5_tv.setOnClickListener(this);
        female6_tv.setOnClickListener(this);

        neuro_img.setOnClickListener(this);
        calculate_img.setOnClickListener(this);

        TextView header = (TextView) findViewById(R.id.header_tv);
        if (checkfullscreen) {
            header.setVisibility(View.GONE);
        } else {
            header.setVisibility(View.VISIBLE);
        }

        sp = getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);

    }


    public void openAlert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                TabFullScreenActivity.this);
        alertDialogBuilder.setTitle("Alert !");
        alertDialogBuilder
                .setMessage("Please select all values from both Tables...")
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
        //Toast.makeText(FullScreenActivity.this, ""+arg0.getId(), Toast.LENGTH_SHORT).show();
        switch (v.getId()) {
            case R.id.score1:
                scoreSelection(-1);
                break;
            case R.id.score2:
                scoreSelection(0);
                break;

            case R.id.score3:
                scoreSelection(1);
                break;
            case R.id.score4:
                scoreSelection(2);
                break;
            case R.id.score5:
                scoreSelection(3);
                break;
            case R.id.score6:
                scoreSelection(4);
                break;
            case R.id.score7:
                scoreSelection(5);
                break;


            case R.id.lanugo1:
                lanugoSelection(-1);
                break;
            case R.id.lanugo2:
                lanugoSelection(0);
                break;

            case R.id.lanugo3:
                lanugoSelection(1);
                break;
            case R.id.lanugo4:
                lanugoSelection(2);
                break;
            case R.id.lanugo5:
                lanugoSelection(3);
                break;
            case R.id.lanugo6:
                lanugoSelection(4);
                break;

            case R.id.breast1:
                breastSelection(-1);
                break;
            case R.id.breast2:
                breastSelection(0);
                break;

            case R.id.breast3:
                breastSelection(1);
                break;
            case R.id.breast4:
                breastSelection(2);
                break;
            case R.id.breast5:
                breastSelection(3);
                break;
            case R.id.breast6:
                breastSelection(4);
                break;


            case R.id.plant1:
                plantSelection(-1);
                break;
            case R.id.plant2:
                plantSelection(0);
                break;

            case R.id.plant3:
                plantSelection(1);
                break;
            case R.id.plant4:
                plantSelection(2);
                break;
            case R.id.plant5:
                plantSelection(3);
                break;
            case R.id.plant6:
                plantSelection(4);
                break;

            case R.id.eye1:
                eyeSelection(-1);
                break;
            case R.id.eye2:
                eyeSelection(0);
                break;

            case R.id.eye3:
                eyeSelection(1);
                break;
            case R.id.eye4:
                eyeSelection(2);
                break;
            case R.id.eye5:
                eyeSelection(3);
                break;
            case R.id.eye6:
                eyeSelection(4);
                break;


            case R.id.male1:
                maleSelection(-1);
                break;
            case R.id.male2:
                maleSelection(0);
                break;

            case R.id.male3:
                maleSelection(1);
                break;
            case R.id.male4:
                maleSelection(2);
                break;
            case R.id.male5:
                maleSelection(3);
                break;
            case R.id.male6:
                maleSelection(4);
                break;


            case R.id.female1:
                femaleSelection(-1);
                break;
            case R.id.female2:
                femaleSelection(0);
                break;

            case R.id.female3:
                femaleSelection(1);
                break;
            case R.id.female4:
                femaleSelection(2);
                break;
            case R.id.female5:
                femaleSelection(3);
                break;
            case R.id.female6:
                femaleSelection(4);
                break;
            case R.id.neuro_img:
                TabFullScreenActivity.this.finish();
                startActivity(new Intent(TabFullScreenActivity.this, TabCalculateMaturityRatingFragment.class));

                break;
            case R.id.calculatescore:
                if (globals.isPhyCalculated && globals.isNeuroCalculated) {
                    globals.totalScore = globals.phyScore + globals.neuroScore;
                    startActivity(new Intent(TabFullScreenActivity.this, MainActivity.class));
                    TabFullScreenActivity.this.finish();
                } else {
                    openAlert();
                }
                break;
        }

    }

    private void scoreSelection(int i) {
        skin1_tv.setBackgroundResource(R.drawable.light_gray_back);
        skin2_tv.setBackgroundResource(R.drawable.light_gray_back);
        skin3_tv.setBackgroundResource(R.drawable.light_gray_back);
        skin4_tv.setBackgroundResource(R.drawable.light_gray_back);
        skin5_tv.setBackgroundResource(R.drawable.light_gray_back);
        skin6_tv.setBackgroundResource(R.drawable.light_gray_back);
        skin7_tv.setBackgroundResource(R.drawable.light_gray_back);


        if (i == -1) {
            skin1_tv.setBackgroundResource(R.drawable.cell_grey);

        }
        if (i == 0) {
            skin2_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 1) {
            skin3_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 2) {
            skin4_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 3) {
            skin5_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 4) {
            skin6_tv.setBackgroundResource(R.drawable.cell_grey);
        }

        if (i == 5) {
            skin7_tv.setBackgroundResource(R.drawable.cell_grey);
        }

        globals.skinPos = i;
        globals.isSkin = true;
        checkAndCalculateValue();
    }

    private void lanugoSelection(int i) {
        lanugo1_tv.setBackgroundResource(R.drawable.light_gray_back);
        lanugo2_tv.setBackgroundResource(R.drawable.light_gray_back);
        lanugo3_tv.setBackgroundResource(R.drawable.light_gray_back);
        lanugo4_tv.setBackgroundResource(R.drawable.light_gray_back);
        lanugo5_tv.setBackgroundResource(R.drawable.light_gray_back);
        lanugo6_tv.setBackgroundResource(R.drawable.light_gray_back);

        if (i == -1) {
            lanugo1_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 0) {
            lanugo2_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 1) {
            lanugo3_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 2) {
            lanugo4_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 3) {
            lanugo5_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 4) {
            lanugo6_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        globals.lanugoPos = i;
        globals.isLanugo = true;
        checkAndCalculateValue();
    }

    private void breastSelection(int i) {
        breast1_tv.setBackgroundResource(R.drawable.light_gray_back);
        breast2_tv.setBackgroundResource(R.drawable.light_gray_back);
        breast3_tv.setBackgroundResource(R.drawable.light_gray_back);
        breast4_tv.setBackgroundResource(R.drawable.light_gray_back);
        breast5_tv.setBackgroundResource(R.drawable.light_gray_back);
        breast6_tv.setBackgroundResource(R.drawable.light_gray_back);

        if (i == -1) {
            breast1_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 0) {
            breast2_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 1) {
            breast3_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 2) {
            breast4_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 3) {
            breast5_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 4) {
            breast6_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        globals.breastPos = i;
        globals.isBreast = true;
        checkAndCalculateValue();
    }


    private void plantSelection(int i) {
        plantar1_tv.setBackgroundResource(R.drawable.light_gray_back);
        plantar2_tv.setBackgroundResource(R.drawable.light_gray_back);
        plantar3_tv.setBackgroundResource(R.drawable.light_gray_back);
        plantar4_tv.setBackgroundResource(R.drawable.light_gray_back);
        plantar5_tv.setBackgroundResource(R.drawable.light_gray_back);
        plantar6_tv.setBackgroundResource(R.drawable.light_gray_back);

        if (i == -1) {
            plantar1_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 0) {
            plantar2_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 1) {
            plantar3_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 2) {
            plantar4_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 3) {
            plantar5_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 4) {
            plantar6_tv.setBackgroundResource(R.drawable.cell_grey);
        }

        globals.plantPos = i;
        globals.isPlant = true;

        checkAndCalculateValue();
    }


    private void eyeSelection(int i) {
        eye1_tv.setBackgroundResource(R.drawable.light_gray_back);
        eye2_tv.setBackgroundResource(R.drawable.light_gray_back);
        eye3_tv.setBackgroundResource(R.drawable.light_gray_back);
        eye4_tv.setBackgroundResource(R.drawable.light_gray_back);
        eye5_tv.setBackgroundResource(R.drawable.light_gray_back);
        eye6_tv.setBackgroundResource(R.drawable.light_gray_back);

        if (i == -1) {
            eye1_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 0) {
            eye2_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 1) {
            eye3_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 2) {
            eye4_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 3) {
            eye5_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 4) {
            eye6_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        globals.eyePos = i;
        globals.isEye = true;
        checkAndCalculateValue();
    }

    private void maleSelection(int i) {
        male1_tv.setBackgroundResource(R.drawable.light_gray_back);
        male2_tv.setBackgroundResource(R.drawable.light_gray_back);
        male3_tv.setBackgroundResource(R.drawable.light_gray_back);
        male4_tv.setBackgroundResource(R.drawable.light_gray_back);
        male5_tv.setBackgroundResource(R.drawable.light_gray_back);
        male6_tv.setBackgroundResource(R.drawable.light_gray_back);
        female1_tv.setBackgroundResource(R.drawable.light_gray_back);
        female2_tv.setBackgroundResource(R.drawable.light_gray_back);
        female3_tv.setBackgroundResource(R.drawable.light_gray_back);
        female4_tv.setBackgroundResource(R.drawable.light_gray_back);
        female5_tv.setBackgroundResource(R.drawable.light_gray_back);
        female6_tv.setBackgroundResource(R.drawable.light_gray_back);

        if (i == -1) {
            male1_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 0) {
            male2_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 1) {
            male3_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 2) {
            male4_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 3) {
            male5_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 4) {
            male6_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        globals.male_femalePos = i;
        globals.ismale_female = true;
        checkAndCalculateValue();
    }

    private void femaleSelection(int i) {
        female1_tv.setBackgroundResource(R.drawable.light_gray_back);
        female2_tv.setBackgroundResource(R.drawable.light_gray_back);
        female3_tv.setBackgroundResource(R.drawable.light_gray_back);
        female4_tv.setBackgroundResource(R.drawable.light_gray_back);
        female5_tv.setBackgroundResource(R.drawable.light_gray_back);
        female6_tv.setBackgroundResource(R.drawable.light_gray_back);
        male1_tv.setBackgroundResource(R.drawable.light_gray_back);
        male2_tv.setBackgroundResource(R.drawable.light_gray_back);
        male3_tv.setBackgroundResource(R.drawable.light_gray_back);
        male4_tv.setBackgroundResource(R.drawable.light_gray_back);
        male5_tv.setBackgroundResource(R.drawable.light_gray_back);
        male6_tv.setBackgroundResource(R.drawable.light_gray_back);
        if (i == -1) {
            female1_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 0) {
            female2_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 1) {
            female3_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 2) {
            female4_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 3) {
            female5_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 4) {
            female6_tv.setBackgroundResource(R.drawable.cell_grey);
        }
        globals.male_femalePos = i;
        globals.ismale_female = true;
        checkAndCalculateValue();
    }


    private void checkAndCalculateValue() {
        if (globals.isSkin && globals.isBreast && globals.isPlant && globals.isLanugo && globals.ismale_female && globals.isEye) {
            int sum = globals.skinPos + globals.lanugoPos + globals.breastPos + globals.eyePos + globals.plantPos + globals.male_femalePos;

            globals.phyScore = sum;
            globals.isPhyCalculated = true;
            score_tv.setText("" + sum);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (globals.isSkin) {
            scoreSelection(globals.skinPos);
        }
        if (globals.isLanugo) {
            lanugoSelection(globals.lanugoPos);
        }
        if (globals.isPlant) {
            plantSelection(globals.plantPos);
        }
        if (globals.isBreast) {
            breastSelection(globals.breastPos);
        }
        if (globals.isEye) {
            eyeSelection(globals.eyePos);
        }
        if (globals.ismale_female) {
            maleSelection(globals.male_femalePos);
        }
        if (globals.ismale_female) {
            femaleSelection(globals.male_femalePos);
        }
    }
}
