package com.halitpractice.tvlangsungturkilight.services;

import com.halitpractice.tvlangsungturkilight.models.YerelTvCategoryModel;

import java.util.List;

public class DunyaTvCountriesDataCache {
    private List<YerelTvCategoryModel> cachedData;
    private static final DunyaTvCountriesDataCache instance = new DunyaTvCountriesDataCache();

    public static DunyaTvCountriesDataCache getInstance() {
        return instance;
    }

    public List<YerelTvCategoryModel> getCachedData() {
        return cachedData;
    }

    public void setCachedData(List<YerelTvCategoryModel> data) {
        cachedData = data;
    }
}

