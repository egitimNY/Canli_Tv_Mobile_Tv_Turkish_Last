package com.halitpractice.tvlangsungturkilight;

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
import com.halitpractice.tvlangsungturkilight.RestApi.ManagerAll;
import com.halitpractice.tvlangsungturkilight.adapters.YtbExtraTvYonlendirAdapter;
import com.halitpractice.tvlangsungturkilight.models.YtbExtraTvYonlendirModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YtbExtraTvYonlendirCategoriesDetailsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<YtbExtraTvYonlendirModel> main_list;
    private YtbExtraTvYonlendirAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ytb_extra_tv_yonlendir_categories_details);

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
            Intent homeIntent = new Intent(YtbExtraTvYonlendirCategoriesDetailsActivity.this, MainActivity.class);
            startActivity(homeIntent);
            finish(); // Close the current activity
        });

        recyclerView = findViewById(R.id.ytb_extra_tv_yonlendir_details_slider_list); // Initialize the RecyclerView
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String selectedCategory = getIntent().getStringExtra("category");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            if (selectedCategory != null) {
                actionBar.setTitle("Kategori: " + selectedCategory);
                fetchData(selectedCategory);
            } else {
                actionBar.setTitle("Kategori: ");
            }
        }


        AdView mAdView = findViewById(R.id.adViewYtbExtraTvYonlendirCategoriesDetails);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        /*
        ImageView closedBtn = findViewById(R.id.closeBtnYtbExtraTvYonlendirCategoriesDetails);
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
        Call<List<YtbExtraTvYonlendirModel>> req = ManagerAll.getInstance().getYtbExtraTvByCategoryYonlendirFetch(selectedCategory);
        req.enqueue(new Callback<List<YtbExtraTvYonlendirModel>>() {
            @Override
            public void onResponse(Call<List<YtbExtraTvYonlendirModel>> call, Response<List<YtbExtraTvYonlendirModel>> response) {

                if (response.isSuccessful()) {
                    main_list = response.body();
                    if (main_list != null && !main_list.isEmpty()) {
                        adapter = new YtbExtraTvYonlendirAdapter(main_list, YtbExtraTvYonlendirCategoriesDetailsActivity.this);
                        recyclerView.setAdapter(adapter);
                    } else {
                        handleNullResponse();
                    }
                } else {
                    handleUnsuccessfulResponse();
                }
            }

            @Override
            public void onFailure(Call<List<YtbExtraTvYonlendirModel>> call, Throwable t) {
                handleNetworkFailure();
                t.printStackTrace(); // Print the error details for debugging
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
        Intent intent = new Intent(YtbExtraTvYonlendirCategoriesDetailsActivity.this, MainActivity.class);
        startActivity(intent);
    }



    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, YtbExtraTvYonlendirCategoriesActivity.class);
        startActivity(intent);
        finish(); // Close the current activity

        // To prevent going back to TurkishLiveTvActivity on the next back press
//        moveTaskToBack(false); // This line ensures the app goes to the background and doesn't go back to the previous activity
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Handle the back button click here, navigate to ThirdActivity
            Intent intent = new Intent(this, YtbExtraTvYonlendirCategoriesActivity.class);
            startActivity(intent);
            finish(); // Finish the current activity if you don't want to return to it
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
