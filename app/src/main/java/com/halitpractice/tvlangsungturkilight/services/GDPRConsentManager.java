package com.halitpractice.tvlangsungturkilight.services;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.android.gms.ads.MobileAds;
import com.google.android.ump.ConsentInformation;
import com.google.android.ump.ConsentRequestParameters;
import com.google.android.ump.UserMessagingPlatform;

public class GDPRConsentManager {

    private static final String TAG = "GDPR_DEBUG_CHECK";
    private final Activity activity;
    private SharedPreferences sharedPreferences;

    public GDPRConsentManager(Activity activity) {
        this.activity = activity;
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
    }

    public void requestConsentAndInitializeAds() {
        ConsentRequestParameters params = new ConsentRequestParameters.Builder()
                .setTagForUnderAgeOfConsent(false)
                .build();

        boolean hasConsent = sharedPreferences.getBoolean("consent_given", false);

        if (hasConsent) {
            initializeMobileAdsSdk(); // Initialize Ads if consent was previously given
        } else {
            ConsentInformation consentInformation = UserMessagingPlatform.getConsentInformation(activity);
            consentInformation.requestConsentInfoUpdate(activity, params,
                    () -> showConsentForm(consentInformation),
                    error -> Log.w(TAG, "Consent request error: " + error.getMessage()));
        }
    }

    private void showConsentForm(ConsentInformation consentInformation) {
        UserMessagingPlatform.loadConsentForm(activity,
                consentForm -> {
                    consentForm.show(activity, consentFormError -> {
                        if (consentFormError != null) {
                            Log.w(TAG, "Consent form error: " + consentFormError.getMessage());
                        } else {
                            // Save consent preference when the user accepts
                            saveConsentPreference(true);
                        }
                    });
                },
                error -> Log.w(TAG, "Load consent form error: " + error.getMessage()));
    }

    private void initializeMobileAdsSdk() {
        MobileAds.initialize(activity);
        // Additional initialization or ad loading can be placed here if needed
    }

    private void saveConsentPreference(boolean consentGiven) {
        sharedPreferences.edit().putBoolean("consent_given", consentGiven).apply();
    }
}
