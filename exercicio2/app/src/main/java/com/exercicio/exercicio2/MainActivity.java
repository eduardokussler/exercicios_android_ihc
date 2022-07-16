package com.exercicio.exercicio2;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;

import static java.lang.System.exit;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener, LocationListener {

    private SensorManager sensorManager;
    private Sensor light;
    private Sensor gyroscope;
    private Location location;
    private EditText txtLight;
    private EditText txtGyroscopeX;
    private EditText txtGyroscopeY;
    private EditText txtGyroscopeZ;
    private EditText txtLatitude;
    private EditText txtLongitude;

    private Tracker gpsTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_NETWORK_STATE}, 1);
        gpsTracker = new Tracker(getApplicationContext());
        txtLight = findViewById(R.id.txtLight);
        txtGyroscopeX = findViewById(R.id.txtGyroscopeX);
        txtGyroscopeY = findViewById(R.id.txtGyroscopeY);
        txtGyroscopeZ = findViewById(R.id.txtGyroscopeZ);
        txtLatitude = findViewById(R.id.txtLatitude);
        txtLongitude = findViewById(R.id.txtLongitude);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if(gyroscope != null)
            sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        if(light != null) {
            sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            txtLight.setText("Sensor de luminosidade não existe");
        }
        location = gpsTracker.getLocation();



    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT && light != null) {
            txtLight.setText(String.format(Float.toString(sensorEvent.values[0])));
        }

        if(sensorEvent.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];
            txtGyroscopeX.setText(String.format(Float.toString(x)));
            txtGyroscopeY.setText(String.format(Float.toString(y)));
            txtGyroscopeZ.setText(String.format(Float.toString(z)));
        }

        if(location != null) {
            txtLatitude.setText(Double.toString(location.getLatitude()));
            txtLongitude.setText(Double.toString(location.getLongitude()));
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(gyroscope != null)
            sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        if(light != null) {
            sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            txtLight.setText("Sensor de luminosidade não existe");
        }
        location = gpsTracker.getLocation();


    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(gyroscope != null)
            sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        if(light != null) {
            sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            txtLight.setText("Sensor de luminosidade não existe");
        }
    }


    @Override
    public void onLocationChanged(@NonNull Location location) {
        this.location = location;
    }

    @Override
    public void onLocationChanged(@NonNull List<Location> locations) {
        LocationListener.super.onLocationChanged(locations);
    }
}