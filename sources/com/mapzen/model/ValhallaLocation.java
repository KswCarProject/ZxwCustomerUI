package com.mapzen.model;

public class ValhallaLocation {
    private float mBearing = 0.0f;
    private float mDistance = 0.0f;
    private boolean mHasBearing = false;
    private float mInitialBearing = 0.0f;
    private double mLat1 = 0.0d;
    private double mLat2 = 0.0d;
    private double mLatitude = 0.0d;
    private double mLon1 = 0.0d;
    private double mLon2 = 0.0d;
    private double mLongitude = 0.0d;
    private final float[] mResults = new float[2];

    public ValhallaLocation() {
    }

    public ValhallaLocation(ValhallaLocation valhallaLocation) {
        set(valhallaLocation);
    }

    public void set(ValhallaLocation valhallaLocation) {
        this.mLatitude = valhallaLocation.mLatitude;
        this.mLongitude = valhallaLocation.mLongitude;
        this.mHasBearing = valhallaLocation.mHasBearing;
        this.mBearing = valhallaLocation.mBearing;
    }

    private static void computeDistanceAndBearing(double d, double d2, double d3, double d4, float[] fArr) {
        double d5;
        double d6;
        float[] fArr2 = fArr;
        double d7 = (0.017453292519943295d * d4) - (d2 * 0.017453292519943295d);
        double atan = Math.atan(Math.tan(d * 0.017453292519943295d) * 0.996647189328169d);
        double atan2 = Math.atan(0.996647189328169d * Math.tan(d3 * 0.017453292519943295d));
        double cos = Math.cos(atan);
        double cos2 = Math.cos(atan2);
        double sin = Math.sin(atan);
        double sin2 = Math.sin(atan2);
        double d8 = cos * cos2;
        double d9 = sin * sin2;
        double d10 = d7;
        double d11 = 0.0d;
        double d12 = 0.0d;
        double d13 = 0.0d;
        double d14 = 0.0d;
        double d15 = 0.0d;
        int i = 0;
        while (true) {
            if (i >= 20) {
                d5 = sin;
                d6 = sin2;
                break;
            }
            d11 = Math.cos(d10);
            d13 = Math.sin(d10);
            double d16 = cos2 * d13;
            double d17 = (cos * sin2) - ((sin * cos2) * d11);
            d5 = sin;
            double sqrt = Math.sqrt((d16 * d16) + (d17 * d17));
            d6 = sin2;
            double d18 = d9 + (d8 * d11);
            d14 = Math.atan2(sqrt, d18);
            double d19 = sqrt == 0.0d ? 0.0d : (d8 * d13) / sqrt;
            double d20 = 1.0d - (d19 * d19);
            double d21 = d20 == 0.0d ? 0.0d : d18 - ((d9 * 2.0d) / d20);
            double d22 = 0.006739496756586903d * d20;
            double d23 = ((d22 / 16384.0d) * (((((320.0d - (175.0d * d22)) * d22) - 0.005859375d) * d22) + 4096.0d)) + 1.0d;
            double d24 = (d22 / 1024.0d) * ((d22 * (((74.0d - (47.0d * d22)) * d22) - 0.03125d)) + 256.0d);
            double d25 = 2.0955066698943685E-4d * d20 * (((4.0d - (d20 * 3.0d)) * 0.0033528106718309896d) + 4.0d);
            double d26 = d21 * d21;
            d15 = d24 * sqrt * (d21 + ((d24 / 4.0d) * ((((d26 * 2.0d) - 4.0d) * d18) - ((((d24 / 6.0d) * d21) * (((sqrt * 4.0d) * sqrt) - 1.5d)) * ((d26 * 4.0d) - 1.5d)))));
            double d27 = d7 + ((1.0d - d25) * 0.0033528106718309896d * d19 * (d14 + (sqrt * d25 * (d21 + (d25 * d18 * (((2.0d * d21) * d21) - 4.0d))))));
            if (Math.abs((d27 - d10) / d27) < 1.0E-12d) {
                d12 = d23;
                break;
            }
            i++;
            sin = d5;
            sin2 = d6;
            d10 = d27;
            d12 = d23;
        }
        float[] fArr3 = fArr;
        fArr3[0] = (float) (6356752.3142d * d12 * (d14 - d15));
        if (fArr3.length > 1) {
            double d28 = d6 * cos;
            double d29 = d5;
            fArr3[1] = (float) (((double) ((float) Math.atan2(cos2 * d13, d28 - ((d29 * cos2) * d11)))) * 57.29577951308232d);
            if (fArr3.length > 2) {
                fArr3[2] = (float) (((double) ((float) Math.atan2(cos * d13, ((-d29) * cos2) + (d28 * d11)))) * 57.29577951308232d);
            }
        }
    }

    public float distanceTo(ValhallaLocation valhallaLocation) {
        float f;
        synchronized (this.mResults) {
            double d = this.mLatitude;
            if (!(d == this.mLat1 && this.mLongitude == this.mLon1 && valhallaLocation.mLatitude == this.mLat2 && valhallaLocation.mLongitude == this.mLon2)) {
                computeDistanceAndBearing(d, this.mLongitude, valhallaLocation.mLatitude, valhallaLocation.mLongitude, this.mResults);
                this.mLat1 = this.mLatitude;
                this.mLon1 = this.mLongitude;
                this.mLat2 = valhallaLocation.mLatitude;
                this.mLon2 = valhallaLocation.mLongitude;
                float[] fArr = this.mResults;
                this.mDistance = fArr[0];
                this.mInitialBearing = fArr[1];
            }
            f = this.mDistance;
        }
        return f;
    }

    public float bearingTo(ValhallaLocation valhallaLocation) {
        float f;
        synchronized (this.mResults) {
            double d = this.mLatitude;
            if (!(d == this.mLat1 && this.mLongitude == this.mLon1 && valhallaLocation.mLatitude == this.mLat2 && valhallaLocation.mLongitude == this.mLon2)) {
                computeDistanceAndBearing(d, this.mLongitude, valhallaLocation.mLatitude, valhallaLocation.mLongitude, this.mResults);
                this.mLat1 = this.mLatitude;
                this.mLon1 = this.mLongitude;
                this.mLat2 = valhallaLocation.mLatitude;
                this.mLon2 = valhallaLocation.mLongitude;
                float[] fArr = this.mResults;
                this.mDistance = fArr[0];
                this.mInitialBearing = fArr[1];
            }
            f = this.mInitialBearing;
        }
        return f;
    }

    public double getLatitude() {
        return this.mLatitude;
    }

    public void setLatitude(double d) {
        this.mLatitude = d;
    }

    public double getLongitude() {
        return this.mLongitude;
    }

    public void setLongitude(double d) {
        this.mLongitude = d;
    }

    public float getBearing() {
        return this.mBearing;
    }

    public void setBearing(float f) {
        while (f < 0.0f) {
            f += 360.0f;
        }
        while (f >= 360.0f) {
            f -= 360.0f;
        }
        this.mBearing = f;
        this.mHasBearing = true;
    }

    public boolean hasBearing() {
        return this.mHasBearing;
    }

    public void removeBearing() {
        this.mBearing = 0.0f;
        this.mHasBearing = false;
    }
}
