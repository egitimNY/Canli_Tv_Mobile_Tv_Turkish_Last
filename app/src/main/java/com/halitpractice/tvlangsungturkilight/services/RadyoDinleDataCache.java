package com.halitpractice.tvlangsungturkilight.services;

import com.halitpractice.tvlangsungturkilight.models.RadyoDinleModel;

import java.util.List;

public class RadyoDinleDataCache {
    private List<RadyoDinleModel> cachedData;
    private static final RadyoDinleDataCache instance = new RadyoDinleDataCache();

    public static RadyoDinleDataCache getInstance() {
        return instance;
    }

    public List<RadyoDinleModel> getCachedData() {
        return cachedData;
    }

    public void setCachedData(List<RadyoDinleModel> data) {
        cachedData = data;
    }
}

