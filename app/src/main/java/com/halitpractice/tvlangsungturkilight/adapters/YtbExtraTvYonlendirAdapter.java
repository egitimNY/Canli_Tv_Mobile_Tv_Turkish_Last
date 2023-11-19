package com.halitpractice.tvlangsungturkilight.adapters;

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

import com.halitpractice.tvlangsungturkilight.R;
import com.halitpractice.tvlangsungturkilight.models.YtbExtraTvYonlendirModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class YtbExtraTvYonlendirAdapter extends RecyclerView.Adapter<YtbExtraTvYonlendirAdapter.MyViewHolder> {
    List<YtbExtraTvYonlendirModel> my_list;
    Context context;

    public YtbExtraTvYonlendirAdapter(List<YtbExtraTvYonlendirModel> my_list, Context context) {
        this.my_list = my_list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ytb_extra_tv_yonlendir_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final YtbExtraTvYonlendirModel ytbExtraTvYonlendirModel = my_list.get(holder.getAdapterPosition()); // Use getAdapterPosition
//        holder.name.setText(chromeTabChannelsModel.getName());

        // Set the name text, and check if it's empty or null
        if (ytbExtraTvYonlendirModel.getName() != null && !ytbExtraTvYonlendirModel.getName().isEmpty()) {
            holder.name.setText(ytbExtraTvYonlendirModel.getName());
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
        if (ytbExtraTvYonlendirModel.getThumbnail() != null && !ytbExtraTvYonlendirModel.getThumbnail().isEmpty()) {
            Picasso.get().load(ytbExtraTvYonlendirModel.getThumbnail())
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
        /*
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChannelInChromeCustomTab(turkishLiveTvModel.getLive_url());
            }
        });
        */
        holder.itemView.setOnClickListener(v -> openChannelInChromeCustomTab(ytbExtraTvYonlendirModel.getLive_url()));


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
            // role=itemView.findViewById(R.id.role);
        }
    }


    private void openChannelInChromeCustomTab(String url) {
        if (url != null && !url.isEmpty()) {
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            // Disable the share icon in the Chrome Custom Tab
            builder.setShareState(CustomTabsIntent.SHARE_STATE_OFF);
            // Customize other properties as needed, for example:
//            builder.setToolbarColor(ContextCompat.getColor(context, R.color.white));
            builder.setShowTitle(true);
            // Build the CustomTabsIntent
            CustomTabsIntent customTabsIntent = builder.build();
            try {
                customTabsIntent.launchUrl(context, Uri.parse(url));
            } catch (ActivityNotFoundException e) {
                // Handle the case where Chrome Custom Tabs are not available on the device
                // Open the URL in the default web browser
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                context.startActivity(webIntent);
            }
        } else {
            // Handle the case where the URL is empty or null
            String urlEmptyOrNullMessage = context.getString(R.string.url_empty_or_null_message);
            Toast.makeText(context, urlEmptyOrNullMessage, Toast.LENGTH_SHORT).show();

        }
    }

    public void setSearchOperation(List<YtbExtraTvYonlendirModel> newList){
        my_list=new ArrayList<>();
        my_list.addAll(newList);
        notifyDataSetChanged();
    }

}
