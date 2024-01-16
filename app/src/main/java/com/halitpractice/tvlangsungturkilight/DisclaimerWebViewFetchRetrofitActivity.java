package com.halitpractice.tvlangsungturkilight;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.halitpractice.tvlangsungturkilight.RestApi.ManagerAll;
import com.halitpractice.tvlangsungturkilight.models.FormattedTextModel;
import com.halitpractice.tvlangsungturkilight.services.MarqueeTextHelperUyariDisclaimer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisclaimerWebViewFetchRetrofitActivity extends AppCompatActivity {

    private WebView webView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclaimer_web_view_fetch_retrofit);

        Toolbar toolbar = findViewById(R.id.toolbarDisc);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Disclaimer");
        }

        TextView marqueeTextView = findViewById(R.id.marqueeDiclaimerFetchTextView);
        MarqueeTextHelperUyariDisclaimer.fetchAndDisplayMarqueeTextUyariDisclaimer(marqueeTextView);

        progressBar = findViewById(R.id.progressBarDisclaimer);
        webView = findViewById(R.id.webViewDisc);

        AdView mAdView = findViewById(R.id.adViewDisc);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        /*
        ImageView closedBtn = findViewById(R.id.closeDisc);
        closedBtn.setOnClickListener(v -> {
            if (mAdView.getVisibility() == View.VISIBLE) {
                mAdView.setVisibility(View.GONE);
            } else {
                mAdView.setVisibility(View.VISIBLE);
            }

            if (closedBtn.getVisibility() == View.VISIBLE) {
                closedBtn.setVisibility(View.GONE);
            } else {
                closedBtn.setVisibility(View.VISIBLE);
            }
        });
        */

        fetchData();
    }

    private void fetchData() {

        Call<FormattedTextModel> req = ManagerAll.getInstance().disclaimerFormated();
        progressBar.setVisibility(View.VISIBLE); // Show the progress bar

        req.enqueue(new Callback<FormattedTextModel>() {
            @Override
            public void onResponse(Call<FormattedTextModel> call, Response<FormattedTextModel> response) {
                if (response.isSuccessful()) {
                    FormattedTextModel formattedTextResponse = response.body();
                    if (formattedTextResponse != null && formattedTextResponse.getFormattedText() != null) {
                        String formattedText = formattedTextResponse.getFormattedText();

                        // Load the formatted text into the WebView
                        webView.loadDataWithBaseURL(null, formattedText, "text/html", "UTF-8", null);
                        progressBar.setVisibility(View.GONE);

                    } else {
//                        handleNullResponse();
                        Toast.makeText(DisclaimerWebViewFetchRetrofitActivity.this, "Error: Empty response", Toast.LENGTH_SHORT).show();
                    }
                } else {
//                    handleUnsuccessfulResponse();
                    progressBar.setVisibility(View.GONE);
                    redirectToFeatureUnderConstruction();
//                    Log.d("Redirection", "Redirecting to FeatureUnderConstructionActivity");

                }
            }

            @Override
            public void onFailure(Call<FormattedTextModel> call, Throwable t) {
//                handleNetworkFailure();
                progressBar.setVisibility(View.GONE);

            }
        });
    }

    private void redirectToFeatureUnderConstruction() {
        // Redirect to FeatureUnderConstructionActivity
        Intent intent = new Intent(DisclaimerWebViewFetchRetrofitActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void handleNullResponse() {
        // Handle the case where the response body or formattedText is null
    }

    private void handleUnsuccessfulResponse() {
        // Show a message to the user indicating an error
        Toast.makeText(this, "Sorry, an error occurred. Please try again later.", Toast.LENGTH_SHORT).show();

        // You can also provide additional guidance or actions the user can take
        // For example, you can enable a retry button or suggest checking their network connection.
        // You might also want to log the specific error details for debugging purposes.
    }

    private void handleNetworkFailure() {
        // Show a message to the user indicating a network failure
        Toast.makeText(this, "No internet connection. Please check your network settings.", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Handle the back button click and navigate to IndiaLiveTvActivity
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
