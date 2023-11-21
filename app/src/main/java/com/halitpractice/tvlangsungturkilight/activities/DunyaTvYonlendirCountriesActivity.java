package com.halitpractice.tvlangsungturkilight.activities;

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
import com.halitpractice.tvlangsungturkilight.MainActivity;
import com.halitpractice.tvlangsungturkilight.R;
import com.halitpractice.tvlangsungturkilight.RestApi.ManagerAll;
import com.halitpractice.tvlangsungturkilight.adapters.DunyaTvYonlendirCountriesAdapter;
import com.halitpractice.tvlangsungturkilight.models.YerelTvYonlendirCategoryModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DunyaTvYonlendirCountriesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<YerelTvYonlendirCategoryModel> main_list;
    private DunyaTvYonlendirCountriesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dunya_tv_yonlendir_countries);


        Toolbar toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);

        // Enable the back button in the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Dünya TV'leri Kategori");
        }

        ImageView homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(v -> {
            // Handle the home button click here
            Intent homeIntent = new Intent(DunyaTvYonlendirCountriesActivity.this, MainActivity.class);
            startActivity(homeIntent);
            finish(); // Close the current activity
        });

        main_list = new ArrayList<>();
        recyclerView = findViewById(R.id.dunya_tv_countries_yonlendir_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AdView mAdView = findViewById(R.id.adViewDunyaTvYonlendirCountries);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        ImageView closedBtn = findViewById(R.id.closeBtnDunyaTvYonlendirCountries);
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

        Call<List<YerelTvYonlendirCategoryModel>> req = ManagerAll.getInstance().dunyaTvYonlendirCountriesFetch();
        req.enqueue(new Callback<List<YerelTvYonlendirCategoryModel>>() {
            @Override
            public void onResponse(Call<List<YerelTvYonlendirCategoryModel>> call, Response<List<YerelTvYonlendirCategoryModel>> response) {

                if (response.isSuccessful()) {

                    main_list = response.body();

                    if (main_list != null && !main_list.isEmpty()) {
                        adapter = new DunyaTvYonlendirCountriesAdapter(main_list, DunyaTvYonlendirCountriesActivity.this);
                        recyclerView.setAdapter(adapter);

                    } else {
                        handleNullResponse();
                    }
                } else {
                    handleUnsuccessfulResponse();
                }
            }

            @Override
            public void onFailure(Call<List<YerelTvYonlendirCategoryModel>> call, Throwable t) {
                handleNetworkFailure();
            }
        });
    }

    private void handleUnsuccessfulResponse() {
        // Handle an unsuccessful response from the server
        // For example, show an error message to the user
//        Toast.makeText(this, "An error occurred while loading data. Please try again later.", Toast.LENGTH_LONG).show();
        Toast.makeText(this, "Veri yüklenirken bir hata oluştu. Lütfen daha sonra tekrar deneyin.", Toast.LENGTH_LONG).show();
        // You can also navigate to a different activity or take other appropriate actions here.
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
        Intent intent = new Intent(DunyaTvYonlendirCountriesActivity.this, MainActivity.class);
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