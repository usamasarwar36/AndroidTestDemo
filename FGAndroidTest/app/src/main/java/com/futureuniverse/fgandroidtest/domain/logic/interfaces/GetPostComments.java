package com.futureuniverse.fgandroidtest.domain.logic.interfaces;

import com.futureuniverse.fgandroidtest.domain.model.Comment;
import com.futureuniverse.fgandroidtest.domain.model.User;

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
