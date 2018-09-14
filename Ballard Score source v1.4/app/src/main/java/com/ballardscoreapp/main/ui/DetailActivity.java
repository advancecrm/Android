package com.ballardscoreapp.main.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.ballardscoreapp.main.R;
import com.ballardscoreapp.main.ui.essay.IntroductionFragment;
import com.ballardscoreapp.main.ui.essay.MaturityFragment;
import com.ballardscoreapp.main.ui.essay.NewScoreFragment;
import com.ballardscoreapp.main.ui.essay.SummaryFragment;
import com.ballardscoreapp.main.ui.faq.ContactFragment;
import com.ballardscoreapp.main.ui.faq.FaqMasterFragment;
import com.ballardscoreapp.main.ui.training.TrainingNeuroFragment;
import com.ballardscoreapp.main.ui.training.TrainingPhysicalFragment;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String fragmentName = getIntent().getStringExtra("displayFragment");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(fragmentName);
        }

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction tr = manager.beginTransaction();
        switch (fragmentName) {
            case "IntroductionFragment":
                tr.add(R.id.container, new IntroductionFragment());
                break;
            case "NewScoreFragment":
                tr.add(R.id.container, new NewScoreFragment());
                break;
            case "MaturityFragment":
                tr.add(R.id.container, new MaturityFragment());
                break;
            case "SummaryFragment":
                tr.add(R.id.container, new SummaryFragment());
                break;
            case "TrainingNeuroFragment":
                tr.add(R.id.container, new TrainingNeuroFragment());
                break;
            case "TrainingPhysicalFragment":
                tr.add(R.id.container, new TrainingPhysicalFragment());
                break;
            case "ContactFragment":
                tr.add(R.id.container, new ContactFragment());
                break;
            case "FaqMasterFragment":
                tr.add(R.id.container, new FaqMasterFragment());
                break;
            case "UnlockFragment":
                tr.add(R.id.container, new UnlockFragment());
                break;
        }
        tr.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }

    public boolean isAppUnlocked() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        return prefs.getBoolean("unlocked", false);
    }
}
