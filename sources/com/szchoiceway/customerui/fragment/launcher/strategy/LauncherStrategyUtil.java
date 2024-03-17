package com.szchoiceway.customerui.fragment.launcher.strategy;

import android.content.Context;
import com.szchoiceway.customerui.fragment.launcher.ksw.gs_ug._1024.KswGsUg1024LauncherStrategy;
import com.szchoiceway.customerui.fragment.launcher.ksw.gs_ug._1920.KswGsUg1920LauncherStrategy;
import com.szchoiceway.customerui.fragment.launcher.ksw.mbux_1024.KswMbux1024LauncherStrategy;
import com.szchoiceway.customerui.fragment.launcher.lexus.default_ui.LexusDefaultLauncherStrategy;

public class LauncherStrategyUtil {
    public static ILauncherStrategy getLauncherStrategy(Context context, int i) {
        if (KswMbux1024LauncherStrategy.matchModeSet(i)) {
            return new KswMbux1024LauncherStrategy(context);
        }
        if (KswGsUg1920LauncherStrategy.matchModeSet(i)) {
            return new KswGsUg1920LauncherStrategy(context);
        }
        if (KswGsUg1024LauncherStrategy.matchModeSet(i)) {
            return new KswGsUg1024LauncherStrategy(context);
        }
        if (LexusDefaultLauncherStrategy.matchModeSet(i)) {
            return new LexusDefaultLauncherStrategy(context);
        }
        return null;
    }

    private LauncherStrategyUtil() {
    }
}
