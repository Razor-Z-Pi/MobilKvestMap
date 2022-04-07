package com.example.mobilprojectkvest;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.mobilprojectkvest.databinding.ActivityMapsBinding;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private static  final int  LOCATION_PERMISSION_CODE = 101;
    private int wayPointsCount = 5;
    private List<Address> listAddresses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        if (isLocationPermissionGartanted() == true)
        {
            try {
                listAddresses = new Geocoder(this).getFromLocationName(
                        "Европа, улица Крылова, Абакан", 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            requestLicationPermission();
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng currentPosition = new LatLng(53.7351056, 91.4444951);
        mMap.addMarker(new MarkerOptions().position(currentPosition).title("Маркер на европу"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentPosition));

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            mMap.setMyLocationEnabled(true);
            mMap.setMinZoomPreference(15);
        }
    }


    private boolean isLocationPermissionGartanted()
    {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            return  true;
        }
        else
        {
            return  false;
        }
    }

    private void requestLicationPermission()
    {
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_CODE);
    }
}