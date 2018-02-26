package com.test.androidtest.presenter.interfaces;

import com.test.androidtest.domain.model.Comment;

import java.util.List;

/**
 * Created by Usama Sarwar on 4/1/2017.
 */

public interface GetPostCommentsCallback {
    void onPostCommentsFetched(List<Comment> comments);
    void onPostCommentsError(String errorMessage);
}
