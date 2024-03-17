package com.mapzen.valhalla;

import com.szchoiceway.zxwlib.utils.EventUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/mapzen/valhalla/TravelMode;", "", "mode", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toString", "DRIVE", "PEDESTRIAN", "BICYCLE", "TRANSIT", "library_release"}, k = 1, mv = {1, 1, 1})
/* compiled from: TravelMode.kt */
public enum TravelMode {
    DRIVE("drive"),
    PEDESTRIAN("pedestrian"),
    BICYCLE("bicycle"),
    TRANSIT("transit");
    
    private final String mode;

    protected TravelMode(String str) {
        Intrinsics.checkParameterIsNotNull(str, EventUtils.DISMISS_SPLIT_SCREEN_EXTRA);
        this.mode = str;
    }

    public String toString() {
        return this.mode;
    }
}
