package com.halitpractice.tvlangsungturkilight.services;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class InternetConnectivityChecker extends AsyncTask<Void, Void, Boolean> {

    private Context context;
    private InternetConnectivityListener listener;

    public interface InternetConnectivityListener {
        void onInternetConnectivityChecked(boolean isConnected);
    }

    public InternetConnectivityChecker(Context context, InternetConnectivityListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        return isInternetAvailable();
    }

    @Override
    protected void onPostExecute(Boolean isConnected) {
        super.onPostExecute(isConnected);
        listener.onInternetConnectivityChecked(isConnected);

        if (!isConnected) {
            showNoInternetDialog();
        }
    }

    private void showNoInternetDialog() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
//        builder.setTitle("No Internet Connection")
        builder.setTitle("İnternet Bağlantısı Yok")
//                .setMessage("Please check your internet connection and try again.")
                .setMessage("Lütfen internet bağlantınızı kontrol edip tekrar deneyin.")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // You can add additional actions here if needed
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private boolean isInternetAvailable() {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("8.8.8.8", 53), 1500);
            socket.close();
            return true;
        } catch (IOException e) {
            Log.e("InternetChecker", "Error checking internet connectivity: " + e.getMessage());
            return false;
        }
    }
}


