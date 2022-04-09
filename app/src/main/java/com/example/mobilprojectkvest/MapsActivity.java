package com.example.mobilprojectkvest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.mobilprojectkvest.databinding.ActivityMapsBinding;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private static  final int  LOCATION_PERMISSION_CODE = 101;
    private int wayPointsCount = 5;
    private List<Address> listAddresses;
    private FrameLayout addMarkerLauout;
    private TextView markerName;
    private TextView markerLatitude;
    private TextView markerLongitude;
    private Button saveMarker;
    private Button cancel;
    private List<Marker> markers;
    private Button cameraButon;
    private LatLng currentLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        addMarkerLauout = findViewById(R.id.AddMarkerFrame);
        markerName = findViewById(R.id.PointName);
        markerLatitude = findViewById(R.id.PointLatidute);
        markerLongitude = findViewById(R.id.PointLongitude);
        saveMarker = findViewById(R.id.SaveButton);
        cancel = findViewById(R.id.CancelButton);
        cameraButon = findViewById(R.id.CameraButton);

        addMarkerLauout.setVisibility(View.INVISIBLE);
        cameraButon.setVisibility(View.INVISIBLE);

        markers = new List<Marker>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(@Nullable Object o) {
                return false;
            }

            @NonNull
            @Override
            public Iterator<Marker> iterator() {
                return null;
            }

            @NonNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NonNull
            @Override
            public <T> T[] toArray(@NonNull T[] ts) {
                return null;
            }

            @Override
            public boolean add(Marker marker) {
                return false;
            }

            @Override
            public boolean remove(@Nullable Object o) {
                return false;
            }

            @Override
            public boolean containsAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public boolean addAll(@NonNull Collection<? extends Marker> collection) {
                return false;
            }

            @Override
            public boolean addAll(int i, @NonNull Collection<? extends Marker> collection) {
                return false;
            }

            @Override
            public boolean removeAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public boolean retainAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public boolean equals(@Nullable Object o) {
                return false;
            }

            @Override
            public int hashCode() {
                return 0;
            }

            @Override
            public Marker get(int i) {
                return null;
            }

            @Override
            public Marker set(int i, Marker marker) {
                return null;
            }

            @Override
            public void add(int i, Marker marker) {

            }

            @Override
            public Marker remove(int i) {
                return null;
            }

            @Override
            public int indexOf(@Nullable Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(@Nullable Object o) {
                return 0;
            }

            @NonNull
            @Override
            public ListIterator<Marker> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<Marker> listIterator(int i) {
                return null;
            }

            @NonNull
            @Override
            public List<Marker> subList(int i, int i1) {
                return null;
            }
        };

        saveMarker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                /*

                МЕТКА ТУТ

                 */

                Marker myMarker;
                myMarker = mMap.addMarker(new MarkerOptions()
                        .position(currentLatLng).title(String.valueOf(markerName.getText())));
                markers.add(myMarker);
                addMarkerLauout.setVisibility(View.INVISIBLE);
                Toast toast = Toast.makeText(MapsActivity.this, "Маркер добавлен", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                addMarkerLauout.setVisibility(View.INVISIBLE);
            }
        });

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

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener()
        {
            @Override
            public void onMapClick(@NonNull LatLng latLng)
            {
                currentLatLng = latLng;
                CreateMarker();
            }

        });

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            mMap.setMyLocationEnabled(true);
            mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                @Override
                public void onMyLocationChange(@NonNull Location location) {
                    for (int i = 0 ; i < markers.size(); i++)
                    {
                        Location location1 = new Location("");
                        location1.setLatitude(markers.get(i).getPosition().latitude);
                        location1.setLongitude(markers.get(i).getPosition().longitude);
                        if (location.distanceTo(location1) <= 100)
                        {
                            cameraButon.setVisibility(View.VISIBLE);
                        }
                    }
                }
            });
            mMap.setMinZoomPreference(15);
        }
    }

    private void CreateMarker()
    {
        addMarkerLauout.setVisibility(View.VISIBLE);
        String latitude = String.valueOf(currentLatLng.latitude);
        String longitude = String.valueOf(currentLatLng.longitude);
        markerLatitude.setText(latitude);
        markerLongitude.setText(longitude);
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