package com.halitpractice.tvlangsungturkilight.services;

import com.halitpractice.tvlangsungturkilight.models.YerelTvModel;

import java.util.List;

public class YerelTvDataCache {
    private List<YerelTvModel> cachedData;
    private static final YerelTvDataCache instance = new YerelTvDataCache();

    public static YerelTvDataCache getInstance() {
        return instance;
    }

    public List<YerelTvModel> getCachedData() {
        return cachedData;
    }

    public void setCachedData(List<YerelTvModel> data) {
        cachedData = data;
    }
}

