package com.test.androidtest.domain.logic.implementation;

import com.test.androidtest.base.executor.implementation.ExecutorImpl;
import com.test.androidtest.base.executor.implementation.UIThreadImpl;
import com.test.androidtest.base.executor.interfaces.UIThread;
import com.test.androidtest.domain.logic.interfaces.GetPosts;
import com.test.androidtest.domain.model.Post;
import com.test.androidtest.rest.RestClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Usama Sarwar on 4/1/2017.
 */

public class GetPostsImpl implements GetPosts {

    Callback callback;
    UIThread uiThread;

    @Override
    public void execute(Callback callback) {
        this.callback = callback;
        this.uiThread = UIThreadImpl.getInstance();

        ExecutorImpl.getInstance().run(new Runnable() {
            @Override
            public void run() {
                // this is useful when internet connection is weak.
                // It will return the local copy if present and go to network for updated data
                List<Post> posts = Paper.book().read(KEY_PAPER_POSTS, null);
                if(posts != null) {
                    notifySuccess(posts);
                }

                Call<List<Post>> callGetPosts = RestClient.getClient().getAllPosts();
                callGetPosts.enqueue(new retrofit2.Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                        List<Post> posts = new ArrayList<Post>();
                        if(response.isSuccessful()) {
                            posts = response.body();
                            Paper.book().write(KEY_PAPER_POSTS, posts);
                            notifySuccess(posts);
                        } else {
                            ResponseBody errorBody = response.errorBody();
                            String errorMessage = "";
                            try {
                                errorMessage = errorBody.string();
                            } catch (IOException e) {
                                e.printStackTrace();
                                errorMessage = "Your request could not be executed at this time.";
                            }
                            notifyFailure(errorMessage);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {
                        notifyFailure(t.getMessage());
                    }
                });
            }
        });
    }

    private void notifyFailure(final String errorMessage) {
        uiThread.post(new Runnable() {
            @Override
            public void run() {
                callback.notifyError(errorMessage);
            }
        });
    }

    private void notifySuccess(final List<Post> posts) {
        uiThread.post(new Runnable() {
            @Override
            public void run() {
                callback.notifyPosts(posts);
            }
        });
    }
}
