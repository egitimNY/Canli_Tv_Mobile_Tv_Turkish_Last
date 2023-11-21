package com.halitpractice.tvlangsungturkilight;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.halitpractice.tvlangsungturkilight.RestApi.ManagerAll;
import com.halitpractice.tvlangsungturkilight.adapters.TurkishCartoonYtbRedirectAdapter;
import com.halitpractice.tvlangsungturkilight.models.TurkishCartoonYtbRedirectModel;
import com.halitpractice.tvlangsungturkilight.services.ChromeInstallDialogHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TurkishCartoonYtbRedirectActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<TurkishCartoonYtbRedirectModel> main_list;
    private TurkishCartoonYtbRedirectAdapter adapter;

    private AdView mAdView;
    ImageView closedBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view
        setContentView(R.layout.activity_india_cartoon_ytb_redirect);

        // Find and set the Toolbar as the action bar
        Toolbar toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Çizgi Filmler");
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        main_list = new ArrayList<>();
        recyclerView = findViewById(R.id.zReleaseOneMovieRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdView = findViewById(R.id.adViewReleaseOne);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        closedBtn = findViewById(R.id.closeBtnReleaseOne);
        closedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });

        fetchData();

        // Show the install Chrome dialog
        ChromeInstallDialogHelper.showInstallChromeDialog(this);

    }

    private void fetchData() {
        Call<List<TurkishCartoonYtbRedirectModel>> req = ManagerAll.getInstance().indiaCartoonRedirectFetch();
        req.enqueue(new Callback<List<TurkishCartoonYtbRedirectModel>>() {
            @Override
            public void onResponse(Call<List<TurkishCartoonYtbRedirectModel>> call, Response<List<TurkishCartoonYtbRedirectModel>> response) {
                if (response.isSuccessful()) {
                    main_list = response.body();
                    if (main_list != null && !main_list.isEmpty()) {
                        adapter = new TurkishCartoonYtbRedirectAdapter(main_list, TurkishCartoonYtbRedirectActivity.this);
                        recyclerView.setAdapter(adapter);
                    } else {
                        handleNullResponse();
                    }
                } else {
                    handleUnsuccessfulResponse();
                }
            }

            @Override
            public void onFailure(Call<List<TurkishCartoonYtbRedirectModel>> call, Throwable t) {
                handleNetworkFailure();
            }
        });
    }

    private void handleNetworkFailure() {
        // Show a message to the user indicating a network failure
        Toast.makeText(this, "No internet connection. Please check your network settings.", Toast.LENGTH_LONG).show();
    }

    private void handleNullResponse() {
        // Handle null response from the server
        // For example, show an error message to the user
        Toast.makeText(this, "No data available. Please try again later.", Toast.LENGTH_LONG).show();
    }

    private void handleUnsuccessfulResponse() {
        // Handle an unsuccessful response from the server
        // For example, show an error message to the user
        Toast.makeText(this, "An error occurred while loading data. Please try again later.", Toast.LENGTH_LONG).show();
    }

    // Method to navigate to FeatureUnderConstructionActivity
    private void navigateToIndiaLiveTvActivity() {
        Intent intent = new Intent(TurkishCartoonYtbRedirectActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // Optionally, you can finish the current activity if you don't want to go back to it.
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(TurkishCartoonYtbRedirectActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // Close the current activity
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Handle the back button click here, navigate to ThirdActivity
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish(); // Finish the current activity if you don't want to return to it
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
