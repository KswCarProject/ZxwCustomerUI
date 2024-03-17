package com.szchoiceway.customerui.model;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.szchoiceway.customerui.drag.DragAppInfo;
import com.szchoiceway.customerui.drag.DragAppListInfo;
import com.szchoiceway.customerui.utils.AppUtil;
import com.szchoiceway.customerui.utils.EventUtils;
import com.szchoiceway.customerui.utils.ShareUtil;
import com.szchoiceway.customerui.utils.SysProviderOpt;
import com.szchoiceway.eventcenter.IEventService;
import java.util.HashMap;

public class LauncherModel {
    private static final int HANDLER_BIND_EVENTSERVICE = 3;
    private static final int HANDLER_CHECK_EVENTSERVICE_ALIVE = 2;
    private static final int HANDLER_REFRESH_APPDATA = 1;
    private static final int HANDLER_REFRESH_APPLIST = 0;
    public static final String TAG = "LauncherModel";
    public static boolean isInit = false;
    private static LauncherModel launcherModel;
    private static SysProviderOpt mSysProviderOpt;
    private final HashMap<String, DragAppInfo> allDragAppInfoMap = new HashMap<>();
    public boolean bInLeftFocus = false;
    /* access modifiers changed from: private */
    public boolean bindEvent = false;
    public String btConnectName = "";
    public int btConnectStatus = 0;
    public boolean hasInItAppList = false;
    public int iHaveDvdType = 0;
    public int iHaveDvrType;
    public boolean inEditMode = false;
    /* access modifiers changed from: private */
    public HandlerThread mAppHandlerThread;
    /* access modifiers changed from: private */
    public AppUtil mAppUtil;
    private LauncherModelCallback mCallback;
    /* access modifiers changed from: private */
    public Context mContext;
    public ServiceConnection mEvtSc = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IEventService unused = LauncherModel.this.mEvtService = IEventService.Stub.asInterface(iBinder);
            Log.i(LauncherModel.TAG, "onServiceConnected: mEvtService = " + LauncherModel.this.mEvtService);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(LauncherModel.TAG, "onServiceDisconnected: ");
            IEventService unused = LauncherModel.this.mEvtService = null;
        }
    };
    /* access modifiers changed from: private */
    public IEventService mEvtService = null;
    public String mImitateOriginalCarClient;
    public int mProductIndex = 0;
    public boolean mShow360 = false;
    public boolean mShowAUX = false;
    public boolean mShowDVR = false;
    public boolean mShowECAR = false;
    public boolean mShowEQ = false;
    public boolean mShowES = false;
    public boolean mShowFC = false;
    public boolean mShowGPS = false;
    public boolean mShowGoogle = false;
    private boolean mShowLogo = true;
    public boolean mShowMS1920 = false;
    public boolean mShowManual = false;
    public boolean mShowTV = false;
    public boolean mShowTXZCare = false;
    public boolean mShowWeather = false;
    public String mStrResolution;
    public int m_iModeSet = 0;
    public int m_iUITypeVer = 102;
    public int m_iUiIndex = 4;
    public int m_i_ksw_evo_id7_main_interface_index = 0;
    /* access modifiers changed from: private */
    public final Handler mainHandler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 0) {
                LauncherModel.this.mAppHandlerThread.quit();
                LauncherModel.this.refreshAppList();
                HandlerThread unused = LauncherModel.this.mAppHandlerThread = null;
            } else if (i == 1) {
                LauncherModel.this.initAppData();
            } else if (i == 2) {
                removeMessages(2);
                boolean isServiceAlive = EventUtils.isServiceAlive(LauncherModel.this.mContext, "com.szchoiceway.eventcenter.EventService");
                Log.d(LauncherModel.TAG, "HANDLER_CHECK_EVENTSERVICE_ALIVE isEventAlive = " + isServiceAlive);
                if (isServiceAlive) {
                    LauncherModel.this.mainHandler.sendEmptyMessage(3);
                    return;
                }
                LauncherModel.this.mContext.startService(new Intent("com.szchoiceway.eventcenter.EventService").setPackage("com.szchoiceway.eventcenter"));
                LauncherModel.this.mainHandler.sendEmptyMessageDelayed(2, 300);
            } else if (i == 3) {
                removeMessages(3);
                try {
                    Intent intent = new Intent();
                    intent.setAction("com.szchoiceway.eventcenter.EventService");
                    intent.setPackage("com.szchoiceway.eventcenter");
                    LauncherModel launcherModel = LauncherModel.this;
                    boolean unused2 = launcherModel.bindEvent = launcherModel.mContext.bindService(intent, LauncherModel.this.mEvtSc, 1);
                    Log.d(LauncherModel.TAG, "HANDLER_BIND_EVENTSERVICE bindEvent = " + LauncherModel.this.bindEvent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (!LauncherModel.this.bindEvent) {
                    removeMessages(3);
                    sendEmptyMessageDelayed(3, 3000);
                }
            }
        }
    };
    public Bitmap musicCover = null;
    public String musicMessage = "";
    public String musicTitle = "";
    public String videoMessage = "";
    public String videoTitle = "";
    public String xml_client = "";

    public interface LauncherModelCallback {
        void onRefreshAppList();

        void onShowAppList(boolean z);
    }

    private LauncherModel() {
    }

    public static LauncherModel getInstance() {
        if (launcherModel == null) {
            synchronized (LauncherModel.class) {
                if (launcherModel == null) {
                    launcherModel = new LauncherModel();
                }
            }
        }
        return launcherModel;
    }

    public void init(Context context) {
        if (!isInit) {
            isInit = true;
            this.mContext = context;
            this.mainHandler.sendEmptyMessage(3);
            initData();
        }
    }

    private void initData() {
        ShareUtil.init(this.mContext);
        initProviderData();
    }

    public void initAppData() {
        if (this.mAppHandlerThread != null) {
            this.mainHandler.removeMessages(1);
            this.mainHandler.sendEmptyMessageDelayed(1, 1000);
            return;
        }
        this.hasInItAppList = false;
        this.mAppUtil = new AppUtil(this.mContext);
        HandlerThread handlerThread = new HandlerThread("AppHandlerThread");
        this.mAppHandlerThread = handlerThread;
        handlerThread.start();
        new Handler(this.mAppHandlerThread.getLooper()) {
            public void handleMessage(Message message) {
                super.handleMessage(message);
                synchronized (new Object()) {
                    LauncherModel.this.mAppUtil.initData();
                }
                LauncherModel.this.hasInItAppList = true;
                LauncherModel.this.mainHandler.removeMessages(0);
                LauncherModel.this.mainHandler.sendEmptyMessage(0);
            }
        }.sendEmptyMessage(0);
    }

    private void initProviderData() {
        SysProviderOpt instance = SysProviderOpt.getInstance(this.mContext);
        mSysProviderOpt = instance;
        this.m_iUITypeVer = instance.getRecordInteger("Set_User_UI_Type", this.m_iUITypeVer);
        this.m_iModeSet = mSysProviderOpt.getRecordInteger("KESAIWEI_SYS_MODE_SELECTION", this.m_iModeSet);
        this.mProductIndex = mSysProviderOpt.getRecordInteger(SysProviderOpt.KSW_DATA_PRODUCT_INDEX, this.mProductIndex);
        Log.d(TAG, "m_iUITypeVer: " + this.m_iUITypeVer + ", m_iModeSet: " + this.m_iModeSet + ", mProductIndex: " + this.mProductIndex);
        this.xml_client = mSysProviderOpt.getRecordValue(SysProviderOpt.KSW_FACTORY_SET_CLIENT, "");
        this.mStrResolution = mSysProviderOpt.getRecordValue("KSW_UI_RESOLUTION", "");
        this.m_i_ksw_evo_id7_main_interface_index = mSysProviderOpt.getRecordInteger(SysProviderOpt.KSW_EVO_ID7_MAIN_INTERFACE_INDEX, this.m_i_ksw_evo_id7_main_interface_index);
        this.m_iUiIndex = mSysProviderOpt.getRecordInteger("Set_User_UI_Type_index", this.m_iUiIndex);
        this.mImitateOriginalCarClient = mSysProviderOpt.getRecordValue(SysProviderOpt.IMITAGE_ORIGINAL_CAL_STYLE_CLIENT, this.mImitateOriginalCarClient);
        this.mShowGoogle = mSysProviderOpt.getRecordBoolean(SysProviderOpt.MAISILUO_SYS_GOOGLEPLAY, false);
        this.mShowAUX = mSysProviderOpt.getRecordBoolean(SysProviderOpt.KSW_HAVE_AUX, false) && this.mProductIndex != 2;
        this.mShowTV = mSysProviderOpt.getRecordBoolean(SysProviderOpt.KSW_HAVE_TV, false) && this.mProductIndex != 2;
        this.mShowFC = mSysProviderOpt.getRecordBoolean(SysProviderOpt.KSW_HAVE_FRONT_CAMERA, true) && this.mProductIndex != 2;
        this.mShowDVR = mSysProviderOpt.getRecordInteger(SysProviderOpt.KESAIWEI_RECORD_DVR, 0) == 1;
        this.mShowMS1920 = mSysProviderOpt.getRecordBoolean(SysProviderOpt.KSW_SCREEN_CAST_MS9120, false);
        this.mShowEQ = mSysProviderOpt.getRecordBoolean(SysProviderOpt.KSW_HAVE_EQ, true);
        this.mShowWeather = mSysProviderOpt.getRecordBoolean(SysProviderOpt.KSW_HAVE_WEATHER, true);
        this.mShow360 = mSysProviderOpt.getRecordInteger("KESAIWEI_SYS_CAMERA_SELECTION", 1) == 3;
        this.mShowGPS = mSysProviderOpt.getRecordBoolean(SysProviderOpt.KSW_HAVE_GPSAPP, false);
        this.mShowTXZCare = EventUtils.getInstallStatus(this.mContext, "com.txznet.smartadapter");
        this.mShowManual = mSysProviderOpt.getRecordBoolean(SysProviderOpt.KSW_HAVE_MANUAL, true);
        this.mShowECAR = mSysProviderOpt.getRecordBoolean(SysProviderOpt.KSW_HAVE_ECAR, true);
        this.mShowES = mSysProviderOpt.getRecordBoolean(SysProviderOpt.KSW_HAVE_ESEXPLORER, true);
    }

    public IEventService getEvtService() {
        return this.mEvtService;
    }

    public int getUITypeVer() {
        return this.m_iUITypeVer;
    }

    public String getResolution() {
        return this.mStrResolution;
    }

    public String getClient() {
        return this.mImitateOriginalCarClient;
    }

    public HashMap<String, DragAppInfo> getAppInfoMap() {
        return DragAppListInfo.mAppFragmentItemDetailsMap;
    }

    public void showAppList(boolean z) {
        LauncherModelCallback launcherModelCallback = this.mCallback;
        if (launcherModelCallback != null) {
            launcherModelCallback.onShowAppList(z);
        }
    }

    public void refreshAppList() {
        LauncherModelCallback launcherModelCallback = this.mCallback;
        if (launcherModelCallback != null) {
            launcherModelCallback.onRefreshAppList();
        }
    }

    public void setCallback(LauncherModelCallback launcherModelCallback) {
        this.mCallback = launcherModelCallback;
    }

    public boolean needRefreshAppList() {
        boolean z;
        boolean recordBoolean = mSysProviderOpt.getRecordBoolean(SysProviderOpt.MAISILUO_SYS_GOOGLEPLAY, false);
        if (this.mShowGoogle != recordBoolean) {
            this.mShowGoogle = recordBoolean;
            z = true;
        } else {
            z = false;
        }
        boolean z2 = mSysProviderOpt.getRecordBoolean(SysProviderOpt.KSW_HAVE_AUX, false) && this.mProductIndex != 2;
        if (this.mShowAUX != z2) {
            this.mShowAUX = z2;
            z = true;
        }
        boolean z3 = mSysProviderOpt.getRecordBoolean(SysProviderOpt.KSW_HAVE_TV, false) && this.mProductIndex != 2;
        if (this.mShowTV != z3) {
            this.mShowTV = z3;
            z = true;
        }
        boolean z4 = mSysProviderOpt.getRecordBoolean(SysProviderOpt.KSW_HAVE_FRONT_CAMERA, true) && this.mProductIndex != 2;
        if (this.mShowFC != z4) {
            this.mShowFC = z4;
            z = true;
        }
        boolean z5 = mSysProviderOpt.getRecordInteger(SysProviderOpt.KESAIWEI_RECORD_DVR, 0) == 1;
        if (this.mShowDVR != z5) {
            this.mShowDVR = z5;
            z = true;
        }
        boolean recordBoolean2 = mSysProviderOpt.getRecordBoolean(SysProviderOpt.KSW_SCREEN_CAST_MS9120, false);
        if (this.mShowMS1920 != recordBoolean2) {
            this.mShowMS1920 = recordBoolean2;
            z = true;
        }
        boolean recordBoolean3 = mSysProviderOpt.getRecordBoolean(SysProviderOpt.KSW_HAVE_EQ, true);
        if (this.mShowEQ != recordBoolean3) {
            this.mShowEQ = recordBoolean3;
            z = true;
        }
        boolean recordBoolean4 = mSysProviderOpt.getRecordBoolean(SysProviderOpt.KSW_HAVE_WEATHER, true);
        if (this.mShowWeather != recordBoolean4) {
            this.mShowWeather = recordBoolean4;
            z = true;
        }
        boolean z6 = mSysProviderOpt.getRecordInteger("KESAIWEI_SYS_CAMERA_SELECTION", 1) == 3;
        if (this.mShow360 != z6) {
            this.mShow360 = z6;
            z = true;
        }
        boolean recordBoolean5 = mSysProviderOpt.getRecordBoolean(SysProviderOpt.KSW_HAVE_GPSAPP, false);
        if (this.mShowGPS != recordBoolean5) {
            this.mShowGPS = recordBoolean5;
            z = true;
        }
        boolean installStatus = EventUtils.getInstallStatus(this.mContext, "com.txznet.smartadapter");
        if (this.mShowTXZCare == installStatus) {
            return z;
        }
        this.mShowTXZCare = installStatus;
        return true;
    }
}
