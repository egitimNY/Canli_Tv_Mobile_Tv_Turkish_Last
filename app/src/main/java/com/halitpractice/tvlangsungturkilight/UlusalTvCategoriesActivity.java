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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UlusalTvCategoriesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<UlusalTvCategoryModel> main_list;
    private UlusalTvCategoryAdapter adapter;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turkish_live_tv_categories);

        // Find and set the Toolbar as the action bar
        Toolbar toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Ulusal TV'ler Ketegori");
        }

        // Enable the back button in the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back); // Replace with your custom button icon
        }

        progressBar = findViewById(R.id.turkishTvCategoryProgressBar); // Initialize the ProgressBar
        progressBar.setVisibility(View.GONE); // Initially, set it to GONE

        main_list = new ArrayList<>();
        recyclerView = findViewById(R.id.turkishTvCategoryRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AdView mAdView = findViewById(R.id.adViewTurkishLiveTvCategories);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

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

        fetchData();

    }

    private void fetchData() {
        progressBar.setVisibility(View.VISIBLE);

        Call<List<UlusalTvCategoryModel>> req = ManagerAll.getInstance().turkishLiveTvCategoryFetch();
        req.enqueue(new Callback<List<UlusalTvCategoryModel>>() {
            @Override
            public void onResponse(Call<List<UlusalTvCategoryModel>> call, Response<List<UlusalTvCategoryModel>> response) {
                progressBar.setVisibility(View.GONE); // Always hide the ProgressBar

                if (response.isSuccessful()) {

                    main_list = response.body();

                    if (main_list != null && !main_list.isEmpty()) {
                        adapter = new UlusalTvCategoryAdapter(main_list, UlusalTvCategoriesActivity.this);
                        recyclerView.setAdapter(adapter);

                        progressBar.setVisibility(View.GONE); // Hide the ProgressBar after data is loaded

                    } else {
                        handleEmptyResponse();
                    }
                } else {
                    handleUnsuccessfulResponse();
                }
            }

            @Override
            public void onFailure(Call<List<UlusalTvCategoryModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE); // Always hide the ProgressBar
                handleNetworkFailure();
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
        Intent intent = new Intent(UlusalTvCategoriesActivity.this, MainActivity.class);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(UlusalTvCategoriesActivity.this, MainActivity.class);
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