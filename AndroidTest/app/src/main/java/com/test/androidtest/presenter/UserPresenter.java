package com.test.androidtest.presenter;

import com.test.androidtest.domain.logic.implementation.GetUserImpl;
import com.test.androidtest.domain.logic.interfaces.GetUser;
import com.test.androidtest.domain.model.User;
import com.test.androidtest.presenter.interfaces.GetUserCallback;

/**
 * Created by Usama Sarwar on 4/1/2017.
 */

public class UserPresenter implements GetUser.Callback {

    GetUser getUser;
    GetUserCallback callback;

    public UserPresenter() {
        getUser = GetUserImpl.getInstance();
    }

    public void getUserById(int userId, GetUserCallback callback) {
        this.callback = callback;
        getUser.execute(userId, this);
    }
    @Override
    public void notifyUser(final User user) {
        if(callback != null) {
            callback.onUserFetched(user);
        }
    }

    @Override
    public void notifyError(final String errorMessage) {
        if(callback != null) {
            callback.onUserError(errorMessage);
        }
    }
}
