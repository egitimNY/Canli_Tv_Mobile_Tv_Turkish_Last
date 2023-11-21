package com.halitpractice.tvlangsungturkilight.services;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;

public class ChromeInstallDialogHelper {

    private static final String PREF_NAME = "ChromeInstallDialog";
    private static final String KEY_DIALOG_SHOWN = "DialogShown";

    public static void showInstallChromeDialog(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        // Check if the dialog has been shown before
        boolean dialogShown = preferences.getBoolean(KEY_DIALOG_SHOWN, false);
        if (!dialogShown) {
            // Dialog has not been shown, check if Google Chrome is installed
            if (isGoogleChromeInstalled(context)) {
                // Google Chrome is installed, no need to show the dialog
                preferences.edit().putBoolean(KEY_DIALOG_SHOWN, true).apply();
            } else {
                // Google Chrome is not installed, show the installation dialog
                showChromeInstallDialog(context);
            }
        }
    }

    private static boolean isGoogleChromeInstalled(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            // Check if Google Chrome is installed by attempting to get its package info
            pm.getPackageInfo("com.android.chrome", PackageManager.GET_ACTIVITIES);
            return true; // Package info found, Chrome is installed
        } catch (PackageManager.NameNotFoundException e) {
            return false; // Package info not found, Chrome is not installed
        }
    }

    private static void showChromeInstallDialog(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle("Install Google Chrome");
        builder.setTitle("Google Chrome'u Yükle");
//        builder.setMessage("For better performance, we recommend installing Google Chrome. Do you want to install it now?");
        builder.setMessage("Daha iyi performans için, Google Chrome'un yüklenmesini öneriyoruz. Şimdi yüklemek ister misiniz?");

        builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                openGoogleChromePlayStore(context);
            }
        });

        builder.setNegativeButton("Hayır", null);

        final AlertDialog dialog = builder.create();

        dialog.show();
    }

    private static void openGoogleChromePlayStore(Context context) {
        try {
            // Open Google Chrome page on the Play Store
            Uri uri = Uri.parse("market://details?id=com.android.chrome");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (android.content.ActivityNotFoundException e) {
            // If Play Store app is not available, open the Play Store website
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.android.chrome");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}

