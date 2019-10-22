package com.albertleal.gimbernat.scenes.gallery.interfaces;

import com.albertleal.gimbernat.models.AssetModel;

public interface IGalleryActivity {
    void navigateToDetail(AssetModel assetModel);
    void setAdapterForGrid();
    void showSpinner();
    void hideSpinner();
}

