package com.halitpractice.tvlangsungturkilight.models;

import java.io.Serializable;

public class UlusalTvModel implements Serializable {
    private int id;
    private String name;
    private String live_url;
    private String thumbnail;
    private String category;

    public UlusalTvModel(int id, String name, String live_url, String thumbnail, String category) {
        this.id = id;
        this.name = name;
        this.live_url = live_url;
        this.thumbnail = thumbnail;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "UlusalTvModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", live_url='" + live_url + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

}
