package com.halitpractice.tvlangsungturkilight.RestApi;

import com.halitpractice.tvlangsungturkilight.models.DunyaTvCategoryModel;
import com.halitpractice.tvlangsungturkilight.models.DunyaTvModel;
import com.halitpractice.tvlangsungturkilight.models.DunyaTvYonlendirCategoryModel;
import com.halitpractice.tvlangsungturkilight.models.DunyaTvYonlendirModel;
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
import com.halitpractice.tvlangsungturkilight.models.YtbExtraTvYonlendirCountryModel;
import com.halitpractice.tvlangsungturkilight.models.YtbExtraTvYonlendirModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApi {

    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/MarqueTexAds/marqueeTextMainActivity.php")
    Call<MarqueeTextModel> getMarqueText();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/MarqueTexAds/marqueeTextUlusalTV.php")
    Call<MarqueeTextModel> getMarqueTextUlusalTv();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/MarqueTexAds/marqueeTextYerelTV.php")
    Call<MarqueeTextModel> getMarqueTextYerelTv();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/MarqueTexAds/marqueeTextUyariDisclaimer.php")
    Call<MarqueeTextModel> getMarqueTextUyariDisclaimer();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/MarqueTexAds/marqueeTextCizgiFilmler.php")
    Call<MarqueeTextModel> getMarqueTextCizgiFilmler();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/MarqueTexAds/marqueeTextYtbExtra.php")
    Call<MarqueeTextModel> getMarqueTextYtbExtra();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/MarqueTexAds/marqueeTextGazeteler.php")
    Call<MarqueeTextModel> getMarqueTextGazeteler();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/MarqueTexAds/marqueeTextDunyaTv.php")
    Call<MarqueeTextModel> getMarqueTextDunyaTv();



    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/fetchDiclaimerYeni.php")
    Call<FormattedTextModel> disclaimerFormatedText();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/underConstructionTR.php")
    Call<FormattedTextModel> underConstructionFormatedText();


    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/ulusalTv.php")
    Call<List<UlusalTvModel>> ulusalTV();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/ulusalTvCategories.php")
    Call<List<UlusalTvCategoryModel>> ulusalTvCategory();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/ulusalTvCategoriesDetails.php")
    Call<List<UlusalTvModel>> ulusalTvCategoryDetails(@Query("cat") String category);


    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/yonlendirilenFiles/yerelTvlerExoo.php")
    Call<List<YerelTvModel>> yerelTv();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/yonlendirilenFiles/yerelTvlerCategoriesExoo.php")
    Call<List<YerelTvCategoryModel>> yerelTvCategory();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/yonlendirilenFiles/yerelTvlerCategoriesDetailsExoo.php")
    Call<List<YerelTvModel>> getYerelTvByCategory(@Query("cat") String category);


    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/zYerelTvYonlendir.php")
    Call<List<YerelTvYonlendirModel>> yerelTvYonlendir();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/zYerelTvCategoriesYonlendir.php")
    Call<List<YerelTvYonlendirCategoryModel>> yerelTvCategoryYonlendir();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/zYerelTvCategoriesDetailsYonlendir.php")
    Call<List<YerelTvYonlendirModel>> getYerelTvByCategoryYonlendir(@Query("cat") String category);


    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/yonlendirilenFiles/turkYabanciMovieNewww.php")
    Call<List<TurkYabanciMovieModel>> urlGetir();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/yonlendirilenFiles/youtubeCizgiFilmNeww.php")
    Call<List<TurkishCartoonYtbModel>> indoCartoonTv();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/youtubeCizgiFilmYonlendir.php")
    Call<List<TurkishCartoonYtbRedirectModel>> urlReleaseRedirect();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/turkRadyolariYeni.php")
    Call<List<RadyoDinleModel>> radyoDinleBakim();


    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/ZReleaseTvOne.php")
    Call<List<TurkYabanciMovieYonlendirModel>> urlRelease();



    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/yonlendirilenFiles/ytbExtraTvler.php")
    Call<List<YtbExtraTvModel>> ytbExtraTv();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/yonlendirilenFiles/ytbExtraTvlerCategories.php")
    Call<List<YtbExtraTvCategoryModel>> ytbExtraTvCategory();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/yonlendirilenFiles/ytbExtraTvlerCategoriesDetails.php")
    Call<List<YtbExtraTvModel>> getYtbExtraTvByCategory(@Query("cat") String category);

    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/yonlendirilenFiles/ytbExtraTvlerCountries.php")
    Call<List<YtbExtraTvCategoryModel>> ytbExtraTvCountry();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/yonlendirilenFiles/ytbExtraTvlerCountriesDetails.php")
    Call<List<YtbExtraTvModel>> getYtbExtraTvByCountry(@Query("cat") String category);




    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/ytbExtraTvYonlendir.php")
    Call<List<YtbExtraTvYonlendirModel>> ytbExtraTvYonlendir();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/ytbExtraTvCategoriesYonlendir.php")
    Call<List<YtbExtraTvYonlendirCategoryModel>> ytbExtraTvCategoryYonlendir();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/ytbExtraTvCategoriesDetailsYonlendir.php")
    Call<List<YtbExtraTvYonlendirModel>> getYtbExtraTvByCategoryYonlendir(@Query("cat") String category);

    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/ytbExtraTvCountriesYonlendir.php")
    Call<List<YtbExtraTvYonlendirCountryModel>> ytbExtraTvCountryYonlendir();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/ytbExtraTvCountriesDetailsYonlendir.php")
    Call<List<YtbExtraTvYonlendirModel>> getYtbExtraTvByCountryYonlendir(@Query("cat") String category);


    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/gazeteler.php")
    Call<List<GazetelerModel>> gazeteler();



    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/yonlendirilenFiles/dunyaTvlerExo.php")
    Call<List<DunyaTvModel>> dunyaTv();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/yonlendirilenFiles/dunyaTvlerCategoriesExo.php")
    Call<List<DunyaTvCategoryModel>> dunyaTvCategory();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/yonlendirilenFiles/dunyaTvlerCategoriesDetailsExo.php")
    Call<List<DunyaTvModel>> getDunyaTvByCategory(@Query("cat") String category);

    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/yonlendirilenFiles/dunyaTvlerCountriesExo.php")
    Call<List<DunyaTvCategoryModel>> dunyaTvCountries();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/yonlendirilenFiles/dunyaTvlerCountriesDetailsExo.php")
    Call<List<DunyaTvModel>> getDunyaTvByCountries(@Query("cat") String category);




    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/dunyaTvlerExoYonlendir.php")
    Call<List<DunyaTvYonlendirModel>> dunyaTvYonlendir();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/dunyaTvlerCategoriesExoYonlendir.php")
    Call<List<DunyaTvYonlendirCategoryModel>> dunyaTvCategoryYonlendir();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/dunyaTvlerCategoriesDetailsExoYonlendir.php")
    Call<List<DunyaTvYonlendirModel>> getDunyaTvByCategoryYonlendir(@Query("cat") String category);

    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/dunyaTvlerCountriesExoYonlendir.php")
    Call<List<DunyaTvYonlendirCategoryModel>> dunyaTvYonlendirCountries();

    @GET("/canliTVTurkishChromeTab/retrofitCanliTVversionOne/dunyaTvlerCountriesDetailsExoYonlendir.php")
    Call<List<DunyaTvYonlendirModel>> getDunyaTvYonlendirByCountries(@Query("cat") String category);

}
