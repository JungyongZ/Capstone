package com.example.jungyong.myapplication;

/***
 * Created by jungyong on 2017-10-05.
 */

public class ListViewItem {
    String title;
    String content;


    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }



    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public ListViewItem(String title, String content){
        this.content=content;

        this.title=title;
    }
}
