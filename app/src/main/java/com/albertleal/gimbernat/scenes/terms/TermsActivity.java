package com.albertleal.gimbernat.scenes.terms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.albertleal.gimbernat.R;
import com.albertleal.gimbernat.scenes.gallery.GalleryActivity;
import com.albertleal.gimbernat.scenes.terms.interfaces.ITermsActivity;

public class TermsActivity extends AppCompatActivity implements ITermsActivity {

    //MVP Variables
    private TermsPresenter presenter;

    //View outlets
    private Button privateButton;

    //Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);


        //Init the presenter
        this.presenter = new TermsPresenter(this);

        //init the UI
        this.privateButton = this.findViewById(R.id.termsAcceptsButton);

        //Setup events

        this.privateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TermsActivity.this.presenter.privateButtonPressed();
            }
        });
    }

    //Interface ITermsActivity
    @Override
    public void navigateToPrivate() {
        TermsActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(TermsActivity.this, GalleryActivity.class);
                intent.setAction(Intent.ACTION_VIEW);
                TermsActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    public void showError(final String error) {
        TermsActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(TermsActivity.this, error, Toast.LENGTH_LONG).show();
            }
        });
    }
}
