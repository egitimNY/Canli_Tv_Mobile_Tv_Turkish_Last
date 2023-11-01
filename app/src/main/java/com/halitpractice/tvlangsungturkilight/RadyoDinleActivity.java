package com.halitpractice.tvlangsungturkilight;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.halitpractice.tvlangsungturkilight.RestApi.ManagerAll;
import com.halitpractice.tvlangsungturkilight.adapters.RadyoDinleAdapter;
import com.halitpractice.tvlangsungturkilight.models.RadyoDinleModel;
import com.halitpractice.tvlangsungturkilight.services.RadyoDinleDataCache;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RadyoDinleActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<RadyoDinleModel> main_list;
    private RadyoDinleAdapter adapter;
    private ProgressBar progressBar;

    private RadyoDinleDataCache dataCache; // Instance of DataCache for caching data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radyo_dinle);

        Toolbar toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);

        // Enable the back button in the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Radyolar");
        }

        main_list = new ArrayList<>();
        recyclerView = findViewById(R.id.movieYoutubeRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressBar = findViewById(R.id.progressBarRadyoDinle);
        progressBar.setVisibility(View.GONE); // Initially, set it to GONE

        AdView mAdView = findViewById(R.id.adViewRadyoDinleYoutube);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        ImageView closedBtn = findViewById(R.id.closeBtnRadyoDinleYoutube);
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

//        fetchData();

        // Initialize the DataCache instance
        dataCache = RadyoDinleDataCache.getInstance();

        // Check if there is cached data
        List<RadyoDinleModel> cachedData = dataCache.getCachedData();

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
        Call<List<RadyoDinleModel>> req = ManagerAll.getInstance().getRadyoDinleFetch();
        req.enqueue(new Callback<List<RadyoDinleModel>>() {
            @Override
            public void onResponse(Call<List<RadyoDinleModel>> call, Response<List<RadyoDinleModel>> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    main_list = response.body();
                    if (main_list != null && !main_list.isEmpty()) {
                        adapter = new RadyoDinleAdapter(main_list, RadyoDinleActivity.this);
                        recyclerView.setAdapter(adapter);

                        // Cache the data
                        dataCache.setCachedData(main_list);
                    } else {
                        handleNullResponse();
                    }
                } else {
                    handleUnsuccessfulResponse();
                    redirectYonlendir();
                }
            }

            @Override
            public void onFailure(Call<List<RadyoDinleModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                handleNetworkFailure();
            }
        });
    }

    private void updateUIWithCachedData(List<RadyoDinleModel> cachedData) {
        if (cachedData != null && !cachedData.isEmpty()) {
            adapter = new RadyoDinleAdapter(cachedData, RadyoDinleActivity.this);
            recyclerView.setAdapter(adapter);
        } else {
            // Handle the case where cached data is empty
        }
    }

    private void handleUnsuccessfulResponse() {
        // Handle an unsuccessful response from the server
        // For example, show an error message to the user
//        Toast.makeText(this, "An error occurred while loading data. Please try again later.", Toast.LENGTH_LONG).show();
        Toast.makeText(this, "Veri yüklenirken bir hata oluştu. Lütfen daha sonra tekrar deneyin.", Toast.LENGTH_LONG).show();
        redirectYonlendir();
    }

    private void handleNullResponse() {
        // Handle null response from the server
        // For example, show an error message to the user
//        Toast.makeText(this, "No data available. Please try again later.", Toast.LENGTH_LONG).show();
        Toast.makeText(this, "Veri bulunamadı. Lütfen daha sonra tekrar deneyin.", Toast.LENGTH_LONG).show();

    }

    private void handleNetworkFailure() {
        // Show a message to the user indicating a network failure
//        Toast.makeText(this, "No internet connection. Please check your network settings.", Toast.LENGTH_LONG).show();
        Toast.makeText(this, "İnternet bağlantısı yok. Lütfen ağ ayarlarınızı kontrol edin.", Toast.LENGTH_LONG).show();
    }


    private void redirectYonlendir() {
        // Redirect to FeatureUnderConstructionActivity
        Intent intent = new Intent(RadyoDinleActivity.this, MainActivity.class);
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