package com.example.jungyong.myapplication;

import io.realm.RealmModel;
import io.realm.annotations.Required;

public class Student implements RealmModel {
    @Required
    private String name;
    private int score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
