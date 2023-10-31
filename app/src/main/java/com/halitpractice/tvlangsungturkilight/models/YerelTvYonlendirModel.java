package com.halitpractice.tvlangsungturkilight.models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class YerelTvYonlendirModel implements Serializable {
    private int id;
    private String name;
    private String live_url;
    private String thumbnail;

    public YerelTvYonlendirModel(int id, String name, String live_url, String thumbnail) {
        this.id = id;
        this.name = name;
        this.live_url = live_url;
        this.thumbnail = thumbnail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLive_url() {
        return live_url;
    }

    public void setLive_url(String live_url) {
        this.live_url = live_url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @NonNull
    @Override
    public String toString() {
        return "YerelTvModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", live_url='" + live_url + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }

}
