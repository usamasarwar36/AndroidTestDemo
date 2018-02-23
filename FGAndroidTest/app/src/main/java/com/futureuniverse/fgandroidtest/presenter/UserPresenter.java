package com.futureuniverse.fgandroidtest.presenter;

import com.futureuniverse.fgandroidtest.domain.logic.implementation.GetPostCommentsImpl;
import com.futureuniverse.fgandroidtest.domain.logic.implementation.GetUserImpl;
import com.futureuniverse.fgandroidtest.domain.logic.interfaces.GetPostComments;
import com.futureuniverse.fgandroidtest.domain.logic.interfaces.GetUser;
import com.futureuniverse.fgandroidtest.domain.model.Comment;
import com.futureuniverse.fgandroidtest.domain.model.User;
import com.futureuniverse.fgandroidtest.presenter.interfaces.GetPostCommentsCallback;
import com.futureuniverse.fgandroidtest.presenter.interfaces.GetUserCallback;

import java.util.List;

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
