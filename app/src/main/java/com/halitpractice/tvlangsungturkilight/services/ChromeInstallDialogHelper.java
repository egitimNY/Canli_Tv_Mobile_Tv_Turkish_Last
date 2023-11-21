package com.halitpractice.tvlangsungturkilight.services;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;

public class ChromeInstallDialogHelper {

    private static final String PREF_NAME = "ChromeInstallDialog";
    private static final String KEY_DIALOG_SHOWN = "DialogShown";

    public static void showInstallChromeDialog(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        // Check if the dialog has been shown before
        boolean dialogShown = preferences.getBoolean(KEY_DIALOG_SHOWN, false);
        if (!dialogShown) {
            // Dialog has not been shown, show it now
            showChromeInstallDialog(context);
            // Mark the dialog as shown
            preferences.edit().putBoolean(KEY_DIALOG_SHOWN, true).apply();
        }
    }

    private static void showChromeInstallDialog(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle("Install Google Chrome");
        builder.setTitle("Google Chrome'u Yükle");
//        builder.setMessage("For better performance, we recommend installing Google Chrome. Do you want to install it now?");
        builder.setMessage("Daha iyi bir performans için, Google Chrome'un yüklenmesini öneriyoruz. Şimdi yüklemek ister misiniz?");

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
