package com.albertleal.gimbernat.scenes.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.albertleal.gimbernat.R;
import com.albertleal.gimbernat.models.AssetModel;
import com.albertleal.gimbernat.scenes.detail.DetailActivity;
import com.albertleal.gimbernat.scenes.gallery.interfaces.IGalleryActivity;

public class GalleryActivity extends AppCompatActivity implements IGalleryActivity {

    //MVP Variables
    private GalleryPresenter presenter;

    //Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_actiivity);

        //Init the presenter
        this.presenter = new GalleryPresenter(this);



        //Call the preseenter to subscribe for assets
        this.presenter.subscribeForAssets();
    }

    //Interface IGalleryActivity
    @Override
    public void navigateToDetail(final AssetModel assetModel){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent myIntent = new Intent(GalleryActivity.this, DetailActivity.class);
                //Adding the ID of the model as a parameter
                myIntent.putExtra(DetailActivity.CONSTANT_ID_ASSET, assetModel.id);
                startActivity(myIntent);
            }
        });

    }

    @Override
    public void setAdapterForGrid() {
        //Setup the presenter as Adapter for the GridView when the presenter is ready
        ((GridView) findViewById(R.id.galleryGrid)).setAdapter(this.presenter);
    }
}