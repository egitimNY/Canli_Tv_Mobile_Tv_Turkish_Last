package com.halitpractice.tvlangsungturkilight.services;

import com.halitpractice.tvlangsungturkilight.models.DunyaTvCategoryModel;

import java.util.List;

public class DunyaTvCategoriesDataCache {
    private List<DunyaTvCategoryModel> cachedData;
    private static final DunyaTvCategoriesDataCache instance = new DunyaTvCategoriesDataCache();

    public static DunyaTvCategoriesDataCache getInstance() {
        return instance;
    }

    public List<DunyaTvCategoryModel> getCachedData() {
        return cachedData;
    }

    public void setCachedData(List<DunyaTvCategoryModel> data) {
        cachedData = data;
    }
}
