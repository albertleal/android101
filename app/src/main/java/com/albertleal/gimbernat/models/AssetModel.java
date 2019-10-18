package com.albertleal.gimbernat.models;

public class AssetModel {
    public String id;
    public String url;
    public String description;
    public int category;

    public AssetModel(String id, String url, String description, int category) {
        this.id = id;
        this.url = url;
        this.description = description;
        this.category = category;
    }
}