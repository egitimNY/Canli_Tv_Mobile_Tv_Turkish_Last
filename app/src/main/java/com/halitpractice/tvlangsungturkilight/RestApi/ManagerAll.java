package com.halitpractice.tvlangsungturkilight.RestApi;

import com.halitpractice.tvlangsungturkilight.models.FormattedTextModel;
import com.halitpractice.tvlangsungturkilight.models.MarqueeTextModel;
import com.halitpractice.tvlangsungturkilight.models.UlusalTvCategoryModel;
import com.halitpractice.tvlangsungturkilight.models.UlusalTvModel;

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

    public Call<MarqueeTextModel> getMarqueTextAds() {
        return getRestApi().getMarqueTextAds();
    }

    public Call<FormattedTextModel> disclaimerFormated() {
        return getRestApi().disclaimerFormatedText();
    }

    public Call<FormattedTextModel> underConstruction() {
        return getRestApi().underConstructionFormatedText();
    }

    public Call<List<UlusalTvModel>> chromeTabFetch() {
        return getRestApi().chromeTab();
    }

    public Call<List<UlusalTvCategoryModel>> turkishLiveTvCategoryFetch() {
        return getRestApi().turkishLiveTvCategory();
    }

    public Call<List<UlusalTvModel>> turkishLoveTvCategoryDetailsFetch(String category) {
        return getRestApi().turkishLiveTvCategoryDetails(category);
    }

}
