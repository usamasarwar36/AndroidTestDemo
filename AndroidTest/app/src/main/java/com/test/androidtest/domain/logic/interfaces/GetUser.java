package com.test.androidtest.domain.logic.interfaces;

import com.test.androidtest.domain.model.User;

/**
 * Created by Usama Sarwar on 4/1/2017.
 */

public interface GetUser {
    interface Callback {
        void notifyUser(User user);
        void notifyError(String errorMessage);
    }
    void execute(int userId, Callback callback);
}
