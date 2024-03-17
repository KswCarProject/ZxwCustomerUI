package com.szchoiceway.customerui.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.drag.DragAppInfo;
import com.szchoiceway.customerui.drag.DragAppListInfo;
import com.szchoiceway.customerui.model.LauncherModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AppUtil {
    private static final String TAG = "AppUtil";
    public static List<String> auxPackageNames = new ArrayList();
    public static HashMap<String, DragAppInfo> mAllDragAppInfoMap;
    private Context mContext;
    int repeat = 0;
    private List<String> sortClassNames;

    public AppUtil(Context context) {
        this.mContext = context;
        initSortClassNames();
        initAuxClassNames();
    }

    public void initData() {
        Log.d(TAG, "initData");
        mAllDragAppInfoMap = new HashMap<>();
        List<ResolveInfo> filterApps = filterApps(loadApps());
        mAllDragAppInfoMap.clear();
        initPresetApp();
        for (ResolveInfo next : filterApps) {
            String charSequence = next.loadLabel(this.mContext.getPackageManager()).toString();
            String str = next.activityInfo.packageName;
            String str2 = next.activityInfo.name;
            if (EventUtils.ZLINK_MODE_PACKAGE_NAME.equals(str)) {
                if (!EventUtils.ZLINK_DLNA_MODE_CLASS_NAME.equals(str2)) {
                    Log.d(TAG, "className = " + str2);
                }
            }
            Drawable loadIcon = next.loadIcon(this.mContext.getPackageManager());
            long j = 0;
            try {
                j = this.mContext.getPackageManager().getPackageInfo(str, 0).firstInstallTime;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            DragAppInfo dragAppInfo = new DragAppInfo();
            dragAppInfo.setTag(str2);
            dragAppInfo.setAppName(charSequence);
            dragAppInfo.setAppPackageName(str);
            if (mAllDragAppInfoMap.containsKey(str2) && !str.equals(mAllDragAppInfoMap.get(str2).getAppPackageName())) {
                this.repeat++;
                str2 = str2 + "===repeat" + this.repeat;
            }
            dragAppInfo.setAppClassName(str2);
            dragAppInfo.setDrawable(loadIcon);
            dragAppInfo.setInstallTime(j);
            mAllDragAppInfoMap.put(str2, dragAppInfo);
        }
        synchronized (new Object()) {
            DragAppListInfo.initAppsDefault(this.mContext, mAllDragAppInfoMap, false, this.sortClassNames);
        }
    }

    private List<ResolveInfo> loadApps() {
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        List<ResolveInfo> queryIntentActivities = this.mContext.getPackageManager().queryIntentActivities(intent, 0);
        boolean z = LauncherModel.getInstance().mShowGoogle;
        for (int size = queryIntentActivities.size() - 1; size >= 0; size--) {
            ResolveInfo resolveInfo = queryIntentActivities.get(size);
            String str = resolveInfo.activityInfo.packageName;
            try {
                PackageInfo packageInfo = this.mContext.getPackageManager().getPackageInfo(str, 0);
                if (isGoogleApp(str)) {
                    if (!z) {
                        queryIntentActivities.remove(resolveInfo);
                    }
                } else if ((packageInfo.applicationInfo.flags & 1) > 0 && !str.startsWith("com.android.settings") && !str.startsWith("com.carletter") && !str.equals("com.android.chrome") && !str.equals("com.szchoiceway.ksw_aux") && !str.equals("com.szchoiceway.ksw_cmmb") && !str.equals("com.szchoiceway.ksw_dvd") && !str.equals("com.szchoiceway.ksw_dvr") && !str.equals("com.szchoiceway.ksw_fc") && !str.equals("com.szchoiceway.equalizer") && !str.equals(EventUtils.APK_INSTALL_MODE_PACKAGE_NAME) && !str.equals("com.szchoiceway.countrycode") && !str.equals("com.ecar.assistantnew") && !str.equals(EventUtils.ESUPER_MODE_PACKAGE_NAME) && !str.equals("com.txznet.weather") && !str.equals("com.fibocom.multicamera") && !str.equals(EventUtils.ZLINK_MODE_PACKAGE_NAME) && !str.startsWith("com.ivicar.avm") && !str.equals("com.chartcross.gpstest")) {
                    queryIntentActivities.remove(resolveInfo);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return queryIntentActivities;
    }

    private List<ResolveInfo> filterApps(List<ResolveInfo> list) {
        LauncherModel instance = LauncherModel.getInstance();
        boolean z = LauncherModel.getInstance().m_iModeSet == 17 || LauncherModel.getInstance().m_iModeSet == 20;
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo next : list) {
            if (!"acr.browser.barebones".equals(next.activityInfo.packageName) && !"android.rk.RockVideoPlayer".equals(next.activityInfo.packageName) && !"com.android.calendar".equals(next.activityInfo.packageName) && !"com.android.camera2".equals(next.activityInfo.packageName) && !"com.android.contacts".equals(next.activityInfo.packageName) && !"com.android.deskclock".equals(next.activityInfo.packageName) && !"com.android.email".equals(next.activityInfo.packageName) && !"com.android.music".equals(next.activityInfo.packageName) && !"com.android.soundrecorder".equals(next.activityInfo.packageName) && !"com.alensw.PicFolder".equals(next.activityInfo.packageName) && !"com.android.apkinstaller".equals(next.activityInfo.packageName) && !"com.android.calculator2".equals(next.activityInfo.packageName) && !"com.android.quicksearchbox".equals(next.activityInfo.packageName) && !"com.szchoiceway.ksw_old_bmw_x1_original".equals(next.activityInfo.packageName) && !"com.szchoiceway.btsuite.BTMusicActivity".equals(next.activityInfo.name)) {
                if ((!"com.szchoiceway.ksw_aux".equals(next.activityInfo.packageName) || instance.mShowAUX) && ((!"com.szchoiceway.ksw_cmmb".equals(next.activityInfo.packageName) || instance.mShowTV) && !"com.szchoiceway.ksw_dvd".equals(next.activityInfo.packageName))) {
                    if ((!"com.szchoiceway.ksw_dvr".equals(next.activityInfo.packageName) || instance.mShowDVR) && ((!"com.szchoiceway.ksw_fc".equals(next.activityInfo.packageName) || instance.mShowFC) && ((!"com.ms.ms2160".equals(next.activityInfo.packageName) || instance.mShowMS1920) && ((!"com.ecar.assistantnew".equals(next.activityInfo.packageName) || instance.mShowECAR) && ((!EventUtils.ESUPER_MODE_PACKAGE_NAME.equals(next.activityInfo.packageName) || instance.mShowES) && ((!"com.szchoiceway.equalizer".equals(next.activityInfo.packageName) || instance.mShowEQ) && ((!"com.ivicar.avm".equals(next.activityInfo.packageName) || instance.mShow360) && ((!"com.txznet.weather".equals(next.activityInfo.packageName) || instance.mShowWeather || !z) && (!"com.fibocom.multicamera".equals(next.activityInfo.packageName) || instance.mShow360))))))))) {
                        arrayList.add(next);
                    }
                }
            }
        }
        return arrayList;
    }

    private void initSortClassNames() {
        ArrayList arrayList = new ArrayList();
        this.sortClassNames = arrayList;
        arrayList.add("com.android.settings.Settings");
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
        this.sortClassNames.add("com.szchoiceway.settings.CareSetActivity");
        this.sortClassNames.add("com.szchoiceway.ksw_aux.MainActivity");
        this.sortClassNames.add("com.szchoiceway.ksw_cmmb.MainActivity");
        this.sortClassNames.add("com.szchoiceway.ksw_dvd.MainActivity");
        this.sortClassNames.add("com.szchoiceway.ksw_dvr.MainActivity");
        this.sortClassNames.add("com.szchoiceway.ksw_fc.MainActivity");
        this.sortClassNames.add("com.ankai.cardvr.ui.GuideActivity");
        this.sortClassNames.add("com.mxtech.videoplayer.ad.ActivityWelcomeMX");
        this.sortClassNames.add("com.zoulou.dab.activity.MainActivity");
    }

    private void initAuxClassNames() {
        auxPackageNames.add("com.szchoiceway.ksw_aux");
        auxPackageNames.add("com.szchoiceway.ksw_cmmb");
        auxPackageNames.add("com.szchoiceway.ksw_dvd");
        auxPackageNames.add("com.szchoiceway.ksw_dvr");
        auxPackageNames.add("com.szchoiceway.ksw_fc");
    }

    public boolean isGoogleApp(String str) {
        return "com.google.android.gm".equals(str) || "com.android.vending".equals(str) || "com.google.android.apps.maps".equals(str) || "com.google.android.googlequicksearchbox".equals(str);
    }

    private void initPresetApp() {
        DragAppInfo dragAppInfo = new DragAppInfo();
        dragAppInfo.setTag("com.szchoiceway.settings.FeedbackActivity");
        dragAppInfo.setAppName(this.mContext.getResources().getString(R.string.lb_feedback));
        dragAppInfo.setAppPackageName("com.szchoiceway.settings");
        dragAppInfo.setAppClassName("com.szchoiceway.settings.FeedbackActivity");
        dragAppInfo.setDrawable(this.mContext.getDrawable(R.drawable.app_icon_feedback));
        dragAppInfo.setDrawableId(R.drawable.app_icon_feedback);
        mAllDragAppInfoMap.put("com.szchoiceway.settings.FeedbackActivity", dragAppInfo);
        if (LauncherModel.getInstance().mShowManual) {
            DragAppInfo dragAppInfo2 = new DragAppInfo();
            dragAppInfo2.setTag("com.szchoiceway.settings.ManualActivity");
            dragAppInfo2.setAppName(this.mContext.getResources().getString(R.string.lb_manual));
            dragAppInfo2.setAppPackageName("com.szchoiceway.settings");
            dragAppInfo2.setAppClassName("com.szchoiceway.settings.ManualActivity");
            dragAppInfo2.setDrawable(this.mContext.getDrawable(R.drawable.app_icon_manual));
            dragAppInfo2.setDrawableId(R.drawable.app_icon_manual);
            mAllDragAppInfoMap.put(dragAppInfo2.getAppClassName(), dragAppInfo2);
        }
        boolean z = LauncherModel.getInstance().mShowGPS;
        Log.d(TAG, "showGps = " + z);
        if (z) {
            DragAppInfo dragAppInfo3 = new DragAppInfo();
            dragAppInfo3.setTag("com.szchoiceway.settings.GPSActivity");
            dragAppInfo3.setAppName("GPS Test");
            dragAppInfo3.setAppPackageName("com.szchoiceway.settings");
            dragAppInfo3.setAppClassName("com.szchoiceway.settings.GPSActivity");
            dragAppInfo3.setDrawable(this.mContext.getDrawable(R.mipmap.ic_launcher));
            dragAppInfo3.setDrawableId(R.mipmap.ic_launcher);
            mAllDragAppInfoMap.put("com.szchoiceway.settings.GPSActivity", dragAppInfo3);
        }
        Log.d(TAG, "showTxzCare = " + LauncherModel.getInstance().mShowTXZCare);
        if (LauncherModel.getInstance().mShowTXZCare) {
            DragAppInfo dragAppInfo4 = new DragAppInfo();
            dragAppInfo4.setTag("com.szchoiceway.settings.CareSetActivity");
            dragAppInfo4.setAppName(this.mContext.getResources().getString(R.string.lb_care_activity));
            dragAppInfo4.setAppPackageName("com.szchoiceway.settings");
            dragAppInfo4.setAppClassName("com.szchoiceway.settings.CareSetActivity");
            dragAppInfo4.setDrawable(this.mContext.getDrawable(R.drawable.care_set_icon));
            dragAppInfo4.setDrawableId(R.drawable.care_set_icon);
            mAllDragAppInfoMap.put("com.szchoiceway.settings.CareSetActivity", dragAppInfo4);
        }
    }

    private String getActivityName(String str) {
        ResolveInfo resolveActivity;
        ActivityInfo activityInfo;
        PackageManager packageManager = this.mContext.getPackageManager();
        Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(str);
        if (launchIntentForPackage == null || (resolveActivity = packageManager.resolveActivity(launchIntentForPackage, 0)) == null || (activityInfo = resolveActivity.activityInfo) == null) {
            return null;
        }
        return activityInfo.name;
    }
}
