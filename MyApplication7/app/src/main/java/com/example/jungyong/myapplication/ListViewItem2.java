package com.example.jungyong.myapplication;

import android.graphics.Bitmap;

public class ListViewItem2 {
    String title;
    String content;
    Bitmap imgIcon;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Bitmap getImgIcon() {
        return imgIcon;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImgIcon(Bitmap imgIcon) {
        this.imgIcon = imgIcon;
    }

    public ListViewItem2(String title, String content, Bitmap imgIcon){
        this.content=content;
        this.imgIcon=imgIcon;
        this.title=title;
    }
}
