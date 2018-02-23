package com.futureuniverse.fgandroidtest.domain.logic.interfaces;

import com.futureuniverse.fgandroidtest.domain.model.User;

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
