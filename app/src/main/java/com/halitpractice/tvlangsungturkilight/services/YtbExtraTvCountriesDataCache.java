package com.halitpractice.tvlangsungturkilight.services;

import com.halitpractice.tvlangsungturkilight.models.YtbExtraTvCategoryModel;

import java.util.List;

public class YtbExtraTvCountriesDataCache {
    private List<YtbExtraTvCategoryModel> cachedData;
    private static final YtbExtraTvCountriesDataCache instance = new YtbExtraTvCountriesDataCache();

    public static YtbExtraTvCountriesDataCache getInstance() {
        return instance;
    }

    public List<YtbExtraTvCategoryModel> getCachedData() {
        return cachedData;
    }

    public void setCachedData(List<YtbExtraTvCategoryModel> data) {
        cachedData = data;
    }
}

