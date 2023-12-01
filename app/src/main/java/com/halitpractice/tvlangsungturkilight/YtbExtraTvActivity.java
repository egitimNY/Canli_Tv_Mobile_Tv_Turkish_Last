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
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.halitpractice.tvlangsungturkilight.RestApi.ManagerAll;
import com.halitpractice.tvlangsungturkilight.adapters.YtbExtraTvAdapter;
import com.halitpractice.tvlangsungturkilight.models.YtbExtraTvModel;
import com.halitpractice.tvlangsungturkilight.services.MarqueeTextHelperYtbExtra;
import com.halitpractice.tvlangsungturkilight.services.YtbExtraTvDataCache;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YtbExtraTvActivity extends AppCompatActivity {

    private SearchView searchView = null;  /////// SearchView codes parts
    private SearchView.OnQueryTextListener queryTextListener;  /////// SearchView codes parts

    private RecyclerView recyclerView;
    private List<YtbExtraTvModel> main_list;
    private YtbExtraTvAdapter adapter;
    private ProgressBar progressBar;

    private YtbExtraTvDataCache dataCache; // Instance of DataCache for caching data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ytb_extra_tv);


        Toolbar toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);

        // Enable the back button in the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Ytb Extra TV");
        }

        TextView marqueeTextView = findViewById(R.id.marqueeTextView);
        MarqueeTextHelperYtbExtra.fetchAndDisplayMarqueeTextYtbExtra(marqueeTextView);

        progressBar = findViewById(R.id.ytbExtraTvProgressBar); // Initialize the ProgressBar
        progressBar.setVisibility(View.GONE); // Initially, set it to GONE

        main_list = new ArrayList<>();
        recyclerView = findViewById(R.id.ytbExtraTvRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        AdView mAdView = findViewById(R.id.adViewYtbExtraTv);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

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

//        fetchData();

        // Initialize the DataCache instance
        dataCache = YtbExtraTvDataCache.getInstance();

        // Check for cached data and update UI
        List<YtbExtraTvModel> cachedData = dataCache.getCachedData();

        if (cachedData != null && !cachedData.isEmpty()) {
            updateUIWithCachedData(cachedData);
        } else {
            // Data is not cached, fetch it from the network
            fetchData();
        }

    }

    private void fetchData() {
        progressBar.setVisibility(View.VISIBLE); // Show the ProgressBar
        Call<List<YtbExtraTvModel>> req = ManagerAll.getInstance().ytbExtraTvFetch();
        req.enqueue(new Callback<List<YtbExtraTvModel>>() {
            @Override
            public void onResponse(Call<List<YtbExtraTvModel>> call, Response<List<YtbExtraTvModel>> response) {
                if (response.isSuccessful()) {

                    main_list = response.body();

                    if (main_list != null && !main_list.isEmpty()) {
                        adapter = new YtbExtraTvAdapter(main_list, YtbExtraTvActivity.this);
                        recyclerView.setAdapter(adapter);

                        // Cache the data
                        dataCache.setCachedData(main_list);
                        progressBar.setVisibility(View.GONE); // Hide the ProgressBar after data is loaded

                    } else {
                        handleNullResponse();
                    }
                } else {
                    handleUnsuccessfulResponse();
                }
            }

            @Override
            public void onFailure(Call<List<YtbExtraTvModel>> call, Throwable t) {
                handleNetworkFailure();
                t.printStackTrace();
                progressBar.setVisibility(View.GONE); // Hide the ProgressBar in case of failure
            }
        });
    }

    private void updateUIWithCachedData(List<YtbExtraTvModel> cachedData) {
        if (cachedData != null && !cachedData.isEmpty()) {
            adapter = new YtbExtraTvAdapter(cachedData, YtbExtraTvActivity.this);
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
        Intent intent = new Intent(YtbExtraTvActivity.this, YtbExtraTvYonlendirActivity.class);
        startActivity(intent);
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
                    List<YtbExtraTvModel> myList = new ArrayList<>();

                    List<YtbExtraTvModel> cachedData = dataCache.getCachedData();

                    if (cachedData != null) {
                        for (YtbExtraTvModel model : cachedData) {
                            if (model != null && model.getName() != null) {
                                String itemName = model.getName().toLowerCase();

                                if (itemName.contains(newText)) {
                                    myList.add(model);
                                }
                            }
                        }
                    }

                    if (adapter != null) {
                        adapter.setSearchOperation(myList);
                    } else {
                        adapter = new YtbExtraTvAdapter(myList, YtbExtraTvActivity.this);
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