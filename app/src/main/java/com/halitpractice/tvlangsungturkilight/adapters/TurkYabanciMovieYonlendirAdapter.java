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
import com.halitpractice.tvlangsungturkilight.models.TurkYabanciMovieYonlendirModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TurkYabanciMovieYonlendirAdapter extends RecyclerView.Adapter<TurkYabanciMovieYonlendirAdapter.MyViewHolder> {
    List<TurkYabanciMovieYonlendirModel> my_list;
    Context context;

    public TurkYabanciMovieYonlendirAdapter(List<TurkYabanciMovieYonlendirModel> my_list, Context context) {
        this.my_list = my_list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.turk_yabanci_movie_yonlendir_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final TurkYabanciMovieYonlendirModel turkYabanciMovieYonlendirModel = my_list.get(position);
        holder.name.setText(turkYabanciMovieYonlendirModel.getChannelname());

        // Load the image with Picasso or any other image loading library
        if (turkYabanciMovieYonlendirModel.getImage() != null && !turkYabanciMovieYonlendirModel.getImage().isEmpty()) {
            Picasso.get().load(turkYabanciMovieYonlendirModel.getImage())
                    .error(R.drawable.default_there_is_no_logo) // Set the default image here
                    .into(holder.image);
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
            customTabsIntent.launchUrl(context, Uri.parse(turkYabanciMovieYonlendirModel.getVideoid()));
        });

        /*
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the URL in a Chrome Custom Tab
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(context, Uri.parse(turkYabanciMovieYonlendirModel.getVideoid()));
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
