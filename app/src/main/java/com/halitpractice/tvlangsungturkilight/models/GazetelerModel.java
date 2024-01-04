package com.halitpractice.tvlangsungturkilight.models;

import java.io.Serializable;

public class GazetelerModel implements Serializable {
    private int id;
    private String name;
    private String live_url;
    private String thumbnail;
    private String countryname;
    private String regional;

    public GazetelerModel(int id, String name, String live_url, String thumbnail, String countryname, String regional) {
        this.id = id;
        this.name = name;
        this.live_url = live_url;
        this.thumbnail = thumbnail;
        this.countryname = countryname;
        this.regional = regional;
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

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    public String getRegional() {
        return regional;
    }

    public void setRegional(String regional) {
        this.regional = regional;
    }

    @Override
    public String toString() {
        return "GazetelerModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", live_url='" + live_url + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", countryname='" + countryname + '\'' +
                ", regional='" + regional + '\'' +
                '}';
    }

}
