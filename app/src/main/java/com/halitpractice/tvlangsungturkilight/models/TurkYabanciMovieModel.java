package com.halitpractice.tvlangsungturkilight.models;

import com.google.gson.annotations.SerializedName;

public class TurkYabanciMovieModel {

    @SerializedName("urlName")
    private String urlName;

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }
}
