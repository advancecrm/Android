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
import com.ballardscoreapp.main.ui.MainActivity;
import com.ballardscoreapp.main.util.Constants;
import com.ballardscoreapp.main.util.Globals;

public class TabCalculateMaturityRatingFragment extends Activity implements
        OnClickListener {

    static Activity act_obj;
    Globals globals;
    SharedPreferences sp;
    ImageView posture1, posture2, posture3, posture4, posture5;
    ImageView square1, square2, square3, square4, square5, square6;
    ImageView arm1, arm2, arm3, arm4, arm5;
    ImageView pop1, pop2, pop3, pop4, pop5, pop6, pop7;
    ImageView scarf1, scarf2, scarf3, scarf4, scarf5, scarf6;
    ImageView heel1, heel2, heel3, heel4, heel5, heel6;
    ImageView fullscreen, physical_btn, calculate_btn;
    TextView neuroScoreText;
    TextView header;
    Editor editor;
    boolean checkfullscreen = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.neurotablemain);

        sp = getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);
        globals = (Globals) getApplicationContext();
        act_obj = TabCalculateMaturityRatingFragment.this;

        posture1 = (ImageView) findViewById(R.id.posture1);
        posture2 = (ImageView) findViewById(R.id.posture2);
        posture3 = (ImageView) findViewById(R.id.posture3);
        posture4 = (ImageView) findViewById(R.id.posture4);
        posture5 = (ImageView) findViewById(R.id.posture5);

        square1 = (ImageView) findViewById(R.id.square1);
        square2 = (ImageView) findViewById(R.id.square2);
        square3 = (ImageView) findViewById(R.id.square3);
        square4 = (ImageView) findViewById(R.id.square4);
        square5 = (ImageView) findViewById(R.id.square5);
        square6 = (ImageView) findViewById(R.id.square6);

        arm1 = (ImageView) findViewById(R.id.arm1);
        arm2 = (ImageView) findViewById(R.id.arm2);
        arm3 = (ImageView) findViewById(R.id.arm3);
        arm4 = (ImageView) findViewById(R.id.arm4);
        arm5 = (ImageView) findViewById(R.id.arm5);

        pop1 = (ImageView) findViewById(R.id.pop1);
        pop2 = (ImageView) findViewById(R.id.pop2);
        pop3 = (ImageView) findViewById(R.id.pop3);
        pop4 = (ImageView) findViewById(R.id.pop4);
        pop5 = (ImageView) findViewById(R.id.pop5);
        pop6 = (ImageView) findViewById(R.id.pop6);
        pop7 = (ImageView) findViewById(R.id.pop7);

        scarf1 = (ImageView) findViewById(R.id.scarf1);
        scarf2 = (ImageView) findViewById(R.id.scarf2);
        scarf3 = (ImageView) findViewById(R.id.scarf3);
        scarf4 = (ImageView) findViewById(R.id.scarf4);
        scarf5 = (ImageView) findViewById(R.id.scarf5);
        scarf6 = (ImageView) findViewById(R.id.scarf6);

        heel1 = (ImageView) findViewById(R.id.heel1);
        heel2 = (ImageView) findViewById(R.id.heel2);
        heel3 = (ImageView) findViewById(R.id.heel3);
        heel4 = (ImageView) findViewById(R.id.heel4);
        heel5 = (ImageView) findViewById(R.id.heel5);
        heel6 = (ImageView) findViewById(R.id.heel6);

        fullscreen = (ImageView) findViewById(R.id.full_screen_btn);
        physical_btn = (ImageView) findViewById(R.id.physical_mature_btn);
        calculate_btn = (ImageView) findViewById(R.id.calc_maturity_rating_btn);

        neuroScoreText = (TextView) findViewById(R.id.neuro_score_txt);

        posture1.setOnClickListener(this);
        posture2.setOnClickListener(this);
        posture3.setOnClickListener(this);
        posture4.setOnClickListener(this);
        posture5.setOnClickListener(this);

        square1.setOnClickListener(this);
        square2.setOnClickListener(this);
        square3.setOnClickListener(this);
        square4.setOnClickListener(this);
        square5.setOnClickListener(this);
        square6.setOnClickListener(this);

        arm1.setOnClickListener(this);
        arm2.setOnClickListener(this);
        arm3.setOnClickListener(this);
        arm4.setOnClickListener(this);
        arm5.setOnClickListener(this);

        pop1.setOnClickListener(this);
        pop2.setOnClickListener(this);
        pop3.setOnClickListener(this);
        pop4.setOnClickListener(this);
        pop5.setOnClickListener(this);
        pop6.setOnClickListener(this);
        pop7.setOnClickListener(this);

        scarf1.setOnClickListener(this);
        scarf2.setOnClickListener(this);
        scarf3.setOnClickListener(this);
        scarf4.setOnClickListener(this);
        scarf5.setOnClickListener(this);
        scarf6.setOnClickListener(this);

        heel1.setOnClickListener(this);
        heel2.setOnClickListener(this);
        heel3.setOnClickListener(this);
        heel4.setOnClickListener(this);
        heel5.setOnClickListener(this);
        heel6.setOnClickListener(this);

        fullscreen.setOnClickListener(this);
        physical_btn.setOnClickListener(this);
        calculate_btn.setOnClickListener(this);

        header = (TextView) findViewById(R.id.header_tv);

    }

    public void openAlert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                TabCalculateMaturityRatingFragment.this);
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
    public void onResume() {
        super.onResume();
        if (globals.isPosture) {
            postureSelection(globals.posturePos);
        }
        if (globals.isSquare) {
            squareSelction(globals.squarePos);
        }
        if (globals.isarm) {
            armSelection(globals.armPos);
        }
        if (globals.isScarf) {
            scarfSelection(globals.scarfPos);
        }
        if (globals.isHeel) {
            heelSelection(globals.heelPos);
        }
        if (globals.isPop) {
            popSlection(globals.popPos);
        }
        checkfullscreen = sp.getBoolean(Constants.FULLLSCREENCHECK, false);

        if (checkfullscreen) {
            fullscreen.setImageResource(R.drawable.close);
            header.setVisibility(View.GONE);
        } else {
            fullscreen.setImageResource(R.drawable.fullscreen);
            header.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.posture1:
                postureSelection(0);
                break;
            case R.id.posture2:
                postureSelection(1);
                break;

            case R.id.posture3:
                postureSelection(2);
                break;

            case R.id.posture4:
                postureSelection(3);
                break;

            case R.id.posture5:
                postureSelection(4);
                break;

            case R.id.square1:
                squareSelction(-1);
                break;

            case R.id.square2:
                squareSelction(0);
                break;
            case R.id.square3:
                squareSelction(1);
                break;
            case R.id.square4:
                squareSelction(2);
                break;

            case R.id.square5:
                squareSelction(3);
                break;

            case R.id.square6:
                squareSelction(4);
                break;

            case R.id.arm1:
                armSelection(0);
                break;

            case R.id.arm2:
                armSelection(1);
                break;

            case R.id.arm3:
                armSelection(2);
                break;
            case R.id.arm4:
                armSelection(3);
                break;
            case R.id.arm5:
                armSelection(4);
                break;

            case R.id.pop1:
                popSlection(-1);
                break;

            case R.id.pop2:
                popSlection(0);
                break;

            case R.id.pop3:
                popSlection(1);
                break;

            case R.id.pop4:
                popSlection(2);
                break;

            case R.id.pop5:
                popSlection(3);
                break;
            case R.id.pop6:
                popSlection(4);
                break;
            case R.id.pop7:
                popSlection(5);
                break;

            case R.id.scarf1:
                scarfSelection(-1);
                break;

            case R.id.scarf2:
                scarfSelection(0);
                break;

            case R.id.scarf3:
                scarfSelection(1);
                break;

            case R.id.scarf4:
                scarfSelection(2);
                break;

            case R.id.scarf5:
                scarfSelection(3);
                break;
            case R.id.scarf6:
                scarfSelection(4);
                break;
            case R.id.heel1:
                heelSelection(-1);
                break;

            case R.id.heel2:
                heelSelection(0);
                break;

            case R.id.heel3:
                heelSelection(1);
                break;

            case R.id.heel4:
                heelSelection(2);
                break;

            case R.id.heel5:
                heelSelection(3);
                break;

            case R.id.heel6:
                heelSelection(4);
                break;

            case R.id.full_screen_btn:

                checkfullscreen = sp.getBoolean(Constants.FULLLSCREENCHECK, false);
                Log.e("before click ", "" + globals.fullscreen);
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
                TabCalculateMaturityRatingFragment.this.finish();
                startActivity(new Intent(TabCalculateMaturityRatingFragment.this, TabFullScreenActivity.class));
                break;

            case R.id.calc_maturity_rating_btn:

                if (globals.isPhyCalculated && globals.isNeuroCalculated) {
                    globals.totalScore = globals.phyScore + globals.neuroScore;
                    TabCalculateMaturityRatingFragment.this.finish();
                    startActivity(new Intent(TabCalculateMaturityRatingFragment.this, MainActivity.class));
                } else {
                    openAlert();
                }

                break;

        }

    }

    private void postureSelection(int i) {
        posture1.setBackgroundResource(R.drawable.light_gray_back);
        posture2.setBackgroundResource(R.drawable.light_gray_back);
        posture3.setBackgroundResource(R.drawable.light_gray_back);
        posture4.setBackgroundResource(R.drawable.light_gray_back);
        posture5.setBackgroundResource(R.drawable.light_gray_back);

        if (i == 0) {
            posture1.setBackgroundResource(R.drawable.cell_grey);

        }
        if (i == 1) {
            posture2.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 2) {
            posture3.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 3) {
            posture4.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 4) {
            posture5.setBackgroundResource(R.drawable.cell_grey);
        }

        globals.posturePos = i;
        globals.isPosture = true;
        checkAndCalculateValue();

    }

    private void squareSelction(int i) {
        square1.setBackgroundResource(R.drawable.light_gray_back);
        square2.setBackgroundResource(R.drawable.light_gray_back);
        square3.setBackgroundResource(R.drawable.light_gray_back);
        square4.setBackgroundResource(R.drawable.light_gray_back);
        square5.setBackgroundResource(R.drawable.light_gray_back);
        square6.setBackgroundResource(R.drawable.light_gray_back);

        if (i == -1) {
            square1.setBackgroundResource(R.drawable.cell_grey);

        }
        if (i == 0) {
            square2.setBackgroundResource(R.drawable.cell_grey);

        }
        if (i == 1) {
            square3.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 2) {
            square4.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 3) {
            square5.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 4) {
            square6.setBackgroundResource(R.drawable.cell_grey);
        }
        globals.squarePos = i;
        globals.isSquare = true;
        checkAndCalculateValue();
    }

    private void armSelection(int i) {
        arm1.setBackgroundResource(R.drawable.light_gray_back);
        arm2.setBackgroundResource(R.drawable.light_gray_back);
        arm3.setBackgroundResource(R.drawable.light_gray_back);
        arm4.setBackgroundResource(R.drawable.light_gray_back);
        arm5.setBackgroundResource(R.drawable.light_gray_back);

        if (i == 0) {
            arm1.setBackgroundResource(R.drawable.cell_grey);

        }
        if (i == 1) {
            arm2.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 2) {
            arm3.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 3) {
            arm4.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 4) {
            arm5.setBackgroundResource(R.drawable.cell_grey);
        }
        globals.armPos = i;
        globals.isarm = true;
        checkAndCalculateValue();
    }

    private void popSlection(int i) {
        pop1.setBackgroundResource(R.drawable.light_gray_back);
        pop2.setBackgroundResource(R.drawable.light_gray_back);
        pop3.setBackgroundResource(R.drawable.light_gray_back);
        pop4.setBackgroundResource(R.drawable.light_gray_back);
        pop5.setBackgroundResource(R.drawable.light_gray_back);
        pop6.setBackgroundResource(R.drawable.light_gray_back);
        pop7.setBackgroundResource(R.drawable.light_gray_back);

        if (i == -1) {
            pop1.setBackgroundResource(R.drawable.cell_grey);

        }
        if (i == 0) {
            pop2.setBackgroundResource(R.drawable.cell_grey);

        }
        if (i == 1) {
            pop3.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 2) {
            pop4.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 3) {
            pop5.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 4) {
            pop6.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 5) {
            pop7.setBackgroundResource(R.drawable.cell_grey);
        }
        globals.popPos = i;
        globals.isPop = true;
        checkAndCalculateValue();
    }

    private void scarfSelection(int i) {
        scarf1.setBackgroundResource(R.drawable.light_gray_back);
        scarf2.setBackgroundResource(R.drawable.light_gray_back);
        scarf3.setBackgroundResource(R.drawable.light_gray_back);
        scarf4.setBackgroundResource(R.drawable.light_gray_back);
        scarf5.setBackgroundResource(R.drawable.light_gray_back);
        scarf6.setBackgroundResource(R.drawable.light_gray_back);

        if (i == -1) {
            scarf1.setBackgroundResource(R.drawable.cell_grey);

        }
        if (i == 0) {
            scarf2.setBackgroundResource(R.drawable.cell_grey);

        }
        if (i == 1) {
            scarf3.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 2) {
            scarf4.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 3) {
            scarf5.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 4) {
            scarf6.setBackgroundResource(R.drawable.cell_grey);
        }
        globals.scarfPos = i;
        globals.isScarf = true;
        checkAndCalculateValue();
    }

    private void heelSelection(int i) {
        heel1.setBackgroundResource(R.drawable.light_gray_back);
        heel2.setBackgroundResource(R.drawable.light_gray_back);
        heel3.setBackgroundResource(R.drawable.light_gray_back);
        heel4.setBackgroundResource(R.drawable.light_gray_back);
        heel5.setBackgroundResource(R.drawable.light_gray_back);
        heel6.setBackgroundResource(R.drawable.light_gray_back);

        if (i == -1) {
            heel1.setBackgroundResource(R.drawable.cell_grey);

        }
        if (i == 0) {
            heel2.setBackgroundResource(R.drawable.cell_grey);

        }
        if (i == 1) {
            heel3.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 2) {
            heel4.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 3) {
            heel5.setBackgroundResource(R.drawable.cell_grey);
        }
        if (i == 4) {
            heel6.setBackgroundResource(R.drawable.cell_grey);
        }

        globals.heelPos = i;
        globals.isHeel = true;
        checkAndCalculateValue();
    }

    private void checkAndCalculateValue() {


        if (globals.isPosture && globals.isSquare && globals.isarm && globals.isPop && globals.isScarf && globals.isHeel) {
            int sum = globals.posturePos + globals.squarePos + globals.armPos + globals.popPos + globals.scarfPos + globals.heelPos;
            globals.neuroScore = sum;
            globals.isNeuroCalculated = true;
            neuroScoreText.setText("" + sum);
        }

    }
}