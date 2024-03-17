package com.szchoiceway.customerui.utils;

import android.content.Context;
import android.location.Criteria;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class GPSMonitor {
    protected static final String TAG = "GPSMonitor";
    private static final int maxSatellites = 12;
    /* access modifiers changed from: private */
    public GpsStatus gpsStatus;
    private Location location;
    private final LocationListener locationListener = new LocationListener() {
        public void onProviderEnabled(String str) {
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
        }

        public void onProviderDisabled(String str) {
            Log.d(GPSMonitor.TAG, "onProviderDisabled");
        }

        public void onLocationChanged(Location location) {
            GPSMonitor.this.updateToNewLocation(location);
        }
    };
    /* access modifiers changed from: private */
    public LocationManager locationManager;
    private float mAltitude = 0.0f;
    private float mBear = 0.0f;
    private float mGPSTimer = 0.0f;
    private float mLatitude = 0.0f;
    private float mLongitude = 0.0f;
    private float mSpeed = 0.0f;
    private final GpsStatus.Listener statusListener = new GpsStatus.Listener() {
        public void onGpsStatusChanged(int i) {
            if (GPSMonitor.this.locationManager != null && i == 4) {
                try {
                    GPSMonitor gPSMonitor = GPSMonitor.this;
                    GpsStatus unused = gPSMonitor.gpsStatus = gPSMonitor.locationManager.getGpsStatus((GpsStatus) null);
                } catch (Exception e) {
                    Log.e(GPSMonitor.TAG, e.toString());
                }
            }
        }
    };

    public static int getMaxSatellites() {
        return 12;
    }

    public GpsStatus getGpsStatus() {
        return this.gpsStatus;
    }

    public float getmAltitude() {
        return this.mAltitude;
    }

    public float getmSpeed() {
        return this.mSpeed;
    }

    public float getmLongitude() {
        return this.mLongitude;
    }

    public float getmLatitude() {
        return this.mLatitude;
    }

    public float getmBear() {
        return this.mBear;
    }

    public float getmGPSTimer() {
        return this.mGPSTimer;
    }

    public GPSMonitor(Context context) {
        LocationManager locationManager2 = (LocationManager) context.getSystemService("location");
        this.locationManager = locationManager2;
        if (locationManager2.isProviderEnabled("gps")) {
            getLocation();
        }
    }

    public void releaseGPSResource() {
        LocationManager locationManager2 = this.locationManager;
        if (locationManager2 != null) {
            locationManager2.removeUpdates(this.locationListener);
            this.locationManager.removeGpsStatusListener(this.statusListener);
            this.locationManager = null;
        }
    }

    private void getLocation() {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(1);
        criteria.setAltitudeRequired(true);
        criteria.setBearingRequired(true);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(1);
        criteria.setSpeedRequired(true);
        String bestProvider = this.locationManager.getBestProvider(criteria, true);
        if (bestProvider != null) {
            Location lastKnownLocation = this.locationManager.getLastKnownLocation(bestProvider);
            this.location = lastKnownLocation;
            updateToNewLocation(lastKnownLocation);
            this.locationManager.requestLocationUpdates(bestProvider, 1000, 0.0f, this.locationListener);
            this.locationManager.addGpsStatusListener(this.statusListener);
        }
    }

    /* access modifiers changed from: private */
    public void updateToNewLocation(Location location2) {
        if (location2 != null) {
            this.mBear = location2.getBearing();
            this.mLatitude = (float) location2.getLatitude();
            this.mLongitude = (float) location2.getLongitude();
            this.mSpeed = location2.getSpeed() * 3.7f;
            new Date(location2.getTime());
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            this.mAltitude = (float) location2.getAltitude();
        }
    }
}
