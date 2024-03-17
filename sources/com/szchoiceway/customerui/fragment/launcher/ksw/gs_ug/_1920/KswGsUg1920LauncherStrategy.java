package com.szchoiceway.customerui.fragment.launcher.ksw.gs_ug._1920;

import android.content.Context;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.fragment.launcher.ksw.gs_ug.KswGsUgBaseLauncherStrategy;

public class KswGsUg1920LauncherStrategy extends KswGsUgBaseLauncherStrategy {
    private static final int LAYOUT_ID = 1879834939;
    private static final int PAGER1_LAYOUT_ID = 1879834779;
    private static final int PAGER2_LAYOUT_ID = 1879834780;
    private static final int PAGER3_LAYOUT_ID = 1879834781;
    private static final int PAGER4_LAYOUT_ID = 1879834782;

    public static boolean matchModeSet(int i) {
        return i == 34;
    }

    public int getLayoutId() {
        return R.layout.small_kesaiwei_common_gs_ug_1920_fragment_main_common;
    }

    public int getPager1LayoutId() {
        return R.layout.ksw_sg_ug_1920_and_1280_whats1;
    }

    public int getPager2LayoutId() {
        return R.layout.ksw_sg_ug_1920_and_1280_whats2;
    }

    public int getPager3LayoutId() {
        return R.layout.ksw_sg_ug_1920_and_1280_whats3;
    }

    public int getPager4LayoutId() {
        return R.layout.ksw_sg_ug_1920_and_1280_whats4;
    }

    public KswGsUg1920LauncherStrategy(Context context) {
        super(context);
    }

    public int[] getBackgroundImg() {
        return new int[]{R.drawable.ksw_common_gs_ug_1920_background_0, R.drawable.ksw_common_gs_ug_1920_background_1, R.drawable.ksw_common_gs_ug_1920_background_2, R.drawable.ksw_common_gs_ug_1920_background_3};
    }
}
