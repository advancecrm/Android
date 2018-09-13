package com.ballardscore.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        //ballardscore banner app id
        MobileAds.initialize(this, "ca-app-pub-7124699817614464~9433246821");
        //experimental/ sample Id:
        //MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

    }

    /** Called when the user taps the Send button **/
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
