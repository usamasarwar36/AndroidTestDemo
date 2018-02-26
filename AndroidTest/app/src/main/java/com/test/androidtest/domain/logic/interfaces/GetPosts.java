package com.test.androidtest.domain.logic.interfaces;

import com.test.androidtest.domain.model.Post;

import java.util.List;

/**
 * Created by Usama Sarwar on 4/1/2017.
 */

public interface GetPosts {

    String KEY_PAPER_POSTS = "posts";

    interface Callback {
        void notifyPosts(List<Post> posts);
        void notifyError(String errorMessage);
    }
    void execute(Callback callback);
}
