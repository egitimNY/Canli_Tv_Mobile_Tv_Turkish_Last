package com.halitpractice.tvlangsungturkilight.services;

import com.halitpractice.tvlangsungturkilight.models.UlusalTvModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UlusalTvCategoriesDetailsDataCache {
    private Map<String, List<UlusalTvModel>> categoryCaches = new HashMap<>();
    private static UlusalTvCategoriesDetailsDataCache instance;

    public static UlusalTvCategoriesDetailsDataCache getInstance() {
        if (instance == null) {
            instance = new UlusalTvCategoriesDetailsDataCache();
        }
        return instance;
    }

    public List<UlusalTvModel> getCachedData(String categoryName) {
        return categoryCaches.get(categoryName);
    }

    public void setCachedData(String categoryName, List<UlusalTvModel> data) {
        categoryCaches.put(categoryName, data);
    }
}


