package com.halitpractice.tvlangsungturkilight.activities;

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
import com.halitpractice.tvlangsungturkilight.MainActivity;
import com.halitpractice.tvlangsungturkilight.R;
import com.halitpractice.tvlangsungturkilight.RestApi.ManagerAll;
import com.halitpractice.tvlangsungturkilight.adapters.DunyaTvAdapter;
import com.halitpractice.tvlangsungturkilight.models.YerelTvModel;
import com.halitpractice.tvlangsungturkilight.services.ChromeInstallDialogHelper;
import com.halitpractice.tvlangsungturkilight.services.DunyaTvDataCache;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DunyaTvActivity extends AppCompatActivity {

    private SearchView searchView = null;  /////// SearchView codes parts
    private SearchView.OnQueryTextListener queryTextListener;  /////// SearchView codes parts

    private RecyclerView recyclerView;
    private List<YerelTvModel> main_list;
    private DunyaTvAdapter adapter;
    private ProgressBar progressBar;

    private DunyaTvDataCache dataCache; // Instance of DataCache for caching data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yerel_tv);

        Toolbar toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);

        // Enable the back button in the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Dünya Tv'leri Hepsi");
        }

//        TextView marqueeTextView = findViewById(R.id.marqueeTextView);
//        MarqueeTextAdsHelper.fetchAndDisplayMarqueeTextAds(marqueeTextView);

        progressBar = findViewById(R.id.yerelTvProgressBar); // Initialize the ProgressBar
        progressBar.setVisibility(View.GONE); // Initially, set it to GONE

        main_list = new ArrayList<>();
        recyclerView = findViewById(R.id.yerelTvRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        AdView mAdView = findViewById(R.id.adViewIndiaWorldTv);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        ImageView closedBtn = findViewById(R.id.closeBtnIndiaWorldTv);
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
        dataCache = DunyaTvDataCache.getInstance();

        // Check for cached data and update UI
        List<YerelTvModel> cachedData = dataCache.getCachedData();

        if (cachedData != null && !cachedData.isEmpty()) {
            updateUIWithCachedData(cachedData);
        } else {
            // Data is not cached, fetch it from the network
            fetchData();
        }

        // Show the install Chrome dialog
        ChromeInstallDialogHelper.showInstallChromeDialog(this);

    }

    private void fetchData() {
        progressBar.setVisibility(View.VISIBLE); // Show the ProgressBar
        Call<List<YerelTvModel>> req = ManagerAll.getInstance().dunyaTvTvFetch();
        req.enqueue(new Callback<List<YerelTvModel>>() {
            @Override
            public void onResponse(Call<List<YerelTvModel>> call, Response<List<YerelTvModel>> response) {
                if (response.isSuccessful()) {

                    main_list = response.body();

                    if (main_list != null && !main_list.isEmpty()) {
                        adapter = new DunyaTvAdapter(main_list, DunyaTvActivity.this);
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
            public void onFailure(Call<List<YerelTvModel>> call, Throwable t) {
                handleNetworkFailure();
                t.printStackTrace();
                progressBar.setVisibility(View.GONE); // Hide the ProgressBar in case of failure
            }
        });
    }

    private void updateUIWithCachedData(List<YerelTvModel> cachedData) {
        if (cachedData != null && !cachedData.isEmpty()) {
            adapter = new DunyaTvAdapter(cachedData, DunyaTvActivity.this);
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
        Intent intent = new Intent(DunyaTvActivity.this, DunyaTvYonlendirActivity.class);
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
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    newText = newText.toLowerCase();
                    List<YerelTvModel> myList = new ArrayList<>();

                    List<YerelTvModel> cachedData = dataCache.getCachedData();

                    if (cachedData != null) {
                        for (YerelTvModel model : cachedData) {
                            if (model != null && model.getName() != null) {
                                String itemName = model.getName().toLowerCase();

                                if (itemName.contains(newText))
                                    myList.add(model);
                            }
                        }
                    }

                    if (adapter != null) {
                        adapter.setSearchOperation(myList);
                    } else {
                        adapter = new DunyaTvAdapter(myList, DunyaTvActivity.this);
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