package com.albertleal.gimbernat.scenes.galleryDetail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.albertleal.gimbernat.R;
import com.albertleal.gimbernat.models.AssetModel;
import com.albertleal.gimbernat.scenes.galleryDetail.interfaces.IDetailActivity;
import com.squareup.picasso.Picasso;



public class DetailActivity extends AppCompatActivity implements IDetailActivity {

    //MVP Variables
    private DetailPresenter presenter;

    //Intent Input parameter
    public static String CONSTANT_ID_ASSET = "PARAM_ID_ASSET";

    //View outlets
    private TextView title;
    private ImageView image;
    private TextView description;

    //Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Setting the UI
        this.title = this.findViewById(R.id.detailTitleTextView);
        this.description = this.findViewById(R.id.detailDescriptionTextView);
        this.image = this.findViewById(R.id.detailImageView);

        //Init the presenter
        this.presenter = new DetailPresenter(this);

        //Getting parameter from intent
        String assetId = DetailActivity.this.getIntent().getStringExtra(CONSTANT_ID_ASSET);

        //Fill the detail information
        this.presenter.getDetailData(assetId);

    }

    //Interface IDetailActivity
    @Override
    public void fillDetailInformation(AssetModel asset) {

        this.title.setText(asset.id);
        this.description.setText(asset.description);
        Picasso.get().load(asset.url).into(this.image);
    }
}
