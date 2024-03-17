package com.szchoiceway.customerui.bmw.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.adapter.HorizontalPageLayoutManager;
import com.szchoiceway.customerui.adapter.PagingScrollHelper;
import com.szchoiceway.customerui.bmw.BMWApplistView;
import com.szchoiceway.customerui.bmw.recycle.BMWAppListFocusRecycleView;
import com.szchoiceway.customerui.bmw.recycle.BMWMRecyclerVierAdapter;
import com.szchoiceway.customerui.drag.DragAppInfo;
import com.szchoiceway.customerui.drag.DragAppListInfo;
import com.szchoiceway.customerui.model.LauncherModel;
import com.szchoiceway.customerui.utils.EventUtils;
import com.szchoiceway.customerui.utils.SysProviderOpt;
import com.szchoiceway.zxwlib.focus.FocusObserver;
import com.szchoiceway.zxwlib.focus.view.FocusIconWithTitleView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ApplistUIController extends ApplistUIControllerBase implements PagingScrollHelper.onPageChangeListener, BMWMRecyclerVierAdapter.OnItemClickListener, BMWMRecyclerVierAdapter.OnItemLongClickListener, View.OnClickListener {
    private static final String TAG = "ApplistUIController";
    /* access modifiers changed from: private */
    public BMWMRecyclerVierAdapter BMWMRecyclerVierAdapter;
    private List<String> auxPackageNames = new ArrayList();
    private boolean bAppsPageChanged = true;
    public boolean bInLeftFocus;
    private int iAppsFocusIndex = -1;
    private int iColumns = 7;
    private int iLastAppsPageIndex = 0;
    private int iRows = 2;
    private View mActionBar;
    private AppListPageReceiver mAppListPageReceiver = new AppListPageReceiver();
    private BMWApplistView mBmwApplistView;
    private FocusIconWithTitleView mBtnE;
    private int mCurPage = 0;
    private FocusObserver mFocusObserver;
    private boolean mInEditMode = false;
    private View mLayoutRecyclerViewRoot;
    private RelativeLayout mLeftR = null;
    /* access modifiers changed from: private */
    public int mMode = 0;
    private long mOldTime = 0;
    int mPageState = 0;
    private BMWAppListFocusRecycleView mRecyclerView;
    private PagingScrollHelper mScrollHelper;
    private Toast mTip;
    private boolean mViewVisible = false;
    private List<String> sortClassNames = new ArrayList();

    public int getLayout() {
        return R.layout.bmw_layout_applist;
    }

    public void setFocusObserver(FocusObserver focusObserver) {
    }

    public ApplistUIController(Context context) {
        super(context);
    }

    public void init(View view, BMWApplistView bMWApplistView) {
        this.mAppListPageReceiver.registerRec();
        this.mBmwApplistView = bMWApplistView;
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.leftR);
        this.mLeftR = relativeLayout;
        relativeLayout.setOnClickListener(this);
        this.mLayoutRecyclerViewRoot = view.findViewById(R.id.LayoutRecyclerViewRoot);
        this.mActionBar = view.findViewById(R.id.VActionBar);
        this.mRecyclerView = (BMWAppListFocusRecycleView) view.findViewById(R.id.app_listview);
        BMWMRecyclerVierAdapter bMWMRecyclerVierAdapter = new BMWMRecyclerVierAdapter(bMWApplistView, LauncherModel.getInstance().getAppInfoMap());
        this.BMWMRecyclerVierAdapter = bMWMRecyclerVierAdapter;
        this.mRecyclerView.setAdapter(bMWMRecyclerVierAdapter);
        this.mRecyclerView.setLayoutManager(new HorizontalPageLayoutManager(this.iRows, this.iColumns));
        PagingScrollHelper pagingScrollHelper = new PagingScrollHelper();
        this.mScrollHelper = pagingScrollHelper;
        pagingScrollHelper.setUpRecycleView(this.mRecyclerView);
        this.mScrollHelper.setOnPageChangeListener(this);
        this.mRecyclerView.setHorizontalScrollBarEnabled(true);
        this.BMWMRecyclerVierAdapter.setOnItemClickListener(this);
        this.BMWMRecyclerVierAdapter.setOnItemLongClickListener(this);
        int recordInteger = SysProviderOpt.getInstance(this.mContext).getRecordInteger("KESAIWEI_SYS_DISPLAY_MODE", 0);
        this.mMode = recordInteger;
        this.BMWMRecyclerVierAdapter.setmKSWDisplayMode(recordInteger);
        this.mBtnE = (FocusIconWithTitleView) view.findViewById(R.id.btnE);
        FocusObserver focusObserver = new FocusObserver();
        this.mFocusObserver = focusObserver;
        focusObserver.setmControlByKnob(true);
        Log.i(TAG, "init: mCurFocusPositon-------xx-------");
        FocusObserver focusObserver2 = this.mFocusObserver;
        if (focusObserver2 != null) {
            focusObserver2.setLayout(this.mLayoutRecyclerViewRoot);
            this.mFocusObserver.setmCurFocusViewId(R.id.app_listview);
            this.mFocusObserver.setmCurPosition(0);
        }
        refreshBtnE();
        initSortClassNames();
        initAuxPackageNames();
    }

    /* access modifiers changed from: private */
    public void refreshBtnE() {
        FocusIconWithTitleView focusIconWithTitleView = this.mBtnE;
        if (focusIconWithTitleView != null) {
            int i = this.mMode;
            if (i == 0) {
                focusIconWithTitleView.setImageForeground((int) R.drawable.bmw_id8_left_btn_forground);
            } else if (i == 1) {
                focusIconWithTitleView.setImageForeground((int) R.drawable.bmw_id8_left_btn_forground_red);
            } else if (i == 2) {
                focusIconWithTitleView.setImageForeground((int) R.drawable.bmw_id8_left_btn_forground_yellow);
            }
        }
    }

    public void unInit() {
        AppListPageReceiver appListPageReceiver = this.mAppListPageReceiver;
        if (appListPageReceiver != null) {
            appListPageReceiver.unregisterRec();
        }
    }

    public void setData(HashMap<String, DragAppInfo> hashMap) {
        BMWMRecyclerVierAdapter bMWMRecyclerVierAdapter = this.BMWMRecyclerVierAdapter;
        if (bMWMRecyclerVierAdapter != null) {
            bMWMRecyclerVierAdapter.setData(hashMap);
        }
        PagingScrollHelper pagingScrollHelper = this.mScrollHelper;
        if (pagingScrollHelper != null) {
            int page = pagingScrollHelper.getPage();
            int pageCount = getPageCount();
            if (pageCount > 0 && page > pageCount - 1) {
                this.mScrollHelper.scrollToPosition(0);
            }
        }
    }

    public void setInEditMode(boolean z) {
        this.mInEditMode = z;
        RelativeLayout relativeLayout = this.mLeftR;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(z ? 0 : 8);
        }
        BMWMRecyclerVierAdapter bMWMRecyclerVierAdapter = this.BMWMRecyclerVierAdapter;
        if (bMWMRecyclerVierAdapter != null) {
            bMWMRecyclerVierAdapter.setmInEditMode(z);
        }
    }

    public void setAppsFocusMove(int i, boolean z) {
        if (i >= 0 && z && this.mViewVisible) {
            BMWMRecyclerVierAdapter bMWMRecyclerVierAdapter = this.BMWMRecyclerVierAdapter;
            if (bMWMRecyclerVierAdapter != null && !bMWMRecyclerVierAdapter.getFocusStatus()) {
                this.BMWMRecyclerVierAdapter.showFocus();
                FocusObserver focusObserver = this.mFocusObserver;
                if (focusObserver != null) {
                    focusObserver.setmCurPosition(-1);
                }
            }
            if (i == 7 || i == 1) {
                i = 3;
            } else if (i == 8 || i == 2) {
                i = 4;
            }
            if (this.mFocusObserver != null) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (elapsedRealtime - this.mOldTime > 100 && this.mPageState == 0) {
                    this.mOldTime = elapsedRealtime;
                    this.mFocusObserver.doNextFocus(i);
                    scrollToPosition((this.mFocusObserver.getmCurPosition() / this.iRows) / this.iColumns);
                }
            }
        }
    }

    public void onVisibilityChanged(int i) {
        FocusObserver focusObserver;
        View view;
        this.mViewVisible = i == 0;
        Log.i(TAG, "onVisibilityChanged: " + i);
        if (i == 0 && (focusObserver = this.mFocusObserver) != null && (view = this.mLayoutRecyclerViewRoot) != null) {
            focusObserver.setLayout(view);
            this.mFocusObserver.setmCurFocusViewId(R.id.app_listview);
            BMWAppListFocusRecycleView bMWAppListFocusRecycleView = this.mRecyclerView;
            if (bMWAppListFocusRecycleView != null) {
                this.mFocusObserver.setmCurPosition(bMWAppListFocusRecycleView.getFirstVisiblePosition());
                BMWMRecyclerVierAdapter bMWMRecyclerVierAdapter = this.BMWMRecyclerVierAdapter;
                if (bMWMRecyclerVierAdapter != null) {
                    bMWMRecyclerVierAdapter.hideFocus();
                }
            }
        }
    }

    private void scrollToPosition(int i) {
        PagingScrollHelper pagingScrollHelper = this.mScrollHelper;
        if (pagingScrollHelper != null) {
            pagingScrollHelper.scrollToPosition(i);
        }
    }

    public void onPageState(int i) {
        this.mPageState = i;
    }

    public void onPageChange(int i) {
        if (!this.BMWMRecyclerVierAdapter.getFocusStatus()) {
            this.BMWMRecyclerVierAdapter.hideFocus();
        }
        BMWAppListFocusRecycleView bMWAppListFocusRecycleView = this.mRecyclerView;
        if (bMWAppListFocusRecycleView != null) {
            bMWAppListFocusRecycleView.setmCueDisplayPage(i);
            int i2 = this.mCurPage;
            if (i > i2) {
                FocusObserver focusObserver = this.mFocusObserver;
                if (focusObserver != null) {
                    focusObserver.setmCurPosition(this.iColumns * i * this.iRows);
                }
            } else if (i < i2) {
                FocusObserver focusObserver2 = this.mFocusObserver;
                if (focusObserver2 != null) {
                    focusObserver2.setmCurPosition((((i + 1) * this.iColumns) * this.iRows) - 1);
                }
            } else {
                FocusObserver focusObserver3 = this.mFocusObserver;
                if (focusObserver3 != null) {
                    focusObserver3.getmCurPosition();
                }
            }
            BMWMRecyclerVierAdapter bMWMRecyclerVierAdapter = this.BMWMRecyclerVierAdapter;
            if (bMWMRecyclerVierAdapter != null) {
                bMWMRecyclerVierAdapter.notifyDataSetChanged();
            }
            this.mCurPage = i;
        }
    }

    public int getPageCount() {
        BMWMRecyclerVierAdapter bMWMRecyclerVierAdapter = this.BMWMRecyclerVierAdapter;
        int i = 0;
        if (bMWMRecyclerVierAdapter == null) {
            return 0;
        }
        int i2 = this.iRows * this.iColumns;
        int itemCount = bMWMRecyclerVierAdapter.getItemCount() / i2;
        if (this.BMWMRecyclerVierAdapter.getItemCount() % i2 > 0) {
            i = 1;
        }
        return itemCount + i;
    }

    public void onItemClick(View view, int i) {
        Log.d(TAG, "onItemClick");
        DragAppInfo dragAppInfo = DragAppListInfo.mAppFragmentItemDetailsMap.get(DragAppListInfo.mAppsFragmentItemTagList.get(i));
        String appPackageName = dragAppInfo.getAppPackageName();
        String appClassName = dragAppInfo.getAppClassName();
        if (this.mInEditMode) {
            if (!"com.txznet.weather".equals(appPackageName) || !"com.txznet.weather.MainActivity".equals(appClassName)) {
                BMWApplistView bMWApplistView = this.mBmwApplistView;
                if (bMWApplistView != null) {
                    bMWApplistView.addItem(dragAppInfo);
                }
            } else {
                SysProviderOpt.getInstance(this.mContext).updateRecord(SysProviderOpt.KSW_HAVE_WEATHER_MAIN, "1");
            }
            LauncherModel.getInstance().showAppList(false);
            this.mInEditMode = false;
        } else {
            LauncherModel instance = LauncherModel.getInstance();
            if (appPackageName.equals(EventUtils.LETTER_CARPLAY_MODE_PACKAGE_NAME)) {
                EventUtils.startActivityType(6, this.mContext, instance.getEvtService());
            } else if (appPackageName.equals(EventUtils.ZLINK_MODE_PACKAGE_NAME) && !appClassName.equals(EventUtils.ZLINK_DLNA_MODE_CLASS_NAME)) {
                EventUtils.startActivityType(7, this.mContext, instance.getEvtService());
            } else if (this.auxPackageNames.contains(appPackageName)) {
                try {
                    if (instance.getEvtService() != null && instance.getEvtService().isUpgradeMode()) {
                        return;
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                if (!"com.szchoiceway.ksw_dvr".equals(appPackageName) || !"com.szchoiceway.ksw_dvr.MainActivity".equals(appClassName)) {
                    EventUtils.startActivityIfNotRunning(this.mContext, appPackageName, appClassName);
                } else {
                    EventUtils.onEnterDvr(this.mContext);
                }
            } else {
                try {
                    Log.d(TAG, "sendThirdApp0");
                    if (!(instance == null || instance.getEvtService() == null)) {
                        instance.getEvtService().sendKSW_0x00_0x67_third();
                    }
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
                EventUtils.startActivityIfNotRunning(this.mContext, appPackageName, appClassName);
            }
        }
        BMWMRecyclerVierAdapter bMWMRecyclerVierAdapter = this.BMWMRecyclerVierAdapter;
        if (bMWMRecyclerVierAdapter != null) {
            bMWMRecyclerVierAdapter.hideFocus();
        }
        FocusObserver focusObserver = this.mFocusObserver;
        if (focusObserver != null) {
            focusObserver.setmCurPosition(i);
        }
    }

    public void onItemLongClick(View view, int i) {
        if (!this.mInEditMode) {
            String appPackageName = DragAppListInfo.mAppFragmentItemDetailsMap.get(DragAppListInfo.mAppsFragmentItemTagList.get(i)).getAppPackageName();
            if (appPackageName == null || !isSystemApplication(this.mContext, appPackageName)) {
                Intent intent = new Intent("android.intent.action.DELETE", Uri.parse("package:" + appPackageName));
                intent.setFlags(268435456);
                this.mContext.startActivity(intent);
                return;
            }
            Log.d(TAG, "系统app无法卸载");
            showTipString(this.mContext, this.mContext.getResources().getString(R.string.lb_uninstall_failed));
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.leftR) {
            LauncherModel.getInstance().showAppList(false);
            this.mInEditMode = false;
        }
    }

    public class AppListPageReceiver extends BroadcastReceiver {
        private boolean isReg = false;

        public AppListPageReceiver() {
        }

        public void registerRec() {
            if (!this.isReg) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("KESAIWEI_DISPLAY_MODE_CHANGED_ACTION");
                ApplistUIController.this.mContext.registerReceiver(this, intentFilter);
                this.isReg = true;
            }
        }

        public void unregisterRec() {
            if (this.isReg) {
                ApplistUIController.this.mContext.unregisterReceiver(this);
                this.isReg = false;
            }
        }

        public void onReceive(Context context, Intent intent) {
            if ("KESAIWEI_DISPLAY_MODE_CHANGED_ACTION".equals(intent.getAction())) {
                ApplistUIController applistUIController = ApplistUIController.this;
                int unused = applistUIController.mMode = SysProviderOpt.getInstance(applistUIController.mContext).getRecordInteger("KESAIWEI_SYS_DISPLAY_MODE", 0);
                if (ApplistUIController.this.BMWMRecyclerVierAdapter != null) {
                    ApplistUIController.this.BMWMRecyclerVierAdapter.setmKSWDisplayMode(ApplistUIController.this.mMode);
                    ApplistUIController.this.BMWMRecyclerVierAdapter.notifyDataSetChanged();
                }
                ApplistUIController.this.refreshBtnE();
            }
        }
    }

    private void initSortClassNames() {
        this.sortClassNames.add("com.android.settings.Settings");
        this.sortClassNames.add("com.android.vending.AssetBrowserActivity");
        this.sortClassNames.add("com.google.android.maps.MapsActivity");
        this.sortClassNames.add("com.google.android.apps.chrome.Main");
        this.sortClassNames.add("com.google.android.googlequicksearchbox.SearchActivity");
        this.sortClassNames.add("com.google.android.googlequicksearchbox.VoiceSearchActivity");
        this.sortClassNames.add("com.autonavi.auto.remote.fill.UsbFillActivity");
        this.sortClassNames.add(EventUtils.ESUPER_MODE_CLASS_NAME);
        this.sortClassNames.add(EventUtils.LETTER_CARPLAY_MODE_CLASS_NAME);
        this.sortClassNames.add("com.txznet.weather.MainActivity");
        this.sortClassNames.add(EventUtils.APK_INSTALL_MODE_CLASS_NAME);
        this.sortClassNames.add("com.szchoiceway.equalizer.MainActivity");
        this.sortClassNames.add("com.szchoiceway.settings.FeedbackActivity");
        this.sortClassNames.add("com.szchoiceway.settings.ManualActivity");
        this.sortClassNames.add("com.szchoiceway.ksw_aux.MainActivity");
        this.sortClassNames.add("com.szchoiceway.ksw_cmmb.MainActivity");
        this.sortClassNames.add("com.szchoiceway.ksw_dvd.MainActivity");
        this.sortClassNames.add("com.szchoiceway.ksw_dvr.MainActivity");
        this.sortClassNames.add("com.szchoiceway.ksw_fc.MainActivity");
        this.sortClassNames.add("com.ankai.cardvr.ui.GuideActivity");
        this.sortClassNames.add("com.mxtech.videoplayer.ad.ActivityWelcomeMX");
        this.sortClassNames.add("com.zoulou.dab.activity.MainActivity");
    }

    private void initAuxPackageNames() {
        this.auxPackageNames.add("com.szchoiceway.ksw_aux");
        this.auxPackageNames.add("com.szchoiceway.ksw_cmmb");
        this.auxPackageNames.add("com.szchoiceway.ksw_dvd");
        this.auxPackageNames.add("com.szchoiceway.ksw_dvr");
        this.auxPackageNames.add("com.szchoiceway.ksw_fc");
    }

    public static boolean isSystemApplication(Context context, String str) {
        try {
            if ((context.getPackageManager().getPackageInfo(str, 16384).applicationInfo.flags & 1) != 0) {
                return true;
            }
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void showTipString(Context context, String str) {
        Toast makeText = Toast.makeText(context, str, 0);
        this.mTip = makeText;
        makeText.setGravity(17, 0, 0);
        this.mTip.show();
    }
}
