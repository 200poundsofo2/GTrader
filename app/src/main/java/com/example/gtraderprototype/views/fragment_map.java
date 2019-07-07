package com.example.gtraderprototype.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.gtraderprototype.R;
import com.example.gtraderprototype.entity.Region;
import com.example.gtraderprototype.entity.System;
import com.example.gtraderprototype.entity.Universe;
import com.example.gtraderprototype.viewmodels.MapViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.HeatmapTileProvider;

import java.util.ArrayList;
import java.util.HashMap;

public class fragment_map extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener{

    private GoogleMap mMap;

    MapView mapView;

    View mView;
    private TextView travelInfo;
    private Button button;
    private TextView spacePort;
    private TextView fuelAmount;

    ArrayList<LatLng> markersList = new ArrayList<>();
    private MapViewModel viewmodel;

    HashMap<String, System> systems = new HashMap<>();
    HashMap<String, Region> regions = new HashMap<>();
    HashMap<String, LatLng> places = new HashMap<>();
    Marker selectedMarker;
    int fuelCost = 0;
    int fuel=50;
    Marker destination = null;


    private TextView region;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewmodel = ViewModelProviders.of(this).get(MapViewModel.class);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_fragment_map, container, false);
        spacePort = getActivity().findViewById(R.id.name_of_region);
        fuelAmount =  getActivity().findViewById(R.id.fuel_amount);
        travelInfo =  mView.findViewById(R.id.travel);
        button = mView.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(destination!=null){
                    viewmodel.travelToRegion(selectedMarker.getTitle());
                    if(fuel - fuelCost < 0){
                        travelInfo.setText("not enough fuel");
                    } else {
                        fuel = fuel - fuelCost;
                        spacePort.setText(selectedMarker.getTitle());
                        fuelAmount.setText(fuel+"//50");
                        travelInfo.setText("Arrived");
                    }
                    destination=null;
                }


            }
        });

        travelInfo.setText(viewmodel.getPlayerLocationName());
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
    public void setMarker(String name, double lat, double lng){

        Marker newMarker = mMap.addMarker(new MarkerOptions()
        .position(new LatLng(lat,lng))
                .title(name).snippet("click here to travel")
        );
        newMarker.setTag(0);
    }

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
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);
        Universe uni = viewmodel.getUniverse();
        for(System sys: uni.systems){
            systems.put(sys.getSystemName(), sys);
            //setMarker(sys.getSystemName(), sys.coordinates[0], sys.coordinates[1]);
            for(Region reg: sys.getRegions()){
                setMarker(reg.regionName, reg.coordinates[0], reg.coordinates[1]);
                LatLng curr = new LatLng(reg.coordinates[0], reg.coordinates[1]);
                markersList.add(curr);
                places.put(reg.regionName,curr);
            }
        }
        addHeatMap();

    }

    public void addHeatMap(){
        HeatmapTileProvider mProvider = new HeatmapTileProvider.Builder()
                .data(markersList)
                .radius(35)
                .build();
        // Add a tile overlay to the map, using the heat map tile provider.
        TileOverlay mOverlay = mMap.addTileOverlay(new TileOverlayOptions().tileProvider(mProvider));

    }
    public boolean onMarkerClick(Marker marker) {
        // TODO Auto-generated method stub
            marker.showInfoWindow();
            return true;

    }
    @Override
    public void onInfoWindowClick(Marker marker) {
        if(marker.equals(selectedMarker)&&!(viewmodel.getPlayerLocationName().equals(marker.getTitle()))){
            destination = marker;
            //Haversine formula
            int R = 6378137;
            LatLng p1 = places.get(marker.getTitle());
            LatLng p2 = places.get(viewmodel.getPlayerLocationName());
            double dLat = (p1.latitude-p2.latitude)*Math.PI/180;
            double dLong = (places.get(marker.getTitle()).longitude-places.get(viewmodel.getPlayerLocationName()).longitude)*Math.PI/180;
            double a = Math.sin(dLat/2) * Math.sin(dLat / 2) +
                    Math.cos((p1.latitude)*Math.PI/180) * Math.cos((p2.latitude)*Math.PI/180) *
                            Math.sin(dLong / 2) * Math.sin(dLong / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            double d = R * c;
            this.fuelCost = (int)(d/10);
            String text = "fuel cost:" + fuelCost;
            travelInfo.setText(text);


        }
        selectedMarker = marker;
        Log.w("Click", marker.getTitle());
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(marker.getPosition().latitude, marker.getPosition().longitude), 17.0f));
        marker.showInfoWindow();
    }





}
