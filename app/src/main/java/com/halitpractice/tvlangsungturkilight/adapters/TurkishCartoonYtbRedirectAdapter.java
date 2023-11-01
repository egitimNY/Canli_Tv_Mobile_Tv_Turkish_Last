package com.halitpractice.tvlangsungturkilight.adapters;

import android.content.ActivityNotFoundException;
import android.content.Context;
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
import androidx.recyclerview.widget.RecyclerView;

import com.halitpractice.tvlangsungturkilight.R;
import com.halitpractice.tvlangsungturkilight.models.TurkishCartoonYtbRedirectModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TurkishCartoonYtbRedirectAdapter extends RecyclerView.Adapter<TurkishCartoonYtbRedirectAdapter.MyViewHolder> {
    List<TurkishCartoonYtbRedirectModel> my_list;
    Context context;

    public TurkishCartoonYtbRedirectAdapter(List<TurkishCartoonYtbRedirectModel> my_list, Context context) {
        this.my_list = my_list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.turkish_cartoon_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final TurkishCartoonYtbRedirectModel turkishCartoonYtbRedirectModel = my_list.get(position);
        holder.name.setText(turkishCartoonYtbRedirectModel.getChannelname());

        // Load the image with Picasso or any other image loading library
        if (turkishCartoonYtbRedirectModel.getImage() != null && !turkishCartoonYtbRedirectModel.getImage().isEmpty()) {
            Picasso.get().load(turkishCartoonYtbRedirectModel.getImage())
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
                String videoUrl = turkishCartoonYtbRedirectModel.getVideoid();
                if (videoUrl != null && !videoUrl.isEmpty()) {
                    try {
                        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                        CustomTabsIntent customTabsIntent = builder.build();
                        customTabsIntent.launchUrl(context, Uri.parse(videoUrl));
                    } catch (ActivityNotFoundException | SecurityException e) {
                        // Catch any exceptions when launching the Chrome Custom Tab
                        Toast.makeText(context, "Failed to open the link", Toast.LENGTH_SHORT).show();
                        e.printStackTrace(); // Log the exception for debugging purposes
                    } catch (Exception e) {
                        Toast.makeText(context, "Failed to open the link", Toast.LENGTH_SHORT).show();
                        e.printStackTrace(); // Log the exception for debugging purposes
                    }
                } else {
                    Toast.makeText(context, "Invalid URL", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return my_list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
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
