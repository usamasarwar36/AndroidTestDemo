package com.futureuniverse.fgandroidtest.presenter;

import com.futureuniverse.fgandroidtest.base.executor.implementation.ExecutorImpl;
import com.futureuniverse.fgandroidtest.base.executor.interfaces.Executor;
import com.futureuniverse.fgandroidtest.base.executor.interfaces.UIThread;
import com.futureuniverse.fgandroidtest.base.executor.implementation.UIThreadImpl;
import com.futureuniverse.fgandroidtest.domain.logic.implementation.GetPostsImpl;
import com.futureuniverse.fgandroidtest.domain.logic.interfaces.GetPosts;
import com.futureuniverse.fgandroidtest.domain.model.Post;
import com.futureuniverse.fgandroidtest.presenter.interfaces.GetPostsCallback;

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
