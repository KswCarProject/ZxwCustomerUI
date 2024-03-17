package com.mapzen.helpers;

import com.mapzen.model.ValhallaLocation;

public class GeometryHelper {
    public static double getBearing(ValhallaLocation valhallaLocation, ValhallaLocation valhallaLocation2) {
        return (double) ((valhallaLocation.bearingTo(valhallaLocation2) + 360.0f) % 360.0f);
    }
}
