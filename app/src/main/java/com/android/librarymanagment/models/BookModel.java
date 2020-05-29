package com.android.librarymanagment.models;

import java.io.Serializable;

public class BookModel implements Serializable {

    private  int id ;
    private  String title ;
    private  int acquired ;
    private  String duration ;
    private  int top_rated;


    public BookModel(int id, String title, int acquired, String duration, int top_rated) {
        this.id = id;
        this.title = title;
        this.acquired = acquired;
        this.duration = duration;
        this.top_rated = top_rated;
    }

    @Override
    public String toString() {
        return "BookModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", acquired=" + acquired +
                ", duration='" + duration + '\'' +
                ", top_rated=" + top_rated +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAcquired(int acquired) {
        this.acquired = acquired;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setTop_rated(int top_rated) {
        this.top_rated = top_rated;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getAcquired() {
        return acquired;
    }

    public String getDuration() {
        return duration;
    }

    public int getTop_rated() {
        return top_rated;
    }
}

