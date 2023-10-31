package com.halitpractice.tvlangsungturkilight;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.halitpractice.tvlangsungturkilight.RestApi.ManagerAll;
import com.halitpractice.tvlangsungturkilight.adapters.UlusalTvAdapter;
import com.halitpractice.tvlangsungturkilight.models.UlusalTvModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UlusalTvCategoriesDetailsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<UlusalTvModel> main_list;
    private UlusalTvAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turkish_live_tv_categories_details);

        // Find and set the Toolbar as the action bar
        Toolbar toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);

        // Enable the back button in the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back); // Replace with your custom button icon
        }

        progressBar = findViewById(R.id.turkishLiveTvCategoryDetailsProgressBar); // Initialize the ProgressBar
        progressBar.setVisibility(View.VISIBLE); // Initially, set it to VISIBLE

        recyclerView = findViewById(R.id.turkish_category_details_list); // Initialize the RecyclerView
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String selectedCategory = getIntent().getStringExtra("category");
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null && selectedCategory != null) {
            actionBar.setTitle("Seçilen Kategori: " + selectedCategory);
            fetchData(selectedCategory);
        }

        AdView mAdView = findViewById(R.id.adViewTurkishLiveTvCategoriesDetails);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        ImageView closedBtn = findViewById(R.id.closeBtnTurkishLiveTvCategoriesDetails);
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

    private void fetchData(String selectedCategory) {
        progressBar.setVisibility(View.VISIBLE);
        Call<List<UlusalTvModel>> req = ManagerAll.getInstance().turkishLoveTvCategoryDetailsFetch(selectedCategory);
        req.enqueue(new Callback<List<UlusalTvModel>>() {
            @Override
            public void onResponse(Call<List<UlusalTvModel>> call, Response<List<UlusalTvModel>> response) {
                progressBar.setVisibility(View.GONE); // Always hide the ProgressBar

                if (response.isSuccessful()) {
                    main_list = response.body();
                    if (main_list != null && !main_list.isEmpty()) {
                        adapter = new UlusalTvAdapter(main_list, UlusalTvCategoriesDetailsActivity.this);
                        recyclerView.setAdapter(adapter);
                    } else {
                        handleEmptyResponse();
                    }
                } else {
                    handleUnsuccessfulResponse();
                }
            }

            @Override
            public void onFailure(Call<List<UlusalTvModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE); // Always hide the ProgressBar
                handleNetworkFailure();
                t.printStackTrace(); // Print the error details for debugging
            }
        });
    }

    private void handleUnsuccessfulResponse() {
        // Handle an unsuccessful response (e.g., HTTP error)
        // You can show an appropriate message to the user
        // For example: Toast.makeText(this, "An error occurred while loading data.", Toast.LENGTH_LONG).show();
        redirectYonlendir();
    }

    private void handleEmptyResponse() {
        // Handle the case when the response is empty
        // You can show an appropriate message to the user
        // For example: Toast.makeText(this, "No data available.", Toast.LENGTH_LONG).show();
    }

    private void handleNetworkFailure() {
        // Handle network failures
        // You can show a message to the user indicating a network failure
        // For example: Toast.makeText(this, "No internet connection. Please check your network settings.", Toast.LENGTH_LONG).show();
    }

    private void redirectYonlendir() {
        // Redirect to FeatureUnderConstructionActivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, UlusalTvCategoriesActivity.class);
        startActivity(intent);
        finish(); // Close the current activity


//        moveTaskToBack(true); // This line ensures the app goes to the background and doesn't go back to the previous activity

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Handle the back button click here, navigate to ThirdActivity
            Intent intent = new Intent(this, UlusalTvCategoriesActivity.class);
            startActivity(intent);
            finish(); // Finish the current activity if you don't want to return to it
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
