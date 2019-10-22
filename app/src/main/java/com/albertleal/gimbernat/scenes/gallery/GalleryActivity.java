package com.albertleal.gimbernat.scenes.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.albertleal.gimbernat.R;
import com.albertleal.gimbernat.models.AssetModel;
import com.albertleal.gimbernat.scenes.galleryDetail.DetailActivity;
import com.albertleal.gimbernat.scenes.gallery.interfaces.IGalleryActivity;
import com.albertleal.gimbernat.scenes.terms.TermsActivity;

public class GalleryActivity extends AppCompatActivity implements IGalleryActivity {

    //MVP Variables
    private GalleryPresenter presenter;

    //Setting the UI
    private ProgressBar spinner;

    //Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_actiivity);

        //init the UI
        this.spinner = (ProgressBar)findViewById(R.id.progressBarGallery);
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

    @Override
    public void showSpinner() {
        GalleryActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                GalleryActivity.this.spinner.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void hideSpinner() {
        GalleryActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                GalleryActivity.this.spinner.setVisibility(View.GONE);
            }
        });
    }
}