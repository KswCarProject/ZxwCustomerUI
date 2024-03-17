package com.szchoiceway.customerui.dialog;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import com.google.android.material.badge.BadgeDrawable;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.adapter.AppListDialogAdapter;
import com.szchoiceway.customerui.utils.AppInfo;
import com.szchoiceway.customerui.utils.EventUtils;
import com.szchoiceway.customerui.utils.SysProviderOpt;
import java.util.ArrayList;
import java.util.List;

public class AppListDialog {
    private static String TAG = "AppListDialog";
    private AppListDialogAdapter adapter;
    private List<AppInfo> appInfoList;
    public boolean isShow = false;
    public int leftAppIndex = -1;
    private Context mContext;
    private GridView mGridView;
    private SysProviderOpt mProvider;
    private View mRootView;
    private WindowManager.LayoutParams params = new WindowManager.LayoutParams();
    private WindowManager wm;

    public AppListDialog(Context context) {
        this.mContext = context;
        this.wm = (WindowManager) context.getSystemService("window");
        this.mProvider = SysProviderOpt.getInstance(context);
        initData();
        initView();
    }

    public List<AppInfo> getAppInfoList() {
        return this.appInfoList;
    }

    private void initView() {
        this.mRootView = View.inflate(this.mContext, R.layout.pemp_id7_dialog_applist, (ViewGroup) null);
        this.params.x = (int) this.mContext.getResources().getDimension(R.dimen.pemp_id7_appDialog_x);
        this.params.y = (int) this.mContext.getResources().getDimension(R.dimen.pemp_id7_appDialog_y);
        this.params.width = (int) this.mContext.getResources().getDimension(R.dimen.pemp_id7_appDialog_width);
        this.params.height = (int) this.mContext.getResources().getDimension(R.dimen.pemp_id7_appDialog_height);
        this.params.windowAnimations = 16973910;
        this.params.type = 2038;
        this.params.format = 1;
        this.params.flags = 296;
        this.params.gravity = BadgeDrawable.TOP_START;
        Button button = (Button) this.mRootView.findViewById(R.id.btnHideApps);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    AppListDialog.this.lambda$initView$0$AppListDialog(view);
                }
            });
        }
    }

    public /* synthetic */ void lambda$initView$0$AppListDialog(View view) {
        hideDialog();
    }

    public void showDialog(int i) {
        initData();
        this.adapter = new AppListDialogAdapter(this.mContext, this.appInfoList);
        GridView gridView = (GridView) this.mRootView.findViewById(R.id.mGirdView);
        this.mGridView = gridView;
        gridView.setAdapter(this.adapter);
        this.mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                AppListDialog.this.lambda$showDialog$1$AppListDialog(adapterView, view, i, j);
            }
        });
        if (!this.isShow) {
            this.isShow = true;
            this.wm.addView(this.mRootView, this.params);
            this.leftAppIndex = i;
        }
    }

    public /* synthetic */ void lambda$showDialog$1$AppListDialog(AdapterView adapterView, View view, int i, long j) {
        int i2 = this.leftAppIndex;
        if (i2 == 0) {
            Intent intent = new Intent(EventUtils.ZXW_ACTION_PEMP_ID7_LEFT_APP_REFRESH);
            this.mProvider.updateRecord(EventUtils.LEFT_APP0_MODE_PACKAGE_NAME, this.appInfoList.get(i).getPackageName());
            this.mProvider.updateRecord(EventUtils.LEFT_APP0_MODE_CLASS_NAME, this.appInfoList.get(i).getClassName());
            intent.putExtra(EventUtils.ZXW_ACTION_PEMP_ID7_LEFT_APP_REFRESH_EXTRA, 0);
            this.mContext.sendBroadcast(intent);
        } else if (i2 == 1) {
            Intent intent2 = new Intent(EventUtils.ZXW_ACTION_PEMP_ID7_LEFT_APP_REFRESH);
            this.mProvider.updateRecord(EventUtils.LEFT_APP1_MODE_PACKAGE_NAME, this.appInfoList.get(i).getPackageName());
            this.mProvider.updateRecord(EventUtils.LEFT_APP1_MODE_CLASS_NAME, this.appInfoList.get(i).getClassName());
            intent2.putExtra(EventUtils.ZXW_ACTION_PEMP_ID7_LEFT_APP_REFRESH_EXTRA, 1);
            this.mContext.sendBroadcast(intent2);
        } else if (i2 == 2) {
            Intent intent3 = new Intent(EventUtils.ZXW_ACTION_PEMP_ID7_LEFT_APP_REFRESH);
            this.mProvider.updateRecord(EventUtils.LEFT_APP2_MODE_PACKAGE_NAME, this.appInfoList.get(i).getPackageName());
            this.mProvider.updateRecord(EventUtils.LEFT_APP2_MODE_CLASS_NAME, this.appInfoList.get(i).getClassName());
            intent3.putExtra(EventUtils.ZXW_ACTION_PEMP_ID7_LEFT_APP_REFRESH_EXTRA, 2);
            this.mContext.sendBroadcast(intent3);
        }
        this.adapter.notifyDataSetChanged();
    }

    public void hideDialog() {
        if (this.isShow) {
            this.isShow = false;
            this.wm.removeView(this.mRootView);
        }
    }

    public void initData() {
        this.appInfoList = new ArrayList();
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        PackageManager packageManager = this.mContext.getPackageManager();
        for (ResolveInfo next : packageManager.queryIntentActivities(intent, 0)) {
            String str = next.activityInfo.packageName;
            try {
                PackageInfo packageInfo = this.mContext.getPackageManager().getPackageInfo(str, 0);
                AppInfo appInfo = new AppInfo();
                if ((packageInfo.applicationInfo.flags & 1) <= 0) {
                    appInfo.setLabel(next.loadLabel(this.mContext.getPackageManager()).toString());
                    appInfo.setPackageName(next.activityInfo.packageName);
                    appInfo.setClassName(next.activityInfo.name);
                    appInfo.setIcon(packageManager.getApplicationIcon(next.activityInfo.packageName));
                    if (appInfo.getLabel() != null || appInfo.getClassName() != null || appInfo.getPackageName() != null) {
                        this.appInfoList.add(appInfo);
                    }
                } else if (str.equals("com.szchoiceway.musicplayer")) {
                    appInfo.setLabel(this.mContext.getResources().getString(R.string.lb_left_music));
                    appInfo.setPackageName(next.activityInfo.packageName);
                    appInfo.setClassName(next.activityInfo.name);
                    appInfo.setIcon(this.mContext.getResources().getDrawable(R.drawable.pemp_id7_icon_music));
                    this.appInfoList.add(appInfo);
                } else if (str.equals("com.szchoiceway.videoplayer")) {
                    appInfo.setLabel(this.mContext.getResources().getString(R.string.lb_left_video));
                    appInfo.setPackageName(next.activityInfo.packageName);
                    appInfo.setClassName(next.activityInfo.name);
                    appInfo.setIcon(this.mContext.getResources().getDrawable(R.drawable.pemp_id7_icon_video));
                    this.appInfoList.add(appInfo);
                } else if ("com.android.chrome".equals(next.activityInfo.packageName) && "com.google.android.apps.chrome.Main".equals(next.activityInfo.name)) {
                    appInfo.setLabel(this.mContext.getResources().getString(R.string.lb_left_bro));
                    appInfo.setPackageName(next.activityInfo.packageName);
                    appInfo.setClassName(next.activityInfo.name);
                    appInfo.setIcon(this.mContext.getResources().getDrawable(R.drawable.pemp_id7_icon_browser));
                    this.appInfoList.add(appInfo);
                } else if (whiteList(next)) {
                    appInfo.setLabel(next.loadLabel(this.mContext.getPackageManager()).toString());
                    appInfo.setPackageName(next.activityInfo.packageName);
                    appInfo.setClassName(next.activityInfo.name);
                    appInfo.setIcon(packageManager.getApplicationIcon(next.activityInfo.packageName));
                    if (appInfo.getLabel() != null || appInfo.getClassName() != null || appInfo.getPackageName() != null) {
                        this.appInfoList.add(appInfo);
                    }
                } else if (SysProviderOpt.getInstance(this.mContext).getRecordBoolean(SysProviderOpt.MAISILUO_SYS_GOOGLEPLAY, false) && isGoogleApp(str)) {
                    appInfo.setLabel(next.loadLabel(this.mContext.getPackageManager()).toString());
                    appInfo.setPackageName(next.activityInfo.packageName);
                    appInfo.setClassName(next.activityInfo.name);
                    appInfo.setIcon(packageManager.getApplicationIcon(next.activityInfo.packageName));
                    if (appInfo.getLabel() != null || appInfo.getClassName() != null || appInfo.getPackageName() != null) {
                        this.appInfoList.add(appInfo);
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isGoogleApp(String str) {
        return "com.google.android.gm".equals(str) || "com.android.vending".equals(str) || "com.google.android.apps.maps".equals(str) || "com.google.android.googlequicksearchbox".equals(str);
    }

    private boolean whiteList(ResolveInfo resolveInfo) {
        if (!EventUtils.LETTER_CARPLAY_MODE_PACKAGE_NAME.equals(resolveInfo.activityInfo.packageName)) {
            return "com.txznet.weather".equals(resolveInfo.activityInfo.packageName) && "com.txznet.weather.MainActivity".equals(resolveInfo.activityInfo.name);
        }
        return true;
    }
}
