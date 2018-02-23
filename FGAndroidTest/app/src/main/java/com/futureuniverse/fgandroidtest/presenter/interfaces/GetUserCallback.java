package com.futureuniverse.fgandroidtest.presenter.interfaces;

import com.futureuniverse.fgandroidtest.domain.model.User;

/**
 * Created by Usama Sarwar on 4/1/2017.
 */

public interface GetUserCallback {
    void onUserFetched(User user);
    void onUserError(String errorMessage);
}
