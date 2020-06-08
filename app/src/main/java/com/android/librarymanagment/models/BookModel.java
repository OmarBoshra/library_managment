package com.android.librarymanagment.models;

import java.io.Serializable;

public class BookModel implements Serializable {

    private  long id ;
    private  String title ;
    private  String acquired ;
    private  String duration ;
    private  int top_rated;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAcquired() {
        return acquired;
    }

    public void setAcquired(String acquired) {
        this.acquired = acquired;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getTop_rated() {
        return top_rated;
    }

    public void setTop_rated(int top_rated) {
        this.top_rated = top_rated;
    }

    public BookModel(long id, String title, String acquired, String duration, int top_rated) {
        this.id = id;
        this.title = title;
        this.acquired = acquired;
        this.duration = duration;
        this.top_rated = top_rated;
    }
}

