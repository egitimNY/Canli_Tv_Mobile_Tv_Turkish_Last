package com.halitpractice.tvlangsungturkilight.services;

import com.halitpractice.tvlangsungturkilight.models.YtbExtraTvLanguageModel;

import java.util.List;

public class YtbExtraTvLanguageDataCache {
    private List<YtbExtraTvLanguageModel> cachedData;
    private static final YtbExtraTvLanguageDataCache instance = new YtbExtraTvLanguageDataCache();

    public static YtbExtraTvLanguageDataCache getInstance() {
        return instance;
    }

    public List<YtbExtraTvLanguageModel> getCachedData() {
        return cachedData;
    }

    public void setCachedData(List<YtbExtraTvLanguageModel> data) {
        cachedData = data;
    }
}

