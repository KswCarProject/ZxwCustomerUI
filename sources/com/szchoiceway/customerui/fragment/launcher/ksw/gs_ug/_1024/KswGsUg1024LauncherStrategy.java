package com.szchoiceway.customerui.fragment.launcher.ksw.gs_ug._1024;

import android.content.Context;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.fragment.launcher.ksw.gs_ug.KswGsUgBaseLauncherStrategy;

public class KswGsUg1024LauncherStrategy extends KswGsUgBaseLauncherStrategy {
    private static final int LAYOUT_ID = 1879834938;
    private static final int PAGER1_LAYOUT_ID = 1879834775;
    private static final int PAGER2_LAYOUT_ID = 1879834776;
    private static final int PAGER3_LAYOUT_ID = 1879834777;
    private static final int PAGER4_LAYOUT_ID = 1879834778;

    public static boolean matchModeSet(int i) {
        return i == 35;
    }

    public int getLayoutId() {
        return R.layout.small_kesaiwei_common_gs_ug_1024_fragment_main_common;
    }

    public int getPager1LayoutId() {
        return R.layout.ksw_sg_ug_1024x600_whats1;
    }

    public int getPager2LayoutId() {
        return R.layout.ksw_sg_ug_1024x600_whats2;
    }

    public int getPager3LayoutId() {
        return R.layout.ksw_sg_ug_1024x600_whats3;
    }

    public int getPager4LayoutId() {
        return R.layout.ksw_sg_ug_1024x600_whats4;
    }

    public KswGsUg1024LauncherStrategy(Context context) {
        super(context);
    }

    public int[] getBackgroundImg() {
        return new int[]{R.drawable.ksw_common_gs_ug_1024_background_0, R.drawable.ksw_common_gs_ug_1024_background_1, R.drawable.ksw_common_gs_ug_1024_background_2, R.drawable.ksw_common_gs_ug_1024_background_3};
    }
}
