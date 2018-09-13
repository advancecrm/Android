package com.ballardscoreapp.main.ui.faq;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ballardscoreapp.main.R;
import com.ballardscoreapp.main.ui.DetailActivity;
import com.ballardscoreapp.main.util.Globals;
import com.ballardscoreapp.main.util.MessageSendService;
//import com.google.ads.AdRequest;
//import com.google.ads.AdSize;
//import com.google.ads.AdView;

public class ContactFragment extends Fragment {
    EditText first_et, last_et, email_et, subject_et, que_et;
    Button submit;
    String firstname = "", lastname = "", email = "", subject = "";
    ProgressDialog pd;
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            String res = (String) msg.obj;
            pd.dismiss();
            if (!res.equalsIgnoreCase("true")) {
                Log.i("res", res);
                Toast.makeText(getActivity(),
                        "Message sent successfully.." + res, Toast.LENGTH_LONG)
                        .show();
            } else {
                Toast.makeText(getActivity(),
                        "Some error occured..Please try again..",
                        Toast.LENGTH_LONG).show();
            }
        }
    };
    private Runnable submitservice = new Runnable() {

        @Override
        public void run() {
            String res = "";
            try {
                MessageSendService service = new MessageSendService();
                res = service.getConvertedWeight("emailsend", firstname, que_et
                        .getText().toString(), email, subject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Message msg = new Message();
            msg.obj = res;
            handler.sendMessage(msg);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.contact_us, parent, false);

        DetailActivity activity = (DetailActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Contact");
        }

//        LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.admob_view);
//        AdView ad = new AdView(getActivity(), AdSize.SMART_BANNER, "ca-app-pub-7124699817614464/7549868151");
//        layout.addView(ad);
//        ad.loadAd(new AdRequest());

        first_et = (EditText) rootView.findViewById(R.id.first_name_et);
        last_et = (EditText) rootView.findViewById(R.id.last_name_et);
        email_et = (EditText) rootView.findViewById(R.id.contactus_email_et);
        subject_et = (EditText) rootView
                .findViewById(R.id.contactus_subject_et);
        que_et = (EditText) rootView.findViewById(R.id.comment_edit);
        submit = (Button) rootView.findViewById(R.id.submit_btn);

        submit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (first_et.getText().toString().equalsIgnoreCase("")) {
                    first_et.setError("Please enter first name");
                } else if (email_et.getText().toString().equalsIgnoreCase("")) {
                    email_et.setError("Please provide Valid Email Address !");
                } else if (subject_et.getText().toString().equalsIgnoreCase("")) {
                    subject_et.setError("Please enter subject");
                } else {
                    firstname = first_et.getText().toString();
                    lastname = last_et.getText().toString();
                    email = email_et.getText().toString();
                    subject = subject_et.getText().toString();

                    pd = ProgressDialog.show(getActivity(), "",
                            "Sending mail.. please wait..");
                    new Thread(null, submitservice, "").start();
                }

            }
        });

        Globals global = (Globals) getActivity().getApplicationContext();
        global.faqfrag = false;
        return rootView;
    }
}
