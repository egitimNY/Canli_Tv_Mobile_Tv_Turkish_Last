package com.halitpractice.tvlangsungturkilight.services;

import com.halitpractice.tvlangsungturkilight.models.UlusalTvModel;

import java.util.List;

public class UlusalTvDataCache {
    private List<UlusalTvModel> cachedData;
    private static final UlusalTvDataCache instance = new UlusalTvDataCache();

    public static UlusalTvDataCache getInstance() {
        return instance;
    }

    public List<UlusalTvModel> getCachedData() {
        return cachedData;
    }

    public void setCachedData(List<UlusalTvModel> data) {
        cachedData = data;
    }
}

