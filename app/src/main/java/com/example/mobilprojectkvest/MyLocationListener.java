package com.example.mobilprojectkvest;

import android.location.Location;
import android.location.LocationListener;

import androidx.annotation.NonNull;

public class MyLocationListener implements LocationListener {
    LocListenerIntarface locListenerIntarface;
    @Override
    public void onLocationChanged(@NonNull Location location) {
        locListenerIntarface.OnLocationChanged(location);
    }
}
