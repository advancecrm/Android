package com.ballardscoreapp.main.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ballardscoreapp.main.R;
import com.ballardscoreapp.main.payment.IabHelper;
import com.ballardscoreapp.main.payment.IabResult;
import com.ballardscoreapp.main.payment.Inventory;
import com.ballardscoreapp.main.util.ActionBar;
import com.ballardscoreapp.main.util.Constants;
import com.ballardscoreapp.main.util.Decompres;
//import com.google.ads.AdRequest;
//import com.google.ads.AdSize;
//import com.google.ads.AdView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class VideoFragment extends Fragment {

    private static final String PRODUCT_ID_UNLOCK = "com.ballard.app.unlock";
    ViewPager viewPager;
    PagerAdapter pageadapter;
    ActionBar action_Bar;
    int noofsize = 5;
    ProgressBar pb;
    SharedPreferences sp;
    boolean buycheck;
    RelativeLayout unlockLayout, videoviewLayout;
    File path = null;
    boolean isdownloading = false;
    TextView percent_tv;
    String video_url = "", videoname = "";
    String Title[] = {"Chapter 1: Introduction", "Chapter 2: Introduction continued", "Chapter 3: Neuromuscular Assessment(continued)",
            "Chapter 4: Physical Assessment(continued)", "Chapter 5: Physical Assessment(continued)"};
    String DesString[] = {"-Method of determine gestational age \n-Discussion of acitive and passive muscle tone",
            "-Assessing Flexor tone \n-Neuromuscular Assessment \n\t-Posture \n\t-Square Window \n\t-Arm Recoil",
            "-Popliteal Angle \n-Scarf Sign \n-Heel to Ear", "-Lanugo(continued) \n-Plantar Surface \n-Breast \n-Eye/Ear:",
            "Assessing the female and male physical characteristics as part of the assessment of gestational age.\n-Genitals-Male \n-Genitals-Female\n" +
                    "SummaryFragment of Gestational Assessment and the New Ballard Score"};
    int imgs[] = {R.drawable.video_1, R.drawable.ch2, R.drawable.ch3, R.drawable.ch4, R.drawable.ch5};
    String url[] = {"http://www.ballardscore.com/videos/Chapter_1.zip", "http://www.ballardscore.com/videos/Chapter_2.zip",
            "http://www.ballardscore.com/videos/Chapter_3.zip", "http://www.ballardscore.com/videos/Chapter_4.zip",
            "http://www.ballardscore.com/videos/Chapter_5.zip"};
    String videonames[] = {"Chapter_1.mp4", "Chapter_2.mp4", "Chapter_3.mp4", "Chapter_4.mp4", "Chapter_5.mp4"};
    int videopos = 565676;
    OnClickListener onvideocicked = new OnClickListener() {

        @Override
        public void onClick(View v) {
            if (!isdownloading) {
                videopos = Integer.parseInt(v.getTag().toString());
                pb.setVisibility(View.VISIBLE);
                percent_tv.setVisibility(View.VISIBLE);
                path = new File("/sdcard/BallardScore/" + videonames[videopos]);

                if (!path.exists()) {
                    File f = new File("/sdcard/BallardScore/");
                    if (!f.exists()) {
                        f.mkdir();
                    }
                    video_url = url[videopos];
                    videoname = "ballard" + videopos + ".zip";
                    isdownloading = true;
                    new DownloadFileFromURL().execute(video_url);

                } else {

                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
                    Uri data = Uri.parse(path.getPath());
                    intent.setDataAndType(data, "video/*");
                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivity(intent);
                    } else {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.mxtech.videoplayer.ad")));
                        Toast.makeText(getActivity(), "Please install any video player app to play " +
                                "downloaded MP4 video", Toast.LENGTH_LONG).show();
                    }
                }
            } else {
                Toast.makeText(getActivity(), "Downloading is in progress.. Please wait..", Toast.LENGTH_SHORT).show();
            }


        }
    };
    private IabHelper mHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        container = (RelativeLayout) inflater.inflate(R.layout.video_frag_act, container, false);

        sp = getActivity().getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);

        videoviewLayout = (RelativeLayout) container.findViewById(R.id.view_pager_layout);
        unlockLayout = (RelativeLayout) container.findViewById(R.id.unlock_view_pager_layout);

        viewPager = (ViewPager) container.findViewById(R.id.viewpager);
        VideoPaggerAdapter adapter = new VideoPaggerAdapter(getActivity(), noofsize);

        pb = (ProgressBar) container.findViewById(R.id.pb);
        percent_tv = (TextView) container.findViewById(R.id.percent);

        viewPager.setAdapter(adapter);

//        LinearLayout layout = (LinearLayout) container.findViewById(R.id.admob_view);

//        AdView ad = new AdView(getActivity(), AdSize.SMART_BANNER, "ca-app-pub-7124699817614464/7549868151");
//        layout.addView(ad);
//        ad.loadAd(new AdRequest());


        viewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int selPos) {
                if (videopos == selPos) {
                    pb.setVisibility(View.VISIBLE);
                    percent_tv.setVisibility(View.VISIBLE);
                } else {
                    pb.setVisibility(View.GONE);
                    percent_tv.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });

        String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1aylmg5342yHQ5Jl0t94WG0Ngvne3UoRAtWUJERiFT5I+uDvCejOymGjs0lqHwRdkynWpDYWUhwGWYl6XYQay5nYusCddJBtxl+JFMTlbyTXR/D4Jt24zRgjil3sHgnHkTKNOr5XJrh7fvJuBxNS9+KGXIfpVUDJCvt+Ckan5bRSnNAYHRe1TlLX1+tZayz1R0NIrN6f8PqvbQJyJqOP9ip1qAzQUh3LoXDfnB/OOgpH8TMWzLstkMyKrjlq5loWmu/0VnY9NXfzZsMa4w3dZbG8Qgb5UpayoaDTe4olhOYZFKMNkkQe157U7OHI38iyF7pPMZm3+x9IRLjWVx+9FQIDAQAB";

        // compute your public key and store it in base64EncodedPublicKey
        mHelper = new IabHelper(getActivity(), base64EncodedPublicKey);

        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                if (result.isSuccess()) {
                    List<String> purchaseList = new ArrayList<String>();
                    purchaseList.add(PRODUCT_ID_UNLOCK);
                    mHelper.queryInventoryAsync(true, purchaseList,
                            new IabHelper.QueryInventoryFinishedListener() {
                                @Override
                                public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
                                    if (result.isFailure()) {
                                        // handle error
                                        return;
                                    }

                                    boolean isAppUnlocked =
                                            inventory.hasPurchase(PRODUCT_ID_UNLOCK);
                                    // update the UI
                                    if (isAppUnlocked) {
                                        unlockLayout.setVisibility(View.GONE);
                                        videoviewLayout.setVisibility(View.VISIBLE);
                                    }
                                }
                            });
                }
            }
        });

        return container;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mHelper != null) mHelper.dispose();
        mHelper = null;
    }

    class VideoPaggerAdapter extends PagerAdapter {
        LayoutInflater mInflater;
        Context mContext;
        int value;
        ImageView[] img;
        TextView title, description;
        ImageView v_img;

        public VideoPaggerAdapter(Context activity, int noofsize) {

            value = noofsize;
            mInflater = LayoutInflater.from(activity);

        }


        @Override
        public Object instantiateItem(View container, int position) {

            View v = (RelativeLayout) mInflater.inflate(R.layout.view_pagger_items, null);


            title = (TextView) v.findViewById(R.id.title_top_text);
            description = (TextView) v.findViewById(R.id.description_text);
            v_img = (ImageView) v.findViewById(R.id.video_img);

            v_img.setImageResource(imgs[position]);
            v_img.setTag(position);
            v_img.setOnClickListener(onvideocicked);
            title.setText(Title[position]);
            description.setText(DesString[position]);

            ((ViewPager) container).addView(v, 0);

            return v;
        }

        @Override
        public void destroyItem(View collection, int position, Object view) {

            ((ViewPager) collection).removeView((RelativeLayout) view);
        }


        @Override
        public Parcelable saveState() {

            return super.saveState();
        }

        @Override
        public int getCount() {

            return value;
        }

        @Override
        public boolean isViewFromObject(View view, Object arg1) {

            return view == ((RelativeLayout) arg1);
        }

    }

    class DownloadFileFromURL extends AsyncTask<String, String, String> {

        Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                isdownloading = false;
                pb.setVisibility(View.GONE);
                percent_tv.setVisibility(View.GONE);
                videopos = 587698;
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
                Uri data = Uri.parse(path.getPath());
                intent.setDataAndType(data, "video/*");
                startActivity(intent);
            }
        };
        private Runnable unzipfiles = new Runnable() {

            @Override
            public void run() {
                try {
                    String zipFile = "/sdcard/BallardScore/" + videoname;
                    String unzipLocation = Environment.getExternalStorageDirectory() + "/BallardScore/";

                    Decompres d = new Decompres(zipFile, unzipLocation);
                    d.unzip();
                } catch (Exception ignored) {
                }

                handler.sendMessage(new Message());
            }
        };

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //showDialog(progress_bar_type);

        }

        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                URLConnection conection = url.openConnection();
                conection.connect();
                int lenghtOfFile = conection.getContentLength();
                InputStream input = new BufferedInputStream(url.openStream(), 8192);
                OutputStream output = new FileOutputStream("/sdcard/BallardScore/" + videoname);
                byte data[] = new byte[1024];
                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    publishProgress("" + (int) ((total * 100) / lenghtOfFile));
                    output.write(data, 0, count);
                }
                output.flush();
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }
            return null;
        }

        protected void onProgressUpdate(String... progress) {
            //pDialog.setProgress(Integer.parseInt(progress[0]));
            try {
                percent_tv.setText(progress[0] + " %");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPostExecute(String file_url) {
            //dismissDialog(progress_bar_type);
            percent_tv.setText("Unzipping file..");
            new Thread(null, unzipfiles, "").start();

        }
    }

}
