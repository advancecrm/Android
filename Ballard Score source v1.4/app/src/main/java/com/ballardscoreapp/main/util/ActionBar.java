package com.ballardscoreapp.main.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ballardscoreapp.main.R;

public class ActionBar extends RelativeLayout {


    TextView actiontext, logout;

    public ActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflat = LayoutInflater.from(context);
        RelativeLayout lay = (RelativeLayout) inflat.inflate(R.layout.action_bar, null);
        addView(lay);
        actiontext = (TextView) lay.findViewById(R.id.action_text);
    }

    public void setActionText(String text) {
        actiontext.setText(text);
    }
}
