package com.test.androidtest.presenter;

import com.test.androidtest.domain.logic.implementation.GetPostsImpl;
import com.test.androidtest.domain.logic.interfaces.GetPosts;
import com.test.androidtest.domain.model.Post;
import com.test.androidtest.presenter.interfaces.GetPostsCallback;

import java.util.List;

/**
 * Created by Usama Sarwar on 4/1/2017.
 */

public class PostPresenter implements GetPosts.Callback {

    GetPosts getPosts;
    GetPostsCallback callback;

    public PostPresenter() {
        getPosts = new GetPostsImpl();
    }

    public void getAllPosts(GetPostsCallback callback) {
        this.callback = callback;
        getPosts.execute(this);
    }
    @Override
    public void notifyPosts(final List<Post> posts) {
        if(callback != null) {
            callback.onPostsFetched(posts);
        }
    }

    @Override
    public void notifyError(final String errorMessage) {
        if(callback != null) {
            callback.onPostsError(errorMessage);
        }
    }
}
