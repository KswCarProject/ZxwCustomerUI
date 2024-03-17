package com.szchoiceway.customerui.bmw.services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.os.IBinder;
import android.util.Log;
import android.view.Surface;
import com.szchoiceway.customerui.BuildConfig;
import com.szchoiceway.zxwlib.GyroScopeWithCompassView;
import com.szchoiceway.zxwlib.utils.EventUtils;

public class VirtualDisplayService extends Service {
    private static final String BUNDLE_VALUE = "BundleValue";
    private static final String SEND_SURFACE = "sendSurface";
    private static final String SURFACE = "Surface";
    private static final String SURFACE_SIZE = "surface_size";
    public boolean activityHasCreate = false;
    /* access modifiers changed from: private */
    public VirtualDisplay mDisplay;
    private SurfaceReceiver mSurfaceReceiver = new SurfaceReceiver();

    public int onStartCommand(Intent intent, int i, int i2) {
        return 1;
    }

    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void onCreate() {
        super.onCreate();
        this.mSurfaceReceiver.registerRec();
        Log.i("TAG", "onCreate: onSurfaceTextureAvailable");
    }

    public void onDestroy() {
        super.onDestroy();
        this.mSurfaceReceiver.unregisterRec();
    }

    public class SurfaceReceiver extends BroadcastReceiver {
        private boolean isReg = false;

        public SurfaceReceiver() {
        }

        public void registerRec() {
            if (!this.isReg) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(VirtualDisplayService.SEND_SURFACE);
                VirtualDisplayService.this.registerReceiver(this, intentFilter);
                this.isReg = true;
            }
        }

        public void unregisterRec() {
            if (this.isReg) {
                VirtualDisplayService.this.unregisterReceiver(this);
                this.isReg = false;
            }
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(VirtualDisplayService.SEND_SURFACE)) {
                Surface surface = (Surface) intent.getBundleExtra(VirtualDisplayService.BUNDLE_VALUE).getParcelable(VirtualDisplayService.SURFACE);
                int[] intArrayExtra = intent.getIntArrayExtra(VirtualDisplayService.SURFACE_SIZE);
                boolean z = false;
                int i = intArrayExtra[0];
                int i2 = intArrayExtra[1];
                StringBuilder append = new StringBuilder().append("onReceive:wegwggg ");
                if (surface == null) {
                    z = true;
                }
                Log.i("TAG", append.append(z).toString());
                if (surface != null) {
                    DisplayManager displayManager = (DisplayManager) VirtualDisplayService.this.getSystemService("display");
                    if (VirtualDisplayService.this.mDisplay == null) {
                        VirtualDisplay unused = VirtualDisplayService.this.mDisplay = displayManager.createVirtualDisplay("videoDisplay", i, i2, GyroScopeWithCompassView.CARTYPE_Nazhijie_U3_LO, surface, 0);
                    } else {
                        VirtualDisplayService.this.mDisplay.resize(i, i2, GyroScopeWithCompassView.CARTYPE_Nazhijie_U3_LO);
                        VirtualDisplayService.this.mDisplay.setSurface(surface);
                    }
                    int displayId = VirtualDisplayService.this.mDisplay.getDisplay().getDisplayId();
                    Log.i("TAG", "onSurfaceTextureAvailable:displayId   " + displayId);
                    EventUtils.startActivityIfNotRuning(VirtualDisplayService.this, BuildConfig.APPLICATION_ID, "com.szchoiceway.customerui.bmw.ui.VideoActivity", displayId);
                } else if (VirtualDisplayService.this.mDisplay != null) {
                    VirtualDisplayService.this.mDisplay.setSurface((Surface) null);
                }
            }
        }
    }
}
