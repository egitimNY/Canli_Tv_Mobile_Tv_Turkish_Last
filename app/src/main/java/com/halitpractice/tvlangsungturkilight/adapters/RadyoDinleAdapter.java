package com.halitpractice.tvlangsungturkilight.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;

import com.halitpractice.tvlangsungturkilight.R;
import com.halitpractice.tvlangsungturkilight.models.RadyoDinleModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RadyoDinleAdapter extends RecyclerView.Adapter<RadyoDinleAdapter.MyViewHolder> {
    List<RadyoDinleModel> my_list;
    Context context;

    public RadyoDinleAdapter(List<RadyoDinleModel> my_list, Context context) {
        this.my_list = my_list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.radyo_dinle_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final RadyoDinleModel radyoDinleModel = my_list.get(position);

        // Check if the name is empty or null
        if (radyoDinleModel.getChannelname() != null && !radyoDinleModel.getChannelname().isEmpty()) {
            holder.name.setText(radyoDinleModel.getChannelname());
        } else {
            // Set a default name if it's empty or null
//            holder.name.setText("Unknown");
            holder.name.setText(R.string.no_text_available);
        }

        // Load the image with Picasso or any other image loading library
        if (radyoDinleModel.getImage() != null && !radyoDinleModel.getImage().isEmpty()) {
            Picasso.get()
                    .load(radyoDinleModel.getImage())
                    .error(R.drawable.default_there_is_no_logo) // Set the default image here
                    .into(holder.image, new Callback() {
                        @Override
                        public void onSuccess() {
                            // Image loaded successfully
                        }

                        @Override
                        public void onError(Exception e) {
                            // Handle image loading error here
                            holder.image.setImageResource(R.drawable.default_there_is_no_logo);
                        }
                    });
        } else {
            // If the image URL is empty or null, set the default image
            holder.image.setImageResource(R.drawable.default_there_is_no_logo);
        }

        // Start marquee scrolling for the name TextView
        holder.name.setSelected(true);

        holder.itemView.setOnClickListener(v -> {
            // Open the URL in a Chrome Custom Tab
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            CustomTabsIntent customTabsIntent = builder.build();

            try {
                customTabsIntent.launchUrl(context, Uri.parse(radyoDinleModel.getVideoid()));
            } catch (Exception e) {
                // Handle URL launch error
                e.printStackTrace();
                // You can show an error message to the user or take other actions
            }
        });

        /*
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the URL in a Chrome Custom Tab
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();

                try {
                    customTabsIntent.launchUrl(context, Uri.parse(radyoDinleModel.getVideoid()));
                } catch (Exception e) {
                    // Handle URL launch error
                    e.printStackTrace();
                    // You can show an error message to the user or take other actions
                }
            }
        });
        */
    }

    @Override
    public int getItemCount() {
        return my_list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, role;
        RelativeLayout relative;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
        }
    }
}
