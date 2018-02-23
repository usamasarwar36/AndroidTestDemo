package com.futureuniverse.fgandroidtest.presenter.interfaces;

import com.futureuniverse.fgandroidtest.domain.model.Post;

import java.util.List;

/**
 * Created by Usama Sarwar on 4/1/2017.
 */

public interface GetPostsCallback {
    void onPostsFetched(List<Post> posts);
    void onPostsError(String errorMessage);
}
