package com.halitpractice.tvlangsungturkilight.services;

import com.halitpractice.tvlangsungturkilight.models.UlusalTvCategoryModel;

import java.util.List;

public class UlusalTvCategoriesDataCache {
    private List<UlusalTvCategoryModel> cachedData;
    private static final UlusalTvCategoriesDataCache instance = new UlusalTvCategoriesDataCache();

    public static UlusalTvCategoriesDataCache getInstance() {
        return instance;
    }

    public List<UlusalTvCategoryModel> getCachedData() {
        return cachedData;
    }

    public void setCachedData(List<UlusalTvCategoryModel> data) {
        cachedData = data;
    }
}

