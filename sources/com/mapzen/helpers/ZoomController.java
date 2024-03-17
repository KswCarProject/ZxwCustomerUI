package com.mapzen.helpers;

import com.mapzen.valhalla.Router;
import java.util.HashMap;

public class ZoomController {
    public static final int DEFAULT_TURN_RADIUS = 50;
    public static final int DEFAULT_TURN_RADIUS_BIKING = 20;
    public static final int DEFAULT_TURN_RADIUS_DRIVING = 50;
    public static final int DEFAULT_TURN_RADIUS_WALKING = 10;
    public static final int DEFAULT_ZOOM = 17;
    public static final int DEFAULT_ZOOM_BIKING = 19;
    public static final int DEFAULT_ZOOM_DRIVING = 17;
    public static final int DEFAULT_ZOOM_WALKING = 21;
    private static final float ONE_METER_PER_SECOND_IN_MILES_PER_HOUR = 2.23694f;
    private DrivingSpeed averageDrivingSpeed = null;
    private int bikingTurnRadius = 20;
    private int bikingZoom = 19;
    private DrivingSpeed currentDrivingSpeed = null;
    private int drivingTurnRadius = 50;
    private int drivingZoom = 17;
    private Router.Type transitMode = Router.Type.DRIVING;
    private HashMap<DrivingSpeed, Integer> turnRadiusMap = new HashMap<>();
    private int walkingTurnRadius = 10;
    private int walkingZoom = 21;
    private HashMap<DrivingSpeed, Integer> zoomMap = new HashMap<>();

    public enum DrivingSpeed {
        MPH_0_TO_15,
        MPH_15_TO_25,
        MPH_25_TO_35,
        MPH_35_TO_50,
        MPH_OVER_50
    }

    public static float metersPerSecondToMilesPerHour(float f) {
        return f * ONE_METER_PER_SECOND_IN_MILES_PER_HOUR;
    }

    public static float milesPerHourToMetersPerSecond(float f) {
        return f / ONE_METER_PER_SECOND_IN_MILES_PER_HOUR;
    }

    /* renamed from: com.mapzen.helpers.ZoomController$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$mapzen$valhalla$Router$Type;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.mapzen.valhalla.Router$Type[] r0 = com.mapzen.valhalla.Router.Type.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$mapzen$valhalla$Router$Type = r0
                com.mapzen.valhalla.Router$Type r1 = com.mapzen.valhalla.Router.Type.WALKING     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$mapzen$valhalla$Router$Type     // Catch:{ NoSuchFieldError -> 0x001d }
                com.mapzen.valhalla.Router$Type r1 = com.mapzen.valhalla.Router.Type.BIKING     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$mapzen$valhalla$Router$Type     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.mapzen.valhalla.Router$Type r1 = com.mapzen.valhalla.Router.Type.DRIVING     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mapzen.helpers.ZoomController.AnonymousClass1.<clinit>():void");
        }
    }

    public int getZoom() {
        int i = AnonymousClass1.$SwitchMap$com$mapzen$valhalla$Router$Type[this.transitMode.ordinal()];
        if (i == 1) {
            return this.walkingZoom;
        }
        if (i == 2) {
            return this.bikingZoom;
        }
        if (i != 3) {
            return 17;
        }
        return getZoomForCurrentDrivingSpeed();
    }

    public int getTurnRadius() {
        int i = AnonymousClass1.$SwitchMap$com$mapzen$valhalla$Router$Type[this.transitMode.ordinal()];
        if (i == 1) {
            return this.walkingTurnRadius;
        }
        if (i == 2) {
            return this.bikingTurnRadius;
        }
        if (i != 3) {
            return 50;
        }
        return getTurnRadiusForCurrentDrivingSpeed();
    }

    private int getZoomForCurrentDrivingSpeed() {
        Integer num = this.zoomMap.get(this.averageDrivingSpeed);
        if (num != null) {
            return num.intValue();
        }
        return this.drivingZoom;
    }

    private int getTurnRadiusForCurrentDrivingSpeed() {
        Integer num = this.turnRadiusMap.get(this.currentDrivingSpeed);
        if (num != null) {
            return num.intValue();
        }
        return this.drivingTurnRadius;
    }

    public void setTransitMode(Router.Type type) {
        this.transitMode = type;
    }

    public void setWalkingZoom(int i) {
        this.walkingZoom = i;
    }

    public void setBikingZoom(int i) {
        this.bikingZoom = i;
    }

    public void setDrivingZoom(int i) {
        this.drivingZoom = i;
    }

    public void setDrivingZoom(int i, DrivingSpeed drivingSpeed) {
        this.zoomMap.put(drivingSpeed, Integer.valueOf(i));
    }

    public void setWalkingTurnRadius(int i) {
        this.walkingTurnRadius = i;
    }

    public void setBikingTurnRadius(int i) {
        this.bikingTurnRadius = i;
    }

    public void setDrivingTurnRadius(int i) {
        this.drivingTurnRadius = i;
    }

    public void setDrivingTurnRadius(int i, DrivingSpeed drivingSpeed) {
        this.turnRadiusMap.put(drivingSpeed, Integer.valueOf(i));
    }

    public void setAverageSpeed(float f) {
        this.averageDrivingSpeed = getDrivingSpeed(f);
    }

    public void setCurrentSpeed(float f) {
        this.currentDrivingSpeed = getDrivingSpeed(f);
    }

    private DrivingSpeed getDrivingSpeed(float f) {
        if (f >= 0.0f) {
            float metersPerSecondToMilesPerHour = metersPerSecondToMilesPerHour(f);
            if (metersPerSecondToMilesPerHour < 15.0f) {
                return DrivingSpeed.MPH_0_TO_15;
            }
            if (metersPerSecondToMilesPerHour < 25.0f) {
                return DrivingSpeed.MPH_15_TO_25;
            }
            if (metersPerSecondToMilesPerHour < 35.0f) {
                return DrivingSpeed.MPH_25_TO_35;
            }
            if (metersPerSecondToMilesPerHour < 50.0f) {
                return DrivingSpeed.MPH_35_TO_50;
            }
            return DrivingSpeed.MPH_OVER_50;
        }
        throw new IllegalArgumentException("Speed less than zero is not permitted.");
    }
}
