package com.szchoiceway.customerui.model;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.drag.DragAppInfo;
import com.szchoiceway.customerui.utils.EventUtils;
import com.szchoiceway.customerui.utils.SysProviderOpt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScannerAppsTask extends AsyncTask<Void, Void, HashMap<String, DragAppInfo>> {
    private static final String TAG = "ScannerAppsTask";
    private ScannerAppsCallback callback;
    private HashMap<String, DragAppInfo> mAllDragAppInfoMap = new HashMap<>();
    private Context mContext;
    int repeat = 0;

    public interface ScannerAppsCallback {
        void ScannedApps(HashMap<String, DragAppInfo> hashMap);
    }

    public ScannerAppsTask(Context context, ScannerAppsCallback scannerAppsCallback) {
        this.mContext = context;
        this.callback = scannerAppsCallback;
    }

    /* access modifiers changed from: protected */
    public HashMap<String, DragAppInfo> doInBackground(Void... voidArr) {
        Log.d(TAG, "doInBackground");
        List<ResolveInfo> filterApps = filterApps(loadApps());
        this.mAllDragAppInfoMap.clear();
        initPresetApp();
        for (ResolveInfo next : filterApps) {
            String charSequence = next.loadLabel(this.mContext.getPackageManager()).toString();
            String str = next.activityInfo.packageName;
            String str2 = next.activityInfo.name;
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
            if (this.mAllDragAppInfoMap.containsKey(str2) && !str.equals(this.mAllDragAppInfoMap.get(str2).getAppPackageName())) {
                this.repeat++;
                str2 = str2 + "===repeat" + this.repeat;
            }
            dragAppInfo.setAppClassName(str2);
            dragAppInfo.setDrawable(loadIcon);
            dragAppInfo.setInstallTime(j);
            this.mAllDragAppInfoMap.put(str2, dragAppInfo);
        }
        return this.mAllDragAppInfoMap;
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(HashMap<String, DragAppInfo> hashMap) {
        super.onPostExecute(hashMap);
        Log.d(TAG, "onPostExecute callback = " + this.callback);
        ScannerAppsCallback scannerAppsCallback = this.callback;
        if (scannerAppsCallback != null) {
            scannerAppsCallback.ScannedApps(hashMap);
        }
    }

    private List<ResolveInfo> loadApps() {
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        List<ResolveInfo> queryIntentActivities = this.mContext.getPackageManager().queryIntentActivities(intent, 0);
        boolean recordBoolean = SysProviderOpt.getInstance(this.mContext).getRecordBoolean(SysProviderOpt.MAISILUO_SYS_GOOGLEPLAY, false);
        for (int size = queryIntentActivities.size() - 1; size >= 0; size--) {
            ResolveInfo resolveInfo = queryIntentActivities.get(size);
            String str = resolveInfo.activityInfo.packageName;
            try {
                PackageInfo packageInfo = this.mContext.getPackageManager().getPackageInfo(str, 0);
                if (isGoogleApp(str)) {
                    if (!recordBoolean) {
                        queryIntentActivities.remove(resolveInfo);
                    }
                } else if ((packageInfo.applicationInfo.flags & 1) > 0 && !str.startsWith("com.android.settings") && !str.startsWith("com.carletter") && !str.equals("com.android.chrome") && !str.equals("com.szchoiceway.ksw_aux") && !str.equals("com.szchoiceway.ksw_cmmb") && !str.equals("com.szchoiceway.ksw_dvd") && !str.equals("com.szchoiceway.ksw_dvr") && !str.equals("com.szchoiceway.ksw_fc") && !str.equals("com.szchoiceway.equalizer") && !str.equals(EventUtils.APK_INSTALL_MODE_PACKAGE_NAME) && !str.equals("com.ecar.assistantnew") && !str.equals(EventUtils.ESUPER_MODE_PACKAGE_NAME) && !str.equals(EventUtils.ANXIAO_MODE_PACKAGE_NAME) && !str.equals("com.megaview.avm") && !str.equals("com.txznet.weather") && !str.equals("com.fibocom.multicamera") && !str.equals(EventUtils.ZLINK_MODE_PACKAGE_NAME) && !str.startsWith(EventUtils.HICAR_MODE_PACKAGE_NAME) && !str.startsWith(EventUtils.CARLINK_MODE_PACKAGE_NAME) && !str.startsWith("com.szchoiceway.aiscamera") && !str.equals("com.chartcross.gpstest")) {
                    queryIntentActivities.remove(resolveInfo);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Log.i(TAG, "loadApps: " + queryIntentActivities.size());
        return queryIntentActivities;
    }

    private List<ResolveInfo> filterApps(List<ResolveInfo> list) {
        SysProviderOpt instance = SysProviderOpt.getInstance(this.mContext);
        boolean z = LauncherModel.getInstance().m_iModeSet == 17 || LauncherModel.getInstance().m_iModeSet == 20;
        boolean recordBoolean = instance.getRecordBoolean(SysProviderOpt.KSW_HAVE_AUX, false);
        boolean recordBoolean2 = instance.getRecordBoolean(SysProviderOpt.KSW_HAVE_TV, false);
        boolean recordBoolean3 = instance.getRecordBoolean(SysProviderOpt.KSW_HAVE_FRONT_CAMERA, true);
        int recordInteger = instance.getRecordInteger(SysProviderOpt.KESAIWEI_RECORD_DVR, 0);
        instance.getRecordInteger(SysProviderOpt.KSW_HAVE_DVD, 0);
        instance.getRecordBoolean(SysProviderOpt.KSW_HAVE_HICAR, false);
        boolean recordBoolean4 = instance.getRecordBoolean(SysProviderOpt.KSW_SCREEN_CAST_MS9120, false);
        boolean recordBoolean5 = instance.getRecordBoolean(SysProviderOpt.KSW_HAVE_ECAR, true);
        boolean recordBoolean6 = instance.getRecordBoolean(SysProviderOpt.KSW_HAVE_ESEXPLORER, true);
        boolean recordBoolean7 = instance.getRecordBoolean(SysProviderOpt.KSW_HAVE_EQ, true);
        boolean recordBoolean8 = instance.getRecordBoolean(SysProviderOpt.KSW_HAVE_WEATHER, true);
        int recordInteger2 = instance.getRecordInteger("KESAIWEI_SYS_CAMERA_SELECTION", 1);
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo next : list) {
            if (!"acr.browser.barebones".equals(next.activityInfo.packageName) && !"android.rk.RockVideoPlayer".equals(next.activityInfo.packageName) && !"com.android.calendar".equals(next.activityInfo.packageName) && !"com.android.camera2".equals(next.activityInfo.packageName) && !"com.android.contacts".equals(next.activityInfo.packageName) && !"com.android.deskclock".equals(next.activityInfo.packageName) && !"com.android.email".equals(next.activityInfo.packageName) && !"com.android.music".equals(next.activityInfo.packageName) && !"com.android.soundrecorder".equals(next.activityInfo.packageName) && !"com.alensw.PicFolder".equals(next.activityInfo.packageName) && !"com.android.apkinstaller".equals(next.activityInfo.packageName) && !"com.android.calculator2".equals(next.activityInfo.packageName) && !"com.android.quicksearchbox".equals(next.activityInfo.packageName) && !"com.szchoiceway.ksw_old_bmw_x1_original".equals(next.activityInfo.packageName) && !"com.szchoiceway.btsuite.BTMusicActivity".equals(next.activityInfo.name) && ((!"com.szchoiceway.ksw_aux".equals(next.activityInfo.packageName) || recordBoolean) && ((!"com.szchoiceway.ksw_cmmb".equals(next.activityInfo.packageName) || recordBoolean2) && !"com.szchoiceway.ksw_dvd".equals(next.activityInfo.packageName)))) {
                if ("com.szchoiceway.ksw_dvr".equals(next.activityInfo.packageName)) {
                    if (recordInteger != 1) {
                    }
                }
                if ((!"com.szchoiceway.ksw_fc".equals(next.activityInfo.packageName) || recordBoolean3) && ((!"com.ms.ms2160".equals(next.activityInfo.packageName) || recordBoolean4) && ((!"com.ecar.assistantnew".equals(next.activityInfo.packageName) || recordBoolean5) && ((!EventUtils.ESUPER_MODE_PACKAGE_NAME.equals(next.activityInfo.packageName) || recordBoolean6) && ((!"com.szchoiceway.equalizer".equals(next.activityInfo.packageName) || recordBoolean7) && ((!EventUtils.ANXIAO_MODE_PACKAGE_NAME.equals(next.activityInfo.packageName) || recordInteger2 == 3) && ((!"com.txznet.weather".equals(next.activityInfo.packageName) || recordBoolean8 || !z) && (!"com.fibocom.multicamera".equals(next.activityInfo.packageName) || (recordInteger2 == 3 && Build.VERSION.SDK_INT != 33))))))))) {
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
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
        this.mAllDragAppInfoMap.put("com.szchoiceway.settings.FeedbackActivity", dragAppInfo);
        if (SysProviderOpt.getInstance(this.mContext).getRecordBoolean(SysProviderOpt.KSW_HAVE_MANUAL, true)) {
            DragAppInfo dragAppInfo2 = new DragAppInfo();
            dragAppInfo2.setTag("com.szchoiceway.settings.ManualActivity");
            dragAppInfo2.setAppName(this.mContext.getResources().getString(R.string.lb_manual));
            dragAppInfo2.setAppPackageName("com.szchoiceway.settings");
            dragAppInfo2.setAppClassName("com.szchoiceway.settings.ManualActivity");
            dragAppInfo2.setDrawable(this.mContext.getDrawable(R.drawable.app_icon_manual));
            dragAppInfo2.setDrawableId(R.drawable.app_icon_manual);
            this.mAllDragAppInfoMap.put(dragAppInfo2.getAppClassName(), dragAppInfo2);
        }
        boolean recordBoolean = SysProviderOpt.getInstance(this.mContext).getRecordBoolean(SysProviderOpt.KSW_HAVE_GPSAPP, false);
        Log.d(TAG, "showGps = " + recordBoolean);
        if (recordBoolean) {
            DragAppInfo dragAppInfo3 = new DragAppInfo();
            dragAppInfo3.setTag("com.szchoiceway.settings.GPSActivity");
            dragAppInfo3.setAppName("GPS Test");
            dragAppInfo3.setAppPackageName("com.szchoiceway.settings");
            dragAppInfo3.setAppClassName("com.szchoiceway.settings.GPSActivity");
            dragAppInfo3.setDrawable(this.mContext.getDrawable(R.mipmap.ic_launcher));
            dragAppInfo3.setDrawableId(R.mipmap.ic_launcher);
            this.mAllDragAppInfoMap.put("com.szchoiceway.settings.GPSActivity", dragAppInfo3);
        }
        boolean installStatus = EventUtils.getInstallStatus(this.mContext, "com.txznet.smartadapter");
        Log.d(TAG, "showTxzCare = " + installStatus);
        if (installStatus) {
            DragAppInfo dragAppInfo4 = new DragAppInfo();
            dragAppInfo.setTag("com.szchoiceway.settings.CareSetActivity");
            dragAppInfo.setAppName(this.mContext.getResources().getString(R.string.lb_feedback));
            dragAppInfo.setAppPackageName("com.szchoiceway.settings");
            dragAppInfo.setAppClassName("com.szchoiceway.settings.CareSetActivity");
            dragAppInfo.setDrawable(this.mContext.getDrawable(R.drawable.care_set_icon));
            dragAppInfo.setDrawableId(R.drawable.care_set_icon);
            this.mAllDragAppInfoMap.put("com.szchoiceway.settings.CareSetActivity", dragAppInfo4);
        }
    }
}
