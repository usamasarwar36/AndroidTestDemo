package com.futureuniverse.fgandroidtest.domain.logic.interfaces;

import com.futureuniverse.fgandroidtest.base.executor.interfaces.Executor;
import com.futureuniverse.fgandroidtest.base.executor.interfaces.UIThread;
import com.futureuniverse.fgandroidtest.domain.model.Post;

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
