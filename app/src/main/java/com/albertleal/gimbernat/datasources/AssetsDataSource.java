package com.albertleal.gimbernat.datasources;

import androidx.annotation.NonNull;

import com.albertleal.gimbernat.helpers.Callback;
import com.albertleal.gimbernat.models.AssetModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AssetsDataSource {

    public static AssetsDataSource shared = new AssetsDataSource();

    private ArrayList<AssetModel> assetsList = new ArrayList<AssetModel>();

    public AssetModel getById(String id){

        for (AssetModel asset : this.assetsList) {
            if (asset.id.equals(id)) {
                return asset;
            }
        }

        return null;
    }

    public void subscribe(final Callback callback) {
        this.fetch(true, callback);

    }

    public void fetchAll(final Callback callback, final Boolean subscribe) {
        this.fetch(false, callback);

    }

    private void fetch(final  Boolean subscribeCallback, final Callback callback){

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("content/assets");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<AssetModel> assetsList = new ArrayList<AssetModel>();

                for (DataSnapshot item_snapshot : dataSnapshot.getChildren()) {

                    assetsList.add(snapshotToAssetModel(item_snapshot));
                }

                AssetsDataSource.this.assetsList = assetsList;

                if(!subscribeCallback){
                    databaseReference.removeEventListener(this);
                }

                callback.onSuccess(assetsList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onError();
            }
        };

        databaseReference.addValueEventListener(eventListener);
    }

    private AssetModel snapshotToAssetModel(DataSnapshot item_snapshot) {

        String id = item_snapshot.getKey().toString();
        Integer category = Integer.parseInt(item_snapshot.child("category").getValue().toString());
        String description = item_snapshot.child("description").getValue().toString();
        String url = item_snapshot.child("url").getValue().toString();

        return new AssetModel(id, url, description, category);

    }
}