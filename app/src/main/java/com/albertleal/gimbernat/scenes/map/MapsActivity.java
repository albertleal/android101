package com.albertleal.gimbernat.scenes.map;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.albertleal.gimbernat.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    //Intent Input parameter
    public static String CONSTANT_LATITUDE = "PARAM_LAT";
    public static String CONSTANT_LONGITUDE = "PARAM_LONG";
    public static String CONSTANT_MARK = "PARAM_MARK";

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        double latitude = Double.valueOf(MapsActivity.this.getIntent().getStringExtra(CONSTANT_LATITUDE));
        double longitude = Double.valueOf(MapsActivity.this.getIntent().getStringExtra(CONSTANT_LONGITUDE));
        String title = MapsActivity.this.getIntent().getStringExtra(CONSTANT_MARK);

        LatLng position = new LatLng(latitude, longitude);
        Marker mark = mMap.addMarker(new MarkerOptions().position(position).title(title));
        mark.showInfoWindow();

        mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));

    }
}
