package com.albertleal.gimbernat.scenes.terms;

import com.albertleal.gimbernat.helpers.Callback;
import com.albertleal.gimbernat.datasources.SessionDataSource;
import com.albertleal.gimbernat.scenes.terms.interfaces.ITermsActivity;
import com.albertleal.gimbernat.scenes.terms.interfaces.ITermsPresenter;

public class TermsPresenter implements ITermsPresenter {

    //MVP Variables
    private ITermsActivity view;

    public TermsPresenter(ITermsActivity view){
        this.view = view;
    }

    //Interface ITermsPresenter
    @Override
    public void privateButtonPressed() {
        this.view.showSpinner();
        SessionDataSource.shared.SignIn(new Callback() {
            @Override
            public void onSuccess(Object responseObject) {
                TermsPresenter.this.view.navigateToPrivate();
                TermsPresenter.this.view.hideSpinner();
            }

            @Override
            public void onError() {
                TermsPresenter.this.view.hideSpinner();
                TermsPresenter.this.view.showError("Can't create a valid firebase user");
            }
        });

    }

}
