package com.albertleal.gimbernat.scenes.boot;

import com.albertleal.gimbernat.helpers.Callback;
import com.albertleal.gimbernat.scenes.boot.interfaces.IBootActivity;
import com.albertleal.gimbernat.scenes.boot.interfaces.IBootInteractor;
import com.albertleal.gimbernat.scenes.boot.interfaces.IBootPresenter;

public class BootPresenter implements IBootPresenter {

    //MVP Variables
    private IBootActivity view;
    private IBootInteractor interactor;

    public BootPresenter(IBootActivity view){
        this.view = view;
        this.interactor = new BootInteractor();
    }

    //Interface IBootPresenter
    @Override
    public void bootApplication() {

        this.interactor.isUserLoggedIn(new Callback() {
            @Override
            public void onSuccess(Object responseObject) {
                BootPresenter.this.view.navigateToPrivate();
            }

            @Override
            public void onError() {
                BootPresenter.this.view.navigateToPublic();
            }
        });
    }
}
