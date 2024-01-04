package com.halitpractice.tvlangsungturkilight.RestApi;

import com.halitpractice.tvlangsungturkilight.models.DunyaTvCategoryModel;
import com.halitpractice.tvlangsungturkilight.models.DunyaTvModel;
import com.halitpractice.tvlangsungturkilight.models.DunyaTvYonlendirCategoryModel;
import com.halitpractice.tvlangsungturkilight.models.DunyaTvYonlendirModel;
import com.halitpractice.tvlangsungturkilight.models.FormattedTextModel;
import com.halitpractice.tvlangsungturkilight.models.GazetelerCategoryModel;
import com.halitpractice.tvlangsungturkilight.models.GazetelerCountryModel;
import com.halitpractice.tvlangsungturkilight.models.GazetelerModel;
import com.halitpractice.tvlangsungturkilight.models.MarqueeTextModel;
import com.halitpractice.tvlangsungturkilight.models.RadyoDinleModel;
import com.halitpractice.tvlangsungturkilight.models.RadyoDinleYonlendirModel;
import com.halitpractice.tvlangsungturkilight.models.TurkYabanciMovieModel;
import com.halitpractice.tvlangsungturkilight.models.TurkYabanciMovieYonlendirModel;
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

    @GET("/CanliTVversionTwo2024/versionTwo/AdsMarqueTex/marqueeTextMainActivity.php")
    Call<MarqueeTextModel> getMarqueText();

    @GET("/CanliTVversionTwo2024/versionTwo/AdsMarqueTex/marqueeTextUlusalTV.php")
    Call<MarqueeTextModel> getMarqueTextUlusalTv();

    @GET("/CanliTVversionTwo2024/versionTwo/AdsMarqueTex/marqueeTextYerelTV.php")
    Call<MarqueeTextModel> getMarqueTextYerelTv();

    @GET("/CanliTVversionTwo2024/versionTwo/AdsMarqueTex/marqueeTextUyariDisclaimer.php")
    Call<MarqueeTextModel> getMarqueTextUyariDisclaimer();

    @GET("/CanliTVversionTwo2024/versionTwo/AdsMarqueTex/marqueeTextCizgiFilmler.php")
    Call<MarqueeTextModel> getMarqueTextCizgiFilmler();

    @GET("/CanliTVversionTwo2024/versionTwo/AdsMarqueTex/marqueeTextYtbExtra.php")
    Call<MarqueeTextModel> getMarqueTextYtbExtra();

    @GET("/CanliTVversionTwo2024/versionTwo/AdsMarqueTex/marqueeTextGazeteler.php")
    Call<MarqueeTextModel> getMarqueTextGazeteler();

    @GET("/CanliTVversionTwo2024/versionTwo/AdsMarqueTex/marqueeTextDunyaTv.php")
    Call<MarqueeTextModel> getMarqueTextDunyaTv();

    @GET("/CanliTVversionTwo2024/versionTwo/AdsMarqueTex/fetchDiclaimerYeni.php")
    Call<FormattedTextModel> disclaimerFormatedText();

    @GET("/CanliTVversionTwo2024/versionTwo/AdsMarqueTex/underConstructionTR.php")
    Call<FormattedTextModel> underConstructionFormatedText();


    @GET("/CanliTVversionTwo2024/versionTwo/ChromeTabFiles/ekstraTv.php")
    Call<List<UlusalTvModel>> ulusalTV();

    @GET("/CanliTVversionTwo2024/versionTwo/ChromeTabFiles/ekstraTvCategories.php")
    Call<List<UlusalTvCategoryModel>> ulusalTvCategory();

    @GET("/CanliTVversionTwo2024/versionTwo/ChromeTabFiles/ekstraTvCategoriesDetails.php")
    Call<List<UlusalTvModel>> ulusalTvCategoryDetails(@Query("cat") String category);


    @GET("/CanliTVversionTwo2024/versionTwo/yonlendirilenFiles/yerelTvExo.php")
    Call<List<YerelTvModel>> yerelTv();

    @GET("/CanliTVversionTwo2024/versionTwo/yonlendirilenFiles/yerelTvCategoriesExo.php")
    Call<List<YerelTvCategoryModel>> yerelTvCategory();

    @GET("/CanliTVversionTwo2024/versionTwo/yonlendirilenFiles/yerelTvCategoriesDetailsExo.php")
    Call<List<YerelTvModel>> getYerelTvByCategory(@Query("cat") String category);


    @GET("/CanliTVversionTwo2024/versionTwo/ChromeTabFiles/yerelTvYonlendir.php")
    Call<List<YerelTvYonlendirModel>> yerelTvYonlendir();

    @GET("/CanliTVversionTwo2024/versionTwo/ChromeTabFiles/yerelTvCategoriesYonlendir.php")
    Call<List<YerelTvYonlendirCategoryModel>> yerelTvCategoryYonlendir();

    @GET("/CanliTVversionTwo2024/versionTwo/ChromeTabFiles/yerelTvCategoriesDetailsYonlendir.php")
    Call<List<YerelTvYonlendirModel>> getYerelTvByCategoryYonlendir(@Query("cat") String category);


    @GET("/CanliTVversionTwo2024/versionTwo/yonlendirilenFiles/turkYabanciFilm.php")
    Call<List<TurkYabanciMovieModel>> urlGetir();

    @GET("/CanliTVversionTwo2024/versionTwo/ChromeTabFiles/turkYabanciFilmYonlendir.php")
    Call<List<TurkYabanciMovieYonlendirModel>> yabanciFilmYonlendir();


    @GET("/CanliTVversionTwo2024/versionTwo/yonlendirilenFiles/turkRadyolari.php")
    Call<List<RadyoDinleModel>> radyoDinleBakim();

    @GET("/CanliTVversionTwo2024/versionTwo/ChromeTabFiles/radyoDinleYonlendir.php")
    Call<List<RadyoDinleYonlendirModel>> turkRadyoDinleYonlendir();



    @GET("/CanliTVversionTwo2024/versionTwo/yonlendirilenFiles/ytbExtraTvler.php")
    Call<List<YtbExtraTvModel>> ytbExtraTv();

    @GET("/CanliTVversionTwo2024/versionTwo/yonlendirilenFiles/ytbExtraTvlerCategories.php")
    Call<List<YtbExtraTvCategoryModel>> ytbExtraTvCategory();

    @GET("/CanliTVversionTwo2024/versionTwo/yonlendirilenFiles/ytbExtraTvlerCategoriesDetails.php")
    Call<List<YtbExtraTvModel>> getYtbExtraTvByCategory(@Query("cat") String category);

    @GET("/CanliTVversionTwo2024/versionTwo/yonlendirilenFiles/ytbExtraTvlerCountries.php")
    Call<List<YtbExtraTvCategoryModel>> ytbExtraTvCountry();

    @GET("/CanliTVversionTwo2024/versionTwo/yonlendirilenFiles/ytbExtraTvlerCountriesDetails.php")
    Call<List<YtbExtraTvModel>> getYtbExtraTvByCountry(@Query("cat") String category);




    @GET("/CanliTVversionTwo2024/versionTwo/ChromeTabFiles/ytbExtraTvYonlendir.php")
    Call<List<YtbExtraTvYonlendirModel>> ytbExtraTvYonlendir();

    @GET("/CanliTVversionTwo2024/versionTwo/ChromeTabFiles/ytbExtraTvCategoriesYonlendir.php")
    Call<List<YtbExtraTvYonlendirCategoryModel>> ytbExtraTvCategoryYonlendir();

    @GET("/CanliTVversionTwo2024/versionTwo/ChromeTabFiles/ytbExtraTvCategoriesDetailsYonlendir.php")
    Call<List<YtbExtraTvYonlendirModel>> getYtbExtraTvByCategoryYonlendir(@Query("cat") String category);

    @GET("/CanliTVversionTwo2024/versionTwo/ChromeTabFiles/ytbExtraTvCountriesYonlendir.php")
    Call<List<YtbExtraTvYonlendirCountryModel>> ytbExtraTvCountryYonlendir();

    @GET("/CanliTVversionTwo2024/versionTwo/ChromeTabFiles/ytbExtraTvCountriesDetailsYonlendir.php")
    Call<List<YtbExtraTvYonlendirModel>> getYtbExtraTvByCountryYonlendir(@Query("cat") String category);


    @GET("/CanliTVversionTwo2024/versionTwo/ChromeTabFiles/gazeteler.php")
    Call<List<GazetelerModel>> gazeteler();

    @GET("/CanliTVversionTwo2024/versionTwo/ChromeTabFiles/gazetelerCategories.php")
    Call<List<GazetelerCategoryModel>> gazetelerCategory();

    @GET("/CanliTVversionTwo2024/versionTwo/ChromeTabFiles/gazetelerCategoriesDetails.php")
    Call<List<GazetelerModel>> gazetelerCategoryDetails(@Query("cat") String category);

    @GET("/CanliTVversionTwo2024/versionTwo/ChromeTabFiles/gazetelerCountries.php")
    Call<List<GazetelerCountryModel>> gazetelerCountry();

    @GET("/CanliTVversionTwo2024/versionTwo/ChromeTabFiles/gazetelerCountriesDetails.php")
    Call<List<GazetelerModel>> gazetelerCountryDetails(@Query("cat") String category);

    @GET("/CanliTVversionTwo2024/versionTwo/ChromeTabFiles/gazetelerRegional.php")
    Call<List<GazetelerCategoryModel>> gazetelerRegional();

    @GET("/CanliTVversionTwo2024/versionTwo/ChromeTabFiles/gazetelerRegionalDetails.php")
    Call<List<GazetelerModel>> gazetelerRegionalDetails(@Query("cat") String category);


    @GET("/CanliTVversionTwo2024/versionTwo/yonlendirilenFiles/dunyaTvlerExo.php")
    Call<List<DunyaTvModel>> dunyaTv();

    @GET("/CanliTVversionTwo2024/versionTwo/yonlendirilenFiles/dunyaTvlerCategoriesExo.php")
    Call<List<DunyaTvCategoryModel>> dunyaTvCategory();

    @GET("/CanliTVversionTwo2024/versionTwo/yonlendirilenFiles/dunyaTvlerCategoriesDetailsExo.php")
    Call<List<DunyaTvModel>> getDunyaTvByCategory(@Query("cat") String category);

    @GET("/CanliTVversionTwo2024/versionTwo/yonlendirilenFiles/dunyaTvlerCountriesExo.php")
    Call<List<DunyaTvCategoryModel>> dunyaTvCountries();

    @GET("/CanliTVversionTwo2024/versionTwo/yonlendirilenFiles/dunyaTvlerCountriesDetailsExo.php")
    Call<List<DunyaTvModel>> getDunyaTvByCountries(@Query("cat") String category);



    @GET("/CanliTVversionTwo2024/versionTwo/ChromeTabFiles/dunyaTvlerExoYonlendir.php")
    Call<List<DunyaTvYonlendirModel>> dunyaTvYonlendir();

    @GET("/CanliTVversionTwo2024/versionTwo/ChromeTabFiles/dunyaTvlerCategoriesExoYonlendir.php")
    Call<List<DunyaTvYonlendirCategoryModel>> dunyaTvCategoryYonlendir();

    @GET("/CanliTVversionTwo2024/versionTwo/ChromeTabFiles/dunyaTvlerCategoriesDetailsExoYonlendir.php")
    Call<List<DunyaTvYonlendirModel>> getDunyaTvByCategoryYonlendir(@Query("cat") String category);

    @GET("/CanliTVversionTwo2024/versionTwo/ChromeTabFiles/dunyaTvlerCountriesExoYonlendir.php")
    Call<List<DunyaTvYonlendirCategoryModel>> dunyaTvYonlendirCountries();

    @GET("/CanliTVversionTwo2024/versionTwo/ChromeTabFiles/dunyaTvlerCountriesDetailsExoYonlendir.php")
    Call<List<DunyaTvYonlendirModel>> getDunyaTvYonlendirByCountries(@Query("cat") String category);

}
