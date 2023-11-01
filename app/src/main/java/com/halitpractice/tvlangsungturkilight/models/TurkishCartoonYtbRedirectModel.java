package com.halitpractice.tvlangsungturkilight.models;

public class TurkishCartoonYtbRedirectModel {
    private String image;
    private String channelname;
    private String videoid;

    public TurkishCartoonYtbRedirectModel(String image, String channelname, String videoid) {
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
    public String toString() {
        return "IndiaCartoonYtbRedirectModel{" +
                "image='" + image + '\'' +
                ", channelname='" + channelname + '\'' +
                ", videoid='" + videoid + '\'' +
                '}';
    }
}