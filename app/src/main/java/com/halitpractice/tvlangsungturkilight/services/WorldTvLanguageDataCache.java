package com.halitpractice.tvlangsungturkilight.services;

import com.halitpractice.tvlangsungturkilight.models.DunyaTvLanguageModel;

import java.util.List;

public class WorldTvLanguageDataCache {
    private List<DunyaTvLanguageModel> cachedData;
    private static final WorldTvLanguageDataCache instance = new WorldTvLanguageDataCache();

    public static WorldTvLanguageDataCache getInstance() {
        return instance;
    }

    public List<DunyaTvLanguageModel> getCachedData() {
        return cachedData;
    }

    public void setCachedData(List<DunyaTvLanguageModel> data) {
        cachedData = data;
    }
}
