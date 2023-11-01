package com.halitpractice.tvlangsungturkilight.services;

import com.halitpractice.tvlangsungturkilight.models.TurkishCartoonYtbModel;

import java.util.List;

public class TurkishCartoonYoutubeDataCache {
    private List<TurkishCartoonYtbModel> cachedData;
    private static final TurkishCartoonYoutubeDataCache instance = new TurkishCartoonYoutubeDataCache();

    public static TurkishCartoonYoutubeDataCache getInstance() {
        return instance;
    }

    public List<TurkishCartoonYtbModel> getCachedData() {
        return cachedData;
    }

    public void setCachedData(List<TurkishCartoonYtbModel> data) {
        cachedData = data;
    }
}

