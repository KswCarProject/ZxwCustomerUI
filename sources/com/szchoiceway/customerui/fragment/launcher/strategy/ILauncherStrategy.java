package com.szchoiceway.customerui.fragment.launcher.strategy;

import android.view.View;

public interface ILauncherStrategy extends ILauncherFocusIndex, ILauncherTextInfo, ILauncherViewPager, ILauncherLeftButton, ILauncherMcuKey {
    int getLayoutId();

    void setView(View view);
}
