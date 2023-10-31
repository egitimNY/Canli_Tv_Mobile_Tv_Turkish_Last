package com.halitpractice.tvlangsungturkilight.RestApi;

import com.halitpractice.tvlangsungturkilight.models.FormattedTextModel;
import com.halitpractice.tvlangsungturkilight.models.MarqueeTextModel;
import com.halitpractice.tvlangsungturkilight.models.UlusalTvCategoryModel;
import com.halitpractice.tvlangsungturkilight.models.UlusalTvModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApi {


    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/marqueeToolBarRetrofit.php")
    Call<MarqueeTextModel> getMarqueText();

    @GET("/indiaLiveTv/retrofitIndiaTV/marqueeToolBarAds.php")
    Call<MarqueeTextModel> getMarqueTextAds();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/fetchDiclaimerYeni.php")
    Call<FormattedTextModel> disclaimerFormatedText();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/underConstructionTR.php")
    Call<FormattedTextModel> underConstructionFormatedText();


    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/TurkishLiveTv.php")
    Call<List<UlusalTvModel>> chromeTab();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/TurkishLiveTvCategories.php")
    Call<List<UlusalTvCategoryModel>> turkishLiveTvCategory();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/TurkishLiveTvCategoriesDetails.php")
    Call<List<UlusalTvModel>> turkishLiveTvCategoryDetails(@Query("cat") String category);

}
