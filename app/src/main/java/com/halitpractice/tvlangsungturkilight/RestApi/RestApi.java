package com.halitpractice.tvlangsungturkilight.RestApi;

import com.halitpractice.tvlangsungturkilight.models.FormattedTextModel;
import com.halitpractice.tvlangsungturkilight.models.MarqueeTextModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApi {


    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/marqueeToolBarRetrofit.php")
    Call<MarqueeTextModel> getMarqueText();

    @GET("/indiaLiveTv/retrofitIndiaTV/marqueeToolBarAds.php")
    Call<MarqueeTextModel> getMarqueTextAds();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/fetchDiclaimerYeni.php")
    Call<FormattedTextModel> disclaimerFormatedText();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/underConstructionTR.php")
    Call<FormattedTextModel> underConstructionFormatedText();


}
