package com.test.androidtest.presenter.interfaces;

import com.test.androidtest.domain.model.User;

/**
 * Created by Usama Sarwar on 4/1/2017.
 */

public interface GetUserCallback {
    void onUserFetched(User user);
    void onUserError(String errorMessage);
}
