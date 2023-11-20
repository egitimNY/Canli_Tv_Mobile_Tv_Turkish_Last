package com.halitpractice.tvlangsungturkilight.services;

import com.halitpractice.tvlangsungturkilight.models.YerelTvModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DunyaTvCategoriesDetailsDataCache {
    private Map<String, List<YerelTvModel>> categoryCaches = new HashMap<>();
    private static DunyaTvCategoriesDetailsDataCache instance;

    public static DunyaTvCategoriesDetailsDataCache getInstance() {
        if (instance == null) {
            instance = new DunyaTvCategoriesDetailsDataCache();
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


