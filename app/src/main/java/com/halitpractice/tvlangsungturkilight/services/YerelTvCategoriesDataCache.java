package com.halitpractice.tvlangsungturkilight.services;

import com.halitpractice.tvlangsungturkilight.models.YerelTvCategoryModel;

import java.util.List;

public class YerelTvCategoriesDataCache {
    private List<YerelTvCategoryModel> cachedData;
    private static final YerelTvCategoriesDataCache instance = new YerelTvCategoriesDataCache();

    public static YerelTvCategoriesDataCache getInstance() {
        return instance;
    }

    public List<YerelTvCategoryModel> getCachedData() {
        return cachedData;
    }

    public void setCachedData(List<YerelTvCategoryModel> data) {
        cachedData = data;
    }
}

