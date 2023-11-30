package com.halitpractice.tvlangsungturkilight;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.halitpractice.tvlangsungturkilight.RestApi.ManagerAll;
import com.halitpractice.tvlangsungturkilight.adapters.TurkishCartoonYtbAdapter;
import com.halitpractice.tvlangsungturkilight.models.TurkishCartoonYtbModel;
import com.halitpractice.tvlangsungturkilight.services.TurkishCartoonYoutubeDataCache;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TurkishCartoonYtbActivity extends AppCompatActivity {

    private SearchView searchView = null;  /////// SearchView codes parts
    private SearchView.OnQueryTextListener queryTextListener;  /////// SearchView codes parts

    private RecyclerView recyclerView;
    private List<TurkishCartoonYtbModel> main_list;
    private TurkishCartoonYtbAdapter adapter;

    private ProgressBar progressBar;

    private TurkishCartoonYoutubeDataCache dataCache; // Instance of DataCache for caching data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turkish_cartoon_ytb);

        // Find and set the Toolbar as the action bar
        Toolbar toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Çizgi Filmler");
        }

        progressBar = findViewById(R.id.indiaCartoonYtbProgressBar); // Initialize the ProgressBar
        progressBar.setVisibility(View.GONE); // Initially, set it to GONE

//        TextView marqueeTextView = findViewById(R.id.marqueeTextView);
//        MarqueeTextAdsHelper.fetchAndDisplayMarqueeTextAds(marqueeTextView);

        // Enable the back button in the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back); // Replace with your custom button icon

        main_list = new ArrayList<>();
        recyclerView = findViewById(R.id.cizgiFilmRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AdView mAdView = findViewById(R.id.adViewIndiaLiveTvCartoonYoutube);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        ImageView closedBtn = findViewById(R.id.closeBtnIndiaLiveTvCartoonYoutube);
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
        dataCache = TurkishCartoonYoutubeDataCache.getInstance();

        // Check if there is cached data
        List<TurkishCartoonYtbModel> cachedData = dataCache.getCachedData();

        if (cachedData != null && !cachedData.isEmpty()) {
            // Use cached data to update the UI
            updateUIWithCachedData(cachedData);
        } else {
            // Data is not cached, fetch it from the network
            fetchData();
        }

    }

    private void fetchData() {
        progressBar.setVisibility(View.VISIBLE); // Show the ProgressBar
        Call<List<TurkishCartoonYtbModel>> req = ManagerAll.getInstance().getIndoCartoonTv();
        req.enqueue(new Callback<List<TurkishCartoonYtbModel>>() {
            @Override
            public void onResponse(Call<List<TurkishCartoonYtbModel>> call, Response<List<TurkishCartoonYtbModel>> response) {
                if (response.isSuccessful()) {
                    main_list = response.body();
                    if (main_list != null && !main_list.isEmpty()) {
                        adapter = new TurkishCartoonYtbAdapter(main_list, TurkishCartoonYtbActivity.this);
                        recyclerView.setAdapter(adapter);

                        // Cache the data
                        dataCache.setCachedData(main_list);
                    } else {
                        handleNullResponse(); // Handle null response from the server
                    }
                } else {
                    handleUnsuccessfulResponse(); // Handle an unsuccessful response from the server
                }
                progressBar.setVisibility(View.GONE); // Hide the ProgressBar after data handling
            }

            @Override
            public void onFailure(Call<List<TurkishCartoonYtbModel>> call, Throwable t) {
                handleNetworkFailure(); // Handle network failure
                t.printStackTrace();
                progressBar.setVisibility(View.GONE); // Hide the ProgressBar in case of failure
            }
        });
    }

    // Existing helper methods for error handling

    private void handleUnsuccessfulResponse() {
        // Handle an unsuccessful response from the server
//        Toast.makeText(this, "An error occurred while loading data. Please try again later.", Toast.LENGTH_LONG).show();
        redirectYonlendir(); // Redirect to a different activity or take appropriate action
    }

    private void handleNullResponse() {
        // Handle null response from the server
//        Toast.makeText(this, "No data available. Please try again later.", Toast.LENGTH_LONG).show();
    }

    private void handleNetworkFailure() {
        // Show a message to the user indicating a network failure
//        Toast.makeText(this, "No internet connection. Please check your network settings.", Toast.LENGTH_LONG).show();
    }

    private void redirectYonlendir() {
        // Redirect to FeatureUnderConstructionActivity or a specific activity
        Intent intent = new Intent(TurkishCartoonYtbActivity.this, TurkishCartoonYtbRedirectActivity.class);
        startActivity(intent);
    }

    private void updateUIWithCachedData(List<TurkishCartoonYtbModel> cachedData) {
        if (cachedData != null && !cachedData.isEmpty()) {
            adapter = new TurkishCartoonYtbAdapter(cachedData, TurkishCartoonYtbActivity.this);
            recyclerView.setAdapter(adapter);
        } else {
            // Handle the case where cached data is empty
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }

        if (searchView != null && searchManager != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    newText = newText.toLowerCase();
                    List<TurkishCartoonYtbModel> myList = new ArrayList<>();

                    List<TurkishCartoonYtbModel> cachedData = dataCache.getCachedData();

                    if (cachedData != null) {
                        for (TurkishCartoonYtbModel model : cachedData) {
                            if (model != null && model.getChannelname() != null) {
                                String itemName = model.getChannelname().toLowerCase();

                                if (itemName.contains(newText)) {
                                    myList.add(model);
                                }
                            }
                        }
                    }

                    if (adapter != null) {
                        adapter.setSearchOperation(myList);
                    } else {
                        adapter = new TurkishCartoonYtbAdapter(myList, TurkishCartoonYtbActivity.this);
                        recyclerView.setAdapter(adapter);
                    }

                    return false;
                }
            });

            // Programmatically set the left margin of the SearchView
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            int marginInDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
            params.setMargins(marginInDp, 0, 0, 0);
            searchView.setLayoutParams(params);
        }

        return true;
    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(TurkishCartoonYtbActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // Close the current activity
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