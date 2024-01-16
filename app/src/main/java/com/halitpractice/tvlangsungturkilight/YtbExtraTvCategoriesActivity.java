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
import com.halitpractice.tvlangsungturkilight.adapters.YtbExtraTvCategoryAdapter;
import com.halitpractice.tvlangsungturkilight.models.YtbExtraTvCategoryModel;
import com.halitpractice.tvlangsungturkilight.services.YtbExtraTvCategoriesDataCache;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YtbExtraTvCategoriesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<YtbExtraTvCategoryModel> main_list;
    private YtbExtraTvCategoryAdapter adapter;
    private ProgressBar progressBar;

    private YtbExtraTvCategoriesDataCache dataCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ytb_extra_tv_categories);


        // Find and set the Toolbar as the action bar
        Toolbar toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);

        // Enable the back button in the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Ytb Ekstra TV Kategori");
        }

        ImageView homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(v -> {
            // Handle the home button click here
            Intent homeIntent = new Intent(YtbExtraTvCategoriesActivity.this, MainActivity.class);
            startActivity(homeIntent);
            finish(); // Close the current activity
        });

        progressBar = findViewById(R.id.ytbExtraTvCategoryProgressBar); // Initialize the ProgressBar
        progressBar.setVisibility(View.GONE); // Initially, set it to GONE

        main_list = new ArrayList<>();
        recyclerView = findViewById(R.id.ytbExtraTvCategoryRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AdView mAdView = findViewById(R.id.adViewYtbExtraTv);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        /*
        ImageView closedBtn = findViewById(R.id.closeBtnYtbExtraTv);
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
        dataCache = YtbExtraTvCategoriesDataCache.getInstance();

        // Check if there is cached data
        List<YtbExtraTvCategoryModel> cachedData = dataCache.getCachedData();

        if (cachedData != null && !cachedData.isEmpty()) {
            // Use cached data to update the UI
            updateUIWithCachedData(cachedData);
        } else {
            // Data is not cached, fetch it from the network
            fetchData();
        }

    }

    private void fetchData() {
        progressBar.setVisibility(View.VISIBLE);

        // Check if there's cached data available
        List<YtbExtraTvCategoryModel> cachedData = YtbExtraTvCategoriesDataCache.getInstance().getCachedData();

        // If cached data exists, use it to update the UI
        if (cachedData != null && !cachedData.isEmpty()) {
            updateUIWithCachedData(cachedData);
            progressBar.setVisibility(View.GONE);
        } else {
            // No cached data, fetch from the network
            Call<List<YtbExtraTvCategoryModel>> req = ManagerAll.getInstance().ytbExtraTvCategoryFetch();
            req.enqueue(new Callback<List<YtbExtraTvCategoryModel>>() {
                @Override
                public void onResponse(Call<List<YtbExtraTvCategoryModel>> call, Response<List<YtbExtraTvCategoryModel>> response) {
                    progressBar.setVisibility(View.GONE); // Always hide the ProgressBar

                    if (response.isSuccessful()) {
                        List<YtbExtraTvCategoryModel> data = response.body();
                        // If data is fetched, cache it and update the UI
                        if (data != null && !data.isEmpty()) {
                            YtbExtraTvCategoriesDataCache.getInstance().setCachedData(data);
                            updateUIWithCachedData(data);
                        } else {
                            // Handle empty response
                            handleNullResponse();
                        }
                    } else {
                        // Handle unsuccessful response
                        handleUnsuccessfulResponse();
                    }
                }

                @Override
                public void onFailure(Call<List<YtbExtraTvCategoryModel>> call, Throwable t) {
                    progressBar.setVisibility(View.GONE); // Always hide the ProgressBar
                    handleNetworkFailure();
                }
            });
        }
    }

    private void updateUIWithCachedData(List<YtbExtraTvCategoryModel> cachedData) {
        if (cachedData != null && !cachedData.isEmpty()) {
            adapter = new YtbExtraTvCategoryAdapter(cachedData, YtbExtraTvCategoriesActivity.this);
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
        Intent intent = new Intent(YtbExtraTvCategoriesActivity.this, YtbExtraTvYonlendirCategoriesActivity.class);
        startActivity(intent);
    }



    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // Close the current activity

        // To prevent going back to TurkishLiveTvActivity on the next back press
//        moveTaskToBack(false); // This line ensures the app goes to the background and doesn't go back to the previous activity
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