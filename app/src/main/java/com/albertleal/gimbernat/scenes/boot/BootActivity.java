package com.albertleal.gimbernat.scenes.boot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.albertleal.gimbernat.R;
import com.albertleal.gimbernat.datasources.SessionDataSource;
import com.albertleal.gimbernat.scenes.boot.interfaces.IBootActivity;
import com.albertleal.gimbernat.scenes.gallery.GalleryActivity;
import com.albertleal.gimbernat.scenes.terms.TermsActivity;


public class BootActivity extends AppCompatActivity implements IBootActivity {

    //MVP Variables
    private BootPresenter presenter;


    //Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boot);

        SessionDataSource.shared.signOut();

        //Init the presenter
        this.presenter = new BootPresenter(this);

        //boot the Application
        this.presenter.bootApplication();
    }

    //Interface IBootActivity
    @Override
    public void navigateToPublic() {
        Intent intent = new Intent(BootActivity.this, TermsActivity.class);
        intent.setAction(Intent.ACTION_VIEW);
        BootActivity.this.startActivity(intent);
    }

    @Override
    public void navigateToPrivate() {
        Intent intent = new Intent(BootActivity.this, GalleryActivity.class);
        intent.setAction(Intent.ACTION_VIEW);
        BootActivity.this.startActivity(intent);
    }
}
