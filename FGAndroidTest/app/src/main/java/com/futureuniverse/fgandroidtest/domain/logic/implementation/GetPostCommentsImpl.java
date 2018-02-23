package com.futureuniverse.fgandroidtest.domain.logic.implementation;

import com.futureuniverse.fgandroidtest.base.executor.implementation.ExecutorImpl;
import com.futureuniverse.fgandroidtest.base.executor.implementation.UIThreadImpl;
import com.futureuniverse.fgandroidtest.base.executor.interfaces.UIThread;
import com.futureuniverse.fgandroidtest.domain.logic.interfaces.GetPostComments;
import com.futureuniverse.fgandroidtest.domain.logic.interfaces.GetUser;
import com.futureuniverse.fgandroidtest.domain.model.Comment;
import com.futureuniverse.fgandroidtest.domain.model.User;
import com.futureuniverse.fgandroidtest.rest.RestClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Usama Sarwar on 4/1/2017.
 */

public class GetPostCommentsImpl implements GetPostComments {

    Callback callback;
    UIThread uiThread;

    @Override
    public void execute(final int postId, Callback callback) {
        this.callback = callback;
        this.uiThread = UIThreadImpl.getInstance();

        ExecutorImpl.getInstance().run(new Runnable() {
            @Override
            public void run() {
                Call<List<Comment>> callGetPostComments = RestClient.getClient().getCommentsByPostId(postId);
                callGetPostComments.enqueue(new retrofit2.Callback<List<Comment>>() {
                    @Override
                    public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                        List<Comment> comments = new ArrayList<Comment>();
                        if(response.isSuccessful()) {
                            comments = response.body();
                            notifySuccess(comments);

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
                    public void onFailure(Call<List<Comment>> call, Throwable t) {
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

    private void notifySuccess(final List<Comment> comments) {
        uiThread.post(new Runnable() {
            @Override
            public void run() {
                callback.notifyComments(comments);
            }
        });
    }
}
