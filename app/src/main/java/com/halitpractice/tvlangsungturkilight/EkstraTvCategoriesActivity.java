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
import com.halitpractice.tvlangsungturkilight.adapters.UlusalTvCategoryAdapter;
import com.halitpractice.tvlangsungturkilight.models.UlusalTvCategoryModel;
import com.halitpractice.tvlangsungturkilight.services.UlusalTvCategoriesDataCache;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EkstraTvCategoriesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<UlusalTvCategoryModel> main_list;
    private UlusalTvCategoryAdapter adapter;

    private ProgressBar progressBar;

    private UlusalTvCategoriesDataCache dataCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekstra_tv_categories);

        // Find and set the Toolbar as the action bar
        Toolbar toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Ekstra TV'ler Ketegori");
        }

        // Enable the back button in the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back); // Replace with your custom button icon
        }

        ImageView homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(v -> {
            // Handle the home button click here
            Intent homeIntent = new Intent(EkstraTvCategoriesActivity.this, MainActivity.class);
            startActivity(homeIntent);
            finish(); // Close the current activity
        });


        progressBar = findViewById(R.id.turkishTvCategoryProgressBar); // Initialize the ProgressBar
        progressBar.setVisibility(View.GONE); // Initially, set it to GONE

        main_list = new ArrayList<>();
        recyclerView = findViewById(R.id.turkishTvCategoryRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AdView mAdView = findViewById(R.id.adViewTurkishLiveTvCategories);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        /*
        ImageView closedBtn = findViewById(R.id.closeBtnTurkishLiveTvCategories);
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

//        fetchData();

        // Initialize the DataCache instance
        dataCache = UlusalTvCategoriesDataCache.getInstance();

        // Check if there is cached data
        List<UlusalTvCategoryModel> cachedData = dataCache.getCachedData();

        if (cachedData != null && !cachedData.isEmpty()) {
            // Use cached data to update the UI
            updateUIWithCachedData(cachedData);
        } else {
            // Data is not cached, fetch it from the network
            fetchData();
        }

    }

    // Checks if data is cached, if so, update UI with cached data; otherwise, fetches data from the network
    private void fetchData() {
        progressBar.setVisibility(View.VISIBLE);

        // Check if there's cached data available
        List<UlusalTvCategoryModel> cachedData = UlusalTvCategoriesDataCache.getInstance().getCachedData();

        // If cached data exists, use it to update the UI
        if (cachedData != null && !cachedData.isEmpty()) {
            updateUIWithCachedData(cachedData);
            progressBar.setVisibility(View.GONE);
        } else {
            // No cached data, fetch from the network
            Call<List<UlusalTvCategoryModel>> req = ManagerAll.getInstance().extraTvCategoryFetch();
            req.enqueue(new Callback<List<UlusalTvCategoryModel>>() {
                @Override
                public void onResponse(Call<List<UlusalTvCategoryModel>> call, Response<List<UlusalTvCategoryModel>> response) {
                    progressBar.setVisibility(View.GONE);

                    if (response.isSuccessful()) {
                        List<UlusalTvCategoryModel> data = response.body();
                        // If data is fetched, cache it and update the UI
                        if (data != null && !data.isEmpty()) {
                            UlusalTvCategoriesDataCache.getInstance().setCachedData(data);
                            updateUIWithCachedData(data);
                        } else {
                            // Handle empty response
                            handleEmptyResponse();
                        }
                    } else {
                        // Handle unsuccessful response
                        handleUnsuccessfulResponse();
                    }
                }

                @Override
                public void onFailure(Call<List<UlusalTvCategoryModel>> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    // Handle network failure
                    handleNetworkFailure();
                }
            });
        }
    }

    private void updateUIWithCachedData(List<UlusalTvCategoryModel> cachedData) {
        if (cachedData != null && !cachedData.isEmpty()) {
            adapter = new UlusalTvCategoryAdapter(cachedData, EkstraTvCategoriesActivity.this);
            recyclerView.setAdapter(adapter);
        } else {
            handleEmptyResponse();
        }
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
        Intent intent = new Intent(EkstraTvCategoriesActivity.this, MainActivity.class);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(EkstraTvCategoriesActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // Close the current activity

//        moveTaskToBack(true); // This line ensures the app goes to the background and doesn't go back to the previous activity

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