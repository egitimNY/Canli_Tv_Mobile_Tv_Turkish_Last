package com.halitpractice.tvlangsungturkilight.services;

import android.widget.TextView;

import com.halitpractice.tvlangsungturkilight.RestApi.ManagerAll;
import com.halitpractice.tvlangsungturkilight.models.MarqueeTextModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarqueeTextHelperCizgiFilmler {

    public static void fetchAndDisplayMarqueeTextCizgiFilmler(TextView marqueeTextView) {
        Call<MarqueeTextModel> req = ManagerAll.getInstance().getMarqueTextCizgiFilmler();
        req.enqueue(new Callback<MarqueeTextModel>() {
            @Override
            public void onResponse(Call<MarqueeTextModel> call, Response<MarqueeTextModel> response) {
                if (response.isSuccessful()) {
                    MarqueeTextModel marqueeTextResponse = response.body();
                    if (marqueeTextResponse != null) {
                        String marqueeText = marqueeTextResponse.getMarqueeText();

                        // Set the marquee text
                        marqueeTextView.setText(marqueeText);
                        // Enable marquee scrolling
                        marqueeTextView.setSelected(true);
                    } else {
                        // Handle the case where the response body is null
                        // Fetch data from another table or source here
                    }
                } else {
                    // Handle the case where the response is not successful
                    // Fetch data from another table or source here
                }
            }

            @Override
            public void onFailure(Call<MarqueeTextModel> call, Throwable t) {
                // Handle the case where the network request fails
                // Fetch data from another table or source here
            }
        });
    }

}
