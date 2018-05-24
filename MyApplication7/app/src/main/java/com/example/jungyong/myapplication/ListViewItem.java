package com.example.jungyong.myapplication;

/**
 * Created by jungyong on 2017-10-05.
 */

public class ListViewItem {
    String title;
    String content;
    int imgIcon;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getImgIcon() {
        return imgIcon;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImgIcon(int imgIcon) {
        this.imgIcon = imgIcon;
    }

    public ListViewItem(String title, String content, int imgIcon){
        this.content=content;
        this.imgIcon=imgIcon;
        this.title=title;
    }
}
