package com.juan.firebaseregistro;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Ubication extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

double latitudd = Double.parseDouble(Favoritos.latitud);
double longitud = Double.parseDouble(Favoritos.longitud);
   // double latitudd = 2.34566;
  //double longitud = -72.45678;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubication);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        //LatLng mostrar = new LatLng(Integer.parseInt(Favoritos.latitud), Integer.parseInt(Favoritos.longitud));
        mMap = googleMap;
        LatLng mostrar = new LatLng( latitudd, longitud);

        mMap.addMarker ( new MarkerOptions ()
                .position ( mostrar )
                .title ( Favoritos.latitud )
                .icon ( BitmapDescriptorFactory.fromResource ( R.mipmap.ic_launcher) ));
        mMap.moveCamera ( CameraUpdateFactory.newLatLngZoom ( mostrar, 15f ) );
if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
{return;}
        mMap.setMyLocationEnabled(true);
mMap.getUiSettings().setZoomControlsEnabled(true);
    }
}
