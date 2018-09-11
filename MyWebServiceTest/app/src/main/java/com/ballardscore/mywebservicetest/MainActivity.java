package com.ballardscore.mywebservicetest;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static String rslt="";    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Web Service attributes
        Button buttonAdd = findViewById(R.id.buttonAdd);
        final AlertDialog ad=new AlertDialog.Builder(this).create();

        // Add threading
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ed1=(EditText)findViewById(R.id.editTexta);
                EditText ed2=(EditText)findViewById(R.id.editTextb);
                EditText ed3=(EditText) findViewById(R.id.editTextAdd);
                try
                {
                    int a=Integer.parseInt(ed1.getText().toString());
                    int b=Integer.parseInt(ed2.getText().toString());
                    rslt="START";
                    Caller c=new Caller(); c.a=a;
                    c.b=b;
                    //c.ad=ad;
                    c.join(); c.start();
                    while(rslt=="START") {
                        try {
                            Thread.sleep(10);
                        }catch(Exception ex) {
                        }
                    }
                    ad.setTitle("RESULT OF ADD of "+a+" and "+b);
                    ad.setMessage(rslt);

                }
                catch (Exception ex)
                {
                    ad.setTitle("Error!"); ad.setMessage(ex.toString());
                }
                ad.show();
            }

        });
    }
 /* onCreate (original)  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }*/
}
