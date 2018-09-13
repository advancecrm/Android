package com.ballardscoreapp.main.ui.faq;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ballardscoreapp.main.R;
import com.ballardscoreapp.main.ui.DetailActivity;
import com.ballardscoreapp.main.util.Constants;
import com.ballardscoreapp.main.util.Globals;
//import com.google.ads.AdRequest;
//import com.google.ads.AdSize;
//import com.google.ads.AdView;

public class FaqDetailFragment extends Fragment {

    WebView faqansview;
    Globals global;
    TextView que_tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        container = (RelativeLayout) inflater.inflate(R.layout.faq_ans,
                container, false);

        global = (Globals) getActivity().getApplicationContext();
        que_tv = (TextView) container.findViewById(R.id.question_text);
        String ans = global.getQueAnsList().get(global.pos)
                .get(Constants.ANSWER);
        String que = global.getQueAnsList().get(global.pos)
                .get(Constants.QUESTION);

 //       LinearLayout layout = (LinearLayout) container
//                .findViewById(R.id.admob_view);

//        AdView ad = new AdView(getActivity(), AdSize.SMART_BANNER,
//                "ca-app-pub-7124699817614464/7549868151");
//        layout.addView(ad);
//        ad.loadAd(new AdRequest());

        que_tv.setText(que);
        String s5 = ans.replace(";\':", ";\">");
        String page = "<html>" + s5 + "</html";

        DetailActivity activity = (DetailActivity) getActivity();
        ActionBar actionbar = activity.getSupportActionBar();
        if (actionbar != null) {
            actionbar.setTitle("Answer");
        }

        faqansview = (WebView) container.findViewById(R.id.webview);
        faqansview.loadDataWithBaseURL(null, page, "text/html", "utf-8", null);

        global.faqfrag = false;

        return container;
    }
}
