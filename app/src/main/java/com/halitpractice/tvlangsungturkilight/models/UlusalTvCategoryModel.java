package com.halitpractice.tvlangsungturkilight.models;

import java.io.Serializable;

public class UlusalTvCategoryModel implements Serializable {
    private int id;
    private String name;
    private String image_url;

    public UlusalTvCategoryModel(int id, String name, String image_url) {
        this.id = id;
        this.name = name;
        this.image_url = image_url;
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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @Override
    public String toString() {
        return "UlusalTvCategoryModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image_url='" + image_url + '\'' +
                '}';
    }

}
