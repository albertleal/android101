package com.albertleal.gimbernat.scenes.detail;

import com.albertleal.gimbernat.helpers.Callback;
import com.albertleal.gimbernat.models.AssetModel;
import com.albertleal.gimbernat.scenes.detail.interfaces.IDetailPresenter;

public class DetailPresenter implements IDetailPresenter {

    //MVP Variables
    private DetailActivity view;
    private DetailInteractor interactor;

    public DetailPresenter(DetailActivity view) {
        this.view = view;
        this.interactor = new DetailInteractor();
    }

    //Interface IDetailPresenter
    @Override
    public void getDetailData(String id) {
        this.interactor.getSingleAsset(id, new Callback() {
            @Override
            public void onSuccess(Object responseObject) {
                AssetModel assetModel = (AssetModel) responseObject;
                DetailPresenter.this.view.fillDetailInformation(assetModel);
            }

            @Override
            public void onError() {
                //Todo Show error
            }
        });
    }
}
