package com.test.androidtest.presenter;

import com.test.androidtest.domain.logic.implementation.GetPostCommentsImpl;
import com.test.androidtest.domain.logic.interfaces.GetPostComments;
import com.test.androidtest.domain.model.Comment;
import com.test.androidtest.presenter.interfaces.GetPostCommentsCallback;

import java.util.List;

/***
 * Created by Usama Sarwar on 4/1/2017.
 */

public class CommentsPresenter implements GetPostComments.Callback {

    GetPostComments getPostComments;
    GetPostCommentsCallback callback;

    public CommentsPresenter() {
        getPostComments = new GetPostCommentsImpl();
    }

    public void getPostComments(int postId, GetPostCommentsCallback callback) {
        this.callback = callback;
        getPostComments.execute(postId, this);
    }
    @Override
    public void notifyComments(final List<Comment> comments) {
        if(callback != null) {
            callback.onPostCommentsFetched(comments);
        }
    }

    @Override
    public void notifyError(final String errorMessage) {
        if(callback != null) {
            callback.onPostCommentsError(errorMessage);
        }
    }
}
