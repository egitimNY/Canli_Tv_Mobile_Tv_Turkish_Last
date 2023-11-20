package com.halitpractice.tvlangsungturkilight.services;

import com.halitpractice.tvlangsungturkilight.models.YerelTvModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DunyaTvCountriesDetailsDataCache {
    private Map<String, List<YerelTvModel>> categoryCaches = new HashMap<>();
    private static DunyaTvCountriesDetailsDataCache instance;

    public static DunyaTvCountriesDetailsDataCache getInstance() {
        if (instance == null) {
            instance = new DunyaTvCountriesDetailsDataCache();
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


