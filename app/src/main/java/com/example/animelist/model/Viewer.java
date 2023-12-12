package com.example.animelist.model;

public class Viewer {

    private int id;
    private String name;
    private String about;
    private CoverImage avatar;
    private String bannerImage;
    private Statistics statistics;

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

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public CoverImage getAvatar() {
        return avatar;
    }

    public void setAvatar(CoverImage avatar) {
        this.avatar = avatar;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

}
