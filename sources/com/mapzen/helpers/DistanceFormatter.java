package com.mapzen.helpers;

import com.mapzen.valhalla.Router;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public final class DistanceFormatter {
    public static final double FEET_IN_ONE_MILE = 5280.0d;
    public static final double METERS_IN_ONE_FOOT = 0.3048d;
    public static final double METERS_IN_ONE_MILE = 1609.0d;
    private static DecimalFormat decimalFormat;

    private DistanceFormatter() {
    }

    public static String format(int i) {
        return format(i, false);
    }

    public static String format(int i, boolean z) {
        return format(i, z, Locale.getDefault());
    }

    public static String format(int i, boolean z, Router.DistanceUnits distanceUnits) {
        return format(i, z, Locale.getDefault(), distanceUnits);
    }

    public static String format(int i, boolean z, Locale locale) {
        if (useMiles(locale)) {
            return format(i, z, locale, Router.DistanceUnits.MILES);
        }
        return format(i, z, locale, Router.DistanceUnits.KILOMETERS);
    }

    public static String format(int i, boolean z, Locale locale, Router.DistanceUnits distanceUnits) {
        DecimalFormat decimalFormat2 = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        decimalFormat = decimalFormat2;
        decimalFormat2.applyPattern("#.#");
        if (i == 0) {
            return "";
        }
        int i2 = AnonymousClass1.$SwitchMap$com$mapzen$valhalla$Router$DistanceUnits[distanceUnits.ordinal()];
        if (i2 == 1) {
            return formatMiles(i, z);
        }
        if (i2 != 2) {
            return "";
        }
        return formatKilometers(i, z);
    }

    /* renamed from: com.mapzen.helpers.DistanceFormatter$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$mapzen$valhalla$Router$DistanceUnits;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.mapzen.valhalla.Router$DistanceUnits[] r0 = com.mapzen.valhalla.Router.DistanceUnits.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$mapzen$valhalla$Router$DistanceUnits = r0
                com.mapzen.valhalla.Router$DistanceUnits r1 = com.mapzen.valhalla.Router.DistanceUnits.MILES     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$mapzen$valhalla$Router$DistanceUnits     // Catch:{ NoSuchFieldError -> 0x001d }
                com.mapzen.valhalla.Router$DistanceUnits r1 = com.mapzen.valhalla.Router.DistanceUnits.KILOMETERS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mapzen.helpers.DistanceFormatter.AnonymousClass1.<clinit>():void");
        }
    }

    private static String formatMiles(int i, boolean z) {
        double d = ((double) i) / 0.3048d;
        if (d < 10.0d) {
            return formatDistanceLessThanTenFeet(d, z);
        }
        if (d < 528.0d) {
            return formatDistanceOverTenFeet(d);
        }
        return formatDistanceInMiles(i);
    }

    private static String formatKilometers(int i, boolean z) {
        if (i >= 100) {
            return formatDistanceInKilometers(i);
        }
        if (i > 10) {
            return formatDistanceOverTenMeters(i);
        }
        return formatShortMeters(i, z);
    }

    private static boolean useMiles(Locale locale) {
        return locale.equals(Locale.US) || locale.equals(Locale.UK);
    }

    private static String formatDistanceOverTenMeters(int i) {
        return String.format(Locale.getDefault(), "%s m", new Object[]{Integer.valueOf(i)});
    }

    private static String formatShortMeters(int i, boolean z) {
        return z ? "now" : formatDistanceOverTenMeters(i);
    }

    private static String formatDistanceInKilometers(int i) {
        String format = decimalFormat.format((double) (((float) i) / 1000.0f));
        return String.format(Locale.getDefault(), "%s km", new Object[]{format});
    }

    private static String formatDistanceLessThanTenFeet(double d, boolean z) {
        if (z) {
            return "now";
        }
        return String.format(Locale.getDefault(), "%d ft", new Object[]{Integer.valueOf((int) Math.floor(d))});
    }

    private static String formatDistanceOverTenFeet(double d) {
        int roundDownToNearestTen = roundDownToNearestTen(d);
        return String.format(Locale.getDefault(), "%d ft", new Object[]{Integer.valueOf(roundDownToNearestTen)});
    }

    private static String formatDistanceInMiles(int i) {
        return String.format(Locale.getDefault(), "%s mi", new Object[]{decimalFormat.format(((double) i) / 1609.0d)});
    }

    private static int roundDownToNearestTen(double d) {
        return ((int) Math.floor(d / 10.0d)) * 10;
    }
}
