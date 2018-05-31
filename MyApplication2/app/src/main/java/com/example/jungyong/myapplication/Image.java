package com.example.jungyong.myapplication;

import io.realm.RealmObject;

public class Image extends RealmObject {
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
