package com.albertleal.gimbernat.models;

public class AssetModel {
    public String id;
    public String url;
    public String description;
    public String title;
    public int category;
    public String latitude;
    public String longitude;

    public AssetModel(String id, String url, String title, String description, int category, String latitude, String longitude) {
        this.id = id;
        this.url = url;
        this.description = description;
        this.title = title;
        this.category = category;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}