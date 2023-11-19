package com.halitpractice.tvlangsungturkilight.services;

import com.halitpractice.tvlangsungturkilight.models.YtbExtraTvModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YtbExtraTvCategoriesDetailsDataCache {
    private Map<String, List<YtbExtraTvModel>> categoryCaches = new HashMap<>();
    private static YtbExtraTvCategoriesDetailsDataCache instance;

    public static YtbExtraTvCategoriesDetailsDataCache getInstance() {
        if (instance == null) {
            instance = new YtbExtraTvCategoriesDetailsDataCache();
        }
        return instance;
    }

    public List<YtbExtraTvModel> getCachedData(String categoryName) {
        return categoryCaches.get(categoryName);
    }

    public void setCachedData(String categoryName, List<YtbExtraTvModel> data) {
        categoryCaches.put(categoryName, data);
    }
}


