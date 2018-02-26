package com.test.androidtest.domain.logic.interfaces;

import com.test.androidtest.domain.model.Comment;

import java.util.List;

/**
 * Created by Usama Sarwar on 4/1/2017.
 */

public interface GetPostComments {
    interface Callback {
        void notifyComments(List<Comment> comments);
        void notifyError(String errorMessage);
    }
    void execute(int postId, Callback callback);
}
