package com.halitpractice.tvlangsungturkilight.services;

import com.halitpractice.tvlangsungturkilight.models.YtbExtraTvModel;

import java.util.List;

public class YtbExtraTvDataCache {
    private List<YtbExtraTvModel> cachedData;
    private static final YtbExtraTvDataCache instance = new YtbExtraTvDataCache();

    public static YtbExtraTvDataCache getInstance() {
        return instance;
    }

    public List<YtbExtraTvModel> getCachedData() {
        return cachedData;
    }

    public void setCachedData(List<YtbExtraTvModel> data) {
        cachedData = data;
    }
}

