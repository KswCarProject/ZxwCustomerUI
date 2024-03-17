package com.szchoiceway.customerui.base;

import android.app.UiModeManager;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Toast;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.model.LauncherModel;
import com.szchoiceway.customerui.utils.EventUtils;
import com.szchoiceway.customerui.utils.SysProviderOpt;
import com.szchoiceway.eventcenter.IEventService;
import com.szchoiceway.widget.WeatherWidget;
import java.io.File;
import java.util.ArrayList;

public abstract class BaseLauncherView extends BaseView {
    private static final String LAUNCHER_ON_BACK_PRESSED = "com.szchoiceway.action.LAUNCHER_ON_BACK_PRESSED";
    private static final int REFRESH_MUSIC_COVER = 0;
    private static final int REFRESH_MUSIC_INFO = 2;
    private static final int REFRESH_PAGING = 1;
    public static final String TAG = "BaseLauncherView";
    /* access modifiers changed from: private */
    public static String zxw_show_gps_filename = "zxw_show_gps.txt";
    protected final String SYSTEM_DIALOG_REASON_BACK_KEY = "backkey";
    protected final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";
    protected final String SYSTEM_DIALOG_REASON_KEY = "reason";
    protected final String SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps";
    /* access modifiers changed from: private */
    public Bitmap bitmap;
    private boolean firstResume = true;
    protected boolean isInMultiWindowMode = false;
    private LauncherModel launcherModel;
    Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 0) {
                removeMessages(0);
                BaseLauncherView baseLauncherView = BaseLauncherView.this;
                Bitmap unused = baseLauncherView.bitmap = EventUtils.loadingCover(baseLauncherView.playPath);
                BaseLauncherView baseLauncherView2 = BaseLauncherView.this;
                baseLauncherView2.setMusicCoverBg(baseLauncherView2.bitmap);
            } else if (i == 1) {
                BaseLauncherView.this.refreshPaging();
            } else if (i == 2) {
                BaseLauncherView.this.refreshPlayState();
                removeMessages(2);
            }
        }
    };
    protected boolean mLauncherResume = true;
    private MReceiver mReceiver;
    /* access modifiers changed from: private */
    public String playPath = "";

    /* access modifiers changed from: protected */
    public abstract void onDestroy();

    /* access modifiers changed from: protected */
    public abstract void onPause();

    /* access modifiers changed from: protected */
    public void onReasonKey(String str) {
    }

    /* access modifiers changed from: protected */
    public abstract void onResume();

    /* access modifiers changed from: protected */
    public void packageChange() {
    }

    /* access modifiers changed from: protected */
    public abstract void refreshAppList();

    /* access modifiers changed from: protected */
    public abstract void refreshBTConnectName(String str);

    /* access modifiers changed from: protected */
    public abstract void refreshBTStatus();

    /* access modifiers changed from: protected */
    public abstract void refreshBenzControl(ArrayList<Integer> arrayList);

    /* access modifiers changed from: protected */
    public abstract void refreshDateTime();

    /* access modifiers changed from: protected */
    public abstract void refreshLeftApp(int i);

    /* access modifiers changed from: protected */
    public abstract void refreshLocalChanged();

    /* access modifiers changed from: protected */
    public abstract void refreshNaviInfo(int i, int i2, Bundle bundle);

    /* access modifiers changed from: protected */
    public abstract void refreshPaging();

    /* access modifiers changed from: protected */
    public abstract void refreshPlayState();

    /* access modifiers changed from: protected */
    public abstract void refreshSignalView(int i, String str);

    /* access modifiers changed from: protected */
    public abstract void refreshStorageView();

    /* access modifiers changed from: protected */
    public abstract void refreshWifiView(int i);

    /* access modifiers changed from: protected */
    public void setBTMusicCoverBg(Bitmap bitmap2) {
    }

    /* access modifiers changed from: protected */
    public abstract void setBtStatus(int i);

    /* access modifiers changed from: protected */
    public abstract void setCurPlayVideoPath(String str);

    /* access modifiers changed from: protected */
    public abstract void setDrivingRangeValue(String str);

    /* access modifiers changed from: protected */
    public abstract void setFocusMove(int i);

    /* access modifiers changed from: protected */
    public abstract void setHandbrakeStatus(boolean z);

    /* access modifiers changed from: protected */
    public abstract void setMusicCoverBg(Bitmap bitmap2);

    /* access modifiers changed from: protected */
    public abstract void setOilConsumption(String str);

    /* access modifiers changed from: protected */
    public abstract void setQilValue(String str);

    /* access modifiers changed from: protected */
    public abstract void setRotatingSpeed(int i);

    /* access modifiers changed from: protected */
    public abstract void setSeatBeltStatus(boolean z);

    /* access modifiers changed from: protected */
    public abstract void setSpeed(int i);

    /* access modifiers changed from: protected */
    public abstract void setSpeedUnit();

    /* access modifiers changed from: protected */
    public abstract void setTempValue(String str);

    /* access modifiers changed from: protected */
    public abstract void showAppList(boolean z);

    public BaseLauncherView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void init() {
        String topActivity = com.szchoiceway.zxwlib.utils.EventUtils.getTopActivity(this.mContext);
        Log.d(TAG, "init topActivity = " + topActivity);
        if (!topActivity.startsWith("com.android.launcher3")) {
            this.mLauncherResume = false;
            SysProviderOpt.getInstance(this.mContext).updateRecord("ZXW_LAUNCHER_IS_RESUME", "0");
        }
        this.launcherModel = LauncherModel.getInstance();
        registerReceiver();
        super.init();
    }

    /* access modifiers changed from: protected */
    public void unInit() {
        super.unInit();
        unregisterReceiver();
    }

    /* access modifiers changed from: private */
    public void launcherStatusChange(int i) {
        boolean z = true;
        if (i != 1) {
            z = false;
        }
        this.mLauncherResume = z;
        if (this.firstResume) {
            String topActivity = com.szchoiceway.zxwlib.utils.EventUtils.getTopActivity(this.mContext);
            Log.d(TAG, "firstResume topActivity = " + topActivity);
            if (this.mLauncherResume && !topActivity.startsWith("com.android.launcher3")) {
                this.mLauncherResume = false;
            }
        }
        SysProviderOpt.getInstance(this.mContext).updateRecord("ZXW_LAUNCHER_IS_RESUME", this.mLauncherResume ? "1" : "0");
        if (this.mLauncherResume) {
            Log.d(TAG, "onResume");
            onResume();
        } else {
            onPause();
            Log.d(TAG, "onPause");
        }
        this.firstResume = false;
    }

    private void registerReceiver() {
        if (this.mReceiver == null) {
            this.mReceiver = new MReceiver();
        }
        Log.i(TAG, "registerReceiver: ");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme("package");
        getContext().registerReceiver(this.mReceiver, intentFilter);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
        intentFilter2.addAction("com.szchoiceway.action.LAUNCHER_STATUS");
        intentFilter2.addAction(WeatherWidget.ACTION_WEATHER_UPDATE);
        getContext().registerReceiver(this.mReceiver, intentFilter2);
        IntentFilter intentFilter3 = new IntentFilter();
        intentFilter3.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
        intentFilter3.addAction("android.intent.action.TIME_TICK");
        intentFilter3.addAction("android.intent.action.TIME_SET");
        intentFilter3.addAction("EventUtils.ZXW_ACTION_UPDATE_GPS_TIME");
        intentFilter3.addAction("com.choiceway.eventcenter.EventUtils.ZXW_ORIGINAL_MCU_KEY_FOCUS_MOVE_EVT");
        intentFilter3.addAction("com.szchoiceway.eventcenter.EventUtils.ZXW_SENDBROADCAST8902MOD");
        intentFilter3.addAction(EventUtils.VALID_MODE_INFOR_CHANGE);
        intentFilter3.addAction(EventUtils.ZXW_ACTION_NOTIIFY_MEDIA_TYPE);
        intentFilter3.addAction("com.szchoiceway.eventcenter.EventUtils.ZXW_ACTION_NOTIIFY_MEDIA_PLAY_PATH");
        intentFilter3.addAction("com.szchoiceway.eventcenter.EventUtils.ZXW_ACTION_NOTIIFY_VIDEO_PLAY_PATH");
        intentFilter3.addAction(EventUtils.HBCP_EVT_HSHF_STATUS);
        intentFilter3.addAction("AUTONAVI_STANDARD_BROADCAST_SEND");
        intentFilter3.addAction(EventUtils.ZXW_CAN_START_UP_APPS);
        intentFilter3.addAction("com.choiceway.musicplayer.bitmap");
        intentFilter3.addAction(EventUtils.MZD_4_ICON);
        intentFilter3.addAction(EventUtils.MZD_Launcher_Zhuansu);
        intentFilter3.addAction(EventUtils.MZD_Launcher_Chesu);
        intentFilter3.addAction(EventUtils.MCU_3DH_INFO);
        intentFilter3.addAction(EventUtils.ZXW_ACTION_BT_CALL_TIME);
        intentFilter3.addAction(EventUtils.HBCP_EVT_CONTACT_NUM);
        intentFilter3.addAction("ZXW.USB_INSTALL_ACTION");
        intentFilter3.addAction("com.szchoiceway.carplay.ACTION_NOTIFY");
        intentFilter3.addAction("android.intent.action.LOCALE_CHANGED");
        intentFilter3.addAction(EventUtils.SET_MODE_SET);
        intentFilter3.addAction(EventUtils.SET_CLIENT_SET);
        intentFilter3.addAction(EventUtils.SET_RESOLUTION_SET);
        intentFilter3.addAction("com.choiceway.eventcenter.EventUtils.KSW_ZXW_BT_CONNECED_SHOW_VIEW");
        intentFilter3.addAction(EventUtils.ZXW_ACTION_SYS_UPDATE_WEATHER);
        intentFilter3.addAction("ZXW_ACTION_SHOW_APPLIST");
        intentFilter3.addAction(EventUtils.ZXW_ACTION_UPDATE_BENZ_CONTROL_DATA_SEND);
        intentFilter3.addAction(EventUtils.ZXW_ACTION_PEMP_ID7_LEFT_APP_REFRESH);
        intentFilter3.addAction("com.szchoiceway.action.mode_change");
        intentFilter3.addAction(EventUtils.ZXW_ACTION_REFRESH_AFTER_LOCALE_CHANGED);
        intentFilter3.addAction(EventUtils.HBCP_EVT_CUR_CONNECTED_DEVICE_NAME);
        intentFilter3.addAction(EventUtils.SET_DAY_NIGHT_MODE);
        intentFilter3.addAction(LAUNCHER_ON_BACK_PRESSED);
        intentFilter3.addAction(EventUtils.ZXW_ACTION_REQUEST_MUSIC_COVER);
        intentFilter3.addAction(EventUtils.ZXW_ACTION_MAIN_TOP_REFRESH_VIEW);
        getContext().registerReceiver(this.mReceiver, intentFilter3);
        IntentFilter intentFilter4 = new IntentFilter();
        intentFilter4.addAction("android.intent.action.MEDIA_MOUNTED");
        intentFilter4.addAction("android.intent.action.MEDIA_UNMOUNTED");
        intentFilter4.addDataScheme("file");
        getContext().registerReceiver(this.mReceiver, intentFilter4);
    }

    private void unregisterReceiver() {
        if (this.mReceiver != null) {
            Log.i(TAG, "unregisterReceiver: ");
            getContext().unregisterReceiver(this.mReceiver);
            this.mReceiver = null;
        }
    }

    class MReceiver extends BroadcastReceiver {
        final String SYSTEM_DIALOG_REASON_KEY = "reason";

        MReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            String str;
            int intExtra;
            String action = intent.getAction();
            if ("android.intent.action.PACKAGE_ADDED".equals(action) || "android.intent.action.PACKAGE_REMOVED".equals(action)) {
                Log.i(BaseLauncherView.TAG, "onReceive: app list change " + intent.getDataString());
                BaseLauncherView.this.packageChange();
            } else if ("android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(action)) {
                BaseLauncherView.this.onReasonKey(intent.getStringExtra("reason"));
            } else {
                boolean z = true;
                if ("com.szchoiceway.action.LAUNCHER_STATUS".equals(action)) {
                    int intExtra2 = intent.getIntExtra("state", 1);
                    Log.d(BaseLauncherView.TAG, "change state: " + intExtra2);
                    BaseLauncherView.this.launcherStatusChange(intExtra2);
                } else if ("com.szchoiceway.action.LAUNCHER_STATUS_DESTROY".equals(action)) {
                    BaseLauncherView.this.onDestroy();
                } else if ("com.choiceway.eventcenter.EventUtils.ZXW_ORIGINAL_MCU_KEY_FOCUS_MOVE_EVT".equals(action)) {
                    if (BaseLauncherView.this.mLauncherResume && (intExtra = intent.getIntExtra("com.choiceway.eventcenter.EventUtils.ZXW_ORIGINAL_MCU_KEY_FOCUS_MOVE_DATA", -1)) != 6 && intExtra != -1) {
                        BaseLauncherView.this.setFocusMove(intExtra);
                    }
                } else if ("com.szchoiceway.eventcenter.EventUtils.ZXW_SENDBROADCAST8902MOD".equals(action)) {
                    int intExtra3 = intent.getIntExtra("com.szchoiceway.eventcenter.EventUtils.ZXW_SENDBROADCAST8902MOD_EXTRA", -1);
                    if (intExtra3 == 16) {
                        boolean booleanExtra = intent.getBooleanExtra(EventUtils.ZXW_SENDBROADCAST8902MOD_shousha, false);
                        boolean booleanExtra2 = intent.getBooleanExtra(EventUtils.ZXW_SENDBROADCAST8902MOD_anquandai, false);
                        BaseLauncherView.this.setHandbrakeStatus(booleanExtra);
                        BaseLauncherView.this.setSeatBeltStatus(booleanExtra2);
                    } else if (intExtra3 == 25) {
                        int intExtra4 = intent.getIntExtra(EventUtils.ZXW_SENDBROADCAST8902MOD_fadongjizhuansu, 0);
                        int intExtra5 = intent.getIntExtra(EventUtils.ZXW_SENDBROADCAST8902MOD_ShunShiSuDu, 0);
                        intent.getBooleanExtra(EventUtils.ZXW_SENDBROADCAST8902MOD_SpeedUnit, false);
                        String stringExtra = intent.getStringExtra(EventUtils.ZXW_SENDBROADCAST8902MOD_huanjinwendu);
                        String stringExtra2 = intent.getStringExtra(EventUtils.ZXW_SENDBROADCAST8902MOD_xushilicheng);
                        String stringExtra3 = intent.getStringExtra(EventUtils.ZXW_SENDBROADCAST8902MOD_youLiang);
                        String stringExtra4 = intent.getStringExtra(EventUtils.ZXW_SENDBROADCAST8902MOD_youhao);
                        BaseLauncherView.this.setRotatingSpeed(intExtra4);
                        BaseLauncherView.this.setSpeedUnit();
                        BaseLauncherView.this.setSpeed(intExtra5);
                        BaseLauncherView.this.setTempValue(stringExtra);
                        BaseLauncherView.this.setQilValue(stringExtra3);
                        BaseLauncherView.this.setDrivingRangeValue(stringExtra2);
                        BaseLauncherView.this.setOilConsumption(stringExtra4);
                    }
                } else if (EventUtils.VALID_MODE_INFOR_CHANGE.equals(action)) {
                    try {
                        IEventService evtService = LauncherModel.getInstance().getEvtService();
                        if (evtService != null) {
                            str = evtService.getValidModeTitleInfor();
                        } else {
                            str = "";
                        }
                        if ("".equals(str)) {
                            BaseLauncherView.this.mHandler.sendEmptyMessageDelayed(2, 500);
                        } else {
                            BaseLauncherView.this.mHandler.sendEmptyMessage(2);
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                } else {
                    Bitmap bitmap = null;
                    if ("com.choiceway.musicplayer.bitmap".equals(action)) {
                        byte[] byteArrayExtra = intent.getByteArrayExtra("bitmap");
                        if (byteArrayExtra.length != 0) {
                            bitmap = BitmapFactory.decodeByteArray(byteArrayExtra, 0, byteArrayExtra.length);
                        }
                        Log.i(BaseLauncherView.TAG, "onReceive: com.choiceway.musicplayer.bitmap = " + bitmap);
                        IEventService evtService2 = LauncherModel.getInstance().getEvtService();
                        if (evtService2 != null) {
                            try {
                                if (evtService2.getValidMode() == EventUtils.eSrcMode.SRC_MUSIC.getIntValue()) {
                                    BaseLauncherView.this.setMusicCoverBg(bitmap);
                                }
                            } catch (RemoteException e2) {
                                e2.printStackTrace();
                            }
                        }
                    } else if ("com.szchoiceway.btsuite.bitmap".equals(action)) {
                        byte[] byteArrayExtra2 = intent.getByteArrayExtra("bitmap");
                        if (byteArrayExtra2.length != 0) {
                            bitmap = BitmapFactory.decodeByteArray(byteArrayExtra2, 0, byteArrayExtra2.length);
                        }
                        Log.i(BaseLauncherView.TAG, "onReceive: com.szchoiceway.btsuite.bitmap = " + bitmap);
                        IEventService evtService3 = LauncherModel.getInstance().getEvtService();
                        if (evtService3 != null) {
                            try {
                                if (evtService3.getValidMode() == EventUtils.eSrcMode.SRC_BTMUSIC.getIntValue()) {
                                    BaseLauncherView.this.setBTMusicCoverBg(bitmap);
                                }
                            } catch (RemoteException e3) {
                                e3.printStackTrace();
                            }
                        }
                    } else if ("com.szchoiceway.eventcenter.EventUtils.ZXW_ACTION_NOTIIFY_MEDIA_PLAY_PATH".equals(action)) {
                        String unused = BaseLauncherView.this.playPath = intent.getStringExtra("com.szchoiceway.eventcenter.EventUtils.ZXW_ACTION_NOTIIFY_MEDIA_PLAY_PATH_EXTRA");
                        Log.d(BaseLauncherView.TAG, "onReceive: ZXW_ACTION_NOTIIFY_MEDIA_PLAY_PATH = " + BaseLauncherView.this.playPath);
                        BaseLauncherView.this.setMusicCoverBg(EventUtils.loadingCover(BaseLauncherView.this.playPath));
                        BaseLauncherView.this.mHandler.removeMessages(0);
                        BaseLauncherView.this.mHandler.sendEmptyMessageDelayed(0, 250);
                    } else if (EventUtils.HBCP_EVT_HSHF_STATUS.equals(action)) {
                        BaseLauncherView.this.setBtStatus(intent.getIntExtra(EventUtils.INTENT_EXTRA_INT_KEYNAME, 0));
                    } else if ("AUTONAVI_STANDARD_BROADCAST_SEND".equals(action)) {
                        BaseLauncherView.this.refreshNaviInfo(intent.getIntExtra("KEY_TYPE", 0), intent.getIntExtra(com.szchoiceway.zxwlib.utils.EventUtils.EXTRA_STATE, 0), intent.getExtras());
                    } else if (EventUtils.ZXW_CAN_START_UP_APPS.equals(action)) {
                        Log.i(BaseLauncherView.TAG, "onReceive: ZXW_CAN_START_UP_APPS = com.szchoiceway.eventcenter.EventUtils.ZXW_CAN_START_UP_APPS");
                        BaseLauncherView.this.showAppList(true);
                    } else if (EventUtils.MZD_Launcher_Zhuansu.equals(action)) {
                        BaseLauncherView.this.setRotatingSpeed(intent.getIntExtra(EventUtils.MZD_Launcher_Zhuansu_EXTRA, 0));
                    } else if (EventUtils.MZD_Launcher_Chesu.equals(action)) {
                        BaseLauncherView.this.setSpeed(intent.getIntExtra(EventUtils.MZD_Launcher_Chesu_EXTRA, 0));
                    } else if ("android.intent.action.LOCALE_CHANGED".equals(action)) {
                        Log.d(BaseLauncherView.TAG, "onReceive = ACTION_LOCALE_CHANGED");
                        BaseLauncherView.this.refreshLocalChanged();
                    } else if ("android.intent.action.MEDIA_MOUNTED".equals(action)) {
                        File file = new File(intent.getData().getPath() + "/" + BaseLauncherView.zxw_show_gps_filename);
                        Log.d(BaseLauncherView.TAG, "ACTION_MEDIA_MOUNTED path = " + file.getPath());
                        if (file.exists()) {
                            Log.d(BaseLauncherView.TAG, "showGpsFile exist");
                            if (SysProviderOpt.getInstance(BaseLauncherView.this.mContext) != null) {
                                if (!SysProviderOpt.getInstance(BaseLauncherView.this.mContext).getRecordBoolean(SysProviderOpt.KSW_HAVE_GPSAPP, false)) {
                                    SysProviderOpt.getInstance(BaseLauncherView.this.mContext).updateRecord(SysProviderOpt.KSW_HAVE_GPSAPP, "1");
                                    LauncherModel.getInstance().mShowGPS = true;
                                    Toast.makeText(BaseLauncherView.this.mContext, BaseLauncherView.this.mContext.getString(R.string.lb_show_gps), 1).show();
                                } else {
                                    SysProviderOpt.getInstance(BaseLauncherView.this.mContext).updateRecord(SysProviderOpt.KSW_HAVE_GPSAPP, "0");
                                    LauncherModel.getInstance().mShowGPS = false;
                                    Toast.makeText(BaseLauncherView.this.mContext, BaseLauncherView.this.mContext.getString(R.string.lb_hide_gps), 1).show();
                                }
                                BaseLauncherView.this.refreshAppList();
                            }
                        }
                        BaseLauncherView.this.refreshStorageView();
                    } else if ("android.intent.action.MEDIA_UNMOUNTED".equals(action)) {
                        Log.i(BaseLauncherView.TAG, "onReceive: ACTION_MEDIA_UNMOUNTED");
                        BaseLauncherView.this.refreshStorageView();
                    } else if (EventUtils.LAUNCHER_IS_IN_MULTIWINDOWMODE.equals(action)) {
                        BaseLauncherView.this.isInMultiWindowMode = intent.getBooleanExtra("multi", false);
                    } else if (EventUtils.SET_MODE_SET.equals(action)) {
                        SysProviderOpt.getInstance(BaseLauncherView.this.mContext).updateRecord("KESAIWEI_SYS_MODE_SELECTION", intent.getStringExtra(EventUtils.SET_MODE_SET_EXTRA));
                    } else if (EventUtils.SET_CLIENT_SET.equals(action)) {
                        SysProviderOpt.getInstance(BaseLauncherView.this.mContext).updateRecord(SysProviderOpt.KSW_FACTORY_SET_CLIENT, intent.getStringExtra(EventUtils.SET_CLIENT_SET_EXTRA).equals("1") ? "XinCheng" : "ALS_6208");
                    } else if (EventUtils.SET_RESOLUTION_SET.equals(action)) {
                        String stringExtra5 = intent.getStringExtra(EventUtils.SET_RESOLUTION_SET_EXTRA);
                        Log.d(BaseLauncherView.TAG, "resolution extra = " + stringExtra5);
                        SysProviderOpt.getInstance(BaseLauncherView.this.mContext).updateRecord("RESOLUTION", stringExtra5);
                    } else if ("com.choiceway.eventcenter.EventUtils.KSW_ZXW_BT_CONNECED_SHOW_VIEW".equals(action)) {
                        if (intent.getBooleanExtra("com.choiceway.eventcenter.EventUtils.KSW_ZXW_BT_CONNECED_SHOW_VIEW_DATA", false)) {
                            LauncherModel.getInstance().btConnectStatus = 1;
                            BaseLauncherView.this.refreshBTStatus();
                        } else if (BluetoothAdapter.getDefaultAdapter().isEnabled()) {
                            LauncherModel.getInstance().btConnectStatus = 0;
                            BaseLauncherView.this.refreshBTStatus();
                        } else {
                            LauncherModel.getInstance().btConnectStatus = -1;
                            BaseLauncherView.this.refreshBTStatus();
                        }
                    } else if ("ZXW_ACTION_SHOW_APPLIST".equals(action)) {
                        int intExtra6 = intent.getIntExtra("data", 1);
                        BaseLauncherView baseLauncherView = BaseLauncherView.this;
                        if (intExtra6 != 1) {
                            z = false;
                        }
                        baseLauncherView.showAppList(z);
                    } else if ("com.szchoiceway.eventcenter.EventUtils.ZXW_ACTION_NOTIIFY_VIDEO_PLAY_PATH".equals(action)) {
                        BaseLauncherView.this.setCurPlayVideoPath(intent.getStringExtra("com.szchoiceway.eventcenter.EventUtils.ZXW_ACTION_NOTIIFY_VIDEO_PLAY_PATH_EXTRA"));
                    } else if (EventUtils.ZXW_ACTION_UPDATE_BENZ_CONTROL_DATA_SEND.equals(action)) {
                        BaseLauncherView.this.refreshBenzControl(intent.getIntegerArrayListExtra("data"));
                    } else if ("EventUtils.ZXW_ACTION_UPDATE_GPS_TIME".equals(action) || "android.intent.action.TIME_SET".equals(action)) {
                        BaseLauncherView.this.refreshDateTime();
                    } else if (EventUtils.ZXW_ACTION_PEMP_ID7_LEFT_APP_REFRESH.equals(action)) {
                        BaseLauncherView.this.refreshLeftApp(intent.getIntExtra(EventUtils.ZXW_ACTION_PEMP_ID7_LEFT_APP_REFRESH_EXTRA, -1));
                    } else if ("com.szchoiceway.action.mode_change".equals(action)) {
                        if (intent.getIntExtra("com.szchoiceway.action.mode_change_extra", 0) != EventUtils.eSrcMode.SRC_MUSIC.getIntValue()) {
                            BaseLauncherView.this.refreshPlayState();
                        }
                    } else if (EventUtils.ZXW_ACTION_REFRESH_AFTER_LOCALE_CHANGED.equals(action)) {
                        BaseLauncherView baseLauncherView2 = BaseLauncherView.this;
                        baseLauncherView2.mLauncherResume = SysProviderOpt.getInstance(baseLauncherView2.mContext).getRecordBoolean("ZXW_LAUNCHER_IS_RESUME", false);
                        Log.d(BaseLauncherView.TAG, "mLauncherResume = " + BaseLauncherView.this.mLauncherResume);
                        BaseLauncherView.this.refreshLocalChanged();
                    } else if (EventUtils.HBCP_EVT_CUR_CONNECTED_DEVICE_NAME.equals(action)) {
                        BaseLauncherView.this.refreshBTConnectName(intent.getStringExtra(EventUtils.INTENT_EXTRA_STR_KEYNAME));
                    } else if (EventUtils.SET_DAY_NIGHT_MODE.equals(action)) {
                        UiModeManager uiModeManager = (UiModeManager) BaseLauncherView.this.mContext.getSystemService("uimode");
                        if (intent.getIntExtra(EventUtils.SET_DAY_NIGHT_MODE_EXTRA, 0) == 0) {
                            uiModeManager.setNightMode(1);
                        } else {
                            uiModeManager.setNightMode(2);
                        }
                        BaseLauncherView.this.mHandler.sendEmptyMessageDelayed(1, 1000);
                    } else if (EventUtils.LAUNCHER_ON_CONFIGURATION_CHANGE.equals(action)) {
                        Log.d(BaseLauncherView.TAG, "LAUNCHER_ON_CONFIGURATION_CHANGE");
                    } else if (BaseLauncherView.LAUNCHER_ON_BACK_PRESSED.equals(action)) {
                        BaseLauncherView.this.onReasonKey("backkey");
                    } else if (EventUtils.ZXW_ACTION_REQUEST_MUSIC_COVER.equals(action)) {
                        Log.d(BaseLauncherView.TAG, "onReceive ZXW_ACTION_REQUEST_MUSIC_COVER");
                        Intent intent2 = new Intent(EventUtils.ZXW_ACTION_NOTIIFY_DASHBOARD_MEDIA_PLAY_PATH);
                        intent2.putExtra("com.szchoiceway.eventcenter.EventUtils.ZXW_ACTION_NOTIIFY_MEDIA_PLAY_PATH_EXTRA", BaseLauncherView.this.playPath);
                        BaseLauncherView.this.mContext.sendBroadcast(intent2);
                    } else if (EventUtils.ZXW_ACTION_MAIN_TOP_REFRESH_VIEW.equals(action)) {
                        String stringExtra6 = intent.getStringExtra(EventUtils.ZXW_ACTION_MAIN_TOP_REFRESH_VIEW_KEY);
                        stringExtra6.hashCode();
                        if (stringExtra6.equals("SIGNAL")) {
                            BaseLauncherView.this.refreshSignalView(intent.getIntExtra(EventUtils.ZXW_ACTION_MAIN_TOP_REFRESH_VIEW_DATA, -1), intent.getStringExtra(EventUtils.ZXW_ACTION_MAIN_TOP_REFRESH_VIEW_DATA2));
                        } else if (stringExtra6.equals("WIFI")) {
                            BaseLauncherView.this.refreshWifiView(intent.getIntExtra(EventUtils.ZXW_ACTION_MAIN_TOP_REFRESH_VIEW_DATA, -1));
                        }
                    }
                }
            }
        }
    }
}
