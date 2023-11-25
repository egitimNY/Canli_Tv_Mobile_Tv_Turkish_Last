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
import com.halitpractice.tvlangsungturkilight.YtbExtraTvCountriesDetailsActivity;
import com.halitpractice.tvlangsungturkilight.models.YtbExtraTvCategoryModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class YtbExtraTvCountriesAdapter extends RecyclerView.Adapter<YtbExtraTvCountriesAdapter.MyViewHolder> {
    List<YtbExtraTvCategoryModel> my_list;
    Context context;

    public YtbExtraTvCountriesAdapter(List<YtbExtraTvCategoryModel> my_list, Context context) {
        this.my_list = my_list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ytb_extra_tv_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final YtbExtraTvCategoryModel ytbExtraTvCategoryModel = my_list.get(position);
        holder.name.setText(ytbExtraTvCategoryModel.getName());

        // Load the image with Picasso or any other image loading library
        if (ytbExtraTvCategoryModel.getImage_url() != null && !ytbExtraTvCategoryModel.getImage_url().isEmpty()) {
            Picasso.get().load(ytbExtraTvCategoryModel.getImage_url())
                    .error(R.drawable.default_there_is_no_logo) // Set the default image here
                    .into(holder.image);
        } else {
            // If the image URL is empty or null, set the default image
            holder.image.setImageResource(R.drawable.default_there_is_no_logo);
        }

        // Start marquee scrolling for the name TextView
        holder.name.setSelected(true);


        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(v.getContext(), YtbExtraTvCountriesDetailsActivity.class);
            int clickedPosition = holder.getAdapterPosition();
            String selectedCategory = my_list.get(clickedPosition).getName(); // Get the category name
            i.putExtra("ytbExtraTvCountries", selectedCategory);
            v.getContext().startActivity(i);
        });


    }

    @Override
    public int getItemCount() {
        return my_list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name,role;
        RelativeLayout relative;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);
//            role=itemView.findViewById(R.id.role);
        }
    }

}
