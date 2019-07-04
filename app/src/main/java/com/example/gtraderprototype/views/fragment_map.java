package com.example.gtraderprototype.views;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gtraderprototype.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class fragment_map extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    MapView mapView;
    View mView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_fragment_map, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        mapView = (MapView) mView.findViewById(R.id.map);
        if(mapView!=null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
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
        MapsInitializer.initialize(getContext());
        mMap = googleMap;
        //Set boundaries
        LatLng lowerLeftBoundary = new LatLng(33.771136, -84.408059);
        LatLng upperRightBoundary = new LatLng(33.782586, -84.385312);
        LatLngBounds CampusBounds = new LatLngBounds(lowerLeftBoundary, upperRightBoundary);
        mMap.setLatLngBoundsForCameraTarget(CampusBounds);

        LatLng centerCampus = new LatLng(33.777129, -84.398231);
        mMap.addMarker(new MarkerOptions().position(centerCampus).title("Center of campus"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(centerCampus));
        mMap.setMaxZoomPreference(20);
        mMap.setMinZoomPreference(15);
    }
}
