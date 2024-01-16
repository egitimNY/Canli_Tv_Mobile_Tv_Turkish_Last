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
import com.halitpractice.tvlangsungturkilight.services.UlusalTvCategoriesDetailsDataCache;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EkstraTvCategoriesDetailsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<UlusalTvModel> main_list;
    private UlusalTvAdapter adapter;
    private ProgressBar progressBar;

    // In your activity class
    private UlusalTvCategoriesDetailsDataCache dataCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekstra_tv_categories_details);

        // Find and set the Toolbar as the action bar
        Toolbar toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);

        // Enable the back button in the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back); // Replace with your custom button icon

        ImageView homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(v -> {
            // Handle the home button click here
            Intent homeIntent = new Intent(EkstraTvCategoriesDetailsActivity.this, MainActivity.class);
            startActivity(homeIntent);
            finish(); // Close the current activity
        });

        progressBar = findViewById(R.id.ekstraTvCategoryDetailsProgressBar); // Initialize the ProgressBar
        progressBar.setVisibility(View.GONE);

        recyclerView = findViewById(R.id.ekstratv_category_details_list); // Initialize the RecyclerView
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AdView mAdView = findViewById(R.id.adViewEkstraTvCategoriesDetails);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        /*
        ImageView closedBtn = findViewById(R.id.closeBtnEkstraTvCategoriesDetails);
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

        String selectedCategory = getIntent().getStringExtra("category");
        ActionBar actionBar = getSupportActionBar();

        /*
        if (actionBar != null && selectedCategory != null) {
            actionBar.setTitle("Seçilen Kategori: " + selectedCategory);
            fetchData(selectedCategory);
        }
        */

        // Initialize the DataCacheCategory instance
        dataCache = UlusalTvCategoriesDetailsDataCache.getInstance();

        if (actionBar != null && selectedCategory != null) {
            actionBar.setTitle("Kategori : " + selectedCategory); // Set the selected category as the title

            // Check if there is cached data for the selected category
            List<UlusalTvModel> cachedData = dataCache.getCachedData(selectedCategory);

            if (cachedData != null && !cachedData.isEmpty()) {
                // Use cached data to update the UI
                updateUIWithCachedData(cachedData);
            } else {
                // Data is not cached, fetch it from the network
                fetchData(selectedCategory);
            }
        }

    }

    // When fetching data for a specific category
    private void fetchData(String selectedCategory) {
        progressBar.setVisibility(View.VISIBLE);

        List<UlusalTvModel> cachedData = dataCache.getCachedData(selectedCategory);

        if (cachedData != null && !cachedData.isEmpty()) {
            // Use cached data to update the UI
            updateUIWithCachedData(cachedData);
            progressBar.setVisibility(View.GONE);
        } else {
            // Data is not cached, fetch it from the network
            Call<List<UlusalTvModel>> req = ManagerAll.getInstance().extraTvCategoryDetailsFetch(selectedCategory);
            req.enqueue(new Callback<List<UlusalTvModel>>() {
                @Override
                public void onResponse(Call<List<UlusalTvModel>> call, Response<List<UlusalTvModel>> response) {
                    progressBar.setVisibility(View.GONE);

                    if (response.isSuccessful()) {
                        List<UlusalTvModel> data = response.body();
                        if (data != null && !data.isEmpty()) {
                            // Cache the data in the specific category cache
                            dataCache.setCachedData(selectedCategory, data);
                            // Update the UI with the fetched data
                            updateUIWithCachedData(data);
                        } else {
                            handleEmptyResponse();
                        }
                    } else {
                        handleUnsuccessfulResponse();
                    }
                }

                @Override
                public void onFailure(Call<List<UlusalTvModel>> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    handleNetworkFailure();
                    t.printStackTrace(); // Print the error details for debugging
                }
            });
        }
    }


    private void updateUIWithCachedData(List<UlusalTvModel> cachedData) {
        if (cachedData != null && !cachedData.isEmpty()) {
            adapter = new UlusalTvAdapter(cachedData, EkstraTvCategoriesDetailsActivity.this);
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
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, EkstraTvCategoriesActivity.class);
        startActivity(intent);
        finish(); // Close the current activity


//        moveTaskToBack(true); // This line ensures the app goes to the background and doesn't go back to the previous activity

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Handle the back button click here, navigate to ThirdActivity
            Intent intent = new Intent(this, EkstraTvCategoriesActivity.class);
            startActivity(intent);
            finish(); // Finish the current activity if you don't want to return to it
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
