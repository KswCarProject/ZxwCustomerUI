package com.szchoiceway.customerui.fragment.launcher.ksw.gs_ug;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.fragment.launcher.strategy.BaseLauncherStrategy;

public abstract class KswGsUgBaseLauncherStrategy extends BaseLauncherStrategy {
    protected View appView;
    protected View browserView;
    protected View btView;
    protected View carAutoView;
    protected View dashBoardView;
    protected View dvrView;
    protected View fileManagerView;
    protected View leftAppView;
    protected View leftMusicView;
    protected View leftNaviView;
    protected View leftSettingsView;
    protected View musicView;
    protected View naviView;
    protected View originalCarView;
    protected View pager1;
    protected View pager2;
    protected View pager3;
    protected View pager4;
    protected View settingsView;
    protected View videoView;

    public int getAppsLeftFocusIndex() {
        return 3;
    }

    public int getAppsMainFocusIndex() {
        return 4;
    }

    public abstract int[] getBackgroundImg();

    public int getBrowserMainFocusIndex() {
        return 9;
    }

    public int getBtMainFocusIndex() {
        return 1;
    }

    public int getCarPlayMainFocusIndex() {
        return 5;
    }

    public int getDVRMainFocusIndex() {
        return 10;
    }

    public int getDashBoardMainFocusIndex() {
        return 7;
    }

    public int getFileManagerMainFocusIndex() {
        return 11;
    }

    public int getMusicLeftFocusIndex() {
        return 0;
    }

    public int getMusicMainFocusIndex() {
        return 2;
    }

    public int getNaviLeftFocusIndex() {
        return 1;
    }

    public int getNaviMainFocusIndex() {
        return 0;
    }

    public int getOriginalCarMainFocusIndex() {
        return 6;
    }

    public abstract int getPager1LayoutId();

    public abstract int getPager2LayoutId();

    public abstract int getPager3LayoutId();

    public abstract int getPager4LayoutId();

    public int getPagerItemCount() {
        return 3;
    }

    public int getSettingsLeftFocusIndex() {
        return 2;
    }

    public int getSettingsMainFocusIndex() {
        return 8;
    }

    public int getVideoMainFocusIndex() {
        return 3;
    }

    /* access modifiers changed from: protected */
    public void setViewInner(View view) {
    }

    protected KswGsUgBaseLauncherStrategy(Context context) {
        super(context);
    }

    public void initPagerList() {
        ViewGroup viewGroup = this.view instanceof ViewGroup ? (ViewGroup) this.view : null;
        if (this.pager1 == null) {
            this.pager1 = initPager1(viewGroup);
        }
        if (this.pager2 == null) {
            this.pager2 = initPager2(viewGroup);
        }
        if (this.pager3 == null) {
            this.pager3 = initPager3(viewGroup);
        }
        if (this.pager4 == null) {
            this.pager4 = initPager4(viewGroup);
        }
    }

    public View getPager1() {
        return this.pager1;
    }

    public View getPager2() {
        return this.pager2;
    }

    public View getPager3() {
        return this.pager3;
    }

    public View getPager4() {
        return this.pager4;
    }

    public int[] getPager1ButtonIdArray() {
        return new int[]{R.id.btnNavi, R.id.btnBt, R.id.btnMusic};
    }

    public int[] getPager2ButtonIdArray() {
        return new int[]{R.id.btnVideo, R.id.btnApps, R.id.btnCarplay};
    }

    public int[] getPager3ButtonIdArray() {
        return new int[]{R.id.btnOriginalCar, R.id.btnDashBoard, R.id.btnSettins};
    }

    public int[] getPager4ButtonIdArray() {
        return new int[]{R.id.btnBrowser, R.id.btnDvr, R.id.btnFile};
    }

    public View[] getMainButtonArray() {
        return new View[]{this.naviView, this.btView, this.musicView, this.videoView, this.appView, this.carAutoView, this.originalCarView, this.dashBoardView, this.settingsView, this.browserView, this.dvrView, this.fileManagerView};
    }

    public View[] getLeftButtonViewArray() {
        return new View[]{this.leftMusicView, this.leftNaviView, this.leftSettingsView, this.leftAppView};
    }

    public void initLeftButton() {
        this.leftMusicView = this.view.findViewById(R.id.btnLeftMusic);
        this.leftNaviView = this.view.findViewById(R.id.btnLeftNavi);
        this.leftSettingsView = this.view.findViewById(R.id.btnLeftSetting);
        this.leftAppView = this.view.findViewById(R.id.btnLeftApps);
    }

    private View initPager1(ViewGroup viewGroup) {
        View inflate = getLayoutInflater().inflate(getPager1LayoutId(), viewGroup, false);
        this.naviView = inflate.findViewById(R.id.btnNavi);
        this.btView = inflate.findViewById(R.id.btnBt);
        this.musicView = inflate.findViewById(R.id.btnMusic);
        return inflate;
    }

    private View initPager2(ViewGroup viewGroup) {
        View inflate = getLayoutInflater().inflate(getPager2LayoutId(), viewGroup, false);
        this.videoView = inflate.findViewById(R.id.btnVideo);
        this.appView = inflate.findViewById(R.id.btnApps);
        this.carAutoView = inflate.findViewById(R.id.btnCarplay);
        return inflate;
    }

    private View initPager3(ViewGroup viewGroup) {
        View inflate = getLayoutInflater().inflate(getPager3LayoutId(), viewGroup, false);
        this.originalCarView = inflate.findViewById(R.id.btnOriginalCar);
        this.dashBoardView = inflate.findViewById(R.id.btnDashBoard);
        this.settingsView = inflate.findViewById(R.id.btnSettins);
        return inflate;
    }

    private View initPager4(ViewGroup viewGroup) {
        View inflate = getLayoutInflater().inflate(getPager4LayoutId(), viewGroup, false);
        this.browserView = inflate.findViewById(R.id.btnBrowser);
        this.dvrView = inflate.findViewById(R.id.btnDvr);
        this.fileManagerView = inflate.findViewById(R.id.btnFile);
        return inflate;
    }
}
