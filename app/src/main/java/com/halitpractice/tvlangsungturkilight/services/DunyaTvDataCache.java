package com.halitpractice.tvlangsungturkilight.services;

import com.halitpractice.tvlangsungturkilight.models.YerelTvModel;

import java.util.List;

public class DunyaTvDataCache {
    private List<YerelTvModel> cachedData;
    private static final DunyaTvDataCache instance = new DunyaTvDataCache();

    public static DunyaTvDataCache getInstance() {
        return instance;
    }

    public List<YerelTvModel> getCachedData() {
        return cachedData;
    }

    public void setCachedData(List<YerelTvModel> data) {
        cachedData = data;
    }
}

