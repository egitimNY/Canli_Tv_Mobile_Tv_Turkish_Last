package com.halitpractice.tvlangsungturkilight.adapters;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.halitpractice.tvlangsungturkilight.R;
import com.halitpractice.tvlangsungturkilight.YtbExtraTvDetailsActivity;
import com.halitpractice.tvlangsungturkilight.models.YtbExtraTvModel;

import java.util.ArrayList;
import java.util.List;

public class YtbExtraTvAdapter extends RecyclerView.Adapter<YtbExtraTvAdapter.MyViewHolder> {
    private List<YtbExtraTvModel> my_list;
    private Context context;
    private int clickCount = 0; // Track the number of item clicks
    private InterstitialAd mInterstitialAd;

    public YtbExtraTvAdapter(List<YtbExtraTvModel> my_list, Context context) {
        this.my_list = my_list;
        this.context = context;
        loadAds(); // Initialize and load the interstitial ad
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ytb_extra_tv_ulkeler_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final YtbExtraTvModel ytbExtraTvModel = my_list.get(position);
        holder.name.setText(ytbExtraTvModel.getName());

        // Set the country name with the "CountryName: " prefix
        String countryName = ytbExtraTvModel.getCountryname();
        if (countryName != null && !countryName.isEmpty()) {
            String countryText = "Ülke: " + countryName;
            holder.country.setText(countryText);
            holder.country.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
        } else {
            // If the country name is null or empty, set a default text and change text color
            holder.country.setText("Ülke: ismi yazılmamış");
            holder.country.setTextColor(ContextCompat.getColor(context, R.color.redChannelColor));
            // You can also choose to hide the TextView or set a different message based on your app logic
        }

        // Load the image with Glide, resizing it to 150x150 pixels
        if (ytbExtraTvModel.getThumbnail() != null && !ytbExtraTvModel.getThumbnail().isEmpty()) {
            Glide.with(context)
                    .load(ytbExtraTvModel.getThumbnail())
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(R.drawable.default_there_is_no_logo)
                    .error(R.drawable.default_there_is_no_logo)
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                    .into(holder.image);
        } else {
            // If the image URL is empty or null, set the default image
            holder.image.setImageResource(R.drawable.default_there_is_no_logo);
        }

        // Start marquee scrolling for the name TextView
        holder.name.setSelected(true);

        holder.itemView.setOnClickListener(v -> {
            try {
                clickCount++;

                if (clickCount >= 3) {
                    showInterstitialAd(holder);
                    resetClickCount(); // Reset the click count after showing the ad
                } else {
                    startDetailsActivity(holder);
                }

            } catch (ActivityNotFoundException e) {
                // Handle activity not found error
                Log.e("StartActivityError", "Error starting activity: " + e.getMessage());
                Toast.makeText(v.getContext(), "Error starting activity", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startDetailsActivity(MyViewHolder holder) {
        Intent i = new Intent(holder.itemView.getContext(), YtbExtraTvDetailsActivity.class);
        i.putExtra("channel", my_list.get(holder.getAdapterPosition()));
        i.putExtra("live_url", my_list.get(holder.getAdapterPosition()).getLive_url()); // Use the variable directly
        holder.itemView.getContext().startActivity(i);
    }

    @Override
    public int getItemCount() {
        return my_list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, country;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            country = itemView.findViewById(R.id.country);
        }
    }

    public void setSearchOperation(List<YtbExtraTvModel> newList) {
        my_list = new ArrayList<>(newList); // Use ArrayList constructor for shallow copy
        notifyDataSetChanged();
    }

    private void showInterstitialAd(MyViewHolder holder) {
        if (mInterstitialAd != null) {
            mInterstitialAd.show((Activity) context);
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent();
                    mInterstitialAd = null;
                    startDetailsActivity(holder); // Start activity after the ad is dismissed
                    resetClickCount(); // Reset the click count
                    loadAds(); // Reload the ad for subsequent interactions
                }
            });
        }
    }

    private void loadAds() {
        MobileAds.initialize(context, initializationStatus -> {
            // AdMob initialization is complete.
        });

        // Load the Ad Unit ID from strings.xml
        String adUnitId = context.getString(R.string.admob_interstitial_ad_unit_id);
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(context, adUnitId, adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                mInterstitialAd = interstitialAd;
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                mInterstitialAd = null;
            }
        });
    }

    private void resetClickCount() {
        clickCount = 0;
    }

}
