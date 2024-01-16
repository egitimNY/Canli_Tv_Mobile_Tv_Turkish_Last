package com.halitpractice.tvlangsungturkilight.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
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
import com.halitpractice.tvlangsungturkilight.adapters.DunyaTvYonlendirAdapter;
import com.halitpractice.tvlangsungturkilight.models.DunyaTvYonlendirModel;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DunyaTvYonlendirLanguageDetailsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<DunyaTvYonlendirModel> main_list;
    private DunyaTvYonlendirAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dunya_tv_yonlendir_language_details);

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
            Intent homeIntent = new Intent(DunyaTvYonlendirLanguageDetailsActivity.this, MainActivity.class);
            startActivity(homeIntent);
            finish(); // Close the current activity
        });

        recyclerView = findViewById(R.id.worldTvYonlendirLanguageDetailsRecyclerView); // Initialize the RecyclerView
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String selectedCategory = getIntent().getStringExtra("dunyaTvYonlendirCategory");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            if (selectedCategory != null) {
                actionBar.setTitle("DİL: " + selectedCategory);
                fetchData(selectedCategory);
            } else {
                actionBar.setTitle("DİL: ");
            }
        }

        AdView mAdView = findViewById(R.id.adViewWorldTvYonlendirLanguageDetails);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        /*
        ImageView closedBtn = findViewById(R.id.closeBtnWorldTvYonlendirLanguageDetails);
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
        Call<List<DunyaTvYonlendirModel>> req = ManagerAll.getInstance().getDunyaTvByLanguageYonlendirFetch(selectedCategory);
        req.enqueue(new Callback<List<DunyaTvYonlendirModel>>() {
            @Override
            public void onResponse(Call<List<DunyaTvYonlendirModel>> call, Response<List<DunyaTvYonlendirModel>> response) {

                if (response.isSuccessful()) {
                    main_list = response.body();
                    if (main_list != null && !main_list.isEmpty()) {
                        adapter = new DunyaTvYonlendirAdapter(main_list, DunyaTvYonlendirLanguageDetailsActivity.this);
                        recyclerView.setAdapter(adapter);
                    } else {
                        handleNullResponse();
                    }
                } else {
                    handleUnsuccessfulResponse();
                }
            }

            @Override
            public void onFailure(Call<List<DunyaTvYonlendirModel>> call, Throwable t) {
                handleNetworkFailure(t);
                t.printStackTrace(); // Print the error details for debugging
            }
        });
    }

    private void handleUnsuccessfulResponse() {
        // Handle an unsuccessful response from the server
        // For example, show an error message to the user
        Toast.makeText(this, "Veri yüklenirken bir hata oluştu. Lütfen daha sonra tekrar deneyin.", Toast.LENGTH_LONG).show();
//        Toast.makeText(this, "An error occurred while loading data. Please try again later.", Toast.LENGTH_LONG).show();
        redirectYonlendir();
    }

    private void handleNullResponse() {
        // Handle null response from the server
        // For example, show an error message to the user
        Toast.makeText(this, "Veri bulunamadı. Lütfen daha sonra tekrar deneyin.", Toast.LENGTH_LONG).show();
//        Toast.makeText(this, "Data not found. Please try again later.", Toast.LENGTH_LONG).show();
    }

    private void handleNetworkFailure(Throwable t) {
        if (t instanceof IOException) {
            // Bu, ağ ile ilgili bir sorun (örneğin, sunucu ulaşılamaz) durumudur.
            Toast.makeText(this, "Sunucu bağlantı sorunu. Lütfen daha sonra tekrar deneyin.", Toast.LENGTH_LONG).show();
//            Toast.makeText(this, "Server connection issue. Please try again later.", Toast.LENGTH_LONG).show();
        } else {
            // Bu, ağ ile ilgili olmayan bir sorun (örneğin, internet bağlantısı yok) durumudur.
            Toast.makeText(this, "İnternet bağlantısı yok. Lütfen ağ ayarlarınızı kontrol edin.", Toast.LENGTH_LONG).show();
//            Toast.makeText(this, "There is no internet connection. Please check your network settings.", Toast.LENGTH_LONG).show();
        }
    }

    private void redirectYonlendir() {
        // Redirect to FeatureUnderConstructionActivity
        Intent intent = new Intent(DunyaTvYonlendirLanguageDetailsActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, DunyaTvYonlendirLanguageActivity.class);
        startActivity(intent);
        finish(); // Close the current activity

        // To prevent going back to TurkishLiveTvActivity on the next back press
//        moveTaskToBack(false); // This line ensures the app goes to the background and doesn't go back to the previous activity
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Handle the back button click here, navigate to ThirdActivity
            Intent intent = new Intent(this, DunyaTvYonlendirLanguageActivity.class);
            startActivity(intent);
            finish(); // Finish the current activity if you don't want to return to it
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
