package com.albertleal.gimbernat.scenes.gallery;

import android.util.Log;

import com.albertleal.gimbernat.datasources.AssetsDataSource;
import com.albertleal.gimbernat.helpers.Callback;
import com.albertleal.gimbernat.models.AssetModel;
import com.albertleal.gimbernat.scenes.gallery.interfaces.IGalleryInteractor;

import java.util.ArrayList;

public class GalleryInteractor implements IGalleryInteractor {

    //Interface IGalleryInteractor
    @Override
    public void subscribeForAssets(final Callback callback){
        AssetsDataSource.shared.subscribe(new Callback() {
            @Override
            public void onSuccess(Object responseObject) {
                ArrayList<AssetModel> assets = (ArrayList<AssetModel>) responseObject;
                callback.onSuccess(assets);
            }

            @Override
            public void onError() {
                Log.d("debug", "error");
                callback.onError();
            }
        });
    }
}
