package com.halitpractice.tvlangsungturkilight.activities;

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
import com.halitpractice.tvlangsungturkilight.MainActivity;
import com.halitpractice.tvlangsungturkilight.R;
import com.halitpractice.tvlangsungturkilight.RestApi.ManagerAll;
import com.halitpractice.tvlangsungturkilight.adapters.DunyaTvAdapter;
import com.halitpractice.tvlangsungturkilight.models.DunyaTvModel;
import com.halitpractice.tvlangsungturkilight.services.WorldTvLanguageDetailsDataCache;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DunyaTvLanguageDetailsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<DunyaTvModel> main_list;
    private DunyaTvAdapter adapter;
    private ProgressBar progressBar;

    // In your activity class
    private WorldTvLanguageDetailsDataCache dataCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dunya_tv_language_details);

        // Find and set the Toolbar as the action bar
        Toolbar toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);

        // Enable the back button in the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        }

        ImageView homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(v -> {
            // Handle the home button click here
            Intent homeIntent = new Intent(DunyaTvLanguageDetailsActivity.this, MainActivity.class);
            startActivity(homeIntent);
            finish(); // Close the current activity
        });

        progressBar = findViewById(R.id.progressBarWorldTvLanguageDetails); // Initialize the ProgressBar
        progressBar.setVisibility(View.GONE); // Initially, set it to VISIBLE

        recyclerView = findViewById(R.id.worldTvLanguageDetailsRecyclerView); // Initialize the RecyclerView
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String selectedCategory = getIntent().getStringExtra("dunyaCategory");
        ActionBar actionBar = getSupportActionBar();

        /*
        if (actionBar != null) {
            if (selectedCategory != null) {
                actionBar.setTitle("Seçilen Kategori: " + selectedCategory);
                fetchData(selectedCategory);
            }
        }
        */

        // Initialize the DataCacheCategory instance
        dataCache = WorldTvLanguageDetailsDataCache.getInstance();

        if (actionBar != null && selectedCategory != null) {
            actionBar.setTitle("DİL : " + selectedCategory); // Set the selected category as the title

            // Check if there is cached data for the selected category
            List<DunyaTvModel> cachedData = dataCache.getCachedData(selectedCategory);

            if (cachedData != null && !cachedData.isEmpty()) {
                // Use cached data to update the UI
                updateUIWithCachedData(cachedData);
            } else {
                // Data is not cached, fetch it from the network
                fetchData(selectedCategory);
            }
        }

        AdView mAdView = findViewById(R.id.adViewWorldTvLanguageDetails);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        /*
        ImageView closedBtn = findViewById(R.id.closeBtnWorldTvLanguageDetails);
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

    }

    private void fetchData(String selectedCategory) {
        progressBar.setVisibility(View.VISIBLE);

        List<DunyaTvModel> cachedData = dataCache.getCachedData(selectedCategory);

        if (cachedData != null && !cachedData.isEmpty()) {
            // Use cached data to update the UI
            updateUIWithCachedData(cachedData);
            progressBar.setVisibility(View.GONE);
        } else {
            Call<List<DunyaTvModel>> req = ManagerAll.getInstance().cigiFilmTvLanguageDetailsFetch(selectedCategory);
            req.enqueue(new Callback<List<DunyaTvModel>>() {
                @Override
                public void onResponse(Call<List<DunyaTvModel>> call, Response<List<DunyaTvModel>> response) {
                    progressBar.setVisibility(View.GONE); // Always hide the ProgressBar

                    if (response.isSuccessful()) {
                        List<DunyaTvModel> data = response.body();
                        if (data != null && !data.isEmpty()) {
                            // Cache the data in the specific category cache
                            dataCache.setCachedData(selectedCategory, data);
                            // Update the UI with the fetched data
                            updateUIWithCachedData(data);
                        } else {
                            handleNullResponse();
                        }
                    } else {
                        handleUnsuccessfulResponse();
                    }
                }

                @Override
                public void onFailure(Call<List<DunyaTvModel>> call, Throwable t) {
                    progressBar.setVisibility(View.GONE); // Always hide the ProgressBar
                    handleNetworkFailure();
                    t.printStackTrace(); // Print the error details for debugging
                }
            });
        }
    }

    private void updateUIWithCachedData(List<DunyaTvModel> cachedData) {
        if (cachedData != null && !cachedData.isEmpty()) {
            adapter = new DunyaTvAdapter(cachedData, DunyaTvLanguageDetailsActivity.this);
            recyclerView.setAdapter(adapter);
        } else {
            handleNullResponse();
        }
    }

    private void handleUnsuccessfulResponse() {
        // Handle an unsuccessful response from the server
        // For example, show an error message to the user
//        Toast.makeText(this, "An error occurred while loading data. Please try again later.", Toast.LENGTH_LONG).show();
//        Toast.makeText(this, "Veri yüklenirken bir hata oluştu. Lütfen daha sonra tekrar deneyin.", Toast.LENGTH_LONG).show();
        redirectYonlendir();
    }

    private void handleNullResponse() {
        // Handle null response from the server
        // For example, show an error message to the user
//        Toast.makeText(this, "No data available. Please try again later.", Toast.LENGTH_LONG).show();
//        Toast.makeText(this, "Veri bulunamadı. Lütfen daha sonra tekrar deneyin.", Toast.LENGTH_LONG).show();

    }

    private void handleNetworkFailure() {
        // Show a message to the user indicating a network failure
//        Toast.makeText(this, "No internet connection. Please check your network settings.", Toast.LENGTH_LONG).show();
//        Toast.makeText(this, "İnternet bağlantısı yok. Lütfen ağ ayarlarınızı kontrol edin.", Toast.LENGTH_LONG).show();
    }

    private void redirectYonlendir() {
        // Redirect to FeatureUnderConstructionActivity
        Intent intent = new Intent(DunyaTvLanguageDetailsActivity.this, DunyaTvYonlendirLanguageDetailsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, DunyaTvLanguageActivity.class);
        startActivity(intent);
        finish(); // Close the current activity

        // To prevent going back to TurkishLiveTvActivity on the next back press
//        moveTaskToBack(false); // This line ensures the app goes to the background and doesn't go back to the previous activity
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Handle the back button click here, navigate to ThirdActivity
            Intent intent = new Intent(this, DunyaTvLanguageActivity.class);
            startActivity(intent);
            finish(); // Finish the current activity if you don't want to return to it
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
