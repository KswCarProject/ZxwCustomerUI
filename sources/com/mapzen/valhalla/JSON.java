package com.mapzen.valhalla;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class JSON {
    public static final int HEADING_NONE = -1;
    public String costing;
    @SerializedName("directions_options")
    public DirectionOptions directionsOptions = new DirectionOptions();
    public List<Location> locations = new ArrayList();

    public static class DirectionOptions {
        public String language;
        public String units;
    }

    public static class Location {
        public String city;
        public int heading = -1;
        public double lat;
        public double lon;
        public String name;
        public String state;
        public String street;

        public Location(double d, double d2) {
            this.lat = d;
            this.lon = d2;
        }

        public Location(double d, double d2, int i) {
            if (i < 0 || i >= 360) {
                throw new IllegalArgumentException("Heading value must in the range [0, 360)");
            }
            this.lat = d;
            this.lon = d2;
            this.heading = i;
        }

        public Location(double d, double d2, String str, String str2, String str3, String str4) {
            this.lat = d;
            this.lon = d2;
            this.name = str;
            this.street = str2;
            this.city = str3;
            this.state = str4;
        }
    }
}
