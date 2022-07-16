package com.exercicio.exercicio2;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

public class Tracker implements LocationListener {
    private Location location;
    private LocationManager gpsManager;
    private Context c;

    public Tracker(Context context) {
        c = context;
        gpsManager = (LocationManager) (LocationManager)
                context.getSystemService(Context.LOCATION_SERVICE);

    }

    Location getLocation() {
        if (ActivityCompat.checkSelfPermission(c, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(c, "Por favor, permita o uso do GPS", Toast.LENGTH_SHORT).show();
            return null;
        }
        if(!gpsManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            Toast.makeText(c, "Por favor, habilite o GPS", Toast.LENGTH_SHORT).show();
            return null;
        }
        gpsManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 10, this);
        location = gpsManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        return location;
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }
}
