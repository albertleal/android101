package com.albertleal.gimbernat.scenes.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.albertleal.gimbernat.R;
import com.albertleal.gimbernat.helpers.Callback;
import com.albertleal.gimbernat.models.AssetModel;
import com.albertleal.gimbernat.scenes.gallery.interfaces.IGalleryPresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GalleryPresenter extends BaseAdapter implements IGalleryPresenter  {

    //MVP Variables
    private GalleryActivity view;
    private GalleryInteractor interactor;

    private ArrayList<AssetModel> items = new ArrayList<AssetModel>(); //data source of the list adapter


    public GalleryPresenter(GalleryActivity view){
        this.view = view;
        this.interactor = new GalleryInteractor();
    }

    //Interface IGalleryPresenter
    @Override
    public void subscribeForAssets() {
        this.view.showSpinner();
        this.interactor.subscribeForAssets(new Callback() {

            @Override
            public void onSuccess(Object responseObject) {

                ArrayList<AssetModel> assets = (ArrayList<AssetModel>) responseObject;
                GalleryPresenter.this.items = assets;
                //Notify the view that the content is ready to be used or updated
                GalleryPresenter.this.view.setAdapterForGrid();
                GalleryPresenter.this.view.hideSpinner();
            }

            @Override
            public void onError() {
                //show some error on the UI
            }
        });
    }


    //GridView BaseAdapter
    @Override
    public int getCount() {
        return items.size(); //returns total of items in the list
    }

    @Override
    public AssetModel getItem(int position) {
        return items.get(position); //returns list item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.view.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Dedicated layout for the item itself
            convertView = inflater.inflate(R.layout.activity_gallery_item, parent, false);
        }


        final AssetModel asset = getItem(position);
        //Setting the texts
        ((TextView) convertView.findViewById(R.id.description)).setText(asset.description);
        ((TextView) convertView.findViewById(R.id.title)).setText(asset.id);

        //Using Picasso to cache the image
        Picasso.get().load(asset.url).into((ImageView) convertView.findViewById(R.id.imageView));

        //Listener for the click on the item
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GalleryPresenter.this.view.navigateToDetail(asset);
            }
        });

        return convertView;
    }
}
