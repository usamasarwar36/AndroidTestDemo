package com.test.androidtest.domain.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Usama Sarwar on 3/31/2017.
 */

public class Comment {
/*
    {
        "postId": 1,
        "id": 1,
        "name": "",
        "email": "",
        "body": ""
    }
    */
    @SerializedName("postId") private int postId;
    @SerializedName("id") private int id;
    @SerializedName("name") private String name;
    @SerializedName("email") private String email;
    @SerializedName("body") private String body;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
