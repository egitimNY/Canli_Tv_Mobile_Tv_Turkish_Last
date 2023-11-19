package com.halitpractice.tvlangsungturkilight.services;

import com.halitpractice.tvlangsungturkilight.models.GazetelerModel;

import java.util.List;

public class GazetelerDataCache {
    private List<GazetelerModel> cachedData;
    private static final GazetelerDataCache instance = new GazetelerDataCache();

    public static GazetelerDataCache getInstance() {
        return instance;
    }

    public List<GazetelerModel> getCachedData() {
        return cachedData;
    }

    public void setCachedData(List<GazetelerModel> data) {
        cachedData = data;
    }
}

