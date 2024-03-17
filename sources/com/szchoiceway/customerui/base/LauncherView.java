package com.szchoiceway.customerui.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.bmw.BMWApplistView;
import com.szchoiceway.customerui.bmw.BMWMainFragment;
import com.szchoiceway.customerui.drag.DragAppInfo;
import com.szchoiceway.customerui.drag.DragAppListInfo;
import com.szchoiceway.customerui.fragment.AppListView;
import com.szchoiceway.customerui.fragment.MainFragment;
import com.szchoiceway.customerui.model.LauncherModel;
import com.szchoiceway.customerui.utils.EventUtils;
import com.szchoiceway.customerui.utils.MultipleUtils;
import com.szchoiceway.customerui.utils.SysProviderOpt;
import com.szchoiceway.eventcenter.IEventService;
import java.util.ArrayList;

public class LauncherView extends BaseLauncherView {
    private static final String TAG = "LauncherView";
    private static final int WHAT_MSG_REFRESH_APPlIST = 1;
    private static final int WHAT_MSG_REFRESH_DASHBOARD_DATA = 0;
    private long firstHome = 0;
    private boolean isFirstHome = true;
    private LauncherModel launcherModel;
    /* access modifiers changed from: private */
    public AppListView mAppListView;
    private MainFragment mMainFragment;
    private MainHandler mMainHandler = new MainHandler(this);
    private final LauncherModel.LauncherModelCallback mModelCallback = new LauncherModel.LauncherModelCallback() {
        public void onShowAppList(boolean z) {
            LauncherView.this.showAppList(z);
        }

        public void onRefreshAppList() {
            if (LauncherView.this.mAppListView != null) {
                LauncherView.this.mAppListView.refreshAppList();
            }
        }
    };
    protected SysProviderOpt mProvider;
    private boolean mShowAppList = false;

    /* access modifiers changed from: protected */
    public void initEvent(View view) {
    }

    /* access modifiers changed from: protected */
    public void refreshPaging() {
    }

    /* access modifiers changed from: protected */
    public void setBtStatus(int i) {
    }

    public LauncherView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mProvider = SysProviderOpt.getInstance(context);
        this.launcherModel = LauncherModel.getInstance();
        Log.i(TAG, "LauncherView:");
    }

    /* access modifiers changed from: protected */
    public int inflateLayout() {
        int recordInteger = SysProviderOpt.getInstance(this.mContext).getRecordInteger("KESAIWEI_SYS_MODE_SELECTION", 16);
        SysProviderOpt.getInstance(this.mContext).getRecordValue(SysProviderOpt.KSW_FACTORY_SET_CLIENT, "");
        return (recordInteger == 17 || recordInteger == 20) ? R.layout.layout_launcher_view_bmw : R.layout.layout_launcher_view;
    }

    /* access modifiers changed from: protected */
    public void init() {
        super.init();
        DragAppListInfo.initMainArray(this.launcherModel.getUITypeVer(), this.launcherModel.getClient(), LauncherModel.getInstance().getResolution());
        this.launcherModel.setCallback(this.mModelCallback);
    }

    /* access modifiers changed from: protected */
    public void initView(View view) {
        Log.d(TAG, "initView");
        MainFragment mainFragment = (MainFragment) view.findViewById(R.id.layout_mainView);
        this.mMainFragment = mainFragment;
        if (mainFragment != null && (mainFragment instanceof BMWMainFragment)) {
            ((BMWMainFragment) mainFragment).setMainFragmentCallBack(new BMWMainFragment.MainFragmentCallBack() {
                public final void setInEditMode(boolean z) {
                    LauncherView.this.lambda$initView$0$LauncherView(z);
                }
            });
        }
        AppListView appListView = (AppListView) view.findViewById(R.id.layout_applist);
        this.mAppListView = appListView;
        if (appListView != null) {
            appListView.setLauncherView(this);
            AppListView appListView2 = this.mAppListView;
            if (appListView2 instanceof BMWApplistView) {
                ((BMWApplistView) appListView2).setBMWApplistCallBack(new BMWApplistView.BMWApplistCallBack() {
                    public final void addItem(DragAppInfo dragAppInfo) {
                        LauncherView.this.lambda$initView$1$LauncherView(dragAppInfo);
                    }
                });
            }
        }
        refreshAppListsView();
        showAppList(false);
        this.mMainHandler.sendEmptyMessageDelayed(0, 500);
    }

    public /* synthetic */ void lambda$initView$0$LauncherView(boolean z) {
        AppListView appListView = this.mAppListView;
        if (appListView != null && (appListView instanceof BMWApplistView)) {
            ((BMWApplistView) appListView).setInEditMode(z);
        }
    }

    public /* synthetic */ void lambda$initView$1$LauncherView(DragAppInfo dragAppInfo) {
        MainFragment mainFragment = this.mMainFragment;
        if (mainFragment != null && (mainFragment instanceof BMWMainFragment)) {
            ((BMWMainFragment) mainFragment).addItem(dragAppInfo);
        }
    }

    public void showAppList(boolean z) {
        Log.i(TAG, "showAppList: show = " + z);
        IEventService evtService = LauncherModel.getInstance().getEvtService();
        this.mShowAppList = z;
        if (evtService != null) {
            try {
                evtService.showAppList(z);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        if (this.mShowAppList) {
            if (evtService != null) {
                try {
                    evtService.sendKSW_0x00_0x67_third();
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            }
            boolean needRefreshAppList = LauncherModel.getInstance().needRefreshAppList();
            Log.d(TAG, "needRefreshAppList = " + needRefreshAppList);
            if (needRefreshAppList) {
                refreshAppListsView();
            }
        }
        MainFragment mainFragment = this.mMainFragment;
        int i = 8;
        if (mainFragment != null) {
            mainFragment.setVisibility(z ? 8 : 0);
            MainFragment mainFragment2 = this.mMainFragment;
            if (mainFragment2 instanceof BMWMainFragment) {
                ((BMWMainFragment) mainFragment2).refreshWeatherItem();
            }
        }
        AppListView appListView = this.mAppListView;
        if (appListView != null) {
            if (z) {
                i = 0;
            }
            appListView.setVisibility(i);
            if (!this.mShowAppList) {
                this.mAppListView.setiAppsFocusIndex(-1);
            }
        }
        int recordInteger = SysProviderOpt.getInstance(this.mContext).getRecordInteger("KSW_LANDROVER_THEME_BACKGROUND_INDEX", 0);
        if ((LauncherModel.getInstance().m_iModeSet == 32 || LauncherModel.getInstance().m_iModeSet == 39) && recordInteger == 1) {
            Log.d(TAG, "send blackFont " + (this.mShowAppList ^ true ? 1 : 0));
            Intent intent = new Intent(EventUtils.ACTION_LAUNCHER_CHANGE_STATUS_BAR_COLOR);
            intent.putExtra("blackFont", this.mShowAppList ^ true ? 1 : 0);
            this.mContext.sendBroadcast(intent);
        }
        if (LauncherModel.getInstance().m_iModeSet != 40) {
            return;
        }
        if (z) {
            setSystemUiVisibility(0);
        } else {
            setSystemUiVisibility(com.szchoiceway.zxwlib.utils.EventUtils.EVENT_BACKCAR_END);
        }
    }

    /* access modifiers changed from: protected */
    public void packageChange() {
        Log.d(TAG, "packageChange");
        this.mMainHandler.removeMessages(1);
        this.mMainHandler.sendEmptyMessageDelayed(1, 1000);
    }

    /* access modifiers changed from: protected */
    public void onReasonKey(String str) {
        MainFragment mainFragment;
        MainFragment mainFragment2;
        MainFragment mainFragment3;
        Log.i(TAG, "onReasonKey: 点击" + str + "...");
        if ("backkey".equals(str)) {
            if (this.mMainFragment != null && this.mLauncherResume) {
                MainFragment mainFragment4 = this.mMainFragment;
                if ((mainFragment4 instanceof BMWMainFragment) && ((BMWMainFragment) mainFragment4).onKeyDown(4)) {
                    return;
                }
            }
            if (this.mShowAppList && this.mLauncherResume && this.mAppListView != null) {
                showAppList(false);
            }
            if (!this.mShowAppList && this.mLauncherResume && (mainFragment3 = this.mMainFragment) != null) {
                mainFragment3.exitEditMode();
                this.mMainFragment.hideDialog();
            }
        } else if ("homekey".equals(str)) {
            if (this.isFirstHome) {
                this.firstHome = System.currentTimeMillis();
                this.isFirstHome = false;
            } else {
                long currentTimeMillis = System.currentTimeMillis();
                this.isFirstHome = true;
                if (Math.abs(currentTimeMillis - this.firstHome) < 300) {
                    return;
                }
            }
            if (this.mMainFragment != null && this.mLauncherResume) {
                MainFragment mainFragment5 = this.mMainFragment;
                if ((mainFragment5 instanceof BMWMainFragment) && ((BMWMainFragment) mainFragment5).onKeyDown(3)) {
                    return;
                }
            }
            if (!this.mShowAppList && this.mLauncherResume && (mainFragment2 = this.mMainFragment) != null) {
                mainFragment2.hideDialog();
            }
            Log.d(TAG, "mLauncherResume = " + this.mLauncherResume);
            showAppList(false);
        } else if ("recentapps".equals(str)) {
            Log.i(TAG, "onReceive: 多任务键被监听");
            if (!this.mShowAppList && this.mLauncherResume && (mainFragment = this.mMainFragment) != null) {
                mainFragment.onClickRecentTask();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setHandbrakeStatus(boolean z) {
        MainFragment mainFragment = this.mMainFragment;
        if (mainFragment != null) {
            mainFragment.setHandbrakeStatus(z);
        }
    }

    /* access modifiers changed from: protected */
    public void setSeatBeltStatus(boolean z) {
        MainFragment mainFragment = this.mMainFragment;
        if (mainFragment != null) {
            mainFragment.setSeatBeltStatus(z);
        }
    }

    /* access modifiers changed from: protected */
    public void setRotatingSpeed(int i) {
        MainFragment mainFragment = this.mMainFragment;
        if (mainFragment != null) {
            mainFragment.setRotatingSpeed(i);
        }
    }

    /* access modifiers changed from: protected */
    public void setSpeed(int i) {
        MainFragment mainFragment = this.mMainFragment;
        if (mainFragment != null) {
            mainFragment.setSpeed(i);
        }
    }

    /* access modifiers changed from: protected */
    public void setSpeedUnit() {
        MainFragment mainFragment = this.mMainFragment;
        if (mainFragment != null) {
            mainFragment.setSpeedUnit();
        }
    }

    /* access modifiers changed from: protected */
    public void setTempValue(String str) {
        MainFragment mainFragment = this.mMainFragment;
        if (mainFragment != null) {
            mainFragment.setTempValue(str);
        }
    }

    /* access modifiers changed from: protected */
    public void setQilValue(String str) {
        MainFragment mainFragment = this.mMainFragment;
        if (mainFragment != null) {
            mainFragment.setQilValue(str);
        }
    }

    /* access modifiers changed from: protected */
    public void setDrivingRangeValue(String str) {
        MainFragment mainFragment = this.mMainFragment;
        if (mainFragment != null) {
            mainFragment.setDrivingRangeValue(str);
        }
    }

    /* access modifiers changed from: protected */
    public void setOilConsumption(String str) {
        MainFragment mainFragment = this.mMainFragment;
        if (mainFragment != null) {
            mainFragment.setOilConsumption(str);
        }
    }

    /* access modifiers changed from: protected */
    public void refreshPlayState() {
        MainFragment mainFragment = this.mMainFragment;
        if (mainFragment instanceof BMWMainFragment) {
            ((BMWMainFragment) mainFragment).refreshPlayState();
        } else if (mainFragment != null) {
            mainFragment.refreshPlayState();
        }
    }

    /* access modifiers changed from: protected */
    public void setMusicCoverBg(Bitmap bitmap) {
        MainFragment mainFragment = this.mMainFragment;
        if (mainFragment != null) {
            mainFragment.setMusicCoverBg(bitmap);
        }
    }

    /* access modifiers changed from: protected */
    public void refreshNaviInfo(int i, int i2, Bundle bundle) {
        MainFragment mainFragment = this.mMainFragment;
        if (mainFragment != null) {
            mainFragment.refreshGaodeNaviInfo(bundle);
        }
    }

    /* access modifiers changed from: protected */
    public void refreshBTStatus() {
        Log.d(TAG, "refreshBTStatus");
        MainFragment mainFragment = this.mMainFragment;
        if (mainFragment != null) {
            mainFragment.refreshBTMessage();
        }
    }

    /* access modifiers changed from: protected */
    public void setCurPlayVideoPath(String str) {
        MainFragment mainFragment = this.mMainFragment;
        if (mainFragment != null) {
            mainFragment.setCurPlayVideoPath(str);
        }
    }

    /* access modifiers changed from: protected */
    public void setFocusMove(int i) {
        if (this.mShowAppList || this.isInMultiWindowMode) {
            AppListView appListView = this.mAppListView;
            if (appListView != null) {
                appListView.setAppsFocusMove(i, this.mLauncherResume);
                return;
            }
            return;
        }
        MainFragment mainFragment = this.mMainFragment;
        if (mainFragment != null) {
            mainFragment.setMainFocusMove(i, this.mLauncherResume);
        }
    }

    /* access modifiers changed from: protected */
    public void refreshStorageView() {
        MainFragment mainFragment = this.mMainFragment;
        if (mainFragment != null) {
            mainFragment.refreshStorageView();
        }
    }

    /* access modifiers changed from: protected */
    public void refreshWifiView(int i) {
        MainFragment mainFragment = this.mMainFragment;
        if (mainFragment != null) {
            mainFragment.refreshWifiView(i);
        }
    }

    /* access modifiers changed from: protected */
    public void refreshSignalView(int i, String str) {
        MainFragment mainFragment = this.mMainFragment;
        if (mainFragment != null) {
            mainFragment.refreshSignalView(i, str);
        }
    }

    public MainHandler getMainHandler() {
        return this.mMainHandler;
    }

    public class MainHandler extends Handler {
        private LauncherView mLauncherView;

        public MainHandler(LauncherView launcherView) {
            this.mLauncherView = launcherView;
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                try {
                    if (LauncherModel.getInstance().getEvtService() != null) {
                        LauncherModel.getInstance().getEvtService().sendMcuData_KSW(new byte[]{-14, 0, EventUtils.MCU_KEY1_3, 2, 5, 0});
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } else if (i == 1) {
                LauncherView.this.refreshAppListsView();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void refreshBenzControl(ArrayList<Integer> arrayList) {
        MainFragment mainFragment = this.mMainFragment;
        if (mainFragment != null) {
            mainFragment.refreshBenzControl(arrayList);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        Log.d(TAG, "onResume");
        MultipleUtils.setCustomDensity((Activity) null, this.mContext.getResources(), 0);
        boolean needRefreshAppList = LauncherModel.getInstance().needRefreshAppList();
        Log.d(TAG, "needRefreshAppList = " + needRefreshAppList);
        if (needRefreshAppList) {
            refreshAppListsView();
        }
        MainFragment mainFragment = this.mMainFragment;
        if (mainFragment != null) {
            mainFragment.onResume();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainFragment mainFragment = this.mMainFragment;
        if (mainFragment != null) {
            mainFragment.onPause();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        removeAllViews();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeAllViews();
    }

    /* access modifiers changed from: protected */
    public void refreshAppList() {
        refreshAppListsView();
    }

    /* access modifiers changed from: protected */
    public void refreshDateTime() {
        MainFragment mainFragment = this.mMainFragment;
        if (mainFragment != null) {
            mainFragment.refreshDateTime();
        }
    }

    /* access modifiers changed from: protected */
    public void refreshLeftApp(int i) {
        MainFragment mainFragment = this.mMainFragment;
        if (mainFragment != null) {
            mainFragment.refreshLeftApp(i);
        }
    }

    /* access modifiers changed from: protected */
    public void refreshLocalChanged() {
        MainFragment mainFragment = this.mMainFragment;
        if (mainFragment != null) {
            mainFragment.refreshLocalChanged();
        }
    }

    /* access modifiers changed from: protected */
    public void refreshBTConnectName(String str) {
        MainFragment mainFragment = this.mMainFragment;
        if (mainFragment != null) {
            mainFragment.refreshBTConnectName(str);
        }
    }

    public void addThirdApp(String str, String str2) {
        MainFragment mainFragment = this.mMainFragment;
        if (mainFragment != null) {
            mainFragment.addThirdApp(str, str2);
        }
        showAppList(false);
    }

    /* access modifiers changed from: private */
    public void refreshAppListsView() {
        Log.d(TAG, "refreshAppListsView");
        LauncherModel.getInstance().initAppData();
    }
}
