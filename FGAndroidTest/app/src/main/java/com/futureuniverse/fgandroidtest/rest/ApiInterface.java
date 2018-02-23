package com.futureuniverse.fgandroidtest.rest;

import com.futureuniverse.fgandroidtest.domain.model.Comment;
import com.futureuniverse.fgandroidtest.domain.model.Post;
import com.futureuniverse.fgandroidtest.domain.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Usama Sarwar on 3/31/2017.
 */

public interface ApiInterface {

    @GET("posts")
    Call<List<Post>> getAllPosts();

    @GET("users")
    Call<List<User>> getAllUsers();

    @GET("users")
    Call<List<User>> getUserById(@Query("id") int userId);

    @GET("comments")
    Call<List<Comment>> getAllComments();

    @GET("comments")
    Call<List<Comment>> getCommentsByPostId(@Query("postId") int postId);

}
