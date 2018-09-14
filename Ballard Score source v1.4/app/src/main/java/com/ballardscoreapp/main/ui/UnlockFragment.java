package com.ballardscoreapp.main.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ballardscoreapp.main.R;
import com.ballardscoreapp.main.payment.IabHelper;
import com.ballardscoreapp.main.payment.IabResult;
import com.ballardscoreapp.main.payment.Inventory;
import com.ballardscoreapp.main.payment.Purchase;
import com.ballardscoreapp.main.util.Constants;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

import java.util.ArrayList;
import java.util.List;

public class UnlockFragment extends Fragment {

    private static final String TAG = "Android BillingService";
    private static final String PRODUCT_ID_UNLOCK = "com.ballard.app.unlock";
    private static final int REQUEST_CODE_UNLOCK = 1001;
    Button unlockButton;
    SharedPreferences sp;
    Editor editor;
    boolean buycheck = false, buyagain_check = false;
    private IabHelper mHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.unlock_frag_act, parent, false);

        DetailActivity activity = (DetailActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Unlock");
        }

        sp = getActivity().getSharedPreferences(Constants.PREFS_NAME,
                Context.MODE_PRIVATE);

        unlockButton = (Button) rootView.findViewById(R.id.unlock_button);

        LinearLayout layout = (LinearLayout) rootView
                .findViewById(R.id.admob_view);
        AdView ad = new AdView(getActivity(), AdSize.SMART_BANNER,
                "ca-app-pub-7124699817614464/7549868151");
        layout.addView(ad);
        ad.loadAd(new AdRequest());

        String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1aylmg5342yHQ5Jl0t94WG0Ngvne3UoRAtWUJERiFT5I+uDvCejOymGjs0lqHwRdkynWpDYWUhwGWYl6XYQay5nYusCddJBtxl+JFMTlbyTXR/D4Jt24zRgjil3sHgnHkTKNOr5XJrh7fvJuBxNS9+KGXIfpVUDJCvt+Ckan5bRSnNAYHRe1TlLX1+tZayz1R0NIrN6f8PqvbQJyJqOP9ip1qAzQUh3LoXDfnB/OOgpH8TMWzLstkMyKrjlq5loWmu/0VnY9NXfzZsMa4w3dZbG8Qgb5UpayoaDTe4olhOYZFKMNkkQe157U7OHI38iyF7pPMZm3+x9IRLjWVx+9FQIDAQAB";

        // compute your public key and store it in base64EncodedPublicKey
        mHelper = new IabHelper(getActivity(), base64EncodedPublicKey);

        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                if (!result.isSuccess()) {
                    // Oh noes, there was a problem.
                    Toast.makeText(getActivity(), "Problem setting up In-app Billing: " + result,
                            Toast.LENGTH_LONG).show();
                } else {
                    // Hooray, IAB is fully set up!
                    // Check if app is already purchased
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
                                        unlockButton.setEnabled(false);
                                        unlockButton.setText("The app is unlocked, congrats!");
                                        rootView.findViewById(R.id.restore_button).setVisibility(View.GONE);
                                        ImageView lockImage = (ImageView) rootView.findViewById(R.id.lock_img1);
                                        lockImage.setImageResource(R.drawable.unlock_tab);
                                        TextView headerLabel = (TextView) rootView.findViewById(R.id.head_text);
                                        headerLabel.setText("Content available:");
                                    }
                                }
                            });
                }
            }
        });

        unlockButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                mHelper.launchPurchaseFlow(getActivity(), PRODUCT_ID_UNLOCK, REQUEST_CODE_UNLOCK,
                        new IabHelper.OnIabPurchaseFinishedListener() {
                            @Override
                            public void onIabPurchaseFinished(IabResult result, Purchase info) {
                                if (result.isSuccess()) {
                                    unlockButton.setEnabled(false);
                                    unlockButton.setText("The app is unlocked, congrats!");
                                    rootView.findViewById(R.id.restore_button).setVisibility(View.GONE);
                                    ImageView lockImage = (ImageView) rootView.findViewById(R.id.lock_img1);
                                    lockImage.setImageResource(R.drawable.unlock_tab);
                                    TextView headerLabel = (TextView) rootView.findViewById(R.id.head_text);
                                    headerLabel.setText("Content available:");
                                }
                            }
                        });
            }

        });

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mHelper != null) mHelper.dispose();
        mHelper = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_UNLOCK) {
            mHelper.handleActivityResult(requestCode, resultCode, data);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
