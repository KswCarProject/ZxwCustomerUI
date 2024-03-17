package com.mapzen.valhalla;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0011"}, d2 = {"Lcom/mapzen/valhalla/TravelType;", "", "type", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toString", "CAR", "FOOT", "ROAD", "TRAM", "METRO", "RAIL", "BUS", "FERRY", "CABLE_CAR", "GONDOLA", "FUNICULAR", "library_release"}, k = 1, mv = {1, 1, 1})
/* compiled from: TravelType.kt */
public enum TravelType {
    CAR("car"),
    FOOT("foot"),
    ROAD("road"),
    TRAM("tram"),
    METRO("metro"),
    RAIL("rail"),
    BUS("bus"),
    FERRY("ferry"),
    CABLE_CAR("cable_car"),
    GONDOLA("gondola"),
    FUNICULAR("funicular");
    
    private final String type;

    protected TravelType(String str) {
        Intrinsics.checkParameterIsNotNull(str, "type");
        this.type = str;
    }

    public String toString() {
        return this.type;
    }
}
