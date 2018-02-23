package com.futureuniverse.fgandroidtest.presenter;

import com.futureuniverse.fgandroidtest.domain.logic.implementation.GetPostCommentsImpl;
import com.futureuniverse.fgandroidtest.domain.logic.implementation.GetPostsImpl;
import com.futureuniverse.fgandroidtest.domain.logic.interfaces.GetPostComments;
import com.futureuniverse.fgandroidtest.domain.logic.interfaces.GetPosts;
import com.futureuniverse.fgandroidtest.domain.model.Comment;
import com.futureuniverse.fgandroidtest.domain.model.Post;
import com.futureuniverse.fgandroidtest.presenter.interfaces.GetPostCommentsCallback;
import com.futureuniverse.fgandroidtest.presenter.interfaces.GetPostsCallback;

import java.util.List;

/**
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
