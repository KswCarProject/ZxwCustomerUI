package com.szchoiceway.customerui.utils;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

public class MultipleUtils {
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    private static float sNoncompatDensity = 0.0f;
    private static float sNoncompatScaledDensity = 0.0f;
    public static float sScaledDensity = 1.0f;
    public static float sTargetDensity = 1.0f;

    public static void setCustomDensity(Activity activity, Resources resources, int i) {
        float f;
        int i2;
        float f2;
        if (resources != null) {
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            sNoncompatDensity = displayMetrics.density;
            sNoncompatScaledDensity = displayMetrics.scaledDensity / resources.getConfiguration().fontScale;
            if (i == 1) {
                i2 = 1080;
                f = (float) displayMetrics.widthPixels;
                f2 = 1.0f;
            } else {
                i2 = 720;
                f = (float) displayMetrics.heightPixels;
                f2 = 1.5f;
            }
            float f3 = (f * f2) / ((float) i2);
            float f4 = (sNoncompatScaledDensity / sNoncompatDensity) * f3;
            int i3 = (int) (160.0f * f3);
            sTargetDensity = f3;
            displayMetrics.density = f3;
            displayMetrics.scaledDensity = f4;
            displayMetrics.densityDpi = i3;
            if (activity != null) {
                DisplayMetrics displayMetrics2 = activity.getResources().getDisplayMetrics();
                displayMetrics2.density = f3;
                displayMetrics2.scaledDensity = f4;
                displayMetrics2.densityDpi = i3;
            }
            Log.i("TAG", "setCustomDensity: " + f3 + "  |   " + f4 + " | " + i3);
        }
    }

    public static boolean curUIModeNight(Configuration configuration) {
        return (configuration.uiMode & 48) == 32;
    }
}
