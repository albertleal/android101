package com.albertleal.gimbernat.scenes.boot;

import com.albertleal.gimbernat.helpers.Callback;
import com.albertleal.gimbernat.datasources.SessionDataSource;
import com.albertleal.gimbernat.scenes.boot.interfaces.IBootInteractor;

public class BootInteractor implements IBootInteractor {

    //Interface IBootInteractor
    @Override
    public void isUserLoggedIn(Callback callback) {

        if (SessionDataSource.shared.isUserLogedIn()) {
            callback.onSuccess(null);
        }else {
            callback.onError();
        }
    }
}
