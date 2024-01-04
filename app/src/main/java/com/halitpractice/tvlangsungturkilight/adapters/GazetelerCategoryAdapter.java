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
import com.halitpractice.tvlangsungturkilight.activities.GazetelerCategoriesDetailsActivity;
import com.halitpractice.tvlangsungturkilight.models.GazetelerCategoryModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GazetelerCategoryAdapter extends RecyclerView.Adapter<GazetelerCategoryAdapter.MyViewHolder> {
    List<GazetelerCategoryModel> my_list;
    Context context;

    public GazetelerCategoryAdapter(List<GazetelerCategoryModel> my_list, Context context) {
        this.my_list = my_list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gazeteler_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final GazetelerCategoryModel gazetelerCategoryModel = my_list.get(position);
//        holder.name.setText(ytbExtraTvYonlendirCategoryModel.getName());

        // Check if the name is not null or empty
        if (gazetelerCategoryModel.getName() != null && !gazetelerCategoryModel.getName().isEmpty()) {
            holder.name.setText(gazetelerCategoryModel.getName());
        } else {
            // If the name is null or empty, set the default value from string resource
//            holder.name.setText(R.string.ytb_extra_category_not_found);
            holder.name.setText(R.string.kategori_bulunamadı);
            // You can also choose to hide the TextView or set a different message based on your app logic
        }

        // Load the image with Picasso or any other image loading library
        if (gazetelerCategoryModel.getImage_url() != null && !gazetelerCategoryModel.getImage_url().isEmpty()) {
            Picasso.get().load(gazetelerCategoryModel.getImage_url())
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
            Intent i = new Intent(v.getContext(), GazetelerCategoriesDetailsActivity.class);
            int clickedPosition = holder.getAdapterPosition();
            if (clickedPosition != RecyclerView.NO_POSITION) {
                String selectedCategory = my_list.get(clickedPosition).getName(); // Get the category name
                i.putExtra("category", selectedCategory);
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
