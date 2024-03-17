package com.szchoiceway.customerui.views;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.os.storage.DiskInfo;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.provider.Settings;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.utils.EventUtils;
import com.szchoiceway.customerui.utils.ModeUtil;
import com.szchoiceway.customerui.utils.SysProviderOpt;
import com.szchoiceway.customerui.views.SbarImageButton;
import com.szchoiceway.eventcenter.IEventService;
import com.szchoiceway.zxwlib.bean.Customer;
import java.io.File;
import java.util.Collections;
import java.util.List;

public class StatusBarView extends RelativeLayout implements View.OnClickListener, SbarImageButton.OnSetImageTintListener {
    public static final String ACTION_LAUNCHER_KEY_CTRL = "com.szchoiceway.ACTION_LAUNCHER_KEY_CTRL";
    public static final String EXTRA_LAUNCHER_KEY_WORD = "LauncherKeyWord";
    public static final int HANDLER_MSG_BING_SERVICE = 4;
    public static final int HANDLER_MSG_CHECK_SERVICE_ALIVE = 3;
    public static final int MODE_EFFICT = 0;
    public static final int MODE_PERSONAL = 2;
    public static final int MODE_SPORT = 1;
    public static final String TAG = "StatusBarView";
    private static final String usb_otg_switch = "/sys/devices/platform/soc/4e00000.ssusb/mode";
    /* access modifiers changed from: private */
    public boolean bindEvent = false;
    private SbarImageButton btnExit = null;
    private SbarImageButton btnHome = null;
    private SbarImageButton btnScreen = null;
    /* access modifiers changed from: private */
    public SbarImageButton btnTask = null;
    private SbarImageButton btnTask2 = null;
    private ImageView chkBT;
    private ImageView iconCharge;
    private ImageView iconSD;
    private ImageView iconUSB;
    private View imageHD;
    /* access modifiers changed from: private */
    public boolean isEventAlive = false;
    private int landRoverThemeIndex = 0;
    private boolean lastIsSdFound = false;
    private boolean lastIsUsbFound = false;
    private int lastPrimaryMode = -1;
    private int lastSecondaryMode = -1;
    /* access modifiers changed from: private */
    public Context mContext;
    public ServiceConnection mEvtSc = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IEventService unused = StatusBarView.this.mEvtService = IEventService.Stub.asInterface(iBinder);
            try {
                if (StatusBarView.this.mEvtService.GetBTStatus() >= 3) {
                    StatusBarView.this.refreshBTStatus(1);
                } else {
                    StatusBarView.this.refreshBTStatus(2);
                }
                int settingInt = StatusBarView.this.mEvtService.getSettingInt("COM.SZCHOICEWAY_BRIGHTNESS_SETTINGS", 50);
                Intent intent = new Intent("ZXW_ACTION_CHANGE_BRIGHTNESS_SETTINGS");
                intent.putExtra("ZXW_ACTION_CHANGE_BRIGHTNESS_EXTRA", settingInt);
                StatusBarView.this.mContext.sendBroadcast(intent);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(StatusBarView.TAG, "onServiceDisconnected: ");
            IEventService unused = StatusBarView.this.mEvtService = null;
        }
    };
    /* access modifiers changed from: private */
    public IEventService mEvtService = null;
    Handler mHandler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 3) {
                removeMessages(3);
                StatusBarView statusBarView = StatusBarView.this;
                boolean unused = statusBarView.isEventAlive = EventUtils.isServiceAlive(statusBarView.mContext, "com.szchoiceway.eventcenter.EventService");
                if (!StatusBarView.this.isEventAlive) {
                    StatusBarView.this.mContext.startService(new Intent("com.szchoiceway.eventcenter.EventService").setPackage("com.szchoiceway.eventcenter"));
                    sendEmptyMessageDelayed(3, 500);
                } else if (!StatusBarView.this.bindEvent) {
                    sendEmptyMessageDelayed(4, 500);
                }
            } else if (i == 4) {
                removeMessages(4);
                try {
                    Intent intent = new Intent();
                    intent.setAction("com.szchoiceway.eventcenter.EventService");
                    intent.setPackage("com.szchoiceway.eventcenter");
                    StatusBarView statusBarView2 = StatusBarView.this;
                    boolean unused2 = statusBarView2.bindEvent = statusBarView2.mContext.bindService(intent, StatusBarView.this.mEvtSc, 1);
                    Log.d(StatusBarView.TAG, "HANDLER_MSG_BING_SERVICE bindEvent = " + StatusBarView.this.bindEvent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (!StatusBarView.this.bindEvent) {
                    removeMessages(4);
                    sendEmptyMessageDelayed(4, 3000);
                }
            }
        }
    };
    private int mIntSignalType = 0;
    private boolean mIsSdFound = false;
    private boolean mIsUsbFound = false;
    private PhoneStateListener mPhoneStateListener;
    /* access modifiers changed from: private */
    public int mSignalStrengthLevel = 0;
    private View mStatusBarView = null;
    /* access modifiers changed from: private */
    public String mStrSignalType = "4G";
    private SysProviderOpt mSysProviderOpt;
    private TelephonyManager mTelephonyManager;
    ColorStateList mTint = null;
    /* access modifiers changed from: private */
    public int mWifiLevel = -1;
    /* access modifiers changed from: private */
    public int m_iModeSet = 0;
    /* access modifiers changed from: private */
    public int m_iUIIndex = 0;
    private int m_iUITypeVer = 0;
    private ModeUtil modeUtil;
    private MyBroadcast myBroadcast;
    private boolean register = false;
    private String resolution = "1920x720";
    /* access modifiers changed from: private */
    public ImageView signalStrengthView;
    private TextView signalTypeText;
    private ImageView signalTypeView;
    private StatusBarUtil statusBarUtil;
    private int themeIndex = 0;
    private TextView txtClock = null;
    /* access modifiers changed from: private */
    public TextView txtModePrimary;
    /* access modifiers changed from: private */
    public TextView txtModeSecondary;
    /* access modifiers changed from: private */
    public String valuePrimary = "";
    /* access modifiers changed from: private */
    public String valueSecondary = "";
    private ImageView wifiLevel;

    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return super.generateLayoutParams(attributeSet);
    }

    public /* bridge */ /* synthetic */ ViewOverlay getOverlay() {
        return super.getOverlay();
    }

    public StatusBarView(Context context) {
        super(context);
        init(context);
    }

    public StatusBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public StatusBarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "onAttachedToWindow");
        registerReceiver();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG, "onDetachedFromWindow");
        TelephonyManager telephonyManager = this.mTelephonyManager;
        if (telephonyManager != null) {
            telephonyManager.listen(this.mPhoneStateListener, 0);
        }
        MyBroadcast myBroadcast2 = this.myBroadcast;
        if (myBroadcast2 != null) {
            this.mContext.unregisterReceiver(myBroadcast2);
            this.myBroadcast = null;
        }
    }

    private void init(Context context) {
        Log.d(TAG, "status bar height " + getStatusBarHeight());
        this.mContext = context;
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.sendEmptyMessage(3);
        }
        this.mTelephonyManager = (TelephonyManager) context.getSystemService("phone");
        MobilePhoneStateListener mobilePhoneStateListener = new MobilePhoneStateListener();
        this.mPhoneStateListener = mobilePhoneStateListener;
        this.mTelephonyManager.listen(mobilePhoneStateListener, 481);
        SysProviderOpt instance = SysProviderOpt.getInstance(this.mContext);
        this.mSysProviderOpt = instance;
        this.m_iUITypeVer = instance.getRecordInteger("Set_User_UI_Type", this.m_iUITypeVer);
        this.m_iModeSet = this.mSysProviderOpt.getRecordInteger("KESAIWEI_SYS_MODE_SELECTION", this.m_iModeSet);
        this.m_iUIIndex = Customer.getUIIndex(this.mContext);
        this.themeIndex = this.mSysProviderOpt.getRecordInteger("KESAIWEI_SYS_DISPLAY_MODE", 0);
        this.resolution = this.mSysProviderOpt.getRecordValue("KSW_UI_RESOLUTION", "1920x720");
        Log.d(TAG, "init m_iUITypeVer = " + this.m_iUITypeVer + ", m_iUIIndex = " + this.m_iUIIndex + ", m_iModeSet = " + this.m_iModeSet);
        StatusBarUtil statusBarUtil2 = new StatusBarUtil(this, getContext(), this.resolution, this.m_iUIIndex, this.m_iModeSet);
        this.statusBarUtil = statusBarUtil2;
        this.mStatusBarView = statusBarUtil2.getView();
        this.modeUtil = new ModeUtil(context);
        initView();
    }

    private void initView() {
        int i;
        View view = this.mStatusBarView;
        if (view != null) {
            SbarImageButton sbarImageButton = (SbarImageButton) view.findViewById(R.id.btnHome);
            this.btnHome = sbarImageButton;
            if (sbarImageButton != null) {
                sbarImageButton.setImageTintListListener(this);
                if (this.m_iModeSet == 34) {
                    if (this.m_iUIIndex != 4) {
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                        if (this.m_iUIIndex == 5) {
                            i = EventUtils.getPx(this.mContext, 44);
                        } else {
                            i = EventUtils.getPx(this.mContext, 36);
                        }
                        layoutParams.setMarginStart(i);
                        this.btnHome.setLayoutParams(layoutParams);
                    }
                } else if (this.m_iUIIndex == 7) {
                    RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
                    layoutParams2.setMarginStart(EventUtils.getPx(this.mContext, 16));
                    this.btnHome.setLayoutParams(layoutParams2);
                }
            }
            this.iconUSB = (ImageView) this.mStatusBarView.findViewById(R.id.iconUSB);
            this.iconSD = (ImageView) this.mStatusBarView.findViewById(R.id.iconSD);
            this.signalTypeView = (ImageView) this.mStatusBarView.findViewById(R.id.signalBg);
            this.signalStrengthView = (ImageView) this.mStatusBarView.findViewById(R.id.signalView);
            this.signalTypeText = (TextView) this.mStatusBarView.findViewById(R.id.signalText);
            refreshPhoneSignalBg();
            SbarImageButton sbarImageButton2 = (SbarImageButton) this.mStatusBarView.findViewById(R.id.btnTask);
            this.btnTask = sbarImageButton2;
            if (sbarImageButton2 != null) {
                sbarImageButton2.setVisibility(Build.VERSION.SDK_INT > 31 ? 8 : 0);
            }
            SbarImageButton sbarImageButton3 = (SbarImageButton) this.mStatusBarView.findViewById(R.id.btnTask2);
            this.btnTask2 = sbarImageButton3;
            if (sbarImageButton3 != null) {
                sbarImageButton3.setVisibility(Build.VERSION.SDK_INT > 31 ? 0 : 8);
                this.btnTask2.setOnClickListener(new View.OnClickListener() {
                    public final void onClick(View view) {
                        StatusBarView.this.lambda$initView$0$StatusBarView(view);
                    }
                });
                this.btnTask2.setOnLongClickListener(new View.OnLongClickListener() {
                    public final boolean onLongClick(View view) {
                        return StatusBarView.this.lambda$initView$1$StatusBarView(view);
                    }
                });
            }
            SbarImageButton sbarImageButton4 = (SbarImageButton) this.mStatusBarView.findViewById(R.id.btnExit);
            this.btnExit = sbarImageButton4;
            if (sbarImageButton4 != null) {
                sbarImageButton4.setImageTintListListener2(this);
            }
            this.imageHD = this.mStatusBarView.findViewById(R.id.imageHD);
            if ("1920x720".equalsIgnoreCase(this.resolution) || "720x1920".equalsIgnoreCase(this.resolution)) {
                this.imageHD.setVisibility(0);
            } else {
                this.imageHD.setVisibility(8);
            }
            TextView textView = (TextView) this.mStatusBarView.findViewById(R.id.txtClock);
            this.txtClock = textView;
            if (textView != null) {
                if (this.m_iUIIndex == 7) {
                    RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, EventUtils.getPx(this.mContext, 80));
                    layoutParams3.setMarginEnd(5);
                    layoutParams3.addRule(16, R.id.iconUSB);
                    this.txtClock.setLayoutParams(layoutParams3);
                } else if ("1024x600".equalsIgnoreCase(this.resolution) && this.m_iUIIndex == 4) {
                    RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) this.txtClock.getLayoutParams();
                    layoutParams4.setMarginEnd(10);
                    layoutParams4.addRule(16, R.id.iconCharge);
                }
            }
            SbarImageButton sbarImageButton5 = (SbarImageButton) this.mStatusBarView.findViewById(R.id.btnScreen);
            this.btnScreen = sbarImageButton5;
            if (sbarImageButton5 != null) {
                sbarImageButton5.setOnClickListener(this);
            }
            ImageView imageView = (ImageView) this.mStatusBarView.findViewById(R.id.levelWifi);
            this.wifiLevel = imageView;
            if (imageView != null) {
                imageView.setVisibility(8);
            }
            ImageView imageView2 = (ImageView) this.mStatusBarView.findViewById(R.id.chkBT);
            this.chkBT = imageView2;
            if (imageView2 != null) {
                imageView2.setVisibility(8);
            }
            TextView textView2 = (TextView) this.mStatusBarView.findViewById(R.id.txtModePrimary);
            this.txtModePrimary = textView2;
            if (textView2 != null) {
                textView2.setText("");
                if (this.m_iUIIndex == 7) {
                    this.txtModePrimary.setVisibility(8);
                }
            }
            TextView textView3 = (TextView) this.mStatusBarView.findViewById(R.id.txtModeSecondary);
            this.txtModeSecondary = textView3;
            if (textView3 != null) {
                textView3.setText("");
                if (this.m_iUIIndex == 7) {
                    this.txtModeSecondary.setVisibility(8);
                }
            }
            ImageView imageView3 = (ImageView) this.mStatusBarView.findViewById(R.id.iconCharge);
            this.iconCharge = imageView3;
            if (imageView3 != null) {
                imageView3.setVisibility(8);
            }
            refresFastCharge();
            refreshBMWId8BtnImage((ColorStateList) null);
            checkStoragePath();
        }
    }

    public /* synthetic */ void lambda$initView$0$StatusBarView(View view) {
        SbarImageButton sbarImageButton = this.btnTask;
        if (sbarImageButton != null) {
            sbarImageButton.performClick();
        }
    }

    public /* synthetic */ boolean lambda$initView$1$StatusBarView(View view) {
        this.btnTask.performLongClick();
        return true;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btnScreen) {
            sendScreenBroadcast();
        }
    }

    /* access modifiers changed from: private */
    public void refreshBTStatus(int i) {
        ImageView imageView = this.chkBT;
        if (imageView != null) {
            if (i == 0) {
                if (BluetoothAdapter.getDefaultAdapter().getState() != 12) {
                    this.chkBT.setVisibility(8);
                }
            } else if (i == 1) {
                imageView.setVisibility(0);
            } else if (i == 2) {
                imageView.setVisibility(8);
            }
        }
    }

    /* access modifiers changed from: private */
    public void refreshWifiSignal() {
        int i;
        WifiManager wifiManager = (WifiManager) this.mContext.getSystemService("wifi");
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        if (wifiManager.getWifiState() != 3) {
            i = -1;
        } else if (connectionInfo.getBSSID() != null) {
            connectionInfo.getSSID();
            i = WifiManager.calculateSignalLevel(connectionInfo.getRssi(), 6);
            connectionInfo.getLinkSpeed();
        } else {
            i = 0;
        }
        ImageView imageView = this.wifiLevel;
        if (imageView != null) {
            switch (i) {
                case -1:
                case 0:
                    imageView.setVisibility(8);
                    break;
                case 1:
                    imageView.setImageLevel(0);
                    this.wifiLevel.setVisibility(0);
                    break;
                case 2:
                    imageView.setImageLevel(1);
                    this.wifiLevel.setVisibility(0);
                    break;
                case 3:
                    imageView.setImageLevel(2);
                    this.wifiLevel.setVisibility(0);
                    break;
                case 4:
                    imageView.setImageLevel(3);
                    this.wifiLevel.setVisibility(0);
                    break;
                case 5:
                    imageView.setImageLevel(4);
                    this.wifiLevel.setVisibility(0);
                    break;
            }
            this.mWifiLevel = i;
            refreshMainView("WIFI", i, "");
        }
    }

    /* access modifiers changed from: private */
    public void refreshPhoneSignalBg() {
        if (!hasSimCard()) {
            this.mSignalStrengthLevel = 0;
            this.mIntSignalType = 0;
            this.mStrSignalType = "";
            ImageView imageView = this.signalTypeView;
            if (!(imageView == null || imageView.getVisibility() == 8)) {
                this.signalTypeView.setVisibility(8);
            }
            ImageView imageView2 = this.signalStrengthView;
            if (!(imageView2 == null || imageView2.getVisibility() == 8)) {
                this.signalStrengthView.setVisibility(8);
            }
            TextView textView = this.signalTypeText;
            if (!(textView == null || textView.getVisibility() == 8)) {
                this.signalTypeText.setVisibility(8);
            }
            refreshMainView("SIGNAL", this.mSignalStrengthLevel, this.mStrSignalType);
            return;
        }
        int networkType = ((TelephonyManager) this.mContext.getSystemService("phone")).getNetworkType();
        Log.d(TAG, "refreshPhoneSignalBg signalType = " + networkType);
        switch (networkType) {
            case 0:
            case 13:
            case 19:
                this.mIntSignalType = 2;
                this.mStrSignalType = "4G";
                break;
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
            case 16:
                this.mIntSignalType = 0;
                this.mStrSignalType = "2G";
                break;
            case 3:
            case 5:
            case 6:
            case 10:
            case 12:
            case 14:
            case 15:
            case 17:
                this.mIntSignalType = 1;
                this.mStrSignalType = "3G";
                break;
            case 20:
                this.mIntSignalType = 2;
                this.mStrSignalType = "5G";
                break;
        }
        ImageView imageView3 = this.signalTypeView;
        if (!(imageView3 == null || imageView3.getVisibility() == 0)) {
            this.signalTypeView.setVisibility(0);
        }
        ImageView imageView4 = this.signalStrengthView;
        if (!(imageView4 == null || imageView4.getVisibility() == 0)) {
            this.signalStrengthView.setVisibility(0);
        }
        TextView textView2 = this.signalTypeText;
        if (!(textView2 == null || textView2.getVisibility() == 0)) {
            this.signalTypeText.setVisibility(0);
        }
        Log.d(TAG, "refreshPhoneSignalBg mSignalStrengthLevel = " + this.mSignalStrengthLevel + ", mIntSignalType = " + this.mIntSignalType + ", mStrSignalType = " + this.mStrSignalType);
        ImageView imageView5 = this.signalStrengthView;
        if (imageView5 != null) {
            imageView5.setImageLevel(this.mSignalStrengthLevel);
        }
        ImageView imageView6 = this.signalTypeView;
        if (imageView6 != null) {
            imageView6.setImageLevel(this.mIntSignalType);
        }
        TextView textView3 = this.signalTypeText;
        if (textView3 != null) {
            textView3.setText(this.mStrSignalType);
        }
        refreshMainView("SIGNAL", this.mSignalStrengthLevel, this.mStrSignalType);
    }

    /* access modifiers changed from: private */
    public void refreshModePrimaryModeText(int i) {
        if (this.txtModePrimary != null && this.lastPrimaryMode != i) {
            if (this.lastSecondaryMode == i) {
                this.modeUtil.refreshMode(this.txtModeSecondary, 0);
            }
            this.modeUtil.refreshMode(this.txtModePrimary, i);
            this.lastPrimaryMode = i;
        }
    }

    /* access modifiers changed from: private */
    public void refreshModeSecondaryTxt(int i) {
        TextView textView = this.txtModeSecondary;
        if (textView != null && this.lastSecondaryMode != i) {
            this.modeUtil.refreshMode(textView, i);
            this.lastSecondaryMode = i;
        }
    }

    /* access modifiers changed from: private */
    public void refresFastCharge() {
        int i = 0;
        boolean recordBoolean = this.mSysProviderOpt.getRecordBoolean(SysProviderOpt.KSW_FAST_CHARGE, false);
        ImageView imageView = this.iconCharge;
        if (imageView != null) {
            if (!recordBoolean) {
                i = 8;
            }
            imageView.setVisibility(i);
        }
    }

    public void setImageTintList(ColorStateList colorStateList) {
        TextView textView = this.txtClock;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
        TextView textView2 = this.txtModePrimary;
        if (textView2 != null) {
            textView2.setTextColor(colorStateList);
        }
        if (this.m_iUIIndex == 2) {
            refreshBMWId8BtnImage(colorStateList);
        }
    }

    public void setImageTintList2(ColorStateList colorStateList) {
        int defaultColor = colorStateList.getDefaultColor();
        SbarImageButton sbarImageButton = this.btnHome;
        if (sbarImageButton != null && this.m_iUIIndex == 5) {
            int i = this.m_iModeSet;
            int i2 = R.drawable.landrover_btn_top_home;
            if (i != 25) {
                if (this.landRoverThemeIndex != 0) {
                    i2 = R.drawable.landrover_btn_top_home_white;
                }
                sbarImageButton.setForeground(getDrawable(i2));
            } else {
                sbarImageButton.setForeground(getDrawable(R.drawable.landrover_btn_top_home));
            }
            this.btnHome.setForegroundTintList(getColorStateList(defaultColor, this.mContext.getColor(R.color.landrover_color)));
        }
        SbarImageButton sbarImageButton2 = this.btnExit;
        if (sbarImageButton2 != null && this.m_iUIIndex == 5) {
            int i3 = this.m_iModeSet;
            int i4 = R.drawable.landrover_btn_top_return;
            if (i3 != 25) {
                if (this.landRoverThemeIndex != 0) {
                    i4 = R.drawable.landrover_btn_top_return_white;
                }
                sbarImageButton2.setForeground(getDrawable(i4));
            } else {
                sbarImageButton2.setForeground(getDrawable(R.drawable.landrover_btn_top_return));
            }
            this.btnExit.setForegroundTintList(getColorStateList(defaultColor, this.mContext.getColor(R.color.landrover_color)));
        }
        ImageView imageView = this.iconUSB;
        if (imageView != null) {
            imageView.setImageTintList(colorStateList);
        }
        ImageView imageView2 = this.iconSD;
        if (imageView2 != null) {
            imageView2.setImageTintList(colorStateList);
        }
        ImageView imageView3 = this.signalTypeView;
        if (imageView3 != null) {
            imageView3.setImageTintList(colorStateList);
        }
        ImageView imageView4 = this.signalStrengthView;
        if (imageView4 != null) {
            imageView4.setImageTintList(colorStateList);
        }
        TextView textView = this.signalTypeText;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
        View view = this.imageHD;
        if (view != null && (view instanceof ImageView)) {
            ((ImageView) view).setImageTintList(colorStateList);
        }
        ImageView imageView5 = this.iconCharge;
        if (imageView5 != null) {
            imageView5.setImageTintList(colorStateList);
        }
        SbarImageButton sbarImageButton3 = this.btnScreen;
        if (sbarImageButton3 != null) {
            if (this.m_iUIIndex == 5) {
                int i5 = this.m_iModeSet;
                int i6 = R.drawable.landrover_btn_top_screen;
                if (i5 != 25) {
                    if (this.landRoverThemeIndex != 0) {
                        i6 = R.drawable.landrover_btn_top_screen_white;
                    }
                    sbarImageButton3.setForeground(getDrawable(i6));
                } else {
                    sbarImageButton3.setForeground(getDrawable(R.drawable.landrover_btn_top_screen));
                }
                this.btnScreen.setForegroundTintList(getColorStateList(defaultColor, this.mContext.getColor(R.color.landrover_color)));
            } else {
                sbarImageButton3.setImageTintList(colorStateList);
            }
        }
        ImageView imageView6 = this.wifiLevel;
        if (imageView6 != null) {
            imageView6.setImageTintList(colorStateList);
        }
        ImageView imageView7 = this.chkBT;
        if (imageView7 != null) {
            imageView7.setImageTintList(colorStateList);
        }
        TextView textView2 = this.txtModeSecondary;
        if (textView2 != null) {
            textView2.setTextColor(colorStateList);
        }
        int i7 = this.m_iUIIndex;
        if (i7 == 2) {
            return;
        }
        if (i7 == 5) {
            ColorStateList colorStateList2 = getColorStateList(defaultColor, this.mContext.getColor(R.color.landrover_color));
            SbarImageButton sbarImageButton4 = this.btnTask;
            int i8 = R.drawable.landrover_btn_top_task_white;
            if (sbarImageButton4 != null) {
                if (this.m_iModeSet != 25) {
                    sbarImageButton4.setForeground(getDrawable(this.landRoverThemeIndex == 0 ? R.drawable.landrover_btn_top_task : R.drawable.landrover_btn_top_task_white));
                } else {
                    sbarImageButton4.setForeground(getDrawable(R.drawable.landrover_btn_top_task));
                }
                this.btnTask.setForegroundTintList(colorStateList2);
            }
            SbarImageButton sbarImageButton5 = this.btnTask2;
            if (sbarImageButton5 != null) {
                if (this.landRoverThemeIndex == 0) {
                    i8 = R.drawable.landrover_btn_top_task;
                }
                sbarImageButton5.setForeground(getDrawable(i8));
                this.btnTask2.setForegroundTintList(colorStateList2);
                return;
            }
            return;
        }
        ColorStateList colorStateList3 = getColorStateList(defaultColor, defaultColor);
        SbarImageButton sbarImageButton6 = this.btnTask;
        if (sbarImageButton6 != null) {
            sbarImageButton6.setImageDrawable(getDrawable(R.drawable.kesaiwei_1920x720_benz_btn_task));
            this.btnTask.setImageTintList(colorStateList3);
        }
        SbarImageButton sbarImageButton7 = this.btnTask2;
        if (sbarImageButton7 != null) {
            sbarImageButton7.setImageDrawable(getDrawable(R.drawable.kesaiwei_1920x720_benz_btn_task));
            this.btnTask2.setImageTintList(colorStateList3);
        }
    }

    /* access modifiers changed from: private */
    public void refreshSdUsbStorageState(String str) {
        checkUsbDebug(str);
        checkStoragePath();
    }

    private void checkStoragePath() {
        DiskInfo disk;
        int i = 0;
        this.mIsSdFound = false;
        this.mIsUsbFound = false;
        List<VolumeInfo> volumes = ((StorageManager) this.mContext.getSystemService(StorageManager.class)).getVolumes();
        Collections.sort(volumes, VolumeInfo.getDescriptionComparator());
        for (VolumeInfo volumeInfo : volumes) {
            if (volumeInfo.getType() == 0 && volumeInfo.isMountedReadable() && (disk = volumeInfo.getDisk()) != null) {
                if (disk.isSd()) {
                    this.mIsSdFound = true;
                } else if (disk.isUsb()) {
                    this.mIsUsbFound = true;
                }
            }
        }
        ImageView imageView = this.iconUSB;
        if (imageView != null) {
            imageView.setVisibility(this.mIsUsbFound ? 0 : 8);
        }
        ImageView imageView2 = this.iconSD;
        if (imageView2 != null) {
            if (!this.mIsSdFound) {
                i = 8;
            }
            imageView2.setVisibility(i);
        }
    }

    private void checkUsbDebug(String str) {
        if (!new File(str + "/usbDebug.txt").exists()) {
            return;
        }
        if (Build.VERSION.SDK_INT == 31) {
            SystemProperties.set("sys.usb_debug", "1");
        } else {
            openAdb(true);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0039 A[SYNTHETIC, Splitter:B:25:0x0039] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0044 A[SYNTHETIC, Splitter:B:30:0x0044] */
    /* JADX WARNING: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void openAdb(boolean r3) {
        /*
            r2 = this;
            if (r3 == 0) goto L_0x0005
            java.lang.String r2 = "peripheral"
            goto L_0x0007
        L_0x0005:
            java.lang.String r2 = "host"
        L_0x0007:
            java.io.File r3 = new java.io.File
            java.lang.String r0 = "/sys/devices/platform/soc/4e00000.ssusb/mode"
            r3.<init>(r0)
            boolean r0 = r3.exists()
            if (r0 != 0) goto L_0x0015
            return
        L_0x0015:
            r0 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x0033 }
            r1.<init>(r3)     // Catch:{ FileNotFoundException -> 0x0033 }
            byte[] r2 = r2.getBytes()     // Catch:{ IOException -> 0x0029 }
            r1.write(r2)     // Catch:{ IOException -> 0x0029 }
            goto L_0x002d
        L_0x0023:
            r2 = move-exception
            r0 = r1
            goto L_0x0042
        L_0x0026:
            r2 = move-exception
            r0 = r1
            goto L_0x0034
        L_0x0029:
            r2 = move-exception
            r2.printStackTrace()     // Catch:{ FileNotFoundException -> 0x0026, all -> 0x0023 }
        L_0x002d:
            r1.close()     // Catch:{ IOException -> 0x003d }
            goto L_0x0041
        L_0x0031:
            r2 = move-exception
            goto L_0x0042
        L_0x0033:
            r2 = move-exception
        L_0x0034:
            r2.printStackTrace()     // Catch:{ all -> 0x0031 }
            if (r0 == 0) goto L_0x0041
            r0.close()     // Catch:{ IOException -> 0x003d }
            goto L_0x0041
        L_0x003d:
            r2 = move-exception
            r2.printStackTrace()
        L_0x0041:
            return
        L_0x0042:
            if (r0 == 0) goto L_0x004c
            r0.close()     // Catch:{ IOException -> 0x0048 }
            goto L_0x004c
        L_0x0048:
            r3 = move-exception
            r3.printStackTrace()
        L_0x004c:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.customerui.views.StatusBarView.openAdb(boolean):void");
    }

    private void registerReceiver() {
        if (this.myBroadcast == null) {
            this.myBroadcast = new MyBroadcast();
        }
        if (this.mContext != null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
            intentFilter.addAction("android.net.wifi.RSSI_CHANGED");
            intentFilter.addAction("android.net.wifi.STATE_CHANGE");
            intentFilter.addAction("com.choiceway.eventcenter.EventUtils.KSW_ZXW_BT_CONNECED_SHOW_VIEW");
            intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
            intentFilter.addAction("com.szchoiceway.ACTION_LAUNCHER_KEY_CTRL");
            intentFilter.addAction("com.szchoiceway.action.mode_title_change");
            intentFilter.addAction("KESAIWEI_DISPLAY_MODE_CHANGED_ACTION");
            intentFilter.addAction("ZXW_ACTION_CHANGE_BRIGHTNESS_SYSTEM");
            intentFilter.addAction(EventUtils.ZXW_ACTION_NOTIFICATION_CLICK_MUTE);
            intentFilter.addAction(EventUtils.ZXW_ACTION_BT_PHONE_CALL_INTERFACE);
            intentFilter.addAction(EventUtils.ZXW_ACTION_KSW_UPDRADE_FAST_CHARGING_STATUS);
            intentFilter.addAction("android.intent.action.SIM_STATE_CHANGED");
            intentFilter.addAction(EventUtils.ZXW_ACTION_MAIN_TOP_ON_CLICK);
            intentFilter.addAction(EventUtils.ZXW_ACTION_MAIN_TOP_REFRESH_VIEW_REQUEST);
            this.mContext.registerReceiver(this.myBroadcast, intentFilter);
            IntentFilter intentFilter2 = new IntentFilter();
            intentFilter2.addAction("android.intent.action.MEDIA_MOUNTED");
            intentFilter2.addAction("android.intent.action.MEDIA_UNMOUNTED");
            intentFilter2.addDataScheme("file");
            this.mContext.registerReceiver(this.myBroadcast, intentFilter2);
        }
    }

    class MyBroadcast extends BroadcastReceiver {
        MyBroadcast() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.net.wifi.WIFI_STATE_CHANGED".equals(action) || "android.net.wifi.RSSI_CHANGED".equals(action) || "android.net.wifi.STATE_CHANGE".equals(action)) {
                StatusBarView.this.refreshWifiSignal();
            } else if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(action)) {
                intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 0);
                StatusBarView.this.refreshBTStatus(0);
                Intent intent2 = new Intent("com.choiceway.eventcenter.EventUtils.KSW_ZXW_BT_CONNECED_SHOW_VIEW");
                intent2.putExtra("com.choiceway.eventcenter.EventUtils.KSW_ZXW_BT_CONNECED_SHOW_VIEW_DATA", false);
                StatusBarView.this.mContext.sendBroadcast(intent2);
            } else {
                int i = 2;
                if ("com.choiceway.eventcenter.EventUtils.KSW_ZXW_BT_CONNECED_SHOW_VIEW".equals(action)) {
                    boolean booleanExtra = intent.getBooleanExtra("com.choiceway.eventcenter.EventUtils.KSW_ZXW_BT_CONNECED_SHOW_VIEW_DATA", false);
                    Log.d(StatusBarView.TAG, "onReceive KSW_ZXW_BT_CONNECED_SHOW_VIEW show = " + booleanExtra);
                    if (booleanExtra) {
                        StatusBarView.this.refreshBTStatus(1);
                    } else {
                        StatusBarView.this.refreshBTStatus(2);
                    }
                } else if ("android.intent.action.MEDIA_MOUNTED".equals(action)) {
                    StatusBarView.this.refreshSdUsbStorageState(intent.getData().getPath());
                } else if ("android.intent.action.MEDIA_UNMOUNTED".equals(action)) {
                    StatusBarView.this.refreshSdUsbStorageState(intent.getData().getPath());
                } else if ("com.szchoiceway.ACTION_LAUNCHER_KEY_CTRL".equals(action)) {
                    String stringExtra = intent.getStringExtra(StatusBarView.EXTRA_LAUNCHER_KEY_WORD);
                    if ("BlackScreen".equals(stringExtra)) {
                        StatusBarView.this.sendScreenBroadcast();
                    } else if (!"DIM".equals(stringExtra) && !"Power".equals(stringExtra)) {
                        "TaskList".equals(stringExtra);
                    }
                } else if ("com.szchoiceway.action.mode_title_change".equals(action)) {
                    int intExtra = intent.getIntExtra("com.szchoiceway.action.mode_title_change_extra", 99);
                    if (intent.getBooleanExtra("com.szchoiceway.action.mode_title_change_screen_extra", false)) {
                        StatusBarView.this.refreshModePrimaryModeText(intExtra);
                    } else {
                        StatusBarView.this.refreshModeSecondaryTxt(intExtra);
                    }
                } else if ("KESAIWEI_DISPLAY_MODE_CHANGED_ACTION".equals(action)) {
                    StatusBarView.this.refreshBMWId8BtnImage((ColorStateList) null);
                } else if ("ZXW_ACTION_CHANGE_BRIGHTNESS_SYSTEM".equals(action)) {
                    int intExtra2 = intent.getIntExtra("ZXW_ACTION_CHANGE_BRIGHTNESS_EXTRA", 50);
                    Intent intent3 = new Intent("com.szchoiceway.action.brightness_change");
                    intent3.putExtra("com.szchoiceway.action.brightness_change_extra", intExtra2);
                    StatusBarView.this.mContext.sendBroadcast(intent3);
                    if (StatusBarView.this.mEvtService != null) {
                        try {
                            StatusBarView.this.mEvtService.SendBLVal((byte) intExtra2, (byte) 0);
                            StatusBarView.this.mEvtService.putSettingInt("COM.SZCHOICEWAY_BRIGHTNESS_SETTINGS", intExtra2);
                            StatusBarView.this.mEvtService.appySetting();
                            StatusBarView.this.mEvtService.commitSetting();
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (EventUtils.ZXW_ACTION_NOTIFICATION_CLICK_MUTE.equals(action)) {
                    if (StatusBarView.this.m_iModeSet != 39) {
                        if (!(StatusBarView.this.m_iUIIndex == 2 || StatusBarView.this.m_iUIIndex == 5 || StatusBarView.this.m_iUIIndex == 4 || StatusBarView.this.m_iUIIndex == 6 || StatusBarView.this.m_iUIIndex == 7)) {
                            i = 3;
                        }
                        Bundle bundle = new Bundle();
                        bundle.putInt("GotoPageNum", i);
                        intent.putExtras(bundle);
                        intent.setComponent(new ComponentName("com.szchoiceway.settings", "com.szchoiceway.settings.MainActivity"));
                        intent.setPackage("com.szchoiceway.settings");
                        intent.setFlags(270532608);
                        StatusBarView.this.mContext.startActivity(intent);
                    }
                } else if (EventUtils.ZXW_ACTION_BT_PHONE_CALL_INTERFACE.equals(action)) {
                    int intExtra3 = intent.getIntExtra(EventUtils.ZXW_ACTION_BT_PHONE_CALL_INTERFACE_EXTRA, 0);
                    if (intExtra3 == 1) {
                        if (StatusBarView.this.txtModePrimary != null) {
                            StatusBarView statusBarView = StatusBarView.this;
                            String unused = statusBarView.valuePrimary = statusBarView.txtModePrimary.getText().toString();
                        }
                        if (StatusBarView.this.txtModeSecondary != null) {
                            StatusBarView statusBarView2 = StatusBarView.this;
                            String unused2 = statusBarView2.valueSecondary = statusBarView2.txtModeSecondary.getText().toString();
                        }
                        if (StatusBarView.this.txtModePrimary != null) {
                            StatusBarView.this.txtModePrimary.setText(StatusBarView.this.mContext.getText(R.string.lb_bt_phone));
                        }
                        if (StatusBarView.this.txtModeSecondary != null) {
                            StatusBarView.this.txtModeSecondary.setText("");
                        }
                    }
                    if (intExtra3 == 0) {
                        if (StatusBarView.this.txtModePrimary != null) {
                            StatusBarView.this.txtModePrimary.setText(StatusBarView.this.valuePrimary);
                        }
                        if (StatusBarView.this.txtModeSecondary != null) {
                            StatusBarView.this.txtModeSecondary.setText(StatusBarView.this.valueSecondary);
                        }
                        String unused3 = StatusBarView.this.valuePrimary = "";
                        String unused4 = StatusBarView.this.valueSecondary = "";
                    }
                } else if (EventUtils.ZXW_ACTION_KSW_UPDRADE_FAST_CHARGING_STATUS.equals(action)) {
                    StatusBarView.this.refresFastCharge();
                } else if ("android.intent.action.SIM_STATE_CHANGED".equals(action)) {
                    StatusBarView.this.refreshPhoneSignalBg();
                } else if (EventUtils.ZXW_ACTION_MAIN_TOP_ON_CLICK.equals(action)) {
                    String stringExtra2 = intent.getStringExtra(EventUtils.ZXW_ACTION_MAIN_TOP_ON_CLICK_KEY);
                    int intExtra4 = intent.getIntExtra(EventUtils.ZXW_ACTION_MAIN_TOP_ON_CLICK_DATA, -1);
                    stringExtra2.hashCode();
                    if (stringExtra2.equals("SCREEN")) {
                        StatusBarView.this.sendScreenBroadcast();
                    } else if (stringExtra2.equals("TASK")) {
                        if (intExtra4 == 1) {
                            if (StatusBarView.this.btnTask != null) {
                                StatusBarView.this.btnTask.performLongClick();
                            }
                        } else if (intExtra4 == 0 && StatusBarView.this.btnTask != null) {
                            StatusBarView.this.btnTask.performClick();
                        }
                    }
                } else if (EventUtils.ZXW_ACTION_MAIN_TOP_REFRESH_VIEW_REQUEST.equals(action)) {
                    String stringExtra3 = intent.getStringExtra(EventUtils.ZXW_ACTION_MAIN_TOP_REFRESH_VIEW_REQUEST_KEY);
                    stringExtra3.hashCode();
                    if (stringExtra3.equals("SIGNAL")) {
                        StatusBarView statusBarView3 = StatusBarView.this;
                        statusBarView3.refreshMainView(stringExtra3, statusBarView3.mSignalStrengthLevel, StatusBarView.this.mStrSignalType);
                    } else if (stringExtra3.equals("WIFI")) {
                        StatusBarView statusBarView4 = StatusBarView.this;
                        statusBarView4.refreshMainView(stringExtra3, statusBarView4.mWifiLevel, "");
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x006b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void refreshBMWId8BtnImage(android.content.res.ColorStateList r10) {
        /*
            r9 = this;
            if (r10 == 0) goto L_0x0004
            r9.mTint = r10
        L_0x0004:
            int r10 = r9.m_iUIIndex
            r0 = 1
            r1 = 0
            java.lang.String r2 = "KESAIWEI_SYS_DISPLAY_MODE"
            r3 = 2
            if (r10 != r3) goto L_0x0072
            com.szchoiceway.customerui.utils.SysProviderOpt r10 = r9.mSysProviderOpt
            int r10 = r10.getRecordInteger(r2, r1)
            r4 = 1879573430(0x700803b6, float:1.6837779E29)
            r5 = 1879573427(0x700803b3, float:1.6837773E29)
            r6 = 1879573436(0x700803bc, float:1.683779E29)
            r7 = 1879573433(0x700803b9, float:1.6837785E29)
            r8 = 1879573434(0x700803ba, float:1.6837787E29)
            if (r10 == r0) goto L_0x0031
            if (r10 == r3) goto L_0x0027
            goto L_0x003b
        L_0x0027:
            r4 = 1879573432(0x700803b8, float:1.6837783E29)
            r5 = 1879573429(0x700803b5, float:1.6837777E29)
            r6 = 1879573438(0x700803be, float:1.6837794E29)
            goto L_0x003a
        L_0x0031:
            r4 = 1879573431(0x700803b7, float:1.683778E29)
            r5 = 1879573428(0x700803b4, float:1.6837775E29)
            r6 = 1879573437(0x700803bd, float:1.6837792E29)
        L_0x003a:
            r7 = r8
        L_0x003b:
            com.szchoiceway.customerui.views.SbarImageButton r10 = r9.btnHome
            if (r10 == 0) goto L_0x0046
            android.graphics.drawable.Drawable r4 = r9.getDrawable(r4)
            r10.setForeground(r4)
        L_0x0046:
            com.szchoiceway.customerui.views.SbarImageButton r10 = r9.btnScreen
            if (r10 == 0) goto L_0x0051
            android.graphics.drawable.Drawable r4 = r9.getDrawable(r7)
            r10.setForeground(r4)
        L_0x0051:
            com.szchoiceway.customerui.views.SbarImageButton r10 = r9.btnExit
            if (r10 == 0) goto L_0x005c
            android.graphics.drawable.Drawable r4 = r9.getDrawable(r5)
            r10.setForeground(r4)
        L_0x005c:
            com.szchoiceway.customerui.views.SbarImageButton r10 = r9.btnTask
            if (r10 == 0) goto L_0x0067
            android.graphics.drawable.Drawable r4 = r9.getDrawable(r6)
            r10.setForeground(r4)
        L_0x0067:
            com.szchoiceway.customerui.views.SbarImageButton r10 = r9.btnTask2
            if (r10 == 0) goto L_0x0072
            android.graphics.drawable.Drawable r4 = r9.getDrawable(r6)
            r10.setForeground(r4)
        L_0x0072:
            int r10 = r9.m_iUIIndex
            if (r10 != r3) goto L_0x00d5
            android.content.Context r10 = r9.mContext
            if (r10 == 0) goto L_0x00d5
            android.content.res.ColorStateList r10 = r9.mTint
            if (r10 == 0) goto L_0x00d5
            int r10 = r10.getDefaultColor()
            r4 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            com.szchoiceway.customerui.utils.SysProviderOpt r5 = r9.mSysProviderOpt
            int r1 = r5.getRecordInteger(r2, r1)
            if (r1 == 0) goto L_0x00a5
            if (r1 == r0) goto L_0x009b
            if (r1 == r3) goto L_0x0091
            goto L_0x00ae
        L_0x0091:
            android.content.Context r0 = r9.mContext
            r1 = 1879441444(0x70060024, float:1.6588465E29)
            int r4 = r0.getColor(r1)
            goto L_0x00ae
        L_0x009b:
            android.content.Context r0 = r9.mContext
            r1 = 1879441445(0x70060025, float:1.6588466E29)
            int r4 = r0.getColor(r1)
            goto L_0x00ae
        L_0x00a5:
            android.content.Context r0 = r9.mContext
            r1 = 1879441443(0x70060023, float:1.6588463E29)
            int r4 = r0.getColor(r1)
        L_0x00ae:
            android.content.res.ColorStateList r10 = r9.getColorStateList(r10, r4)
            com.szchoiceway.customerui.views.SbarImageButton r0 = r9.btnHome
            if (r0 == 0) goto L_0x00b9
            r0.setForegroundTintList(r10)
        L_0x00b9:
            com.szchoiceway.customerui.views.SbarImageButton r0 = r9.btnTask
            if (r0 == 0) goto L_0x00c0
            r0.setForegroundTintList(r10)
        L_0x00c0:
            com.szchoiceway.customerui.views.SbarImageButton r0 = r9.btnTask2
            if (r0 == 0) goto L_0x00c7
            r0.setForegroundTintList(r10)
        L_0x00c7:
            com.szchoiceway.customerui.views.SbarImageButton r0 = r9.btnExit
            if (r0 == 0) goto L_0x00ce
            r0.setForegroundTintList(r10)
        L_0x00ce:
            com.szchoiceway.customerui.views.SbarImageButton r9 = r9.btnScreen
            if (r9 == 0) goto L_0x00d5
            r9.setForegroundTintList(r10)
        L_0x00d5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.customerui.views.StatusBarView.refreshBMWId8BtnImage(android.content.res.ColorStateList):void");
    }

    private ColorStateList getColorStateList(int i, int i2) {
        return new ColorStateList(new int[][]{new int[]{-16842919}, new int[]{16842919}}, new int[]{i, i2});
    }

    private Drawable getDrawable(int i) {
        Context context = this.mContext;
        if (context != null) {
            return context.getDrawable(i);
        }
        return null;
    }

    public int getSystemBrightness(ContentResolver contentResolver) {
        try {
            return Settings.System.getInt(contentResolver, "screen_brightness");
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    class MobilePhoneStateListener extends PhoneStateListener {
        public void onDataActivity(int i) {
        }

        public void onServiceStateChanged(ServiceState serviceState) {
        }

        MobilePhoneStateListener() {
        }

        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            int i;
            if (!signalStrength.isGsm()) {
                i = signalStrength.getCdmaLevel();
            } else {
                i = signalStrength.getLevel();
            }
            if (StatusBarView.this.signalStrengthView != null) {
                int i2 = i * 2;
                if (i2 > 5) {
                    i2 = 5;
                }
                int unused = StatusBarView.this.mSignalStrengthLevel = i2;
                StatusBarView.this.signalStrengthView.setImageLevel(StatusBarView.this.mSignalStrengthLevel);
                StatusBarView statusBarView = StatusBarView.this;
                statusBarView.refreshMainView("SIGNAL", statusBarView.mSignalStrengthLevel, StatusBarView.this.mStrSignalType);
            }
        }

        public void onDataConnectionStateChanged(int i, int i2) {
            StatusBarView.this.refreshPhoneSignalBg();
        }

        public void onCarrierNetworkChange(boolean z) {
            Log.i(StatusBarView.TAG, "onCarrierNetworkChange: ");
        }
    }

    public boolean hasSimCard() {
        return ((TelephonyManager) this.mContext.getSystemService("phone")).getSimState() == 5;
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Log.d(TAG, "onConfigurationChanged");
        this.mContext.sendBroadcast(new Intent(EventUtils.ZXW_ACTION_REQUEST_BT_STATUS));
    }

    public int getStatusBarHeight() {
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    private void onLongClickTask() {
        SbarImageButton sbarImageButton = this.btnTask;
        if (sbarImageButton != null) {
            sbarImageButton.performClick();
            this.mHandler.postDelayed(new Runnable() {
                public final void run() {
                    StatusBarView.this.lambda$onLongClickTask$2$StatusBarView();
                }
            }, 1000);
        }
    }

    public /* synthetic */ void lambda$onLongClickTask$2$StatusBarView() {
        this.mContext.sendBroadcast(new Intent("ZXW_ACTION_LONGCLICK_ENTER_SPLIT_SCREEN"));
    }

    /* access modifiers changed from: private */
    public void sendScreenBroadcast() {
        Intent intent = new Intent("com.android.quicksetting.BROADCAST");
        intent.putExtra(NotificationCompat.CATEGORY_MESSAGE, "backlight");
        this.mContext.sendBroadcast(intent);
    }

    /* access modifiers changed from: private */
    public void refreshMainView(String str, int i, String str2) {
        Intent intent = new Intent(EventUtils.ZXW_ACTION_MAIN_TOP_REFRESH_VIEW);
        intent.putExtra(EventUtils.ZXW_ACTION_MAIN_TOP_REFRESH_VIEW_KEY, str);
        intent.putExtra(EventUtils.ZXW_ACTION_MAIN_TOP_REFRESH_VIEW_DATA, i);
        if ("SIGNAL".equals(str)) {
            intent.putExtra(EventUtils.ZXW_ACTION_MAIN_TOP_REFRESH_VIEW_DATA2, str2);
        }
        this.mContext.sendBroadcast(intent);
    }
}
