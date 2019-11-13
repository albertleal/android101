package com.albertleal.gimbernat.scenes.galleryDetail;

import com.albertleal.gimbernat.datasources.AssetsDataSource;
import com.albertleal.gimbernat.helpers.Callback;
import com.albertleal.gimbernat.models.AssetModel;
import com.albertleal.gimbernat.scenes.galleryDetail.interfaces.IDetailPresenter;

public class DetailPresenter implements IDetailPresenter {

    //MVP Variables
    private DetailActivity view;
    private AssetModel assetModel;

    public DetailPresenter(DetailActivity view) {
        this.view = view;
    }

    //Interface IDetailPresenter
    @Override
    public void getDetailData(String id) {
        AssetModel asset = AssetsDataSource.shared.getById(id);

        if (asset != null) {
            DetailPresenter.this.assetModel = asset;
            DetailPresenter.this.view.fillDetailInformation(DetailPresenter.this.assetModel);
        }else {
            //Todo Show error
        }
    }

    @Override
    public void navigateToMap() {
        if(assetModel != null) {
            DetailPresenter.this.view.navigateToMap(DetailPresenter.this.assetModel);
        }
    }

}
