package com.halitpractice.tvlangsungturkilight.models;

import androidx.annotation.NonNull;

public class RadyoDinleModel {
    private String image;
    private String channelname;
    private String videoid;

    public RadyoDinleModel(String image, String channelname, String videoid) {
        this.image = image;
        this.channelname = channelname;
        this.videoid = videoid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getChannelname() {
        return channelname;
    }

    public void setChannelname(String channelname) {
        this.channelname = channelname;
    }

    public String getVideoid() {
        return videoid;
    }

    public void setVideoid(String videoid) {
        this.videoid = videoid;
    }

    @Override
    @NonNull
    public String toString() {
        return "IndiaCartoonYoutubeModel{" +
                "image='" + image + '\'' +
                ", channelname='" + channelname + '\'' +
                ", videoid='" + videoid + '\'' +
                '}';
    }
}