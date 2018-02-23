package com.futureuniverse.fgandroidtest.domain.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Usama Sarwar on 3/31/2017.
 */

public class Post {
/*
    {
      "userId": 1,
        "id": 1,
        "title": "",
        "body": ""
    }
    */
    @SerializedName("userId") private int userId;
    @SerializedName("id") private int id;
    @SerializedName("title") private String title;
    @SerializedName("body") private String body;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
