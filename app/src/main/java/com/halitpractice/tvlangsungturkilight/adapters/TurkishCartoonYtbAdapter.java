package com.halitpractice.tvlangsungturkilight.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.halitpractice.tvlangsungturkilight.R;
import com.halitpractice.tvlangsungturkilight.TurkishCartoonYtbDetailsActivity;
import com.halitpractice.tvlangsungturkilight.models.TurkishCartoonYtbModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TurkishCartoonYtbAdapter extends RecyclerView.Adapter<TurkishCartoonYtbAdapter.MyViewHolder> {
    private List<TurkishCartoonYtbModel> my_list;
    private Context context;
    private int clickCount = 0; // Track the number of item clicks
    private InterstitialAd mInterstitialAd;

    public TurkishCartoonYtbAdapter(List<TurkishCartoonYtbModel> my_list, Context context) {
        this.my_list = my_list;
        this.context = context;
        loadAds(); // Initialize and load the interstitial ad
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.turkish_cartoon_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final TurkishCartoonYtbModel turkishCartoonYtbModel = my_list.get(position);
        holder.name.setText(turkishCartoonYtbModel.getChannelname());

        // Load the image with Picasso or any other image loading library
        if (turkishCartoonYtbModel.getImage() != null && !turkishCartoonYtbModel.getImage().isEmpty()) {
            Picasso.get().load(turkishCartoonYtbModel.getImage())
                    .error(R.drawable.default_there_is_no_logo) // Set the default image here
                    .into(holder.image);
        } else {
            // If the image URL is empty or null, set the default image
            holder.image.setImageResource(R.drawable.default_there_is_no_logo);
        }

        // Start marquee scrolling for the name TextView
        holder.name.setSelected(true);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCount++;

                if (clickCount >= 3) {
                    showInterstitialAd(holder);
                    resetClickCount(); // Reset the click count after showing the ad
                } else {
                    startDetailsActivity(holder);
                }
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
            //role=itemView.findViewById(R.id.role);
        }
    }

    private void startDetailsActivity(MyViewHolder holder) {
        Intent intent = new Intent(context, TurkishCartoonYtbDetailsActivity.class);
        intent.putExtra("image", my_list.get(holder.getAdapterPosition()).getImage());
        intent.putExtra("channelName", my_list.get(holder.getAdapterPosition()).getChannelname());
        intent.putExtra("videoId", my_list.get(holder.getAdapterPosition()).getVideoid());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private void showInterstitialAd(MyViewHolder holder) {
        if (mInterstitialAd != null) {
            mInterstitialAd.show((Activity) context);
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent();
                    mInterstitialAd = null;
                    startDetailsActivity(holder); // Start the details activity after the ad is dismissed
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

    public void setSearchOperation(List<TurkishCartoonYtbModel> newList) {
        my_list = new ArrayList<>(newList); // Use ArrayList constructor for a shallow copy
        notifyDataSetChanged();
    }
}
