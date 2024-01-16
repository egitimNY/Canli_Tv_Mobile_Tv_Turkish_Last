package com.halitpractice.tvlangsungturkilight.adapters;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.halitpractice.tvlangsungturkilight.R;
import com.halitpractice.tvlangsungturkilight.models.YerelTvYonlendirModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class YerelTvYonlendirAdapter extends RecyclerView.Adapter<YerelTvYonlendirAdapter.MyViewHolder> {
    private List<YerelTvYonlendirModel> my_list;
    private Context context;
    private int clickCount = 0; // Track the number of item clicks
    private InterstitialAd mInterstitialAd;

    public YerelTvYonlendirAdapter(List<YerelTvYonlendirModel> my_list, Context context) {
        this.my_list = my_list;
        this.context = context;
        loadAds(); // Initialize and load the interstitial ad
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.yerel_tv_yonlendir_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final YerelTvYonlendirModel yerelTvYonlendirModel = my_list.get(holder.getAdapterPosition()); // Use getAdapterPosition
//        holder.name.setText(chromeTabChannelsModel.getName());

        // Set the name text, and check if it's empty or null
        if (yerelTvYonlendirModel.getName() != null && !yerelTvYonlendirModel.getName().isEmpty()) {
            holder.name.setText(yerelTvYonlendirModel.getName());
            // Set the text color to the default color (e.g., black)
            holder.name.setTextColor(ContextCompat.getColor(context, R.color.defaultChannelColor));
        } else {
            // If the text is empty or null, display "No text available" in red
//            holder.name.setText("No text available");
//            holder.name.setText("Metin mevcut değil");
            holder.name.setText(context.getString(R.string.no_text_available));
            holder.name.setTextColor(ContextCompat.getColor(context, R.color.redChannelColor)); // Set the text color to red
        }

        // Load the image with Picasso or any other image loading library
        if (yerelTvYonlendirModel.getThumbnail() != null && !yerelTvYonlendirModel.getThumbnail().isEmpty()) {
            Picasso.get().load(yerelTvYonlendirModel.getThumbnail())
                    .error(R.drawable.default_there_is_no_logo) // Set the default image here
                    .into(holder.image, new Callback() {
                        @Override
                        public void onSuccess() {
                            // Image loaded successfully
                        }

                        @Override
                        public void onError(Exception e) {
                            // Handle image loading error, e.g., set the default image
                            holder.image.setImageResource(R.drawable.default_there_is_no_logo);
                        }
                    });
        } else {
            // If the image URL is empty or null, set the default image
            holder.image.setImageResource(R.drawable.default_there_is_no_logo);
        }

        // Start marquee scrolling for the name TextView
        holder.name.setSelected(true);

        // Set an OnClickListener to open the Chrome Custom Tab when the channel is clicked
        holder.itemView.setOnClickListener(v -> {
            clickCount++;
            if (clickCount >= 2) {
                // Show interstitial ad
                showInterstitialAd(yerelTvYonlendirModel);
                // Reset the click count (do not proceed to Chrome Custom Tab immediately)
                resetClickCount();
            } else {
                // If click count is less than 4, increment count and proceed as usual
                openChannelInChromeCustomTab(yerelTvYonlendirModel.getLive_url());
            }
        });

    }

    @Override
    public int getItemCount() {
        return my_list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, role;
        RelativeLayout relative;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
        }
    }

    public void setSearchOperation(List<YerelTvYonlendirModel> newList) {
        my_list = new ArrayList<>(newList); // Use ArrayList constructor for shallow copy
        notifyDataSetChanged();
    }

    private void openChannelInChromeCustomTab(String url) {
        if (url != null && !url.isEmpty()) {
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            // Disable the share icon in the Chrome Custom Tab
            builder.setShareState(CustomTabsIntent.SHARE_STATE_OFF);
            // Customize other properties as needed, for example:
            builder.setShowTitle(true);
            // Build the CustomTabsIntent
            CustomTabsIntent customTabsIntent = builder.build();

            try {
                customTabsIntent.launchUrl(context, Uri.parse(url));
            } catch (ActivityNotFoundException e) {
                // Handle the case where Chrome Custom Tabs are not available on the device
                // Open the URL in the default web browser
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                if (webIntent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(webIntent);
                } else {
                    // If no browser is found, you can show a message to the user or take other appropriate action
                    String noBrowserMessage = context.getString(R.string.no_browser_found_message);
                    Toast.makeText(context, noBrowserMessage, Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            // Handle the case where the URL is empty or null
            String urlEmptyOrNullMessage = context.getString(R.string.url_empty_or_null_message);
            Toast.makeText(context, urlEmptyOrNullMessage, Toast.LENGTH_SHORT).show();
        }
    }

    private void showInterstitialAd(YerelTvYonlendirModel model) {
        if (mInterstitialAd != null) {
            mInterstitialAd.show((Activity) context);
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent();
                    mInterstitialAd = null;
                    // Proceed to open Chrome Custom Tab after ad dismissal
                    openChannelInChromeCustomTab(model.getLive_url());
                    // Load a new ad for subsequent interactions
                    loadAds();
                }
            });
        } else {
            // If ad is not available, proceed to open Chrome Custom Tab
            openChannelInChromeCustomTab(model.getLive_url());
            // Load a new ad for subsequent interactions
            loadAds();
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
