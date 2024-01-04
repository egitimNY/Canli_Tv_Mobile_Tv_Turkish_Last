package com.halitpractice.tvlangsungturkilight.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.halitpractice.tvlangsungturkilight.MainActivity;
import com.halitpractice.tvlangsungturkilight.R;
import com.halitpractice.tvlangsungturkilight.RestApi.ManagerAll;
import com.halitpractice.tvlangsungturkilight.models.TurkYabanciMovieModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TurkYabanciMovieActivity extends AppCompatActivity {

    private WebView webView;
    private FrameLayout fullScreenContainer;
    private ProgressBar progressBar;
    private TextView loadingMessage;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turk_yabanci_movie);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Yabancı Filmler");
        }

        webView = findViewById(R.id.webView);
        fullScreenContainer = findViewById(R.id.fullScreenContainer);
        progressBar = findViewById(R.id.progressBar);
        loadingMessage = findViewById(R.id.loadingMessage);
        backButton = findViewById(R.id.backButton);

        initWebView();
        showLoadingIndicator();

        // Fetch the website URL from your RestApi and load it into the WebView
        fetchWebsiteUrl();

        AdView mAdView = findViewById(R.id.adViewYabanciMovie);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        ImageView closedBtn = findViewById(R.id.closeBtnYabanciMovie);
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

    }

    private void fetchWebsiteUrl() {
        try {
            // Create an instance of your ManagerAll class
            ManagerAll managerAll = ManagerAll.getInstance();

            // Call the method to get the URL from your RestApi
            Call<List<TurkYabanciMovieModel>> call = managerAll.getTurkYabanciMovie();

            // Enqueue the call and handle the response asynchronously
            call.enqueue(new Callback<List<TurkYabanciMovieModel>>() {
                @Override
                public void onResponse(Call<List<TurkYabanciMovieModel>> call, Response<List<TurkYabanciMovieModel>> response) {
                    try {
                        if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                            // Get the URL from the response (assuming there's only one URL in the list)
                            String url = response.body().get(0).getUrlName();

                            // Load the website URL into the WebView
                            webView.loadUrl(url);
                        } else {
                            // Handle the case where the response is empty or not successful
                            hideLoadingIndicator();
                            showLoadError();
                            navigateToMainActivity(); // Navigate back to the MainActivity
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        // Handle any exceptions that might occur during response processing
                        hideLoadingIndicator();
                        showLoadError();
                        navigateToMainActivity(); // Navigate back to the MainActivity
                    }
                }

                @Override
                public void onFailure(Call<List<TurkYabanciMovieModel>> call, Throwable t) {
                    try {
                        // Handle the failure to fetch the URL, e.g., show an error message
                        hideLoadingIndicator();
                        showLoadError();
                        navigateToMainActivity(); // Navigate back to the MainActivity
                    } catch (Exception e) {
                        e.printStackTrace();
                        // Handle any exceptions that might occur during failure handling
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            // Handle any exceptions that might occur during the fetchWebsiteUrl method
        }
    }

    private void initWebView() {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // Page loading started
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // Page loading finished
                hideLoadingIndicator();
//                scrollToMiddleOfPage(); // Scroll to the middle of the page after it's loaded
                scrollToMiddleOfPageWithOffset(300); // Scroll up by 100 pixels
//                scrollToMiddleOfPageWithOffset(100); // Scroll down by 100 pixels


            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                try {
                    // Handle page load error here
                    // This method is called when the WebView fails to load a page
                    hideLoadingIndicator();
                    showLoadError();
                    navigateToMainActivity(); // Navigate back to the MainActivity
                } catch (Exception e) {
                    e.printStackTrace();
                    // Handle any exceptions that might occur during error handling
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                // Handle URL loading as needed
                return false;
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            private View mCustomView;

            @Override
            public void onShowCustomView(View view, CustomViewCallback callback) {
                if (mCustomView != null) {
                    callback.onCustomViewHidden();
                    return;
                }

                mCustomView = view;
                fullScreenContainer.addView(view, new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                fullScreenContainer.setVisibility(View.VISIBLE);

                // Set landscape orientation if needed
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                // Hide the status bar
                getWindow().setFlags(
                        WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN
                );

                // Hide the button when the video goes full-screen
                backButton.setVisibility(View.GONE);
            }

            @Override
            public void onHideCustomView() {
                if (mCustomView == null) {
                    return;
                }

                // Restore your views and settings here
                fullScreenContainer.removeView(mCustomView);
                mCustomView = null;
                fullScreenContainer.setVisibility(View.GONE);

                // Set portrait orientation if needed
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

                // Show the status bar
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

                // Show the button when the video exits full-screen
                backButton.setVisibility(View.VISIBLE);
            }
        });

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    private void showLoadingIndicator() {
        progressBar.setVisibility(View.VISIBLE);
//        loadingMessage.setVisibility(View.VISIBLE);
    }

    private void hideLoadingIndicator() {
        progressBar.setVisibility(View.GONE);
//        loadingMessage.setVisibility(View.GONE);
    }

    private void showLoadError() {
        // Display a popup message indicating that the URL couldn't be loaded
//        Toast.makeText(TurkYabanciMovieActivity.this, "Websitesi yüklenemedi, Daha sonra tekrar deneyin", Toast.LENGTH_LONG).show();
    }

    private void navigateToMainActivity() {
        // Redirect to the MainActivity
        Intent intent = new Intent(this, TurkYabanciMovieYonlendirActivity.class);
        startActivity(intent);
        finish(); // Optionally, finish the current activity to remove it from the back stack
    }

    // Add this method to handle the back button click
    public void onBackButtonClicked(View view) {
        // Navigate back to the MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // Optionally, finish the current activity to remove it from the back stack
    }

    // JavaScript code to scroll to the middle of the page

    private void scrollToMiddleOfPage() {
        webView.post(() -> {
            int webViewHeight = webView.getHeight();
            int contentHeight = (int) Math.floor(webView.getContentHeight() * webView.getScaleX());
            int scrollY = (contentHeight - webViewHeight) / 2;
            if (scrollY < 0) {
                scrollY = 0;
            }
            webView.scrollTo(0, scrollY);
        });
    }

    // JavaScript code to scroll to the middle of the page with an offset
    private void scrollToMiddleOfPageWithOffset(final int offset) {
        webView.post(() -> {
            int webViewHeight = webView.getHeight();
            int contentHeight = (int) Math.floor(webView.getContentHeight() * webView.getScale());
//            int contentHeight = (int) Math.floor(webView.getContentHeight() * webView.getScaleX());
            int scrollY = (contentHeight - webViewHeight) / 18 + offset;
            if (scrollY < 0) {
                scrollY = 0;
            }
            webView.scrollTo(0, scrollY);
        });
    }

}
