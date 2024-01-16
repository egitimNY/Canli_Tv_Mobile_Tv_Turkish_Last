package com.halitpractice.tvlangsungturkilight.adapters;

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

import com.halitpractice.tvlangsungturkilight.R;
import com.halitpractice.tvlangsungturkilight.activities.DunyaTvYonlendirLanguageDetailsActivity;
import com.halitpractice.tvlangsungturkilight.models.DunyaTvYonlendirLanguageModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DunyaTvYonlendirLanguageAdapter extends RecyclerView.Adapter<DunyaTvYonlendirLanguageAdapter.MyViewHolder> {
    List<DunyaTvYonlendirLanguageModel> my_list;
    Context context;

    public DunyaTvYonlendirLanguageAdapter(List<DunyaTvYonlendirLanguageModel> my_list, Context context) {
        this.my_list = my_list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dunya_tv_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final DunyaTvYonlendirLanguageModel dunyaTvYonlendirLanguageModel = my_list.get(position);
        holder.name.setText(dunyaTvYonlendirLanguageModel.getName());


        // Load the image with Picasso or any other image loading library
        if (dunyaTvYonlendirLanguageModel.getImage_url() != null && !dunyaTvYonlendirLanguageModel.getImage_url().isEmpty()) {
            Picasso.get().load(dunyaTvYonlendirLanguageModel.getImage_url())
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


        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(v.getContext(), DunyaTvYonlendirLanguageDetailsActivity.class);
            int clickedPosition = holder.getAdapterPosition();
            if (clickedPosition != RecyclerView.NO_POSITION) {
                String selectedCategory = my_list.get(clickedPosition).getName(); // Get the category name
                i.putExtra("dunyaTvYonlendirCategory", selectedCategory);
                v.getContext().startActivity(i);
            }
        });


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
            // role=itemView.findViewById(R.id.role);
        }
    }
}
