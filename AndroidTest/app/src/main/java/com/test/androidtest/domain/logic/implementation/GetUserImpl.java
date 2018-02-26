package com.test.androidtest.domain.logic.implementation;

import com.test.androidtest.base.executor.implementation.ExecutorImpl;
import com.test.androidtest.base.executor.implementation.UIThreadImpl;
import com.test.androidtest.base.executor.interfaces.UIThread;
import com.test.androidtest.domain.logic.interfaces.GetUser;
import com.test.androidtest.domain.model.User;
import com.test.androidtest.rest.RestClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Usama Sarwar on 4/1/2017.
 */

public class GetUserImpl implements GetUser {

    private static GetUser getUser;
    Callback callback;
    UIThread uiThread;

    public static GetUser getInstance() {
        if(getUser == null) {
            getUser = new GetUserImpl();
        }
        return getUser;
    }

    private GetUserImpl(){}

    @Override
    public void execute(final int userId, Callback callback) {
        this.callback = callback;
        this.uiThread = UIThreadImpl.getInstance();

        ExecutorImpl.getInstance().run(new Runnable() {
            @Override
            public void run() {
                Call<List<User>> callGetUserWithId = RestClient.getClient().getUserById(userId);
                callGetUserWithId.enqueue(new retrofit2.Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        List<User> users = new ArrayList<User>();
                        if(response.isSuccessful()) {
                            users = response.body();
                            if(users.size() != 0) {
                                notifySuccess(users.get(0));
                            } else {
                                notifySuccess(null);
                            }

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
                    public void onFailure(Call<List<User>> call, Throwable t) {
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

    private void notifySuccess(final User user) {
        uiThread.post(new Runnable() {
            @Override
            public void run() {
                callback.notifyUser(user);
            }
        });
    }
}
