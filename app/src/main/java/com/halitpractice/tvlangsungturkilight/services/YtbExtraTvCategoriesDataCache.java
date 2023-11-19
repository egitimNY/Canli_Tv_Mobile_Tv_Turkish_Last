package com.halitpractice.tvlangsungturkilight.services;

import com.halitpractice.tvlangsungturkilight.models.YtbExtraTvCategoryModel;

import java.util.List;

public class YtbExtraTvCategoriesDataCache {
    private List<YtbExtraTvCategoryModel> cachedData;
    private static final YtbExtraTvCategoriesDataCache instance = new YtbExtraTvCategoriesDataCache();

    public static YtbExtraTvCategoriesDataCache getInstance() {
        return instance;
    }

    public List<YtbExtraTvCategoryModel> getCachedData() {
        return cachedData;
    }

    public void setCachedData(List<YtbExtraTvCategoryModel> data) {
        cachedData = data;
    }
}

