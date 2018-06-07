package com.example.jungyong.myapplication;

public class ListViewItem3 {

    String tel; //전화번호
    String con;  //혼잡도
    String tag_1; //메뉴태그
    String tag_2; //이미지태그

    public ListViewItem3(String tel, String con, String tag_1, String tag_2) {
        this.tel = tel;
        this.con = con;
        this.tag_1 = tag_1;
        this.tag_2 = tag_2;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCon() {
        return con;
    }

    public void setCon(String con) {
        this.con = con;
    }

    public String getTag_1() {
        return tag_1;
    }

    public void setTag_1(String tag_1) {
        this.tag_1 = tag_1;
    }

    public String getTag_2() {
        return tag_2;
    }

    public void setTag_2(String tag_2) {
        this.tag_2 = tag_2;
    }
}
