package com.halitpractice.tvlangsungturkilight.services;

import com.halitpractice.tvlangsungturkilight.models.YerelTvModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YerelTvCategoriesDetailsDataCache {
    private Map<String, List<YerelTvModel>> categoryCaches = new HashMap<>();
    private static YerelTvCategoriesDetailsDataCache instance;

    public static YerelTvCategoriesDetailsDataCache getInstance() {
        if (instance == null) {
            instance = new YerelTvCategoriesDetailsDataCache();
        }
        return instance;
    }

    public List<YerelTvModel> getCachedData(String categoryName) {
        return categoryCaches.get(categoryName);
    }

    public void setCachedData(String categoryName, List<YerelTvModel> data) {
        categoryCaches.put(categoryName, data);
    }
}


