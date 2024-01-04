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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
import com.halitpractice.tvlangsungturkilight.adapters.GazetelerAdapter;
import com.halitpractice.tvlangsungturkilight.models.GazetelerModel;
import com.halitpractice.tvlangsungturkilight.services.ChromeInstallDialogHelper;
import com.halitpractice.tvlangsungturkilight.services.MarqueeTextHelperGazeteler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GazetelerActivity extends AppCompatActivity {

    private SearchView searchView = null;  /////// SearchView codes parts
    private SearchView.OnQueryTextListener queryTextListener;  /////// SearchView codes parts

    private RecyclerView recyclerView;
    private List<GazetelerModel> main_list;
    private GazetelerAdapter adapter;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gazeteler);


        Toolbar toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);

        // Enable the back button in the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Gazeteler");
        }

        TextView marqueeTextView = findViewById(R.id.marqueeTextView);
        MarqueeTextHelperGazeteler.fetchAndDisplayMarqueeTextGazeteler(marqueeTextView);

        progressBar = findViewById(R.id.progressBarGazeteler); // Initialize the ProgressBar
        progressBar.setVisibility(View.GONE); // Initially, set it to GONE

        main_list = new ArrayList<>();
        recyclerView = findViewById(R.id.gazetelerRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchData();

        AdView mAdView = findViewById(R.id.adViewGazeteler);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        /*
        ImageView closedBtn = findViewById(R.id.closeBtnGazeteler);
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

        // Show the install Chrome dialog
        ChromeInstallDialogHelper.showInstallChromeDialog(this);

    }

    private void fetchData() {
        progressBar.setVisibility(View.VISIBLE); // Show the ProgressBar
        Call<List<GazetelerModel>> req = ManagerAll.getInstance().gazetelerFetch();
        req.enqueue(new Callback<List<GazetelerModel>>() {
            @Override
            public void onResponse(Call<List<GazetelerModel>> call, Response<List<GazetelerModel>> response) {
                if (response.isSuccessful()) {

                    main_list = response.body();

                    if (main_list != null && !main_list.isEmpty()) {
                        adapter = new GazetelerAdapter(main_list, GazetelerActivity.this);
                        recyclerView.setAdapter(adapter);

                        progressBar.setVisibility(View.GONE); // Hide the ProgressBar after data is loaded

                    } else {
                        handleNullResponse();
                    }
                } else {
                    handleUnsuccessfulResponse();
                }
            }

            @Override
            public void onFailure(Call<List<GazetelerModel>> call, Throwable t) {
                handleNetworkFailure(t);
                t.printStackTrace(); // Print the error details for debugging
                progressBar.setVisibility(View.GONE); // Hide the ProgressBar in case of failure
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
        Intent intent = new Intent(GazetelerActivity.this, MainActivity.class);
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

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    newText = newText.toLowerCase();
                    List<GazetelerModel> myList = new ArrayList<>();

                    for (GazetelerModel model : main_list) {
                        String javaSoru = model.getName().toLowerCase();

                        if (javaSoru.contains(newText))
                            myList.add(model);
                    }

                    adapter.setSearchOperation(myList);
                    return false;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);

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
