package com.mapzen.valhalla;

import com.mapzen.model.ValhallaLocation;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0006\u0010\u0013\u001a\u00020\u0014J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u001a\u0010\u0006\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\bR\u001a\u0010\u0010\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\b\"\u0004\b\u0012\u0010\n¨\u0006\u0017"}, d2 = {"Lcom/mapzen/valhalla/Node;", "", "lat", "", "lng", "(DD)V", "bearing", "getBearing", "()D", "setBearing", "(D)V", "getLat", "legDistance", "getLegDistance", "setLegDistance", "getLng", "totalDistance", "getTotalDistance", "setTotalDistance", "getLocation", "Lcom/mapzen/model/ValhallaLocation;", "toString", "", "library_release"}, k = 1, mv = {1, 1, 1})
/* compiled from: Node.kt */
public class Node {
    private double bearing;
    private final double lat;
    private double legDistance;
    private final double lng;
    private double totalDistance;

    public Node(double d, double d2) {
        this.lat = d;
        this.lng = d2;
    }

    public final double getLat() {
        return this.lat;
    }

    public final double getLng() {
        return this.lng;
    }

    public final double getTotalDistance() {
        return this.totalDistance;
    }

    public final void setTotalDistance(double d) {
        this.totalDistance = d;
    }

    public final double getBearing() {
        return this.bearing;
    }

    public final void setBearing(double d) {
        this.bearing = d;
    }

    public final double getLegDistance() {
        return this.legDistance;
    }

    public final void setLegDistance(double d) {
        this.legDistance = d;
    }

    public final ValhallaLocation getLocation() {
        ValhallaLocation valhallaLocation = new ValhallaLocation();
        valhallaLocation.setLatitude(this.lat);
        valhallaLocation.setLongitude(this.lng);
        valhallaLocation.setBearing((float) this.bearing);
        return valhallaLocation;
    }

    public String toString() {
        return "[" + String.valueOf(this.lat) + "," + String.valueOf(this.lng) + "]" + " getLegDistance: " + String.valueOf(this.legDistance);
    }
}
