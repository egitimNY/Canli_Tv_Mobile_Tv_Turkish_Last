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

public class ManagerAll extends BaseManager {

    private static final ManagerAll ourInstance = new ManagerAll();

    public static synchronized ManagerAll getInstance() {
        return ourInstance;
    }


    public Call<MarqueeTextModel> getMarqueText() {
        return getRestApi().getMarqueText();
    }

    public Call<MarqueeTextModel> getMarqueTextTolbarAds() {
        return getRestApi().getMarqueTextAds();
    }

    public Call<FormattedTextModel> disclaimerFormated() {
        return getRestApi().disclaimerFormatedText();
    }

    public Call<FormattedTextModel> underConstruction() {
        return getRestApi().underConstructionFormatedText();
    }



    public Call<List<UlusalTvModel>> ulusalTvFetch() {
        return getRestApi().ulusalTV();
    }

    public Call<List<UlusalTvCategoryModel>> ulusalTvCategoryFetch() {
        return getRestApi().ulusalTvCategory();
    }

    public Call<List<UlusalTvModel>> ulusalTvCategoryDetailsFetch(String category) {
        return getRestApi().ulusalTvCategoryDetails(category);
    }




    public Call<List<YerelTvModel>> yerelTvFetch() {
        return getRestApi().yerelTv();
    }

    public Call<List<YerelTvCategoryModel>> yerelTvCategoryFetch() {
        return getRestApi().yerelTvCategory();
    }

    public Call<List<YerelTvModel>> yerelTvCategoryDetailsFetch(String category) {
        return getRestApi().getYerelTvByCategory(category);
    }



    public Call<List<YerelTvYonlendirModel>> yerelTvYonlendirFetch() {
        return getRestApi().yerelTvYonlendir();
    }


    public Call<List<YerelTvYonlendirCategoryModel>> yerelTvCategoryYonlendirFetch() {
        return getRestApi().yerelTvCategoryYonlendir();
    }

    public Call<List<YerelTvYonlendirModel>> getYerelTvByCategoryYonlendirFetch(String category) {
        return getRestApi().getYerelTvByCategoryYonlendir(category);
    }


    public Call<List<TurkYabanciMovieModel>> getTurkYabanciMovie() {
        return getRestApi().urlGetir();
    }

    public Call<List<TurkYabanciMovieYonlendirModel>> ZReleaseTvOne() {
        return getRestApi().urlRelease();
    }



    public Call<List<RadyoDinleModel>> getRadyoDinleFetch() {
        return getRestApi().radyoDinleBakim();
    }


    public Call<List<TurkishCartoonYtbModel>> getIndoCartoonTv() {
        return getRestApi().indoCartoonTv();
    }

    public Call<List<TurkishCartoonYtbRedirectModel>> indiaCartoonRedirectFetch() {
        return getRestApi().urlReleaseRedirect();
    }



    public Call<List<YtbExtraTvModel>> ytbExtraTvFetch() {
        return getRestApi().ytbExtraTv();
    }

    public Call<List<YtbExtraTvCategoryModel>> ytbExtraTvCategoryFetch() {
        return getRestApi().ytbExtraTvCategory();
    }

    public Call<List<YtbExtraTvModel>> ytbExtraTvCategoryDetailsFetch(String category) {
        return getRestApi().getYtbExtraTvByCategory(category);
    }

    public Call<List<YtbExtraTvCategoryModel>> ytbExtraTvCountriesFetch() {
        return getRestApi().ytbExtraTvCountry();
    }

    public Call<List<YtbExtraTvModel>> ytbExtraTvCountriesDetailsFetch(String category) {
        return getRestApi().getYtbExtraTvByCountry(category);
    }


    public Call<List<YtbExtraTvYonlendirModel>> ytbExtraTvYonlendirFetch() {
        return getRestApi().ytbExtraTvYonlendir();
    }

    public Call<List<YtbExtraTvYonlendirCategoryModel>> ytbExtraTvCategoryYonlendirFetch() {
        return getRestApi().ytbExtraTvCategoryYonlendir();
    }

    public Call<List<YtbExtraTvYonlendirModel>> getYtbExtraTvByCategoryYonlendirFetch(String category) {
        return getRestApi().getYtbExtraTvByCategoryYonlendir(category);
    }



    public Call<List<GazetelerModel>> gazetelerFetch() {
        return getRestApi().gazeteler();
    }




    public Call<List<YerelTvModel>> dunyaTvTvFetch() {
        return getRestApi().dunyaTv();
    }

    public Call<List<YerelTvCategoryModel>> dunyaTvCategoryFetch() {
        return getRestApi().dunyaTvCategory();
    }

    public Call<List<YerelTvModel>> dunyaTvCategoryDetailsFetch(String category) {
        return getRestApi().getDunyaTvByCategory(category);
    }



    public Call<List<YerelTvCategoryModel>> dunyaTvCountriesFetch() {
        return getRestApi().dunyaTvCountries();
    }

    public Call<List<YerelTvModel>> dunyaTvCountriesDetailsFetch(String category) {
        return getRestApi().getDunyaTvByCountries(category);
    }

    public Call<List<YerelTvYonlendirModel>> dunyaTvYonlendirFetch() {
        return getRestApi().dunyaTvYonlendir();
    }

    public Call<List<YerelTvYonlendirCategoryModel>> dunyaTvCategoryYonlendirFetch() {
        return getRestApi().dunyaTvCategoryYonlendir();
    }

    public Call<List<YerelTvYonlendirModel>> getDunyaTvByCategoryYonlendirFetch(String category) {
        return getRestApi().getDunyaTvByCategoryYonlendir(category);
    }

    public Call<List<YerelTvYonlendirCategoryModel>> dunyaTvYonlendirCountriesFetch() {
        return getRestApi().dunyaTvYonlendirCountries();
    }


    public Call<List<YerelTvYonlendirModel>> dunyaTvYonlendirCountriesDetailsFetch(String category) {
        return getRestApi().getDunyaTvYonlendirByCountries(category);
    }



}
