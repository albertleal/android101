package com.albertleal.gimbernat.scenes.galleryDetail;

import com.albertleal.gimbernat.datasources.AssetsDataSource;
import com.albertleal.gimbernat.helpers.Callback;
import com.albertleal.gimbernat.models.AssetModel;
import com.albertleal.gimbernat.scenes.galleryDetail.interfaces.IDetailInteractor;

public class DetailInteractor implements IDetailInteractor {

    //Interface IDetailInteractor
    @Override
    public void getSingleAsset(String id, Callback callback) {

        AssetModel asset = AssetsDataSource.shared.getById(id);

        if (asset != null) {
            callback.onSuccess(asset);
        }else {
            callback.onError();
        }
    }
}
