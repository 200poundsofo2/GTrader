package com.example.gtraderprototype.views;

import android.content.Intent;
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
import com.example.gtraderprototype.entity.Player;
import com.example.gtraderprototype.entity.Region;
import com.example.gtraderprototype.entity.Ship;
import com.example.gtraderprototype.entity.System;
import com.example.gtraderprototype.entity.Universe;
import com.example.gtraderprototype.model.Model;
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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

class fragment_map extends Fragment
        implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener,
        GoogleMap.OnInfoWindowClickListener{

    private GoogleMap mMap;

    private View mView;
    private TextView travelInfo;
    private Button button;
    private TextView spacePort;
    private TextView fuelAmount;

    private final Collection<LatLng> markersList = new ArrayList<>();
    private MapViewModel viewmodel;

    private final Map<String, System> systems = new HashMap<>();
    HashMap<String, Region> regions = new HashMap<>();
    private final HashMap<String, LatLng> places = new HashMap<>();
    private Marker selectedMarker;
    Ship playerShip;
    private int fuelCost;
    private int fuel;
    int fuelCapacity;
    private Marker destination;
    private final double ENCOUNTER_PROB = 1.0;


    private TextView region;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewmodel = ViewModelProviders.of(this).get(MapViewModel.class);

    }
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_fragment_map, container, false);
        spacePort = getActivity().findViewById(R.id.name_of_region);
        fuelAmount =  getActivity().findViewById(R.id.fuel_amount);
        travelInfo =  mView.findViewById(R.id.travel);
        button = mView.findViewById(R.id.button);
        Player player = Model.getInstance().getPlayerInteractor().getPlayer();
        fuel = player.getShip().getFuel();
        fuelCapacity = player.getShip().getFuelCapacity();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(destination!=null){
                    if((fuel - fuelCost) < 0){
                        travelInfo.setText(getString(R.string.not_enough));
                    } else {
                        viewmodel.travelToRegion(selectedMarker.getTitle(), fuelCost);
                        fuel = fuel - fuelCost;

                        spacePort.setText(selectedMarker.getTitle());
                        fuelAmount.setText(new StringBuilder().append(fuel)
                                .append(getString(R.string.forward_slash)).append(viewmodel
                                        .getPlayerShipRange()).toString());
                        travelInfo.setText(getString(R.string.arrived));

                        //generate random event encounter
                        if(Math.random() < 1.0){
                            encounter();
                            Log.d("encounter", viewmodel.getPlayerLocationName());
                        }

                        Log.d("GTrader", viewmodel.getPlayerFuel()+" fuel remaining");
                        button.setEnabled(false);


                    }
                    destination=null;
                }


            }
        });

        travelInfo.setText(viewmodel.getPlayerLocationName());
        button.setEnabled(false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        MapView mapView = mView.findViewById(R.id.map);
        if(mapView !=null){
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
    private void setMarker(String name, double lat, double lng, boolean isCurrentLocation){
        float markerValue = 17.0f;
        Marker newMarker = mMap.addMarker(new MarkerOptions()
        .position(new LatLng(lat,lng))
                .title(name).snippet("click here to travel")
        );
        newMarker.setTag(0);
        if(isCurrentLocation){
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), markerValue));
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mMap = googleMap;
        //Set boundaries
        double lowBoundaryLatValue = 33.771136;
        double lowBoundaryLongValue = -84.408059;
        LatLng lowerLeftBoundary = new LatLng(lowBoundaryLatValue, lowBoundaryLongValue);
        double highBoundaryLatValue = 33.782586;
        double highBoundaryLongValue = -84.385312;
        LatLng upperRightBoundary = new LatLng(highBoundaryLatValue, highBoundaryLongValue);
        LatLngBounds CampusBounds = new LatLngBounds(lowerLeftBoundary, upperRightBoundary);
        mMap.setLatLngBoundsForCameraTarget(CampusBounds);

       //LatLng centerCampus = new LatLng(33.777129, -84.398231);
        //mMap.addMarker(new MarkerOptions().position(centerCampus).title("Center of campus"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(centerCampus));
        int maxZoom = 20;
        mMap.setMaxZoomPreference(maxZoom);
        int minZoom = 15;
        mMap.setMinZoomPreference(minZoom);
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);
        Universe uni = viewmodel.getUniverse();
        for(System sys: uni.systems){
            systems.put(sys.getSystemName(), sys);
            //setMarker(sys.getSystemName(), sys.coordinates[0], sys.coordinates[1], false);
            for(Region reg: sys.getRegions()){
                LatLng curr = new LatLng(reg.coordinates[0], reg.coordinates[1]);
                markersList.add(curr);
                places.put(reg.regionName,curr);
                if(reg.regionName.equals(viewmodel.getPlayerLocationName())){
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(curr));
                    setMarker(reg.regionName, reg.coordinates[0], reg.coordinates[1], true);
                }else{
                    setMarker(reg.regionName, reg.coordinates[0], reg.coordinates[1], false);
                }
            }
        }
        addHeatMap();
    }

    private void addHeatMap(){
        int radius = 35;
        HeatmapTileProvider mProvider = new HeatmapTileProvider.Builder()
                .data(markersList)
                .radius(radius)
                .build();
        // Add a tile overlay to the map, using the heat map tile provider.
         mMap.addTileOverlay(new TileOverlayOptions().tileProvider(mProvider));

    }
    public boolean onMarkerClick(Marker marker) {
        // TODO Auto-generated method stub
            marker.showInfoWindow();
            return true;

    }
    @SuppressWarnings("MagicNumber")
    @Override
    public void onInfoWindowClick(Marker marker) {
        if(!(viewmodel.getPlayerLocationName().equals(marker.getTitle()))){
            destination = marker;
            //Haversine formula
            int R = 6378137;
            LatLng p1 = places.get(marker.getTitle());
            LatLng p2 = places.get(viewmodel.getPlayerLocationName());
            double dLat = ((p1.latitude - p2.latitude) * Math.PI) / 180;
            double dLong = ((places.get(marker.getTitle()).longitude - places.get(viewmodel
                    .getPlayerLocationName()).longitude) * Math.PI) / 180;
            double a = (Math.sin(dLat / 2) * Math.sin(dLat / 2)) +
                    (Math.cos(((p1.latitude) * Math.PI) / 180) * Math.cos(((p2.latitude) * Math.PI) / 180) *
                            Math.sin(dLong / 2) * Math.sin(dLong / 2));
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            double d = R * c;
            this.fuelCost = (int)(d/10);
            String text = "fuel cost:" + fuelCost;
            if(fuelCost>fuel){
                button.setEnabled(false);
            }else{
                button.setEnabled(true);
            }
            travelInfo.setText(text);
        }else{
            travelInfo.setText(getString(R.string.then_who_is_that));
            button.setEnabled(false);
        }

        selectedMarker = marker;
        Log.w("Click", marker.getTitle());
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(marker.getPosition()
                .latitude, marker.getPosition().longitude), 17.0f));
        marker.showInfoWindow();
    }

    private void encounter(){ //trade, police, pirate
        double pirateProbability = ((viewmodel
                .getPlayer().getDifficultyLevel() + 1) * 2) / 10.0; //Beginner: 0.2, Impossible:1
        Random rand = new Random();
        if(Math.random() < pirateProbability){
            // encounter pirate
            Intent pirateIntent = new Intent(getActivity(), EncounterPirateActivity.class);
            startActivity(pirateIntent);
        }else{
            if(rand.nextBoolean()){
                //encounter trader
                Intent traderIntent=new Intent(getActivity(),EncounterTraderActivity.class);
                startActivity(traderIntent);
            }else{
                //encounter police
                Intent policeIntent=new Intent(getActivity(), EncounterPoliceActivity.class);
                startActivity(policeIntent);
            }
        }

    }





}
