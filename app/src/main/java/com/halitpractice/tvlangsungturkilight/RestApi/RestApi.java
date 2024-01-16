package com.halitpractice.tvlangsungturkilight.RestApi;

import com.halitpractice.tvlangsungturkilight.models.DunyaTvCategoryModel;
import com.halitpractice.tvlangsungturkilight.models.DunyaTvLanguageModel;
import com.halitpractice.tvlangsungturkilight.models.DunyaTvModel;
import com.halitpractice.tvlangsungturkilight.models.DunyaTvYonlendirCategoryModel;
import com.halitpractice.tvlangsungturkilight.models.DunyaTvYonlendirLanguageModel;
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
import com.halitpractice.tvlangsungturkilight.models.YtbExtraTvLanguageModel;
import com.halitpractice.tvlangsungturkilight.models.YtbExtraTvModel;
import com.halitpractice.tvlangsungturkilight.models.YtbExtraTvYonlendirCategoryModel;
import com.halitpractice.tvlangsungturkilight.models.YtbExtraTvYonlendirCountryModel;
import com.halitpractice.tvlangsungturkilight.models.YtbExtraTvYonlendirModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApi {

    @GET("/CanliTVversionTwo2024/versionThree/AdsMarqueTex/marqueeTextMainActivity.php")
    Call<MarqueeTextModel> getMarqueText();

    @GET("/CanliTVversionTwo2024/versionThree/AdsMarqueTex/marqueeTextUlusalTV.php")
    Call<MarqueeTextModel> getMarqueTextUlusalTv();

    @GET("/CanliTVversionTwo2024/versionThree/AdsMarqueTex/marqueeTextYerelTV.php")
    Call<MarqueeTextModel> getMarqueTextYerelTv();

    @GET("/CanliTVversionTwo2024/versionThree/AdsMarqueTex/marqueeTextUyariDisclaimer.php")
    Call<MarqueeTextModel> getMarqueTextUyariDisclaimer();

    @GET("/CanliTVversionTwo2024/versionThree/AdsMarqueTex/marqueeTextCizgiFilmler.php")
    Call<MarqueeTextModel> getMarqueTextCizgiFilmler();

    @GET("/CanliTVversionTwo2024/versionThree/AdsMarqueTex/marqueeTextYtbExtra.php")
    Call<MarqueeTextModel> getMarqueTextYtbExtra();

    @GET("/CanliTVversionTwo2024/versionThree/AdsMarqueTex/marqueeTextGazeteler.php")
    Call<MarqueeTextModel> getMarqueTextGazeteler();

    @GET("/CanliTVversionTwo2024/versionThree/AdsMarqueTex/marqueeTextDunyaTv.php")
    Call<MarqueeTextModel> getMarqueTextDunyaTv();

    @GET("/CanliTVversionTwo2024/versionThree/ChromeTabFiles/fetchDiclaimer.php")
    Call<FormattedTextModel> disclaimerFormatedText();

    @GET("/CanliTVversionTwo2024/versionThree/AdsMarqueTex/underConstructionTR.php")
    Call<FormattedTextModel> underConstructionFormatedText();




    @GET("/CanliTVversionTwo2024/versionThree/yonlendirilenExo/yerelTurkTvExo.php")
    Call<List<YerelTvModel>> yerelTurkTvs();

    @GET("/CanliTVversionTwo2024/versionThree/yonlendirilenExo/yerelTurkTvCategoriesExo.php")
    Call<List<YerelTvCategoryModel>> yerelTurkTvsCategory();

    @GET("/CanliTVversionTwo2024/versionThree/yonlendirilenExo/yerelTurkTvCategoriesDetailsExo.php")
    Call<List<YerelTvModel>> yerelTurkTvsCategory(@Query("cat") String category);



    @GET("/CanliTVversionTwo2024/versionThree/ChromeTabFiles/yerelTurkTvYonlendir.php")
    Call<List<YerelTvYonlendirModel>> yerelTurkTvsYonlendir();

    @GET("/CanliTVversionTwo2024/versionThree/ChromeTabFiles/yerelTurkTvCategoriesYonlendir.php")
    Call<List<YerelTvYonlendirCategoryModel>> yerelTurkTvsCategoryYonlendir();

    @GET("/CanliTVversionTwo2024/versionThree/ChromeTabFiles/yerelTurkTvCategoriesDetailsYonlendir.php")
    Call<List<YerelTvYonlendirModel>> yerelTurkTvsCategoryYonlendir(@Query("cat") String category);




    @GET("/CanliTVversionTwo2024/versionThree/ChromeTabFiles/turkRadyolari.php")
    Call<List<RadyoDinleModel>> radyoDinleBakim();

    @GET("/CanliTVversionTwo2024/versionThree/ChromeTabFiles/radyoDinleYonlendir.php")
    Call<List<RadyoDinleYonlendirModel>> turkRadyoDinleYonlendir();



    @GET("/CanliTVversionTwo2024/versionThree/yonlendirilenExo/dunyaTvExo.php")
    Call<List<DunyaTvModel>> dunyaTv();

    @GET("/CanliTVversionTwo2024/versionThree/yonlendirilenExo/dunyaTvCategoriesExo.php")
    Call<List<DunyaTvCategoryModel>> dunyaTvCategory();

    @GET("/CanliTVversionTwo2024/versionThree/yonlendirilenExo/dunyaTvCategoriesDetailsExo.php")
    Call<List<DunyaTvModel>> getDunyaTvByCategory(@Query("cat") String category);

    @GET("/CanliTVversionTwo2024/versionThree/yonlendirilenExo/dunyaTvCountriesExo.php")
    Call<List<DunyaTvCategoryModel>> dunyaTvCountries();

    @GET("/CanliTVversionTwo2024/versionThree/yonlendirilenExo/dunyaTvCountriesDetailsExo.php")
    Call<List<DunyaTvModel>> getDunyaTvByCountries(@Query("cat") String category);

    @GET("/CanliTVversionTwo2024/versionThree/yonlendirilenExo/dunyaTvLanguageExo.php")
    Call<List<DunyaTvLanguageModel>> cigiFilmTvLanguage();

    @GET("/CanliTVversionTwo2024/versionThree/yonlendirilenExo/dunyaTvLanguageDetailsExo.php")
    Call<List<DunyaTvModel>> cigiFilmTvLanguageDetails(@Query("cat") String category);


    @GET("/CanliTVversionTwo2024/versionThree/ChromeTabFiles/dunyaTvYonlendir.php")
    Call<List<DunyaTvYonlendirModel>> dunyaTvYonlendir();

    @GET("/CanliTVversionTwo2024/versionThree/ChromeTabFiles/dunyaTvCategoriesYonlendir.php")
    Call<List<DunyaTvYonlendirCategoryModel>> dunyaTvCategoryYonlendir();

    @GET("/CanliTVversionTwo2024/versionThree/ChromeTabFiles/dunyaTvCategoriesDetailsYonlendir.php")
    Call<List<DunyaTvYonlendirModel>> getDunyaTvByCategoryYonlendir(@Query("cat") String category);

    @GET("/CanliTVversionTwo2024/versionThree/ChromeTabFiles/dunyaTvCountriesYonlendir.php")
    Call<List<DunyaTvYonlendirCategoryModel>> dunyaTvYonlendirCountries();

    @GET("/CanliTVversionTwo2024/versionThree/ChromeTabFiles/dunyaTvCountriesDetailsYonlendir.php")
    Call<List<DunyaTvYonlendirModel>> getDunyaTvYonlendirByCountries(@Query("cat") String category);

    @GET("/CanliTVversionTwo2024/versionThree/ChromeTabFiles/dunyaTvLanguageYonlendir.php")
    Call<List<DunyaTvYonlendirLanguageModel>> dunyaTvLanguageYonlendir();

    @GET("/CanliTVversionTwo2024/versionThree/ChromeTabFiles/dunyaTvLanguageDetailsYonlendir.php")
    Call<List<DunyaTvYonlendirModel>> getDunyaTvByLanguageYonlendir(@Query("cat") String category);



    @GET("/CanliTVversionTwo2024/versionThree/ChromeTabFiles/ekstraTv.php")
    Call<List<UlusalTvModel>> extraTvs();

    @GET("/CanliTVversionTwo2024/versionThree/ChromeTabFiles/ekstraTvCategories.php")
    Call<List<UlusalTvCategoryModel>> extraTvsCategory();

    @GET("/CanliTVversionTwo2024/versionThree/ChromeTabFiles/ekstraTvCategoriesDetails.php")
    Call<List<UlusalTvModel>> extraTvsCategoryDetails(@Query("cat") String category);



    @GET("/CanliTVversionTwo2024/versionThree/ChromeTabFiles/turkYabanciFilm.php")
    Call<List<TurkYabanciMovieModel>> urlGetir();

    @GET("/CanliTVversionTwo2024/versionThree/ChromeTabFiles/turkYabanciFilmYonlendir.php")
    Call<List<TurkYabanciMovieYonlendirModel>> yabanciFilmYonlendir();



    @GET("/CanliTVversionTwo2024/versionThree/yonlendirilenYtb/ytbExtraTvler.php")
    Call<List<YtbExtraTvModel>> ytbExtraTv();

    @GET("/CanliTVversionTwo2024/versionThree/yonlendirilenYtb/ytbExtraTvlerCategories.php")
    Call<List<YtbExtraTvCategoryModel>> ytbExtraTvCategory();

    @GET("/CanliTVversionTwo2024/versionThree/yonlendirilenYtb/ytbExtraTvlerCategoriesDetails.php")
    Call<List<YtbExtraTvModel>> getYtbExtraTvByCategory(@Query("cat") String category);

    @GET("/CanliTVversionTwo2024/versionThree/yonlendirilenYtb/ytbExtraTvlerCountries.php")
    Call<List<YtbExtraTvCategoryModel>> ytbExtraTvCountry();

    @GET("/CanliTVversionTwo2024/versionThree/yonlendirilenYtb/ytbExtraTvlerCountriesDetails.php")
    Call<List<YtbExtraTvModel>> getYtbExtraTvByCountry(@Query("cat") String category);

    @GET("/CanliTVversionTwo2024/versionThree/yonlendirilenYtb/ytbExtraTvlerLanguage.php")
    Call<List<YtbExtraTvLanguageModel>> ytbExtraTvLanguage();

    @GET("/CanliTVversionTwo2024/versionThree/yonlendirilenYtb/ytbExtraTvlerLanguageDetails.php")
    Call<List<YtbExtraTvModel>> ytbExtraTvLanguageDetails(@Query("cat") String category);



    @GET("/CanliTVversionTwo2024/versionThree/ChromeTabFiles/ytbExtraTvYonlendir.php")
    Call<List<YtbExtraTvYonlendirModel>> ytbExtraTvYonlendir();

    @GET("/CanliTVversionTwo2024/versionThree/ChromeTabFiles/ytbExtraTvCategoriesYonlendir.php")
    Call<List<YtbExtraTvYonlendirCategoryModel>> ytbExtraTvCategoryYonlendir();

    @GET("/CanliTVversionTwo2024/versionThree/ChromeTabFiles/ytbExtraTvCategoriesDetailsYonlendir.php")
    Call<List<YtbExtraTvYonlendirModel>> getYtbExtraTvByCategoryYonlendir(@Query("cat") String category);

    @GET("/CanliTVversionTwo2024/versionThree/ChromeTabFiles/ytbExtraTvCountriesYonlendir.php")
    Call<List<YtbExtraTvYonlendirCountryModel>> ytbExtraTvCountryYonlendir();

    @GET("/CanliTVversionTwo2024/versionThree/ChromeTabFiles/ytbExtraTvCountriesDetailsYonlendir.php")
    Call<List<YtbExtraTvYonlendirModel>> getYtbExtraTvByCountryYonlendir(@Query("cat") String category);

    @GET("/CanliTVversionTwo2024/versionThree/ChromeTabFiles/ytbExtraTvLanguageYonlendir.php")
    Call<List<YtbExtraTvLanguageModel>> ytbExtraTvLanguageYonlendir();

    @GET("/CanliTVversionTwo2024/versionThree/ChromeTabFiles/ytbExtraTvLanguageDetailsYonlendir.php")
    Call<List<YtbExtraTvYonlendirModel>> ytbExtraTvLanguageDetailsYonlendir(@Query("cat") String category);


    @GET("/CanliTVversionTwo2024/versionThree/ChromeTabFiles/gazeteler.php")
    Call<List<GazetelerModel>> gazeteler();

    @GET("/CanliTVversionTwo2024/versionThree/ChromeTabFiles/gazetelerCategories.php")
    Call<List<GazetelerCategoryModel>> gazetelerCategory();

    @GET("/CanliTVversionTwo2024/versionThree/ChromeTabFiles/gazetelerCategoriesDetails.php")
    Call<List<GazetelerModel>> gazetelerCategoryDetails(@Query("cat") String category);

    @GET("/CanliTVversionTwo2024/versionThree/ChromeTabFiles/gazetelerCountries.php")
    Call<List<GazetelerCountryModel>> gazetelerCountry();

    @GET("/CanliTVversionTwo2024/versionThree/ChromeTabFiles/gazetelerCountriesDetails.php")
    Call<List<GazetelerModel>> gazetelerCountryDetails(@Query("cat") String category);

    @GET("/CanliTVversionTwo2024/versionThree/ChromeTabFiles/gazetelerRegional.php")
    Call<List<GazetelerCategoryModel>> gazetelerRegional();

    @GET("/CanliTVversionTwo2024/versionThree/ChromeTabFiles/gazetelerRegionalDetails.php")
    Call<List<GazetelerModel>> gazetelerRegionalDetails(@Query("cat") String category);



}
