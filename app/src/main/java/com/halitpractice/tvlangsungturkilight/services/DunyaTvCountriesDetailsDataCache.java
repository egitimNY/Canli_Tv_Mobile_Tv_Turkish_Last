package com.halitpractice.tvlangsungturkilight.services;

import com.halitpractice.tvlangsungturkilight.models.DunyaTvModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DunyaTvCountriesDetailsDataCache {
    private Map<String, List<DunyaTvModel>> categoryCaches = new HashMap<>();
    private static DunyaTvCountriesDetailsDataCache instance;

    public static DunyaTvCountriesDetailsDataCache getInstance() {
        if (instance == null) {
            instance = new DunyaTvCountriesDetailsDataCache();
        }
        return instance;
    }

    public List<DunyaTvModel> getCachedData(String categoryName) {
        return categoryCaches.get(categoryName);
    }

    public void setCachedData(String categoryName, List<DunyaTvModel> data) {
        categoryCaches.put(categoryName, data);
    }
}


