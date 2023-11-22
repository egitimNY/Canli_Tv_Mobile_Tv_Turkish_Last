package com.halitpractice.tvlangsungturkilight.services;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.halitpractice.tvlangsungturkilight.MainActivity;

public class InterstitialAdHelper {

    private static final String TAG = "InterstitialAdHelper";
    private InterstitialAd mInterstitialAd;

    public InterstitialAdHelper(Context context, String adUnitId) {
        loadInterstitialAd(context, adUnitId);
    }

    private void loadInterstitialAd(Context context, String adUnitId) {
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(context, adUnitId, adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                mInterstitialAd = interstitialAd;
                Log.i(TAG, "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                mInterstitialAd = null;
                Log.d(TAG, "Ad failed to load: " + loadAdError.toString());
            }
        });
    }

    public void showInterstitialAd(Activity activity) {
        if (mInterstitialAd != null) {
            mInterstitialAd.show(activity);
        }
    }

    public void onBackPressedWithAd(Activity activity, Intent intentToStart) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intentToStart);
        if (mInterstitialAd == null) {
            activity.finish();
        }
    }
}


