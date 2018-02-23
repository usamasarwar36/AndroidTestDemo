package com.futureuniverse.fgandroidtest.presenter.interfaces;

import com.futureuniverse.fgandroidtest.domain.model.Comment;
import com.futureuniverse.fgandroidtest.domain.model.Post;

import java.util.List;

/**
 * Created by Usama Sarwar on 4/1/2017.
 */

public interface GetPostCommentsCallback {
    void onPostCommentsFetched(List<Comment> comments);
    void onPostCommentsError(String errorMessage);
}
