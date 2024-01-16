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
import com.halitpractice.tvlangsungturkilight.models.DunyaTvLanguageModel;
import com.halitpractice.tvlangsungturkilight.models.DunyaTvYonlendirLanguageModel;
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

public class ManagerAll extends BaseManager {

    private static final ManagerAll ourInstance = new ManagerAll();

    public static synchronized ManagerAll getInstance() {
        return ourInstance;
    }


    public Call<MarqueeTextModel> getMarqueText() {
        return getRestApi().getMarqueText();
    }

    public Call<MarqueeTextModel> getMarqueTextUlusalTv() {
        return getRestApi().getMarqueTextUlusalTv();
    }

    public Call<MarqueeTextModel> getMarqueTextYerelTv() {
        return getRestApi().getMarqueTextYerelTv();
    }

    public Call<MarqueeTextModel> getMarqueTextUyariDisclaimer() {
        return getRestApi().getMarqueTextUyariDisclaimer();
    }

    public Call<MarqueeTextModel> getMarqueTextCizgiFilmler() {
        return getRestApi().getMarqueTextCizgiFilmler();
    }

    public Call<MarqueeTextModel> getMarqueTextYtbExtra() {
        return getRestApi().getMarqueTextYtbExtra();
    }

    public Call<MarqueeTextModel> getMarqueTextGazeteler() {
        return getRestApi().getMarqueTextGazeteler();
    }

    public Call<MarqueeTextModel> getMarqueTextDunyaTv() {
        return getRestApi().getMarqueTextDunyaTv();
    }

    public Call<FormattedTextModel> disclaimerFormated() {
        return getRestApi().disclaimerFormatedText();
    }

    public Call<FormattedTextModel> underConstruction() {
        return getRestApi().underConstructionFormatedText();
    }




    public Call<List<YerelTvModel>> yerelTurkTvFetch() {
        return getRestApi().yerelTurkTvs();
    }

    public Call<List<YerelTvCategoryModel>> yerelTurkTvCategoryFetch() {
        return getRestApi().yerelTurkTvsCategory();
    }

    public Call<List<YerelTvModel>> yerelTurkTvCategoryDetailsFetch(String category) {
        return getRestApi().yerelTurkTvsCategory(category);
    }



    public Call<List<YerelTvYonlendirModel>> yerelTurkTvYonlendirFetch() {
        return getRestApi().yerelTurkTvsYonlendir();
    }


    public Call<List<YerelTvYonlendirCategoryModel>> yerelTurkTvCategoryYonlendirFetch() {
        return getRestApi().yerelTurkTvsCategoryYonlendir();
    }

    public Call<List<YerelTvYonlendirModel>> yerelTurkTvCategoryYonlendirFetch(String category) {
        return getRestApi().yerelTurkTvsCategoryYonlendir(category);
    }






    public Call<List<UlusalTvModel>> extraTvFetch() {
        return getRestApi().extraTvs();
    }

    public Call<List<UlusalTvCategoryModel>> extraTvCategoryFetch() {
        return getRestApi().extraTvsCategory();
    }

    public Call<List<UlusalTvModel>> extraTvCategoryDetailsFetch(String category) {
        return getRestApi().extraTvsCategoryDetails(category);
    }





    public Call<List<TurkYabanciMovieModel>> getTurkYabanciMovie() {
        return getRestApi().urlGetir();
    }

    public Call<List<TurkYabanciMovieYonlendirModel>> turkYabanciFilmYonlendir() {
        return getRestApi().yabanciFilmYonlendir();
    }

    public Call<List<RadyoDinleYonlendirModel>> radyoDinleYonlendir() {
        return getRestApi().turkRadyoDinleYonlendir();
    }



    public Call<List<RadyoDinleModel>> getRadyoDinleFetch() {
        return getRestApi().radyoDinleBakim();
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

    public Call<List<YtbExtraTvLanguageModel>> ytbExtraTvLanguageFetch() {
        return getRestApi().ytbExtraTvLanguage();
    }

    public Call<List<YtbExtraTvModel>> ytbExtraTvLanguageDetailsFetch(String category) {
        return getRestApi().ytbExtraTvLanguageDetails(category);
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

    public Call<List<YtbExtraTvYonlendirCountryModel>> ytbExtraTvCountryYonlendirFetch() {
        return getRestApi().ytbExtraTvCountryYonlendir();
    }

    public Call<List<YtbExtraTvYonlendirModel>> getYtbExtraTvByCountryYonlendirFetch(String category) {
        return getRestApi().getYtbExtraTvByCountryYonlendir(category);
    }

    public Call<List<YtbExtraTvLanguageModel>> ytbExtraTvLanguageYonlendirFetch() {
        return getRestApi().ytbExtraTvLanguageYonlendir();
    }

    public Call<List<YtbExtraTvYonlendirModel>> ytbExtraTvLanguageDetailsYonlendirFetch(String category) {
        return getRestApi().ytbExtraTvLanguageDetailsYonlendir(category);
    }



    public Call<List<GazetelerModel>> gazetelerFetch() {
        return getRestApi().gazeteler();
    }

    public Call<List<GazetelerCategoryModel>> gazetelerCategoryFetch() {
        return getRestApi().gazetelerCategory();
    }

    public Call<List<GazetelerModel>> gazetelerCategoryDetailsFetch(String category) {
        return getRestApi().gazetelerCategoryDetails(category);
    }

    public Call<List<GazetelerCountryModel>> gazetelerCountryFetch() {
        return getRestApi().gazetelerCountry();
    }

    public Call<List<GazetelerModel>> gazetelerCountryDetailsFetch(String category) {
        return getRestApi().gazetelerCountryDetails(category);
    }

    public Call<List<GazetelerCategoryModel>> gazetelerRegionalFetch() {
        return getRestApi().gazetelerRegional();
    }

    public Call<List<GazetelerModel>> gazetelerRegionalDetailsFetch(String category) {
        return getRestApi().gazetelerRegionalDetails(category);
    }




    public Call<List<DunyaTvModel>> dunyaTvTvFetch() {
        return getRestApi().dunyaTv();
    }

    public Call<List<DunyaTvCategoryModel>> dunyaTvCategoryFetch() {
        return getRestApi().dunyaTvCategory();
    }

    public Call<List<DunyaTvModel>> dunyaTvCategoryDetailsFetch(String category) {
        return getRestApi().getDunyaTvByCategory(category);
    }

    public Call<List<DunyaTvCategoryModel>> dunyaTvCountriesFetch() {
        return getRestApi().dunyaTvCountries();
    }

    public Call<List<DunyaTvModel>> dunyaTvCountriesDetailsFetch(String category) {
        return getRestApi().getDunyaTvByCountries(category);
    }

    public Call<List<DunyaTvLanguageModel>> cizgiFilmTvLanguageFetch() {
        return getRestApi().cigiFilmTvLanguage();
    }

    public Call<List<DunyaTvModel>> cigiFilmTvLanguageDetailsFetch(String category) {
        return getRestApi().cigiFilmTvLanguageDetails(category);
    }



    public Call<List<DunyaTvYonlendirModel>> dunyaTvYonlendirFetch() {
        return getRestApi().dunyaTvYonlendir();
    }

    public Call<List<DunyaTvYonlendirCategoryModel>> dunyaTvCategoryYonlendirFetch() {
        return getRestApi().dunyaTvCategoryYonlendir();
    }

    public Call<List<DunyaTvYonlendirModel>> getDunyaTvByCategoryYonlendirFetch(String category) {
        return getRestApi().getDunyaTvByCategoryYonlendir(category);
    }

    public Call<List<DunyaTvYonlendirCategoryModel>> dunyaTvYonlendirCountriesFetch() {
        return getRestApi().dunyaTvYonlendirCountries();
    }


    public Call<List<DunyaTvYonlendirModel>> dunyaTvYonlendirCountriesDetailsFetch(String category) {
        return getRestApi().getDunyaTvYonlendirByCountries(category);
    }

    public Call<List<DunyaTvYonlendirLanguageModel>> dunyaTvLanguageYonlendirFetch() {
        return getRestApi().dunyaTvLanguageYonlendir();
    }

    public Call<List<DunyaTvYonlendirModel>> getDunyaTvByLanguageYonlendirFetch(String category) {
        return getRestApi().getDunyaTvByLanguageYonlendir(category);
    }

}
