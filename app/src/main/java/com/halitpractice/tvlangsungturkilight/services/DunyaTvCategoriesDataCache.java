package com.halitpractice.tvlangsungturkilight.services;

import com.halitpractice.tvlangsungturkilight.models.YerelTvCategoryModel;

import java.util.List;

public class DunyaTvCategoriesDataCache {
    private List<YerelTvCategoryModel> cachedData;
    private static final DunyaTvCategoriesDataCache instance = new DunyaTvCategoriesDataCache();

    public static DunyaTvCategoriesDataCache getInstance() {
        return instance;
    }

    public List<YerelTvCategoryModel> getCachedData() {
        return cachedData;
    }

    public void setCachedData(List<YerelTvCategoryModel> data) {
        cachedData = data;
    }
}

