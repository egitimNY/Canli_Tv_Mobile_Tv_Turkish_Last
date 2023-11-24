package com.halitpractice.tvlangsungturkilight.RestApi;

import com.halitpractice.tvlangsungturkilight.models.FormattedTextModel;
import com.halitpractice.tvlangsungturkilight.models.GazetelerModel;
import com.halitpractice.tvlangsungturkilight.models.MarqueeTextModel;
import com.halitpractice.tvlangsungturkilight.models.RadyoDinleModel;
import com.halitpractice.tvlangsungturkilight.models.TurkYabanciMovieModel;
import com.halitpractice.tvlangsungturkilight.models.TurkYabanciMovieYonlendirModel;
import com.halitpractice.tvlangsungturkilight.models.TurkishCartoonYtbModel;
import com.halitpractice.tvlangsungturkilight.models.TurkishCartoonYtbRedirectModel;
import com.halitpractice.tvlangsungturkilight.models.UlusalTvCategoryModel;
import com.halitpractice.tvlangsungturkilight.models.UlusalTvModel;
import com.halitpractice.tvlangsungturkilight.models.YerelTvCategoryModel;
import com.halitpractice.tvlangsungturkilight.models.YerelTvModel;
import com.halitpractice.tvlangsungturkilight.models.YerelTvYonlendirCategoryModel;
import com.halitpractice.tvlangsungturkilight.models.YerelTvYonlendirModel;
import com.halitpractice.tvlangsungturkilight.models.YtbExtraTvCategoryModel;
import com.halitpractice.tvlangsungturkilight.models.YtbExtraTvModel;
import com.halitpractice.tvlangsungturkilight.models.YtbExtraTvYonlendirCategoryModel;
import com.halitpractice.tvlangsungturkilight.models.YtbExtraTvYonlendirModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApi {

    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/marqueeTextAds.php")
    Call<MarqueeTextModel> getMarqueText();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/marqueeToolBarAds.php")
    Call<MarqueeTextModel> getMarqueTextAds();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/fetchDiclaimerYeni.php")
    Call<FormattedTextModel> disclaimerFormatedText();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/underConstructionTR.php")
    Call<FormattedTextModel> underConstructionFormatedText();


    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/ulusalTv.php")
    Call<List<UlusalTvModel>> ulusalTV();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/ulusalTvCategories.php")
    Call<List<UlusalTvCategoryModel>> ulusalTvCategory();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/ulusalTvCategoriesDetails.php")
    Call<List<UlusalTvModel>> ulusalTvCategoryDetails(@Query("cat") String category);


    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/yerelTvlerExoo.php")
    Call<List<YerelTvModel>> yerelTv();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/yerelTvlerCategoriesExoo.php")
    Call<List<YerelTvCategoryModel>> yerelTvCategory();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/yerelTvlerCategoriesDetailsExoo.php")
    Call<List<YerelTvModel>> getYerelTvByCategory(@Query("cat") String category);


    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/zYerelTvYonlendir.php")
    Call<List<YerelTvYonlendirModel>> yerelTvYonlendir();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/zYerelTvCategoriesYonlendir.php")
    Call<List<YerelTvYonlendirCategoryModel>> yerelTvCategoryYonlendir();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/zYerelTvCategoriesDetailsYonlendir.php")
    Call<List<YerelTvYonlendirModel>> getYerelTvByCategoryYonlendir(@Query("cat") String category);


    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/turkYabanciMovieNewww.php")
    Call<List<TurkYabanciMovieModel>> urlGetir();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/ZReleaseTvOne.php")
    Call<List<TurkYabanciMovieYonlendirModel>> urlRelease();


    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/turkRadyolariYeni.php")
    Call<List<RadyoDinleModel>> radyoDinleBakim();


    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/youtubeCizgiFilmNeww.php")
    Call<List<TurkishCartoonYtbModel>> indoCartoonTv();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/youtubeCizgiFilmYonlendir.php")
    Call<List<TurkishCartoonYtbRedirectModel>> urlReleaseRedirect();



    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/ytbExtraTvler.php")
    Call<List<YtbExtraTvModel>> ytbExtraTv();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/ytbExtraTvlerCategories.php")
    Call<List<YtbExtraTvCategoryModel>> ytbExtraTvCategory();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/ytbExtraTvlerCategoriesDetails.php")
    Call<List<YtbExtraTvModel>> getYtbExtraTvByCategory(@Query("cat") String category);



    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/ytbExtraTvYonlendir.php")
    Call<List<YtbExtraTvYonlendirModel>> ytbExtraTvYonlendir();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/ytbExtraTvCategoriesYonlendir.php")
    Call<List<YtbExtraTvYonlendirCategoryModel>> ytbExtraTvCategoryYonlendir();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/ytbExtraTvCategoriesDetailsYonlendir.php")
    Call<List<YtbExtraTvYonlendirModel>> getYtbExtraTvByCategoryYonlendir(@Query("cat") String category);



    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/gazeteler.php")
    Call<List<GazetelerModel>> gazeteler();



    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/dunyaTvlerExo.php")
    Call<List<YerelTvModel>> dunyaTv();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/dunyaTvlerCategoriesExo.php")
    Call<List<YerelTvCategoryModel>> dunyaTvCategory();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/dunyaTvlerCategoriesDetailsExo.php")
    Call<List<YerelTvModel>> getDunyaTvByCategory(@Query("cat") String category);

    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/dunyaTvlerCountriesExo.php")
    Call<List<YerelTvCategoryModel>> dunyaTvCountries();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/dunyaTvlerCountriesDetailsExo.php")
    Call<List<YerelTvModel>> getDunyaTvByCountries(@Query("cat") String category);


    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/dunyaTvlerExoYonlendir.php")
    Call<List<YerelTvYonlendirModel>> dunyaTvYonlendir();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/dunyaTvlerCategoriesExoYonlendir.php")
    Call<List<YerelTvYonlendirCategoryModel>> dunyaTvCategoryYonlendir();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/dunyaTvlerCategoriesDetailsExoYonlendir.php")
    Call<List<YerelTvYonlendirModel>> getDunyaTvByCategoryYonlendir(@Query("cat") String category);

    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/dunyaTvlerCountriesExoYonlendir.php")
    Call<List<YerelTvYonlendirCategoryModel>> dunyaTvYonlendirCountries();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTV/dunyaTvlerCountriesDetailsExoYonlendir.php")
    Call<List<YerelTvYonlendirModel>> getDunyaTvYonlendirByCountries(@Query("cat") String category);

}
