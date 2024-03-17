package com.szchoiceway.customerui.fragment.launcher.strategy;

import android.view.View;

public interface ILauncherViewPager {
    View[] getMainButtonArray() {
        return null;
    }

    View getPager1() {
        return null;
    }

    int[] getPager1ButtonIdArray() {
        return null;
    }

    View getPager2() {
        return null;
    }

    int[] getPager2ButtonIdArray() {
        return null;
    }

    View getPager3() {
        return null;
    }

    int[] getPager3ButtonIdArray() {
        return null;
    }

    View getPager4() {
        return null;
    }

    int[] getPager4ButtonIdArray() {
        return null;
    }

    int getPagerItemCount() {
        return -1;
    }

    void initPagerList() {
    }

    void setPageSelected(int i) {
    }
}
