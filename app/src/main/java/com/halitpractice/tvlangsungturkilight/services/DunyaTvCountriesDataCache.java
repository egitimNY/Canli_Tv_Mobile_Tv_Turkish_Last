package com.halitpractice.tvlangsungturkilight.services;

import com.halitpractice.tvlangsungturkilight.models.DunyaTvCategoryModel;

import java.util.List;

public class DunyaTvCountriesDataCache {
    private List<DunyaTvCategoryModel> cachedData;
    private static final DunyaTvCountriesDataCache instance = new DunyaTvCountriesDataCache();

    public static DunyaTvCountriesDataCache getInstance() {
        return instance;
    }

    public List<DunyaTvCategoryModel> getCachedData() {
        return cachedData;
    }

    public void setCachedData(List<DunyaTvCategoryModel> data) {
        cachedData = data;
    }
}

